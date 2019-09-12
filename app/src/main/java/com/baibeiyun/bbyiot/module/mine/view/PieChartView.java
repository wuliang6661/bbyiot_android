package com.baibeiyun.bbyiot.module.mine.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.AnalysisMalfunctionResponse;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.chart.CustomMarkerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class PieChartView extends LinearLayout {

    private Activity mContext;
    private PieChart mPieChart;

    private AnalysisMalfunctionResponse dataResponse;

    public PieChartView(Context context) {
        super(context);
        initView(context, null);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = (Activity) context;

        View mRootView = LayoutInflater.from(mContext).inflate(
                R.layout.view_chart_pie, this, true);

        mPieChart = mRootView.findViewById(R.id.view_chart_pie_piechart);

        mPieChart.setNoDataText("没有数据");
    }

    public void setData(List<AnalysisMalfunctionResponse> response) {
        try {
            dataResponse = response.get(0);
            initPieChart();
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }


    /**
     * ----------      饼状图----------------------
     */
    void initPieChart() {

        // 设置 pieChart 图表基本属性
        mPieChart.setUsePercentValues(true);            // 显示成百分比 ---> item中 用百分比的形式去显示

        mPieChart.getDescription().setEnabled(false);    //设置pieChart图表的描述
        mPieChart.setBackgroundColor(Color.WHITE);      //设置pieChart图表背景色
        mPieChart.setExtraOffsets(5, 10, 60, 10);        //设置pieChart图表上下左右的偏移，类似于外边距
        mPieChart.setDragDecelerationFrictionCoef(0.95f);//设置pieChart图表转动阻力摩擦系数[0,1]
        mPieChart.setRotationAngle(0);                   //设置pieChart图表起始角度
        mPieChart.setRotationEnabled(true);              //设置pieChart图表是否可以手动旋转
        mPieChart.setHighlightPerTapEnabled(true);       //设置piecahrt图表点击Item高亮是否可用
        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);// 设置pieChart图表展示动画效果


        // 设置 pieChart 图表Item文本属性
        mPieChart.setDrawEntryLabels(false);              //设置pieChart是否只显示饼图上百分比不显示文字（true：下面属性才有效果）
        mPieChart.setEntryLabelColor(Color.WHITE);       //设置pieChart图表文本字体颜色
        //pie_chart.setEntryLabelTypeface(mTfRegular);     //设置pieChart图表文本字体样式
        mPieChart.setEntryLabelTextSize(10f);            //设置pieChart图表文本字体大小

        /**
         * 设置 pieChart 内部圆环属性
         *
         * */
        mPieChart.setDrawHoleEnabled(false);              //是否显示PieChart内部圆环(true:下面属性才有意义)
        mPieChart.setHoleRadius(80f);                    //设置PieChart内部圆的半径(这里设置28.0f)
        mPieChart.setTransparentCircleRadius(80f);       //设置PieChart内部透明圆的半径(这里设置31.0f)
        mPieChart.setTransparentCircleColor(Color.BLACK);//设置PieChart内部透明圆与内部圆间距(31f-28f)填充颜色
        mPieChart.setTransparentCircleAlpha(50);         //设置PieChart内部透明圆与内部圆间距(31f-28f)透明度[0~255]数值越小越透明
        mPieChart.setHoleColor(Color.WHITE);             //设置PieChart内部圆的颜色
        mPieChart.setDrawCenterText(true);               //是否绘制PieChart内部中心文本（true：下面属性才有意义）

        //pie_chart.setCenterTextTypeface(mTfLight);       //设置PieChart内部圆文字的字体样式

        mPieChart.setCenterText("");                 //设置PieChart内部圆文字的内容
        mPieChart.setCenterTextSize(10f);                //设置PieChart内部圆文字的大小
        mPieChart.setCenterTextColor(Color.RED);         //设置PieChart内部圆文字的颜色

        // pieChart添加数据
        setData();

        // 获取pieCahrt图列  建议设置为true
        Legend legend = mPieChart.getLegend();
        legend.setEnabled(true);                    //是否启用图列（true：下面属性才有意义）
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setForm(Legend.LegendForm.DEFAULT); //设置图例的形状
        legend.setFormSize(10);                      //设置图例的大小
        legend.setFormToTextSpace(10f);              //设置每个图例实体中标签和形状之间的间距
        legend.setDrawInside(false);
        legend.setWordWrapEnabled(true);              //设置图列换行(注意使用影响性能,仅适用legend位于图表下面)
        legend.setXEntrySpace(10f);                  //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        legend.setYEntrySpace(8f);                  //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        legend.setYOffset(0f);                      //设置比例块Y轴偏移量
        legend.setTextSize(14f);                      //设置图例标签文本的大小
        //legend.setTextColor(Color.parseColor("#8E8E8E"));//设置图例标签文本的颜色
        legend.setTextColor(Color.parseColor("#ff9933"));//设置图例标签文本的颜色


        //pieChart 选择监听
        mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });


        //设置MARKERVIEW
        CustomMarkerView mv = new CustomMarkerView(mContext, R.layout.chart_marker);
        mv.setChartView(mPieChart);
        mPieChart.setMarker(mv);

    }


    /**
     * 设置饼图的数据
     */
    private void setData() {
        if (dataResponse == null) {
            return;
        }
        LogUtils.w("设置饼状图数据");

        ArrayList<PieEntry> pieEntryList = new ArrayList<PieEntry>();
        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(Color.parseColor("#52BE2E"));
        colors.add(Color.parseColor("#FF5554"));

        //饼图实体 PieEntry
        PieEntry zhengchang = new PieEntry(dataResponse.getCollectCount() > 0 ? dataResponse.getCollectCount() : 0, "正常次数:  " + dataResponse.getCollectCount());
        PieEntry gaojing = new PieEntry(dataResponse.getFaultCount() > 0 ? dataResponse.getFaultCount() : 0, "故障次数:  " + dataResponse.getFaultCount());

        pieEntryList.add(zhengchang);
        pieEntryList.add(gaojing);


        //饼状图数据集 PieDataSet
        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "");

        pieDataSet.setSliceSpace(1f);           //设置饼状Item之间的间隙
        pieDataSet.setSelectionShift(10f);      //设置饼状Item被选中时变化的距离
        pieDataSet.setColors(colors);           //为DataSet中的数据匹配上颜色集(饼图Item颜色)

        //最终数据 PieData  --> 设置是否显示百分比
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);            //设置是否显示数据实体(百分比，true:以下属性才有意义)
        pieData.setValueTextColor(Color.parseColor("#4645D0"));  //设置所有DataSet内数据实体（百分比）的文本颜色
        pieData.setValueTextSize(12f);          //设置所有DataSet内数据实体（百分比）的文本字体大小
        //pieData.setValueTypeface(mTfLight);     //设置所有DataSet内数据实体（百分比）的文本字体样式
        pieData.setValueFormatter(new PercentFormatter());//设置所有DataSet内数据实体（百分比）的文本字体格式
        mPieChart.setData(pieData);

        //折线显示 百分比
        // 当ValuePosits为OutsiDice时，指示偏移为切片大小的百分比
        pieDataSet.setValueLinePart1OffsetPercentage(100.f);
        //当值位置为外边线时，表示线的前半段长度。
        pieDataSet.setValueLinePart1Length(0.4f);
        // 当值位置为外边线时，表示线的后半段长度。
        pieDataSet.setValueLinePart2Length(0.8f);
        // 当值位置为外边线时，表示线的颜色。
        pieDataSet.setValueLineColor(Color.parseColor("#4645D0"));
        //设置Y值的位置是在圆内还是圆外
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        // 撤销所有的亮点
        mPieChart.highlightValues(null);

        mPieChart.invalidate();                    //将图表重绘以显示设置的属性和数据
    }

}