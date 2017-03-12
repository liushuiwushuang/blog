package com.magic.rain.cn.firstcode.activity.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.magic.rain.cn.firstcode.R;
import com.magic.rain.cn.firstcode.activity.BaseActivity;

public class BroadCastPerfectActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_perfect);

        Button offlineButton = (Button) findViewById(R.id.force_offline);
        offlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.magic.rain.firstcode.Force_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}
