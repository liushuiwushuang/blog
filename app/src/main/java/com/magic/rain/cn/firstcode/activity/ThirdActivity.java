package com.magic.rain.cn.firstcode.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.magic.rain.cn.firstcode.R;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "Task id is " + getTaskId());
        setContentView(R.layout.activity_third);
    }
}
