package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by warmfire_2 on 2016/8/15.
 */
public class VedioActivity extends Activity {

    Button vedio_back, video_play;
    VideoView videoView;
    EditText video_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio);
        networkOk();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setClick();
    }

    public void init(){
        vedio_back = (Button) findViewById(R.id.vedio_back);
        video_play = (Button) findViewById(R.id.video_play);
        videoView = (VideoView) findViewById(R.id.video_view);
        video_url = (EditText) findViewById(R.id.video_url);
    }

    public void setClick(){
        vedio_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        video_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(video_url.getText().toString().equals("") || video_url.getText().toString() == null){
                    Toast.makeText(VedioActivity.this, "链接不能为空", Toast.LENGTH_SHORT).show();
                }
                else if(checkURL(video_url.getText().toString())){
                    try {
                        startPlay(video_url.getText().toString());
                    } catch (Exception e){
                        Toast.makeText(VedioActivity.this, "播放失败", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(VedioActivity.this, "链接错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void startPlay(String url){
        Uri uri = Uri.parse(url);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.requestFocus();
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

    public static boolean checkURL(String url) {
        boolean value = false;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            int code = conn.getResponseCode();
            System.out.println(">>>>>>>>>>>>>>>> " + code + " <<<<<<<<<<<<<<<<<<");
            if (code != 200) {
                value = false;
            } else {
                value = true;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return value;
    }

}
