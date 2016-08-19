package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by warmfire_2 on 2016/7/27.
 */
public class HandlerActivity extends Activity {

    ImageView handler_img;
    Button handler_back, handler_show, handler_showurl, handler_start;
    Bitmap bitmap;
    EditText handler_edit;
    String lasturl = "";
    ProgressBar handler_progressbar;

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

        handler_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //让进度条显示出来
                handler_progressbar.setVisibility(View.VISIBLE);
                //将线程加入到handler的线程队列中
                update_progress_bar.post(update_thread);
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
        handler_start = (Button) findViewById(R.id.handler_start);
        handler_progressbar = (ProgressBar) findViewById(R.id.handler_progressbar);
    }

    public void downloadImg(){
        try {
            String urlstr = handler_edit.getText().toString();
            if(!lasturl.equals(urlstr)){
                lasturl = urlstr;
                if(urlstr.equals("") || urlstr == null){
                    Toast.makeText(HandlerActivity.this, "请输入url", Toast.LENGTH_SHORT).show();
                }
                else{
                    URL url = new URL(urlstr);
                    InputStream is = url.openStream();
                    bitmap = BitmapFactory.decodeStream(is);
                }
            }
        }
        catch(Exception e){
            Toast.makeText(HandlerActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //创建一个handler，内部完成处理消息方法
    Handler update_progress_bar = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //super.handleMessage(msg);
            //显示进度条
            if(msg.arg1 <= 100) {
                handler_progressbar.setProgress(msg.arg1);
                Log.d("===warmfire", "i1 = " + msg.arg1);
                //重新把进程加入到进程队列中
                update_progress_bar.post(update_thread);
            }
        }
    };

    Runnable update_thread = new Runnable()
    {
        int i = 0;
        public void run() {
            // TODO Auto-generated method stub
            if(i <= 100) {
                i += 1;
                //首先获得一个消息结构
                Message msg = update_progress_bar.obtainMessage();
                //给消息结构的arg1参数赋值
                msg.arg1 = i;
                Log.d("===warmfire", "i2 = " + i);
                //延时1s，java中的try+catch用来排错处理
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                //把消息发送到消息队列中
                update_progress_bar.sendMessage(msg);
                if (i == 100) {
                    update_progress_bar.removeCallbacks(update_thread);
                    Log.d("===warmfire", "i3 = " + i);
                }
            }
        }
    };

    Runnable runnable = new Runnable(){
        @Override
        public void run() {
            handler_img.setImageDrawable(null);
            handler_img.setBackgroundResource(R.drawable.ic_launcher);
        }
    };

    Runnable url_runnable = new Runnable(){
        @Override
        public void run() {
            try {
                handler_img.setBackgroundResource(0);
                handler_img.setImageBitmap(bitmap);
            } catch (Exception e){
                Toast.makeText(HandlerActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    };


}
