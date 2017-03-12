package com.magic.rain.cn.firstcode.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.magic.rain.cn.firstcode.activity.broadcast.LoginActivity;
import com.magic.rain.cn.firstcode.utils.ActivityCollector;

/**
 * Created by Administrator on 2017/3/10.
 * @author magicRain
 */

public class BaseActivity extends AppCompatActivity {
    
    private ForceOfflineReceiver offlineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.magic.rain.firstcode.Force_OFFLINE");
        offlineReceiver = new ForceOfflineReceiver();
        registerReceiver(offlineReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (offlineReceiver != null) {
            unregisterReceiver(offlineReceiver);
            offlineReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
    
    class ForceOfflineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder dialogBuild = new AlertDialog.Builder(context);
            dialogBuild.setTitle("Warning");
            dialogBuild.setMessage("You are forced to be offline. Please try to login again.");
            dialogBuild.setCancelable(false);
            dialogBuild.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            dialogBuild.show();
        }
    }
}
