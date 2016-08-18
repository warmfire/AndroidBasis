package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.ComboLineColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.ComboLineColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SelectedValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.ComboLineColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by warmfire_2 on 2016/8/17.
 */
public class ChartActivity extends Activity {

    Button chart_back;
    LineChartView chart_line;
    PieChartView chart_pie;
    ColumnChartView chart_column;
    ComboLineColumnChartView chart_comboline;
    LineChartData lineChartData;
    private PieChartData piedata;
    List<AxisValue> axisValues;
    private boolean hasLabels = true; // 是否显示数据
    private boolean hasLabelsOutside = false; // 数据是否显示在外面
    private boolean hasCenterCircle = true; // 是否含有中圈，显示下面的内容这个必须为true
    private boolean hasCenterText1 = true; // 圆中是否含有内容1
    private boolean hasCenterText2 = true; // 圆中是否含有内容2
    private boolean isExploded = false; // 是否爆破形式
    private boolean hasLabelForSelected = false; // 是否选中显示数据，一般为false
    public final static String[] months = new String[] { "Jan", "Feb", "Mar",
            "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", };
    ColumnChartData columnData;
    List<Column> lsColumn = new ArrayList<Column>();
    List<SubcolumnValue> lsValue;

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
        dataInit();
        setComboLine();
    }

    public void init(){
        chart_back = (Button) findViewById(R.id.chart_back);
        chart_line = (LineChartView) findViewById(R.id.chart_line);
        chart_pie = (PieChartView) findViewById(R.id.chart_pie);
        chart_column = (ColumnChartView) findViewById(R.id.chart_column);
        chart_comboline = (ComboLineColumnChartView) findViewById(R.id.chart_comboline);
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
        values.add(new PointValue(0, 20));
        values.add(new PointValue(1, 35));
        values.add(new PointValue(2, 25));
        values.add(new PointValue(3, 30));
        values.add(new PointValue(4, 28));
        values.add(new PointValue(5, 20));
        values.add(new PointValue(6, 35));
        values.add(new PointValue(7, 25));
        values.add(new PointValue(8, 30));
        values.add(new PointValue(9, 28));
        values.add(new PointValue(10, 28));
        values.add(new PointValue(11, 20));
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        lineChartData = new LineChartData();
        lineChartData.setLines(lines);
        chart_line.setLineChartData(lineChartData);
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
            piedata.setCenterText1Color(getResources().getColor(R.color.black));
        }

        if (hasCenterText2) {
            piedata.setCenterText2("未做占比");
            piedata.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                    (int) getResources().getDimension(R.dimen.pie_chart_text2_size)));
            piedata.setCenterText2Color(getResources().getColor(R.color.black));
        }

        chart_pie.setPieChartData(piedata);
    }

    private void dataInit() {

        int numSubcolumns = 1;
        int numColumns = months.length;

        axisValues = new ArrayList<AxisValue>();
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5,
                        ChartUtils.pickColor()));
            }
            // 点击柱状图就展示数据量
            axisValues.add(new AxisValue(i).setLabel(months[i]));
            columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
        }

        columnData = new ColumnChartData(columns);

        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true)
                .setTextColor(Color.BLACK));
        columnData.setAxisYLeft(new Axis().setHasLines(true)
                .setTextColor(Color.BLACK).setMaxLabelChars(2));

        chart_column.setColumnChartData(columnData);

        // Set value touch listener that will trigger changes for chartTop.
        chart_column.setOnValueTouchListener(new ValueTouchListener());

        // Set selection mode to keep selected month column highlighted.
        chart_column.setValueSelectionEnabled(true);

        chart_column.setZoomType(ZoomType.HORIZONTAL);

    }

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex,
                                    SubcolumnValue value) {
            //generateLineData(value.getColor(), 100);
        }

        @Override
        public void onValueDeselected() {
            //generateLineData(ChartUtils.COLOR_GREEN, 0);
        }
    }

    public void setComboLine(){
        ComboLineColumnChartOnValueSelectListener ccovsl = new ComboLineColumnChartOnValueSelectListener() {
            @Override
            public void onColumnValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {

            }

            @Override
            public void onPointValueSelected(int lineIndex, int pointIndex, PointValue value) {

            }

            @Override
            public void onValueDeselected() {

            }
        };

        chart_comboline.setZoomEnabled(true);//设置是否支持缩放
        chart_comboline.setOnValueTouchListener(ccovsl);//为图表设置值得触摸事件
        chart_comboline.setInteractive(true);//设置图表是否可以与用户互动
        chart_comboline.setValueSelectionEnabled(true);//设置图表数据是否选中进行显示

        ComboLineColumnChartData comboLineColumnChartData = new ComboLineColumnChartData();//定义组合数据对象
        comboLineColumnChartData.setLineChartData(lineChartData);//为组合图设置折线图数据
        comboLineColumnChartData.setColumnChartData(columnData);//为组合图设置柱形图数据
        comboLineColumnChartData.setValueLabelsTextColor(Color.BLACK);// 设置数据文字颜色
        comboLineColumnChartData.setValueLabelTextSize(15);// 设置数据文字大小
        comboLineColumnChartData.setValueLabelTypeface(Typeface.MONOSPACE);// 设置数据文字样式
        comboLineColumnChartData.setAxisYLeft(new Axis().setHasLines(true)
                .setTextColor(Color.BLACK).setMaxLabelChars(2));// 将Y轴属性设置到左边
        comboLineColumnChartData.setAxisXBottom(new Axis(axisValues).setHasLines(true)
                .setTextColor(Color.BLACK));// 将X轴属性设置到底部
        chart_comboline.setComboLineColumnChartData(comboLineColumnChartData);//为足额和图添加数据
    }
}
