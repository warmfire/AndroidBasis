package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by warmfire_2 on 2016/7/28.
 */
public class JsonActivity extends Activity {

    Button json_start, json_empty, json_back;
    TextView json_jsontxt;
    EditText json_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        init();
        networkOk();

        json_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = json_edit.getText().toString();
                if(url.equals("") || url ==null){
                    Toast.makeText(JsonActivity.this, "请输入url", Toast.LENGTH_SHORT).show();
                }
                else{
                    String arg0 = null;
                    try{
                        arg0 = readParse(url);
                    } catch (Exception e){
                        Toast.makeText(JsonActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    json_jsontxt.setText(arg0);
                }
            }
        });
        json_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                json_jsontxt.setText("json已清空");
            }
        });
        json_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init(){
        json_edit = (EditText) findViewById(R.id.json_edit);
        json_start = (Button) findViewById(R.id.json_start);
        json_empty = (Button) findViewById(R.id.json_empty);
        json_back = (Button) findViewById(R.id.json_back);
        json_jsontxt = (TextView) findViewById(R.id.json_jsontxt);
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
    // 从指定的url中获取字节数组
    public static String readParse(String urlPath) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[6000];
        int len = 0;
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inStream = conn.getInputStream();
        while ((len = inStream.read(data)) != -1) {
            outStream.write(data, 0, len);
        }
        inStream.close();
        return outStream.toString();
    }
}
