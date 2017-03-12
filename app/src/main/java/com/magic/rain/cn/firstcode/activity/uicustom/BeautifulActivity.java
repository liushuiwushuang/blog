package com.magic.rain.cn.firstcode.activity.uicustom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.magic.rain.cn.firstcode.R;
import com.magic.rain.cn.firstcode.custom.MsgAdapter;
import com.magic.rain.cn.firstcode.entity.Msg;

import java.util.ArrayList;
import java.util.List;

public class BeautifulActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();

    private EditText inputTextEditText;

    private RecyclerView recyclerView;

    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautiful);

        initMsgs();
        inputTextEditText = (EditText) findViewById(R.id.input_text);
        Button sendButton = (Button) findViewById(R.id.send);
        recyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MsgAdapter(msgList);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputTextEditText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);

                    // 刷新 RecyclerView 中的显示
                    recyclerView.scrollToPosition(msgList.size() - 1); // 将 RecyclerView定位到最后一行
                    inputTextEditText.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        Msg msg3 = new Msg("This is Tom. Nice taking to you.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        msgList.add(msg2);
        msgList.add(msg3);
    }
}
