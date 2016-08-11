package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by warmfire_2 on 2016/8/1.
 */
public class ListView1Activity extends Activity {

    Button l_v_back;
    ListView l_v_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        init();
        networkOk();

        l_v_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setadapter();
    }

    public void init(){
        l_v_back = (Button) findViewById(R.id.l_v_back);
        l_v_list = (ListView) findViewById(R.id.l_v_list);
    }

    public void setadapter(){
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 1000;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                LayoutInflater mInflater = LayoutInflater.from(ListView1Activity.this);
                TextView id;
                ImageView listview;
                view = mInflater.inflate(R.layout.list_item, null);
                id = (TextView) view.findViewById(R.id.item_id);
                listview = (ImageView) view.findViewById(R.id.item_img);
                id.setText("&&&&");
                listview.setImageBitmap(returnBitMap("http://www.51ppt.com.cn/Article/Uploadphotos_0708/200604/200641316412380.png"));
                return view;
            }
        };
        l_v_list.setAdapter(baseAdapter);
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

    // url转换成bitmap
    public Bitmap returnBitMap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        if(!"".equals(url) || url.equals(null)){
            try {
                myFileUrl = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(ListView1Activity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            return null;
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ListView1Activity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
        return bitmap;
    }

}
