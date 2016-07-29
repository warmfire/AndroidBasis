package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by warmfire_2 on 2016/7/28.
 */
public class TablelayoutActivity extends Activity {

    Button tl_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablelayout);
        tl_back = (Button) findViewById(R.id.tl_back);
        tl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
