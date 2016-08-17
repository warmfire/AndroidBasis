package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by warmfire_2 on 2016/8/11.
 */
public class LunbotuActivity extends Activity {

    AutoScrollViewPager lunbotu_lunbotu;
    Button lunbotu_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunbotu);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setClick();
        setLunbo();
    }

    public void setClick(){
        lunbotu_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init(){
        lunbotu_lunbotu = (AutoScrollViewPager) findViewById(R.id.lunbotu_lunbotu);
        lunbotu_back = (Button) findViewById(R.id.lunbotu_back);
    }

    public void setLunbo(){
        for (int i = 0; i < 4; i++) {
            ImageView img = new ImageView(LunbotuActivity.this);
            img.setImageResource(R.drawable.default_ptr_rotate);
            lunbotu_lunbotu.addView(img);
        }
        if (lunbotu_lunbotu != null) {
            lunbotu_lunbotu.startAutoScroll();
        }
        lunbotu_lunbotu.setInterval(3000); //设置自动滚动的间隔时间，单位为毫秒
        lunbotu_lunbotu.setDirection(1); //设置自动滚动的方向，默认向右
        lunbotu_lunbotu.setCycle(true); //是否自动循环轮播，默认为true
        lunbotu_lunbotu.setAutoScrollDurationFactor(1000);
        lunbotu_lunbotu.setSwipeScrollDurationFactor(1000); //设置ViewPager滑动动画间隔时间的倍率，达到减慢动画或改变动画速度的效果
        lunbotu_lunbotu.setStopScrollWhenTouch(true); //当手指碰到ViewPager时是否停止自动滚动，默认为true
        lunbotu_lunbotu.setSlideBorderMode(1); //滑动到第一个或最后一个Item的处理方式，支持没有任何操作、轮播以及传递到父View三种模式
        lunbotu_lunbotu.setBorderAnimation(true); //设置循环滚动时滑动到从边缘滚动到下一个是否需要动画，默认为true
    }
}
