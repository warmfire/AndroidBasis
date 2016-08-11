package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by warmfire_2 on 2016/8/3.
 */
public class ZuheActivity extends Activity {

    Button zuhe_back;
    ZuheLayout zuheLayout;
    RedBlock rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuhe);

        init();

        zuhe_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init(){
        zuhe_back = (Button) findViewById(R.id.zuhe_back);
        zuheLayout = (ZuheLayout) findViewById(R.id.zuhe_layout);
        rb = (RedBlock) findViewById(R.id.zuhe_redblock);
    }
}
