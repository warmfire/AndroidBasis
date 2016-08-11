package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;


/**
 * Created by warmfire_2 on 2016/8/11.
 */
public class PullToRefreshActicity extends Activity {

    PullToRefreshScrollView scrollView;
    LinearLayout pull_ll;
    Button pull_back;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPull();
        setClick();
    }

    public void init(){
        scrollView = (PullToRefreshScrollView) findViewById(R.id.expand_list);
        pull_ll = (LinearLayout) findViewById(R.id.pull_ll);
        pull_back = (Button) findViewById(R.id.pull_back);
    }

    public void setClick(){
        pull_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setPull(){
        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置顶部下拉刷新
        final Typeface typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
        scrollView.getLoadingLayoutProxy(true,false).setTextTypeface(typeface);
        scrollView.getLoadingLayoutProxy(true,false).setLoadingDrawable(null);
        scrollView.getLoadingLayoutProxy(true,false).setPullLabel("下拉刷新");
        scrollView.getLoadingLayoutProxy(true,false).setRefreshingLabel("正在刷新...");
        //scrollView.getLoadingLayoutProxy(true,false).setLastUpdatedLabel("上次刷新时间：" + sdf.format(lastUpdateDate));
        scrollView.getLoadingLayoutProxy(true,false).setReleaseLabel("松开立即刷新");
        //设置底部上拉加载更多
        scrollView.getLoadingLayoutProxy(false,true).setTextTypeface(typeface);
        scrollView.getLoadingLayoutProxy(false,true).setLoadingDrawable(null);
        scrollView.getLoadingLayoutProxy(false,true).setPullLabel("上拉加载更多");
        scrollView.getLoadingLayoutProxy(false,true).setRefreshingLabel("正在加载更多藏品...");
        //scrollView.getLoadingLayoutProxy(false, true).setLastUpdatedLabel("上次加载时间：" + sdf.format(lastUpdateDate));
        scrollView.getLoadingLayoutProxy(false,true).setReleaseLabel("松开立即加载更多");
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                Toast.makeText(PullToRefreshActicity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                b2 = new Button(PullToRefreshActicity.this);
                b2.setText("确定");
                pull_ll.addView(b2);
                scrollView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                Toast.makeText(PullToRefreshActicity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                pull_ll.removeView(b2);
                scrollView.onRefreshComplete();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
