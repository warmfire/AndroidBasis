package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by warmfire_2 on 2016/7/27.
 */
public class HandlerActivity extends Activity {

    ImageView handler_img;
    Button handler_back, handler_show, handler_showurl;
    Bitmap bitmap;
    EditText handler_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        networkOk();
        init();

        handler_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    public void run(){
                        new Handler(HandlerActivity.this.getMainLooper()).post(runnable);
                    }
                }.start();
            }
        });

        handler_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        handler_showurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    downloadImg();
                    new Thread(){
                        @Override
                        public void run() {
                            new Handler(HandlerActivity.this.getMainLooper()).post(url_runnable);
                        }
                    }.start();
                } catch(Exception e){
                    Toast.makeText(HandlerActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void networkOk(){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectAll()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    public void init(){
        handler_back = (Button) findViewById(R.id.handler_back);
        handler_show = (Button) findViewById(R.id.handler_show);
        handler_showurl = (Button) findViewById(R.id.handler_showurl);
        handler_img = (ImageView) findViewById(R.id.handler_img);
        handler_edit = (EditText) findViewById(R.id.handler_edit);
    }

    public void downloadImg(){
        try {
            String urlstr = handler_edit.getText().toString();
            if(urlstr.equals("") || urlstr == null){
                Toast.makeText(HandlerActivity.this, "请输入url", Toast.LENGTH_SHORT).show();
            }
            else{
                URL url = new URL(urlstr);
                InputStream is = url.openStream();
                bitmap = BitmapFactory.decodeStream(is);
            }
        }
        catch(Exception e){
            Toast.makeText(HandlerActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    Runnable runnable = new Runnable(){
        @Override
        public void run() {
            handler_img.setBackgroundResource(R.drawable.ic_launcher);
        }
    };

    Runnable url_runnable = new Runnable(){
        @Override
        public void run() {
            try {
                handler_img.setImageBitmap(bitmap);
            } catch (Exception e){
                Toast.makeText(HandlerActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    };

}
