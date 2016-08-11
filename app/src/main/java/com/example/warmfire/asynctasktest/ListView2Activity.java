package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by warmfire_2 on 2016/8/1.
 */
public class ListView2Activity extends Activity {

    Button l_v_back;
    ListView l_v_list;
    int i = 0;

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
        MyAdapter ma = new MyAdapter();
        l_v_list.setAdapter(ma);
        super.onResume();
    }

    public void init(){
        l_v_back = (Button) findViewById(R.id.l_v_back);
        l_v_list = (ListView) findViewById(R.id.l_v_list);
    }


    class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public MyAdapter(){
            mInflater = LayoutInflater.from(ListView2Activity.this);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 10000;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.list_item, null);
                holder.id = (TextView) convertView.findViewById(R.id.item_id);
                holder.listview = (ImageView) convertView.findViewById(R.id.item_img);
                convertView.setTag(holder);
                holder.id.setText("&&&&");
                holder.listview.setImageBitmap(returnBitMap("http://www.51ppt.com.cn/Article/Uploadphotos_0708/200604/200641314454983.png"));
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }
    }

    class ViewHolder{
        private TextView id;
        private ImageView listview;
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
            Toast.makeText(ListView2Activity.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
        return bitmap;
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
