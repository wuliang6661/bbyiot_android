package com.baibeiyun.bbyiot.module.mine.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.DevicePropertyResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceResponse;
import com.baibeiyun.bbyiot.model.Response.HisAlarmChartResponse;
import com.baibeiyun.bbyiot.model.Response.HisAlarmListResponse;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.mine.adapter.BaojingAdapter;
import com.baibeiyun.bbyiot.module.mine.contract.MonitoringAlarmContract;
import com.baibeiyun.bbyiot.module.mine.presenter.MonitoringAlarmPresenter;
import com.baibeiyun.bbyiot.module.mine.ui.analysis.StatisticAnalysisActivityNew;
import com.baibeiyun.bbyiot.module.mine.view.DateSelectUtil22;
import com.baibeiyun.bbyiot.module.mine.view.SelectGroupDialog;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.ListUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.NOScrollListView;
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

import butterknife.BindView;
import butterknife.OnClick;

public class MonitoringAlarmActivity extends BaseActivity<MonitoringAlarmPresenter> implements MonitoringAlarmContract.View {

    @BindView(R.id.act_monitoring_alarm_tv_real_baojing)
    TextView tv_real_baojing;

    @BindView(R.id.act_monitoring_alarm_view_real_baojing)
    View view_real_baojing;

    @BindView(R.id.act_monitoring_alarm_tv_history_baojing)
    TextView tv_history_baojing;

    @BindView(R.id.act_monitoring_alarm_view_history_baojing)
    View view_history_baojing;


    @BindView(R.id.tv_analysis_group_name)
    TextView tv_analysis_group_name;

    @BindView(R.id.tv_analysis_device_name)
    TextView tv_analysis_device_name;

    @BindView(R.id.tv_analysis_property_name)
    TextView tv_analysis_property_name;


    @BindView(R.id.tv_analysis_start_time)
    TextView tv_analysis_start_time;

    @BindView(R.id.ll_date)
    ViewGroup ll_date;

    @BindView(R.id.tv_analysis_end_time)
    TextView tv_analysis_end_time;

    @BindView(R.id.tv_x_hint)
    TextView tv_x_hint;
    @BindView(R.id.tv_y_hint)
    TextView tv_y_hint;

    @BindView(R.id.act_monitoring_alarm_barchart)
    BarChart mBarChart;

    @BindView(R.id.act_monitoring_alarm_listview)
    NOScrollListView listView;

    @BindView(R.id.layout_analysis_select_condition_rl_property_name)
    ViewGroup rl_property_name;
    private String endDate;
    private String startDate;
    private int groupId = -1;
    private int deciveID = -1;
    private SelectGroupDialog selectDeviceDialog;
    private String deviceName;
    private double lastDevicePosition;
    private SelectGroupDialog selectGroupDialog;
    private String groupName;
    private double lastGroupPosition;
    private int mPageType;
    private SelectGroupDialog selectPropertyDialog;
    private int attributeId;
    private String attributeName;
    private BaojingAdapter mBaojingAdapter;
    private int pageNum = 1;
    private int pageSize = 20;
    private YAxis leftAxis;
    private XAxis xAxis;
    private boolean noLoadMore = false;
    private TextView loadmore_text;
    private View loadmore_layout;

    List<HisAlarmListResponse> dataList =new ArrayList<>();
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_monitoring_alarm;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("监控报警");

        changePage(1);

        //mPresenter.getDefaultData();
        String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
        endDate = currentDate;

        long longTime = DateUtils.getTimeStamp(currentDate, "yyyy-MM-dd HH:mm:ss");
        startDate = DateUtils.stamp2String(longTime - 1 * 60 * 60 * 1000, "yyyy-MM-dd HH:mm:ss");

        tv_analysis_start_time.setText(startDate);
        tv_analysis_end_time.setText(endDate);



