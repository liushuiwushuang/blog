package com.magic.rain.cn.firstcode.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.magic.rain.cn.firstcode.R;
import com.magic.rain.cn.firstcode.entity.Book;

import java.lang.ref.WeakReference;

public class HelloWorldActivity extends AppCompatActivity {

    private static final String TAG = "HelloWorldActivity";

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//
//        }
//    };

    static class MyHandler extends Handler {

        WeakReference<HelloWorldActivity> mActivity;

        MyHandler(HelloWorldActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HelloWorldActivity theActivity = mActivity.get();
            switch (msg.what) {
                case 0:
                    // 控制主线程 UI
                    Log.e(TAG, "handleMessage: .." + theActivity);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "Task id is " + getTaskId());
        setContentView(R.layout.activity_hello_world);

        final Handler handler = new MyHandler(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj = new Book();
                handler.sendMessage(message);
            }
        }).start();

        Book book = getIntent().getParcelableExtra("book_data");
        Toast.makeText(this, book.toString(), Toast.LENGTH_LONG).show();
//        TextView textView = (TextView) findViewById(R.id.textview_hello_world);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HelloWorldActivity.this, ThirdActivity.class);
//                startActivity(intent);
//            }
//        });
//        Intent intent = new Intent();
//        intent.setClassName("包名", "全类名");
//        startActivity(intent);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(), 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long triggerAtTime = SystemClock.elapsedRealtime() + 10 * 1000;
        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
