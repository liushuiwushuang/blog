package com.magic.rain.cn.firstcode.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.magic.rain.cn.firstcode.R;

public class NewActivity extends AppCompatActivity {

    private static final String TAG = "NewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Log.e(TAG, "onCreate: ++++++++++++++++++++++++++++");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: *****************************");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ##############################");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: 000000000000000000000000000000000");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ------------------------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ////////////////////////////");
    }

}
