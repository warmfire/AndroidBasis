package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by warmfire_2 on 2016/7/28.
 */
public class ActivityActivity extends Activity {

    Button activity_linearlayout,activity_relativelayout,activity_framelayout,activity_absoultelayout,activity_tablelayout,activity_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        init();

        activity_linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(ActivityActivity.this, LinearlayoutActivity.class), 0);
            }
        });
        activity_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(ActivityActivity.this, RelativelayoutActivity.class), 0);
            }
        });
        activity_framelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(ActivityActivity.this, FramelayoutActivity.class), 0);
            }
        });
        activity_absoultelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(ActivityActivity.this, AbsoultelayoutActivity.class), 0);
            }
        });
        activity_tablelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(ActivityActivity.this, TablelayoutActivity.class), 0);
            }
        });
        activity_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init(){
        activity_linearlayout = (Button) findViewById(R.id.activity_linearlayout);
        activity_relativelayout = (Button) findViewById(R.id.activity_relativelayout);
        activity_framelayout = (Button) findViewById(R.id.activity_framelayout);
        activity_absoultelayout = (Button) findViewById(R.id.activity_absoultelayout);
        activity_tablelayout = (Button) findViewById(R.id.activity_tablelayout);
        activity_back = (Button) findViewById(R.id.activity_back);
    }

}
