package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by warmfire_2 on 2016/7/29.
 */
public class ServiceActivity extends Activity {

    Button service_start, service_back;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        init();

        service_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ServiceActivity.this, HelloService.class);
                startService(intent);
            }
        });
        service_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init() {
        service_start = (Button) findViewById(R.id.service_start);
        service_back = (Button) findViewById(R.id.service_back);
    }

}
