package com.example.warmfire.asynctasktest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by warmfire_2 on 2016/7/29.
 */
public class MyBroadCast extends BroadcastReceiver {

    public MyBroadCast(){
        Log.d("warmfire", "广播已启动");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "广播已接收", Toast.LENGTH_SHORT).show();
    }
}