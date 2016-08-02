package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by warmfire_2 on 2016/8/1.
 */
public class DownloadActivity extends Activity {

    Button download_down, download_start, download_back, download_client;
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
        download_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = download_url.getText().toString();
                try {
                    downLoadFile(url);
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
        download_client = (Button) findViewById(R.id.download_client);
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
            temp = urlStr.split("\\.");
            int i = temp.length;
            String fileName = temp[i-1];
            URL url = new URL(urlStr);
            SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
            Date curDate = new Date(System.currentTimeMillis());
            String str = formatter.format(curDate);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);

            String SDCard = Environment.getExternalStorageDirectory() + "";
            String pathName = SDCard + "/" + path + "/" + str + "." + fileName;
            File file = new File(pathName);

            InputStream input = conn.getInputStream();
            if (file.exists()) {
                System.out.println("exits");
                return;
            } else {
                String dir = SDCard + "/" + path;
                new File(dir).mkdir();
                file.createNewFile();
                int len = 0;
                output = new FileOutputStream(file);
                byte[] buffer = new byte[10 * 1024];
                while ((len = input.read(buffer)) != -1) {
                    output.write(buffer, 0, len);
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
                Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "file/*");
                startActivityForResult(openAlbumIntent, 0);
            } catch (Exception e) {
                System.out.println("fail");
                e.printStackTrace();
                Toast.makeText(DownloadActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void downLoadFile(String url) {
        HttpClient httpClient1 = new DefaultHttpClient();
        HttpGet httpGet1 = new HttpGet(url);
        String[] temp = null;
        try {
            temp = url.split("\\.");
            int i = temp.length;
            String fileName = temp[i-1];
            HttpResponse httpResponse1 = httpClient1.execute(httpGet1);
            StatusLine statusLine = httpResponse1.getStatusLine();
            SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
            Date curDate = new Date(System.currentTimeMillis());
            String str = formatter.format(curDate);
            if (statusLine.getStatusCode() == 200) {
                String filePath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/file/" + str + "." + fileName;
                Log.d("warmfire", filePath);
                File file = new File(filePath);
                FileOutputStream outputStream = new FileOutputStream(file);
                InputStream inputStream = httpResponse1.getEntity()
                        .getContent();
                byte b[] = new byte[10 * 1024];
                int j = 0;
                while ((j = inputStream.read(b)) != -1) {
                    outputStream.write(b, 0, j);
                }
                Toast.makeText(DownloadActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                outputStream.flush();
                outputStream.close();
                Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "file/*");
                startActivityForResult(openAlbumIntent, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(DownloadActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
        } finally {
            httpClient1.getConnectionManager().shutdown();
        }
    }

}
