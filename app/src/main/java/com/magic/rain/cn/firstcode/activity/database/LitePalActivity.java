package com.magic.rain.cn.firstcode.activity.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.magic.rain.cn.firstcode.R;

import org.litepal.LitePal;

public class LitePalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);
        findViewById(R.id.button_create_database).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });
    }
}
