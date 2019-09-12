package com.baibeiyun.bbyiot.module.mine.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.AnalysisGatherResponse;
import com.baibeiyun.bbyiot.module.mine.ui.analysis.StatisticAnalysisActivityNew;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.ListUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.chart.MyMarkerViewBar;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱形图
 */
public class BarChartView extends LinearLayout {

    private Activity mContext;
    private BarChart mBarChart;

    private XAxis xAxis;
    private YAxis leftAxis;
    private AnalysisGatherResponse dataResponse;

    public BarChartView(Context context) {
        super(context);
        initView(context, null);
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = (Activity) context;

        View mRootView = LayoutInflater.from(mContext).inflate(
                R.layout.view_chart_bar, this, true);

        mBarChart = mRootView.findViewById(R.id.view_chart_bar_barchart);

        mBarChart.setNoDataText("没有数据");
    }

    public void setData(AnalysisGatherResponse response) {
        dataResponse = response;
        showBarChart();
    }


    private void showBarChart() {
        if (dataResponse == null) {
            return;
        }
        try {
            /**
             * ----
             * */
            initBarChart(mBarChart);

            List<Long> xListPillar = dataResponse.getXTimes();
            List<AnalysisGatherResponse.YDatasBean> yListPillar = dataResponse.getYDatas();

            LogUtils.w(" x xListPillar = " + xListPillar.size() + "  yListPillar ==  " + yListPillar.size());

            final List<String> xValues = new ArrayList<>();

            List<VtDateValueBean> dateValueList = new ArrayList<>();


            if (!ListUtils.isEmpty(xListPillar)) {
                for (int i = 0; i < xListPillar.size(); i++) {
                    xValues.add(DateUtils.stamp2String(xListPillar.get(i), "HH:mm:ss"));
                }
            }

            if(!ListUtils.isEmpty(xValues)) {
                for (int i = 0; i < xValues.size(); i++) {
                    VtDateValueBean valueBean = new VtDateValueBean();
                    valueBean.xName = xValues.get(i);

                    if (!ListUtils.isEmpty(yListPillar)) {
                        valueBean.yValue = Float.parseFloat(yListPillar.get(i).getCollectCount() + "");
                    } else {
                        valueBean.yValue = 0f;
                    }
                    LogUtils.w(i + " , x== " + xValues.get(i) + " ,  y== " + valueBean.yValue);
                    dateValueList.add(valueBean);
                }
            }else {
                return;
            }


            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int i = 0; i < dateValueList.size(); i++) {
                /**
                 * 此处还可传入Drawable对象 BarEntry(float x, float y, Drawable icon)
                 * 即可设置柱状图顶部的 icon展示
                 */
                BarEntry barEntry = new BarEntry(i, dateValueList.get(i).yValue);
                entries.add(barEntry);
            }
            // 每一个BarDataSet代表一类柱状图
            BarDataSet barDataSet = new BarDataSet(entries, "");

            /**
             * ----
             */
            initBarDataSet(barDataSet, dateValueList);

//        // 添加多个BarDataSet时
//        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//        dataSets.add(barDataSet);
//        BarData data = new BarData(dataSets);

            BarData data = new BarData(barDataSet);
//        data.setBarWidth(DipToPxUtils.dipToPx(getContext(), 11));

            /**
             * 设置 value 值
             */
//        data.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
//                return FloatFormatHelper.floatFormat(value, "0.0");
//            }
//        });

            mBarChart.setData(data);
            mBarChart.setDoubleTapToZoomEnabled(false);

            //设置x轴最多显示数据条数
            mBarChart.setMaxVisibleValueCount(15);

            //设置X轴的刻度数量
            xAxis.setLabelCount(15);

            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    try {
                        return xValues.get((int) Math.abs(value) % xValues.size());
                    } catch (Exception e) {
                        LogUtils.w(e.toString());
                        return "";
                    }
                }
            });

            /** 经过测试此属性必须在绑定数据后进行设置才起作用，若在初始化chart之前设置是无用的*/
            mBarChart.setVisibleXRangeMaximum(30);

            mBarChart.notifyDataSetChanged();
            mBarChart.invalidate();

        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    private void initBarDataSet(BarDataSet barDataSet, List<VtDateValueBean> dateValueList) {

        /**
         *  设置最后一个条目 高亮的颜色
         */
        List<Integer> colorList = new ArrayList<>();
        for (int i = 0; i < dateValueList.size(); i++) {
//            if (i != dateValueList.size() - 1) {
//                colorList.add(ContextCompat.getColor(getContext(), AnalysisHistoryFragment.lineColorArr[0]));
//            } else {
//                colorList.add(ContextCompat.getColor(getContext(), AnalysisHistoryFragment.lineColorArr[1]));
//            }
            colorList.add(Color.parseColor("#52BE2E"));
        }
        barDataSet.setColors(colorList);


        barDataSet.setFormLineWidth(1f);
//        barDataSet.setFormSize(11f);
//        barDataSet.setBarBorderWidth(11);

        //false不显示柱状图顶部值
        barDataSet.setDrawValues(false);


        barDataSet.setHighLightAlpha(0xff);
        barDataSet.setHighLightColor(ContextCompat.getColor(getContext(), StatisticAnalysisActivityNew.lineColorArr[1]));
//        barDataSet.setValueTextSize(10f);
//        barDataSet.setValueTextColor(color);

    }


    /**
     * 初始化BarChart图表
     */
    private void initBarChart(BarChart barChart) {

        barChart.clear();
        /***图表设置***/
        //背景颜色
        barChart.setBackgroundColor(Color.WHITE);
        //不显示图表网格
        barChart.setDrawGridBackground(false);
        //背景阴影
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);

        //不显示边框
        barChart.setDrawBorders(false);
        //设置动画效果
        barChart.animateY(500);
        barChart.animateX(500);

        //
        //设置网格底下的那条线的颜色
        barChart.setBorderColor(Color.parseColor("#000000"));

        MyMarkerViewBar detailsMarkerView = new MyMarkerViewBar(getContext(), R.layout.chart_marker,1);
        //一定要设置这个玩意，不然到点击到最边缘的时候不会自动调整布局
        detailsMarkerView.setTextSize(12);
        detailsMarkerView.setTextColor(ContextCompat.getColor(getContext(), R.color.main_color));
        detailsMarkerView.setChartView(barChart);
        barChart.setMarker(detailsMarkerView);

        //X轴自定义值
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return "2019/05";
//            }
//        });


        /***XY轴的设置***/
        //X轴设置显示位置在底部
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //  xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        leftAxis = barChart.getAxisLeft();

        YAxis rightAxis = barChart.getAxisRight();
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        rightAxis.setAxisMinimum(0f);
        /***折线图例 标签 设置***/
        Legend legend = barChart.getLegend();
        legend.setEnabled(false);

   /*     legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        // legend.setEnabled(false);
        //是否绘制在图表里面
        legend.setDrawInside(false);*/

        //设置X轴倾斜度
        xAxis.setLabelRotationAngle(-60);

        //柱形图-是否画X轴线  ------
        xAxis.setDrawAxisLine(true);

        leftAxis.setDrawAxisLine(true);
        rightAxis.setDrawAxisLine(false);

        //是否像是网格
        xAxis.setDrawGridLines(false);
        rightAxis.setEnabled(false);
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);

        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //右侧Y轴网格线设置为虚线
        rightAxis.enableGridDashedLine(10f, 10f, 0f);

    }

    class VtDateValueBean {
        String xName;
        float yValue;
    }
}
