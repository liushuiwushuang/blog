package cn.magic.rain.com.firstcode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.magic.rain.com.firstcode.R;

public class HelloWorldActivity extends AppCompatActivity {

    private static final String TAG = "HelloWorldActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "Task id is " + getTaskId());
        setContentView(R.layout.activity_hello_world);

//        TextView textView = (TextView) findViewById(R.id.textview_hello_world);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HelloWorldActivity.this, ThirdActivity.class);
//                startActivity(intent);
//            }
//        });
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
}
