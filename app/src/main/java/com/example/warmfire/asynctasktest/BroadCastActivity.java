package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by warmfire_2 on 2016/7/29.
 */
public class BroadCastActivity extends Activity {

    Button bc_send, bc_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcst);

        init();

        bc_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.warmfire.asynctasktest.MyBroadCast");
                sendBroadcast(intent);
            }
        });
        bc_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init(){
        bc_send = (Button) findViewById(R.id.bc_send);
        bc_back = (Button) findViewById(R.id.bc_back);
    }
}
