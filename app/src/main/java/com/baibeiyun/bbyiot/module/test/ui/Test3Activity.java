package com.baibeiyun.bbyiot.module.test.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.home.view.CustomDrawable;
import com.baibeiyun.bbyiot.module.mine.ui.analysis.StatisticAnalysisActivityNew;
import com.baibeiyun.bbyiot.module.test.contract.TestContract;
import com.baibeiyun.bbyiot.module.test.presenter.TestPresenter;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.chart.my.MyLineChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test3Activity extends BaseActivity<TestPresenter> implements TestContract.View {

    private MyLineChart mChart1;

    int lenth = 180;

    protected String[] mMonths = new String[lenth];

    protected String[] testArr = new String[]{"1:00", "2:00", "3:00", "4:00", "5:00",
            "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00",
            "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00",
            "22:00", "23:00", "00:00"};

    private Context mContext;
    protected DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
    private ArrayList<ILineDataSet> dataSets;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_test;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("test");

        for (int i = 0; i < lenth; i++) {
            mMonths[i] = testArr[i % 24];
        }

        mContext = this;
        df.setMaximumFractionDigits(1);

        mChart1 = (MyLineChart) findViewById(R.id.chart1);
        initLineView(mChart1, 400f);

        List<Float> valList = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            float val = (float) (Math.random() * 80) + 185;
            valList.add(val);
        }
        //找出最大值和最小值，然后添加icon
        float mMaxData = Collections.max(valList);
        float mMinData = Collections.min(valList);
        ArrayList<Entry> values3 = new ArrayList<Entry>();
        for (int i = 0; i < valList.size(); i++) {
            float data = valList.get(i);
            if (data == mMaxData) {
                CustomDrawable customDrawable = new CustomDrawable(mContext);
                customDrawable.setmIsUp(true);
                customDrawable.setmBackGroundColor(ContextCompat.getColor(mContext, R.color.color_green_alpa));
                customDrawable.setmText(df.format(mMaxData) + " kg");
                values3.add(new Entry(i, valList.get(i), customDrawable));
//                values3.add(new Entry(i, valList.get(i), ContextCompat.getDrawable(mContext,R.drawable.star)));
            } else if (data == mMinData) {
                CustomDrawable customDrawable = new CustomDrawable(mContext);
                customDrawable.setmIsUp(false);
                customDrawable.setmBackGroundColor(ContextCompat.getColor(mContext, R.color.color_red_alpa));
                customDrawable.setmText(df.format(mMinData) + " kg");
                values3.add(new Entry(i, valList.get(i), customDrawable));
//                values3.add(new Entry(i, valList.get(i), ContextCompat.getDrawable(mContext,R.drawable.star)));
            } else {
                values3.add(new Entry(i, valList.get(i)));
            }
        }
        setData(mChart1, values3);
    }

    private void initLineView(LineChart mChart, float maxData) {
        mChart.setNoDataText("图表无数据");

        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        mChart.setDoubleTapToZoomEnabled(false);

        mChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.animateX(1000);

        mChart.getLegend().setEnabled(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(11f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mMonths[(int) value % mMonths.length];
            }
        };

        xAxis.setValueFormatter(formatter);


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);//是否显示水平标尺线
//        leftAxis.setAxisMinimum(0f);
//        leftAxis.setAxisMaximum(maxData);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setAxisMinimum(0);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
        rightAxis.setEnabled(false);

        //设置是否显示 图例标签
        //mChart.getLegend().setEnabled(true);
    }

    private void setData(LineChart mChart, ArrayList<Entry> yVals1) {

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
            LogUtils.w("mpchart" + "getData!=null");
        } else {
            dataSets = new ArrayList<>();
            for (int m = 0; m < 2; m++) {

                List<Float> valList = new ArrayList<>();
                for (int i = 0; i < lenth; i++) {
                    float val = (float) (Math.random() * 80) + 185;
                    valList.add(val);
                }
                //找出最大值和最小值，然后添加icon
                float mMaxData = Collections.max(valList);
                float mMinData = Collections.min(valList);
                ArrayList<Entry> values3 = new ArrayList<Entry>();
                for (int i = 0; i < valList.size(); i++) {
                    float data = valList.get(i);
                    if (data == mMaxData) {
                        CustomDrawable customDrawable = new CustomDrawable(mContext);
                        customDrawable.setmIsUp(true);
                        customDrawable.setmBackGroundColor(ContextCompat.getColor(mContext, R.color.color_green_alpa));
                        customDrawable.setmText(df.format(mMaxData) + " kg");
                        values3.add(new Entry(i, valList.get(i), customDrawable));
//                values3.add(new Entry(i, valList.get(i), ContextCompat.getDrawable(mContext,R.drawable.star)));
                    } else if (data == mMinData) {
                        CustomDrawable customDrawable = new CustomDrawable(mContext);
                        customDrawable.setmIsUp(false);
                        customDrawable.setmBackGroundColor(ContextCompat.getColor(mContext, R.color.color_red_alpa));
                        customDrawable.setmText(df.format(mMinData) + " kg");
                        values3.add(new Entry(i, valList.get(i), customDrawable));
//                values3.add(new Entry(i, valList.get(i), ContextCompat.getDrawable(mContext,R.drawable.star)));
                    } else {
                        values3.add(new Entry(i, valList.get(i)));
                    }
                }
                if (m == 1) {
                    set1 = new LineDataSet(values3, "");
                } else {

                    LogUtils.w("mpchart" + "getData==null");
                    // create a dataset and give it a type
                    set1 = new LineDataSet(yVals1, "");
                }

                set1.setAxisDependency(YAxis.AxisDependency.LEFT);
                //set1.setColor(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[0]));
                set1.setCircleColor(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[0]));
                set1.setLineWidth(2f);
                set1.setCircleRadius(2f);
                set1.setFillAlpha(65);
                set1.setFillColor(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[0]));
                set1.setHighLightColor(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[0]));
                set1.setDrawCircleHole(true);
                set1.setCircleColor(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[0]));
                set1.setMode(LineDataSet.Mode.LINEAR);
                set1.setDrawCircles(false);

                List<Integer> colors = new ArrayList<>();
                for (int i = 0; i < lenth; i++) {
                    if (i > lenth - 5) {
                        colors.add(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[2]));
                    } else {
                        colors.add(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[m % 2]));
                    }
                }
                set1.setColors(colors);

                dataSets.add(set1);
                // create a data object with the datasets

            }

            LineData data = new LineData(dataSets);
            data.setValueTextColor(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[0]));
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            mChart.setData(data);
        }
    }

    @Override
    protected TestPresenter getPresenter() {
        return null;
    }
}
