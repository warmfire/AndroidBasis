package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by warmfire_2 on 2016/8/1.
 */
public class DownloadActivity extends Activity {

    Button download_down, download_start, download_back;
    EditText download_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        init();
        networkOk();

        download_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveToFile("http://192.168.16.45:8080/app-release.apk");
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DownloadActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        download_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = download_url.getText().toString();
                try {
                    saveToFile(url);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DownloadActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        download_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init() {
        download_down = (Button) findViewById(R.id.download_down);
        download_start = (Button) findViewById(R.id.download_start);
        download_back = (Button) findViewById(R.id.download_back);
        download_url = (EditText) findViewById(R.id.download_url);
    }

    public void networkOk() {
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

    public void saveToFile(String urlStr) throws IOException {
        String path = "file";
        OutputStream output = null;
        String [] temp = null;
        try {
            temp = urlStr.split("8080/");
            String fileName = temp[1];
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置请求方法，默认是GET
            conn.setRequestMethod("GET");
            // 设置字符集
            conn.setRequestProperty("Charset", "utf-8");
            // 设置文件类型
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setConnectTimeout(10 * 1000);
            String SDCard = Environment.getExternalStorageDirectory() + "";
            String pathName = SDCard + "/" + path + "/" + fileName;
            File file = new File(pathName);
            InputStream input = conn.getInputStream();
            if (file.exists()) {
                System.out.println("exits");
                return;
            } else {
                String dir = SDCard + "/" + path;
                new File(dir).mkdir();
                file.createNewFile();
                output = new FileOutputStream(file);
                byte[] buffer = new byte[10 * 1024];
                while (input.read(buffer) != -1) {
                    output.write(buffer);
                }
                output.flush();
                output.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(DownloadActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                System.out.println("success");
                Toast.makeText(DownloadActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                System.out.println("fail");
                e.printStackTrace();
                Toast.makeText(DownloadActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
