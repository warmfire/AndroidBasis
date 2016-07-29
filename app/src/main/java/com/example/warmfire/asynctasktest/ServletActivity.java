package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by warmfire_2 on 2016/7/28.
 */
public class ServletActivity extends Activity {

    EditText servlet_username, servlet_password;
    Button servlet_submit, servlet_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servlet);

        init();
        networkOk();

        servlet_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = servlet_username.getText().toString();
                String password = servlet_password.getText().toString();
                if(username.equals("") || username == null){
                    Toast.makeText(ServletActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }
                else if(password.equals("") || password == null){
                    Toast.makeText(ServletActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    //请求数据
                    HttpGet httpRequest  = new HttpGet("http://192.168.16.45:8080/AndroidServelet/AndroidServlet?username=" + username + "&password=" + password);
                    try {
                        HttpResponse httpResponse;
                        httpResponse = new DefaultHttpClient().execute(httpRequest);
                    }
                    catch(Exception e){
                        Toast.makeText(ServletActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        servlet_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init(){
        servlet_username = (EditText) findViewById(R.id.servlet_username);
        servlet_password = (EditText) findViewById(R.id.servlet_password);
        servlet_submit = (Button) findViewById(R.id.servlet_submit);
        servlet_back = (Button) findViewById(R.id.servlet_back);
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
}
