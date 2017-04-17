package com.magic.rain.cn.firstcode.activity.service;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/18.
 * @author magicRain
 */

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private static final String TAG = "DownloadTask";

    public static final int TYPE_SUCCESS = 0;

    public static final int TYPE_FAILED = 1;

    public static final int TYPE_PAUSED = 2;

    public static final int TYPE_CANCELED = 3;

    private MyDownloadListener myDownloadListener;

    private boolean isCanceled = false;

    private boolean isPaused = false;

    private int lastProgress;

    public DownloadTask(MyDownloadListener myDownloadListener) {
        this.myDownloadListener = myDownloadListener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        File file = null;
        try {
            long downloadedLength = 0;
            String downloadUrl = params[0];
            String filename = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + filename);
            if (file.exists()) {
                downloadedLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadedLength) {
                // 已下载字节和文件字节相等， 说明已经下载完成了
                return TYPE_SUCCESS;
            }
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "byte=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response != null) {
                inputStream = response.body().byteStream();
                randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(downloadedLength);
                byte[] bytes = new byte[1024];
                int total = 0;
                int len;
                while ((len = inputStream.read(bytes)) != -1) {
                    if (isCanceled) {
                        return  TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        randomAccessFile.write(bytes, 0, len);
                        int progress = (int) ((total + downloadedLength) * 100 / contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (Exception e) {
            Log.e(TAG, "doInBackground: ", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                Log.e(TAG, "doInBackground: ", e);
            }
        }

        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            myDownloadListener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        switch (status) {
            case TYPE_SUCCESS:
                myDownloadListener.onSuccess();
                break;
            case TYPE_FAILED:
                myDownloadListener.onFailed();
                break;
            case TYPE_PAUSED:
                myDownloadListener.onPaused();
                break;
            case TYPE_CANCELED:
                myDownloadListener.onCanceled();
                break;
            default:
                break;
        }
    }

    private long getContentLength(String downloadUrl) throws IOException{
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.body().close();
            return  contentLength;
        }
        return 0;
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCanceled = true;
    }
}
