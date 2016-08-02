package com.example.warmfire.asynctasktest;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button main_handler, main_servlet,
            main_json, main_activity,
            main_content_providers,
            main_service, main_broadcast,
            main_animation, main_listview,
            main_download;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        main_handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(MainActivity.this, HandlerActivity.class), 0);
            }
        });
        main_servlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(MainActivity.this, ServletActivity.class), 0);
            }
        });
        main_json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(MainActivity.this, JsonActivity.class), 0);
            }
        });
        main_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(MainActivity.this, ActivityActivity.class), 0);
            }
        });
        main_content_providers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(MainActivity.this, ContentProviderActivity.class), 0);
            }
        });
        main_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(MainActivity.this, ServiceActivity.class), 0);
            }
        });
        main_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(MainActivity.this, BroadCastActivity.class), 0);
            }
        });
        main_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(MainActivity.this, AnimationActivity.class), 0);
            }
        });
        main_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(MainActivity.this, ListViewActivity.class), 0);
            }
        });
        main_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent().setClass(MainActivity.this, DownloadActivity.class), 0);
            }
        });
    }

    public void init(){
        main_handler = (Button) findViewById(R.id.main_handler);
        main_servlet = (Button) findViewById(R.id.main_servlet);
        main_json = (Button) findViewById(R.id.main_json);
        main_activity = (Button) findViewById(R.id.main_activity);
        main_content_providers = (Button) findViewById(R.id.main_content_providers);
        main_service = (Button) findViewById(R.id.main_service);
        main_animation = (Button) findViewById(R.id.main_animation);
        main_broadcast = (Button) findViewById(R.id.main_broadcast);
        main_listview = (Button) findViewById(R.id.main_listview);
        main_download = (Button) findViewById(R.id.main_download);
    }

}
