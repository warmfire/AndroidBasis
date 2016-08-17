package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SelectedValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by warmfire_2 on 2016/8/17.
 */
public class ChartActivity extends Activity {

    Button chart_back;
    LineChartView chart_line;
    PieChartView chart_pie;

    private PieChartData piedata;

    private boolean hasLabels = true; // 是否显示数据
    private boolean hasLabelsOutside = false; // 数据是否显示在外面
    private boolean hasCenterCircle = true; // 是否含有中圈，显示下面的内容这个必须为true
    private boolean hasCenterText1 = true; // 圆中是否含有内容1
    private boolean hasCenterText2 = true; // 圆中是否含有内容2
    private boolean isExploded = false; // 是否爆破形式
    private boolean hasLabelForSelected = false; // 是否选中显示数据，一般为false

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setClick();
        setLineChart();
        setPieChart();
    }

    public void init(){
        chart_back = (Button) findViewById(R.id.chart_back);
        chart_line = (LineChartView) findViewById(R.id.chart_line);
        chart_pie = (PieChartView) findViewById(R.id.chart_pie);
    }

    public void setClick(){
        chart_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setLineChart(){
        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 4));
        values.add(new PointValue(4, 5));
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        chart_line.setLineChartData(data);
    }

    public void setPieChart(){
        int numValues = 6;

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            values.add(sliceValue);
        }

        piedata = new PieChartData(values);
        piedata.setHasLabels(hasLabels);
        piedata.setHasLabelsOnlyForSelected(hasLabelForSelected);
        piedata.setHasLabelsOutside(hasLabelsOutside);
        piedata.setHasCenterCircle(hasCenterCircle);
        // 设置不显示数据的背景颜色
        piedata.setValueLabelBackgroundEnabled(false);

        if (isExploded) {
            piedata.setSlicesSpacing(24);
        }

        if (hasCenterText1) {
            piedata.setCenterText1("92.14%");
            piedata.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                    (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));
            piedata.setCenterText1Color(getResources().getColor(R.color.gray));
        }

        if (hasCenterText2) {
            piedata.setCenterText2("未做占比");
            piedata.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                    (int) getResources().getDimension(R.dimen.pie_chart_text2_size)));
            piedata.setCenterText2Color(getResources().getColor(R.color.gray));
        }

        chart_pie.setPieChartData(piedata);
    }
}
