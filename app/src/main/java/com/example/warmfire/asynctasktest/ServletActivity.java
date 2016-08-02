package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by warmfire_2 on 2016/7/28.
 */
public class ServletActivity extends Activity {

    EditText servlet_username, servlet_password;
    Button servlet_submit, servlet_back;
    ListView servlet_listview;

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
                if (username.equals("") || username == null) {
                    Toast.makeText(ServletActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (password.equals("") || password == null) {
                    Toast.makeText(ServletActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //请求数据
                    HttpGet httpRequest = new HttpGet("http://192.168.16.45:8080/AndroidServelet/AndroidServlet?id=1&username=" + username + "&password=" + password);
                    try {
                        HttpResponse httpResponse;
                        httpResponse = new DefaultHttpClient().execute(httpRequest);
                        Toast.makeText(ServletActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(ServletActivity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                setadapter();
            }
        });
        servlet_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setadapter();
    }

    public void init() {
        servlet_username = (EditText) findViewById(R.id.servlet_username);
        servlet_password = (EditText) findViewById(R.id.servlet_password);
        servlet_submit = (Button) findViewById(R.id.servlet_submit);
        servlet_back = (Button) findViewById(R.id.servlet_back);
        servlet_listview = (ListView) findViewById(R.id.servlet_listview);
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

    public List<Map<String, Object>> refreshList() {
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.16.45:3306/androidservlet", "root", "123456");
            statement = conn.createStatement();
            rs = statement.executeQuery("select * from user order by id desc");
            while(rs.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", rs.getInt(1));
                map.put("username", rs.getString(2));
                map.put("password", rs.getString(3));
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void setadapter(){
        SimpleAdapter adapter = new SimpleAdapter(ServletActivity.this, refreshList(),
                R.layout.servlet_list_item, new String[] {"id", "username", "password"},
                new int[] {R.id.servlet_item_id, R.id.servlet_item_username, R.id.servlet_item_password});
        servlet_listview.setAdapter(adapter);
    }

}
