package cn.magic.rain.com.firstcode.activity.singletask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.magic.rain.com.firstcode.R;

public class SingleTwoActivity extends AppCompatActivity {

    private static final String TAG = "SingleTwoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_two);
        Log.e(TAG, "onCreate: " + getTaskId());
        Button button1 = (Button) findViewById(R.id.button_activity_single_two_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTwoActivity.this, SingleThreeActivity.class);
                startActivity(intent);
            }
        });
    }
}
