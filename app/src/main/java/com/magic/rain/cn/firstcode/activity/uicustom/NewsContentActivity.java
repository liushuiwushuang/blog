package com.magic.rain.cn.firstcode.activity.uicustom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.magic.rain.cn.firstcode.R;
import com.magic.rain.cn.firstcode.custom.NewContentFragment;

public class NewsContentActivity extends AppCompatActivity {

    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");
        NewContentFragment newContentFragment = (NewContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newContentFragment.refresh(newsTitle, newsContent);
    }

}
