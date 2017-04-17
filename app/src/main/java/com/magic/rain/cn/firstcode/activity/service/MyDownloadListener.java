package com.magic.rain.cn.firstcode.activity.service;

/**
 * Created by Administrator on 2017/3/18.
 * @author magicRain
 */

public interface MyDownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
