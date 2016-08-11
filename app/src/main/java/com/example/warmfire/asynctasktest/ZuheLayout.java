package com.example.warmfire.asynctasktest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by warmfire_2 on 2016/8/3.
 */
public class ZuheLayout extends RelativeLayout {

    private TextView zuhe_txt;
    private Button zuhe_btn;

    public ZuheLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public ZuheLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ZuheLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        // TODO Auto-generated method stub
        final Context con = context;
        View.inflate(context, R.layout.layout_zuhe, this);
        zuhe_txt = (TextView) findViewById(R.id.zuhe_txt);
        zuhe_btn = (Button) findViewById(R.id.zuhe_btn);
        zuhe_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(con, "您按了这个按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
