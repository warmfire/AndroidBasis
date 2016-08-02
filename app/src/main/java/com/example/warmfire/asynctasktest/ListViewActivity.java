package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by warmfire_2 on 2016/8/1.
 */
public class ListViewActivity extends Activity {

    Button lv_before, lv_after, lv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        init();

        lv_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(ListViewActivity.this, ListView1Activity.class), 0);
            }
        });
        lv_after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(ListViewActivity.this, ListView2Activity.class), 0);
            }
        });
        lv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init(){
        lv_before = (Button) findViewById(R.id.lv_before);
        lv_after = (Button) findViewById(R.id.lv_after);
        lv_back = (Button) findViewById(R.id.lv_back);
    }
}
