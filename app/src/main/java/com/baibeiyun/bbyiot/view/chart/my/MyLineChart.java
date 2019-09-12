package com.baibeiyun.bbyiot.view.chart.my;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;

/**
 *
 */

public class MyLineChart extends LineChart {
    public MyLineChart(Context context) {
        super(context);
    }

    public MyLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        /** ------------------------ start ------------------------ */
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mRenderer = new MyLineChartRenderer(this, mAnimator, mViewPortHandler,metrics.widthPixels);
        /** ------------------------ end ------------------------ */
    }
}
