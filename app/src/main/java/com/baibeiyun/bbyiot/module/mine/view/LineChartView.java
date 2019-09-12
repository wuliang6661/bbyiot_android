package com.baibeiyun.bbyiot.module.mine.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.AnalysisHistoryResponse;
import com.baibeiyun.bbyiot.module.home.view.RectangleDrawable;
import com.baibeiyun.bbyiot.module.mine.ui.analysis.StatisticAnalysisActivityNew;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.view.chart.MyMarkerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineChartView extends LinearLayout {

    private Activity mContext;
    protected DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();

    private LineChart mLineChart;
    private LineDataSet lineDataSet;
    private ArrayList<String> xName;
    private boolean isMin;
    private boolean isMax;

    public LineChartView(Context context) {
        super(context);
        initView(context, null);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = (Activity) context;

        View mRootView = LayoutInflater.from(mContext).inflate(
                R.layout.view_chart_line, this, true);

        mLineChart = mRootView.findViewById(R.id.view_chart_line_linechart);

        mLineChart.setNoDataText("没有数据");
    }

    public void setData(AnalysisHistoryResponse response) {
        showLine(response);
    }


    /**
     * ----------------      折线图
     */
    private void showLine(AnalysisHistoryResponse response) {
        try {
            mLineChart.clear();

            //设置手势滑动事件
            //mLineChart.setOnChartGestureListener(new onc);
            //设置数值选择监听
            //mLineChar.setOnChartValueSelectedListener(this);
            //后台绘制
            mLineChart.setDrawGridBackground(false);

            //设置描述文本
            mLineChart.getDescription().setEnabled(false);

            //设置支持触控手势
            mLineChart.setTouchEnabled(true);

            //设置缩放
            mLineChart.setDragEnabled(true);
            //设置推动
            mLineChart.setScaleEnabled(true);

            //如果禁用,扩展可以在x轴和y轴分别完成
            mLineChart.setPinchZoom(true);


            mLineChart.setDragEnabled(false);
            mLineChart.setScaleEnabled(false);
            mLineChart.setDrawGridBackground(false);

            mLineChart.setDrawBorders(false);


            /**
             * --------------------------------
             */
            setLineData(response);


            /**
             * ------------  设置坐标轴
             *
             */
            LogUtils.w("设置 坐标轴 --------- ");
            LimitLine llXAxis = new LimitLine(10f, "时间");

            //设置线宽
            llXAxis.setLineWidth(1.5f);
            //
            llXAxis.enableDashedLine(10f, 10f, 0f);
            //设置
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);

            /**
             * ---------  设置x轴
             */
            LogUtils.w("设置x轴 --------- ");
            XAxis xAxis = mLineChart.getXAxis();
            xAxis.enableGridDashedLine(10f, 10f, 0f);
            //设置x轴的最大值
            //xAxis.setAxisMaximum(100f);
            //设置x轴的最小值
            //xAxis.setAxisMinimum(0f);

            xAxis.setValueFormatter(new IAxisValueFormatter() {

                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return xName.get((int) Math.abs(value) % xName.size());
                }
            });

            //---------------------
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴在折线图的底部
            xAxis.setTextSize(12f);//设置X轴的坐标字体的大小
            xAxis.setTextColor(0xff999999);//设置X轴的坐标字体的颜色
            xAxis.setDrawAxisLine(true);//设置显示底部X轴
            xAxis.setLabelRotationAngle(-80);

            xAxis.setGridColor(mContext.getResources().getColor(R.color.transparent));

            //防止X轴数据重复
            xAxis.setGranularity(1f);

//        LimitLine ll1 = new LimitLine(150f, "优秀");
//        ll1.setLineWidth(4f);
//        ll1.enableDashedLine(10f, 10f, 0f);
//        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
//        ll1.setTextSize(10f);
//
//        LimitLine ll2 = new LimitLine(30f, "不及格");
//        ll2.setLineWidth(4f);
//        ll2.enableDashedLine(10f, 10f, 0f);
//        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//        ll2.setTextSize(10f);

            /**
             * -------------------  Y轴
             */
            LogUtils.w("设置 Y轴 --------- ");

            YAxis leftAxis = mLineChart.getAxisLeft();
            //重置所有限制线,以避免重叠线
            leftAxis.removeAllLimitLines();
            //设置优秀线
//        leftAxis.addLimitLine(ll1);
            //设置及格线
//        leftAxis.addLimitLine(ll2);
            //y轴最大
            //leftAxis.setAxisMaximum(200f);
            //y轴最小
            //leftAxis.setAxisMinimum(0f);

            leftAxis.enableGridDashedLine(10f, 10f, 0f);
            //leftAxis.setDrawZeroLine(false);

            // 限制数据(而不是背后的线条勾勒出了上面)
            leftAxis.setDrawLimitLinesBehindData(true);
            mLineChart.getAxisRight().setEnabled(false);
            leftAxis.setDrawAxisLine(false);//设置显示y轴

            MyMarkerView myMarkerView = new MyMarkerView(getContext(), R.layout.chart_marker);
            //一定要设置这个玩意，不然到点击到最边缘的时候不会自动调整布局
            myMarkerView.setChartView(mLineChart);
            myMarkerView.setTextSize(20);

            //
            myMarkerView.setTextColor(ContextCompat.getColor(getContext(), R.color.main_color));
            mLineChart.setMarker(myMarkerView);

            //设置是否显示 图例标签
            mLineChart.getLegend().setEnabled(true);

            mLineChart.notifyDataSetChanged();
            mLineChart.invalidate();
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }


    //传递数据集
    private void setLineData(AnalysisHistoryResponse response) {
        try {
            List<AnalysisHistoryResponse.YDatasBean> yDatasList = response.getYDatas();
            List<Long> xTimeList = response.getXTimes();

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();

            /**
             * -----------------------------------------
             */
            LogUtils.w("yDatas == " + yDatasList.size() + ", xTimes == " + xTimeList.size());

            if (yDatasList != null) {
                for (int i = 0; i < yDatasList.size(); i++) {
                    AnalysisHistoryResponse.YDatasBean yDatasBean = yDatasList.get(i);
                    List<AnalysisHistoryResponse.YDatasBean.StatisticsBean> statisticList = yDatasBean.getStatistics();

                    xName = new ArrayList<>();

                    LogUtils.w("外层循环 i == " + i);


                    List<Float> values = new ArrayList<>();

                    List<Integer> colors = new ArrayList<>();

                    for (int j = 0; j < xTimeList.size(); j++) {
                        if (j > xTimeList.size() - 5) {
                            colors.add(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[2]));
                        } else {
                            colors.add(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[i % 4]));
                        }

                        Long aLong = xTimeList.get(j);
                        //xName.add(DateUtils.stamp2String(aLong, "yyyy-MM-dd"));
                        //yyyy-MM-dd HH:mm:ss
                        xName.add(DateUtils.stamp2String(aLong, "HH:mm:ss"));
                        float yValue = 0;

                        try {
                            yValue = StringUtils.toFloat(statisticList.get(j).getAverageValue());

                            LogUtils.w("i == " + i + "  ，  x = " + DateUtils.stamp2String(aLong, "HH:mm:ss")
                                    + " ，  j== " + j + "  ， y = " + yValue + " ,  statisticList== " + statisticList.size()
                                    + ",  AverageValue == " + statisticList.get(j).getAverageValue());
                        } catch (Exception e) {
                            LogUtils.w("j== " + j + ", 异常 ： " + e.toString());
                        }

                        values.add(yValue);
                    }

                    LogUtils.w("创建数据集 ------------- values == " + values.size());
                    // 创建一个数据集,并给它一个类型
                    //lineDataSet = new LineDataSet(values, yDatasBean.getAttributeName());
                    /**
                     * ----------------------------------
                     * 找出最大值和最小值，然后添加icon
                     * ------------------------------------------
                     */
                    float mMaxData = Collections.max(values);
                    float mMinData = Collections.min(values);
                    ArrayList<Entry> yVals1 = new ArrayList<Entry>();
                    for (int m = 0; m < values.size(); m++) {
                        float data = values.get(m);
                        if (data == mMaxData && mMaxData != mMinData && !isMax) {

                            if (yDatasList.size() == 1) {
                                RectangleDrawable rectangleDrawable = new RectangleDrawable(mContext);
                                rectangleDrawable.setmIsUp(true);
                                rectangleDrawable.setmBackGroundColor(ContextCompat.getColor(mContext, R.color.color_green_alpa));
                                rectangleDrawable.setmText(df.format(mMaxData));
                                yVals1.add(new Entry(m, values.get(m), rectangleDrawable));
                                isMax = true;
                            } else {
                                yVals1.add(new Entry(m, values.get(m)));
                            }

//                values3.add(new Entry(i, valList.get(i), ContextCompat.getDrawable(mContext,R.drawable.star)));
                        } else if (data == mMinData && mMaxData != mMinData && !isMin) {

                            if (yDatasList.size() == 1) {
                                RectangleDrawable rectangleDrawable = new RectangleDrawable(mContext);
                                rectangleDrawable.setmIsUp(false);
                                rectangleDrawable.setmBackGroundColor(ContextCompat.getColor(mContext, R.color.color_red_alpa));
                                rectangleDrawable.setmText(df.format(mMinData));
                                yVals1.add(new Entry(m, values.get(m), rectangleDrawable));
                                isMin = true;
                            } else {
                                yVals1.add(new Entry(m, values.get(m)));
                            }

                        } else {
                            yVals1.add(new Entry(m, values.get(m)));
                        }
                        LogUtils.w(" nnnnnnnnnnnnnnnnn data== " + data + " ,mMaxData ==" + mMaxData + ", mMinData== " + mMinData);
                    }

                    lineDataSet = new LineDataSet(yVals1, yDatasBean.getAttributeName());

                    // 在这里设置线
                    //lineDataSet.enableDashedLine(10f, 5f, 0f);
                    //lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);
                    lineDataSet.setCircleColor(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[i % 4]));

                    //设置折线图的宽度
                    //设置折线图的宽度
                    lineDataSet.setLineWidth(1.5f);
                    //设置点击折线图上点的时候的颜色
                    lineDataSet.setHighLightColor(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[i % 4]));//设置点击折线图上点的时候的颜色
                    lineDataSet.setColor(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[i % 4]));//设置折线图的颜色

                    lineDataSet.setCircleRadius(3f);//设置折线图的点的圆的半径
                    lineDataSet.setCircleColor(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[i % 4]));//设置折线图的点的颜色
                    //   set.setCircleHoleColor(0xffffffff);//设置折线图的圆的中心的颜色
                    lineDataSet.setValueTextSize(14);//设置折线图上点的字体的大小
                    lineDataSet.setValueTextColor(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[i % 4]));//

                    lineDataSet.setCircleRadius(3f);
                    lineDataSet.setDrawCircleHole(false);
                    lineDataSet.setValueTextSize(9f);

                    lineDataSet.setDrawValues(false);
                    lineDataSet.setFormLineWidth(1f);
                    //lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
                    lineDataSet.setFormSize(15.f);




                    //设置底部填充颜色
                    if (Utils.getSDKInt() >= 18) {
                        // 填充背景只支持18以上
                        //Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_launcher);
                        //lineDataSet.setFillDrawable(drawable);
                        //lineDataSet.setFillColor(getResources().getColor(AnalysisHistoryFragment.lineColorArr[i % 4]));
                        lineDataSet.setFillDrawable(ContextCompat.getDrawable(getContext(), R.drawable.fade_green));
                    } else {
                        lineDataSet.setFillColor(getResources().getColor(StatisticAnalysisActivityNew.lineColorArr[i % 4]));
                    }
                    //设置曲线为圆滑曲线(不设置则为折线)
                    lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

                    if (yDatasList.size() == 1) {
                        //设 置底部是否填充颜色
                        lineDataSet.setDrawFilled(true);
                    } else {
                        lineDataSet.setDrawFilled(false);
                    }


                    lineDataSet.setDrawCircles(false);//在点上画圆 默认true

                    lineDataSet.setHighlightEnabled(true);

                    //lineDataSet.setColors(colors);

                    //添加数据集
                    dataSets.add(lineDataSet);

                    LogUtils.w("添加数据集 -------------  end ------------------------------");
                }

                //----------

            }

            LogUtils.w("添加数据集 ------------- ");
            LineData lineData = new LineData(dataSets);//这里可以添加多个数据集
            mLineChart.setData(lineData);

            LogUtils.w("刷新 图表 ------------- ");

        } catch (Exception e) {
            LogUtils.w(e.toString());
        }


    }
}
