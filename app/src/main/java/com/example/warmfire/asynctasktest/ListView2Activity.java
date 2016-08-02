package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

        MyAdapter ma = new MyAdapter();
        l_v_list.setAdapter(ma);

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


    class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public MyAdapter(){
            mInflater = LayoutInflater.from(ListView2Activity.this);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 1000;
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
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.id.setText("&&&&&");
            return convertView;
        }
    }

    class ViewHolder{
        private TextView id;
    }

}