        View view_footer = View.inflate(this, R.layout.loadmore_footer, null);
        loadmore_text = view_footer.findViewById(R.id.loadmore_text);
        loadmore_layout = view_footer.findViewById(R.id.loadmore_layout);
        loadmore_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!noLoadMore) {
                    pageNum += 1;
                    mPresenter.getDataHisAlarmList(attributeId, deciveID, startDate, endDate, pageNum, pageSize);
                }
            }
        });
        listView.addFooterView(view_footer);

        mBaojingAdapter = new BaojingAdapter(this, R.layout.item_baojing);
        listView.setAdapter(mBaojingAdapter);

        mBarChart.setNoDataText("没有数据");
        loadmore_layout.setVisibility(View.INVISIBLE);

        mPresenter.getGroupData(true);


    }

    @OnClick({R.id.act_monitoring_alarm_rl_real_baojing, R.id.act_monitoring_alarm_rl_history_baojing

            , R.id.layout_analysis_select_condition_rl_group, R.id.layout_analysis_select_condition_rl_device_name
            , R.id.layout_analysis_select_condition_rl_property_name
            , R.id.tv_analysis_start_time, R.id.tv_analysis_end_time
    })
    void click(View view) {
        switch (view.getId()) {
            case R.id.act_monitoring_alarm_rl_real_baojing:
                changePage(1);
                break;
            case R.id.act_monitoring_alarm_rl_history_baojing:
                changePage(2);
                break;

            case R.id.layout_analysis_select_condition_rl_group:
                mPresenter.getGroupData(false);
                break;
            case R.id.layout_analysis_select_condition_rl_device_name:
                if (groupId == -1) {
                    ToastUtils.showToast("请选择分组");
                    return;
                }
                mPresenter.getDeviceData(groupId, false);
                break;

            case R.id.layout_analysis_select_condition_rl_property_name:
                if (deciveID == -1) {
                    ToastUtils.showToast("请选择设备");
                    return;
                }
                mPresenter.getRealDatasByEid(deciveID, false);
                break;

            case R.id.tv_analysis_start_time:
                if (mPageType == 1) {
                    return;
                }
                DateSelectUtil22.showDatePopu(this, "选择开始时间", new DateSelectUtil22.SelectListener() {
                    @Override
                    public void onDateSelect(String dataString) {
                        if (DateUtils.getTimeStamp(dataString, "yyyy-MM-dd HH:mm:ss") > DateUtils.getCurrentTimeStamp()) {
                            ToastUtils.showToast("起始时间不能大于当前时间");
                        } else {
                            startDate = dataString;
                            tv_analysis_start_time.setText(dataString);
                            getData();
                        }
                    }
                });
                break;

            case R.id.tv_analysis_end_time:
                if (mPageType == 1) {
                    return;
                }
                DateSelectUtil22.showDatePopu(this, "选择结束时间", new DateSelectUtil22.SelectListener() {
                    @Override
                    public void onDateSelect(String dataString) {

                        if (DateUtils.getTimeStamp(dataString, "yyyy-MM-dd HH:mm:ss") > DateUtils.getCurrentTimeStamp()) {
                            ToastUtils.showToast("结束时间不能大于当前时间");
                        } else if (DateUtils.getTimeStamp(dataString, "yyyy-MM-dd HH:mm:ss") < DateUtils.getTimeStamp(startDate, "yyyy-MM-dd HH:mm:ss")) {
                            ToastUtils.showToast("结束时间不能小于起始时间");
                        } else {
                            endDate = dataString;
                            tv_analysis_end_time.setText(dataString);

                            getData();
                        }

                    }
                });
                break;
        }
    }

    @Override
    protected MonitoringAlarmPresenter getPresenter() {
        return new MonitoringAlarmPresenter(this);
    }


    private void changePage(int page) {
        mPageType = page;
        switch (page) {
            case 1:
                view_real_baojing.setVisibility(View.VISIBLE);
                view_history_baojing.setVisibility(View.GONE);
                String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
                endDate = currentDate;

                long longTime = DateUtils.getTimeStamp(currentDate, "yyyy-MM-dd HH:mm:ss");
                startDate = DateUtils.stamp2String(longTime - 24 * 60 * 60 * 1000, "yyyy-MM-dd HH:mm:ss");

                tv_analysis_start_time.setText(startDate);
                tv_analysis_end_time.setText(endDate);
                getData();
                ll_date.setVisibility(View.GONE);
                break;

            case 2:
                view_real_baojing.setVisibility(View.GONE);
                view_history_baojing.setVisibility(View.VISIBLE);
                ll_date.setVisibility(View.VISIBLE);
                break;
        }
    }


    //分组名称
    @Override
    public void getGroupDataFinish(final List<HomeGroupsResponse> list, boolean isDefault) {
        if (isDefault) {
            if (!ListUtils.isEmpty(list)) {
                HomeGroupsResponse response = list.get(0);
                mPresenter.getDeviceData(response.getId(), true);
                groupId = response.getId();
                groupName = response.getName();

                tv_analysis_group_name.setText(groupName);
            }
            return;
        }

        //选择分组
        if (selectGroupDialog == null) {
            selectGroupDialog = new SelectGroupDialog(this);
        }
        final List<String> selectGroupBeanList = new ArrayList<>();
        for (HomeGroupsResponse homeGroupsResponse : list) {
            selectGroupBeanList.add(homeGroupsResponse.getName());
        }
        selectGroupDialog.show();
        selectGroupDialog.setData(selectGroupBeanList);
        selectGroupDialog.setOnClickLinsenter(new SelectGroupDialog.OnClickLinsenter() {
            @Override
            public void cofirm(int position) {
                try {
                    HomeGroupsResponse homeGroupsResponse = list.get(position);
                    groupId = homeGroupsResponse.getId();
                    groupName = homeGroupsResponse.getName();

                    tv_analysis_group_name.setText(groupName);

                    if (lastGroupPosition != position) {
                        deciveID = -1;
                        attributeId = -1;
                        deviceName = "";
                        attributeName = "";
                        tv_analysis_device_name.setText("请选择");
                        tv_analysis_property_name.setText("请选择");

                        mPresenter.getDeviceData(groupId, true);
                    }
                    lastGroupPosition = position;
                } catch (Exception e) {
                    LogUtils.w(e.toString());
                }

                selectGroupDialog.dismiss();
            }
        });
    }

    //设备名称
    @Override
    public void getDeviceDataFinish(final List<DeviceResponse> list, boolean isDefault) {
        //选择设备

        if (isDefault) {
            if (!ListUtils.isEmpty(list)) {
                DeviceResponse response = list.get(0);
                mPresenter.getRealDatasByEid(response.getId(), true);

                deciveID = response.getId();
                deviceName = response.getName();

                tv_analysis_device_name.setText(deviceName);
            }
            return;
        }

        final List<String> selectGroupBeanList = new ArrayList<>();
        for (DeviceResponse response : list) {
            selectGroupBeanList.add(response.getName());
        }
        if (selectDeviceDialog == null) {
            selectDeviceDialog = new SelectGroupDialog(this);
        }
        selectDeviceDialog.show();
        selectDeviceDialog.setData(selectGroupBeanList);
        selectDeviceDialog.setOnClickLinsenter(new SelectGroupDialog.OnClickLinsenter() {
            @Override
            public void cofirm(int position) {
                try {
                    DeviceResponse response = list.get(position);
                    deciveID = response.getId();
                    deviceName = response.getName();

                    tv_analysis_device_name.setText(deviceName);

                    if (lastDevicePosition != position) {
                        attributeId = -1;
                        attributeName = "";
                        tv_analysis_property_name.setText("请选择");
                        mPresenter.getRealDatasByEid(deciveID, true);
                    }
                    lastDevicePosition = position;

                } catch (Exception e) {
                    LogUtils.w(e.toString());
                }

                selectDeviceDialog.dismiss();
            }
        });
    }

    //属性名称
    @Override
    public void getRealDatasByEidFinish(final List<DevicePropertyResponse> list, boolean isDefault) {
        //选择属性

        if (isDefault) {
            if (!ListUtils.isEmpty(list)) {
                DevicePropertyResponse response = list.get(0);
                attributeId = response.getAttributeId();
                attributeName = response.getAttributeName();
                tv_analysis_property_name.setText(attributeName);
            }
            getData();
            return;
        }
        if (selectPropertyDialog == null) {
            selectPropertyDialog = new SelectGroupDialog(this);
        }
        final List<String> selectGroupBeanList = new ArrayList<>();
        for (DevicePropertyResponse response : list) {
            selectGroupBeanList.add(response.getAttributeName());
        }
        selectPropertyDialog.show();
        selectPropertyDialog.setData(selectGroupBeanList);
        selectPropertyDialog.setOnClickLinsenter(new SelectGroupDialog.OnClickLinsenter() {
            @Override
            public void cofirm(int position) {
                try {
                    DevicePropertyResponse response = list.get(position);
                    attributeId = response.getAttributeId();
                    attributeName = response.getAttributeName();
                    tv_analysis_property_name.setText(attributeName);

                    getData();
                } catch (Exception e) {
                    LogUtils.w(e.toString());
                }

                selectPropertyDialog.dismiss();
            }
        });
    }

    void getData() {
        mPresenter.getDataHisAlarmChart(attributeId, deciveID, startDate, endDate);
        mPresenter.getDataHisAlarmList(attributeId, deciveID, startDate, endDate, pageNum, pageSize);
    }


    @Override
    public void getDataHisAlarmChartFinish(List<HisAlarmChartResponse> responseList) {
        showBarChart(responseList);
    }

    @Override
    public void getDataHisAlarmListFinish(List<HisAlarmListResponse> responseList) {
        if(ListUtils.isEmpty(responseList)){
            loadmore_layout.setVisibility(View.INVISIBLE);
        }else {
            loadmore_layout.setVisibility(View.VISIBLE);
            dataList.addAll(responseList);
            if (responseList.size() < pageSize) {
                noLoadMore = true;
                loadmore_text.setText("没有更多了");
            }
        }
        mBaojingAdapter.update(dataList);
    }

    private void showBarChart(List<HisAlarmChartResponse> responseList) {
        mBarChart.clear();
        if (ListUtils.isEmpty(responseList)) {
            tv_x_hint.setVisibility(View.GONE);
            tv_y_hint.setVisibility(View.GONE);
            return;
        }
        tv_x_hint.setVisibility(View.VISIBLE);
        tv_y_hint.setVisibility(View.VISIBLE);
        try {
            /**
             * ----
             * */
            initBarChart(mBarChart);

            List<Integer> colorList = new ArrayList<>();

            final List<String> xValues = new ArrayList<>();
            List<VtDateValueBean> dateValueList = new ArrayList<>();
            for (int i = 0; i < responseList.size(); i++) {
                xValues.add(responseList.get(i).getAlarmType());
                VtDateValueBean valueBean = new VtDateValueBean();
                valueBean.xName = responseList.get(i).getAlarmType();
                valueBean.yValue = responseList.get(i).getNum();

                colorList.add(Color.parseColor(responseList.get(i).getColorType()));
                dateValueList.add(valueBean);
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

            barDataSet.setColors(colorList);
            /**
             * ----
             */
            initBarDataSet(barDataSet, dateValueList);

//        // 添加多个BarDataSet时
//        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//        dataSets.add(barDataSet);
//        BarData data = new BarData(dataSets);

            BarData data = new BarData(barDataSet);
            //设置柱子的宽度
            data.setBarWidth(0.1f);
//        data.setBarWidth(DipToPxUtils.dipToPx(getContext(), 11));

            /**
             * 设置 value 值
             */
//            data.setValueFormatter(new IValueFormatter() {
//                @Override
//                public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
//                    return FloatFormatHelper.floatFormat(value, "0.0");
//                }
//            });

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


        barDataSet.setFormLineWidth(1f);
//        barDataSet.setFormSize(11f);
//        barDataSet.setBarBorderWidth(11);

        //false不显示柱状图顶部值
        barDataSet.setDrawValues(false);


        barDataSet.setHighLightAlpha(0xff);
        barDataSet.setHighLightColor(ContextCompat.getColor(this, StatisticAnalysisActivityNew.lineColorArr[1]));
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

        MyMarkerViewBar detailsMarkerView = new MyMarkerViewBar(this, R.layout.chart_marker, 0);
        //一定要设置这个玩意，不然到点击到最边缘的时候不会自动调整布局
        detailsMarkerView.setTextSize(12);
        detailsMarkerView.setTextColor(ContextCompat.getColor(this, R.color.main_color));
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
