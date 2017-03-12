package com.magic.rain.cn.firstcode.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 2017/3/10.
 * @author magicRain
 */

public class FileEdit {

    public static void save(Context context) {
        String data = "Data to save";
        FileOutputStream fileOutputStream;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = context.openFileOutput("data", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(data);
        } catch (IOException e) {
            Log.e(context.getClass().getSimpleName(), "save, bufferedWrite write exception: " + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                Log.e(context.getClass().getSimpleName(), "save, bufferedWrite close exception: " + e.getMessage());
            }
        }
    }

    public String load(Context context) {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader = null;
        StringBuilder content = new StringBuilder();
        try {
            fileInputStream = context.openFileInput("data");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            Log.e(context.getClass().getSimpleName(), "load, bufferedReader read exception: " + e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(context.getClass().getSimpleName(), "load, bufferedReader close exception: " + e.getMessage());
                }
            }
        }
        return content.toString();
    }
}
