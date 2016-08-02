package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
        setadapter();

        l_v_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init(){
        l_v_back = (Button) findViewById(R.id.l_v_back);
        l_v_list = (ListView) findViewById(R.id.l_v_list);
    }

    public List<Map<String, Object>> refreshList() {
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            for(int i = 0; i < 1000; i++){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", i);
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void setadapter(){
        SimpleAdapter adapter = new SimpleAdapter(ListView1Activity.this, refreshList(),
                R.layout.list_item, new String[] {"id"},
                new int[] {R.id.item_id});
        l_v_list.setAdapter(adapter);
    }

}
