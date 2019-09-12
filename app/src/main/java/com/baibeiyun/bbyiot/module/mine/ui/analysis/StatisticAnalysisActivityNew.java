package com.baibeiyun.bbyiot.module.mine.ui.analysis;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.AnalysisGatherResponse;
import com.baibeiyun.bbyiot.model.Response.AnalysisHistoryResponse;
import com.baibeiyun.bbyiot.model.Response.AnalysisMalfunctionResponse;
import com.baibeiyun.bbyiot.model.Response.DefaultDeviceResponse;
import com.baibeiyun.bbyiot.model.Response.DevicePropertyResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceResponse;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.model.event.AnalysisEvent;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.mine.contract.AnalysisHistoryContract;
import com.baibeiyun.bbyiot.module.mine.presenter.AnalysisHistoryPresenter;
import com.baibeiyun.bbyiot.module.mine.view.BarChartView;
import com.baibeiyun.bbyiot.module.mine.view.DateSelectUtil22;
import com.baibeiyun.bbyiot.module.mine.view.LineChartView;
import com.baibeiyun.bbyiot.module.mine.view.PieChartView;
import com.baibeiyun.bbyiot.module.mine.view.SelectGroupDialog;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.ListUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class StatisticAnalysisActivityNew extends BaseActivity<AnalysisHistoryPresenter> implements AnalysisHistoryContract.View {

    public static int[] lineColorArr = {R.color.line_color_01,
            R.color.line_color_02, R.color.line_color_03, R.color.line_color_04};
    public final static String DECIVE_ID = "decive_id";

    @BindView(R.id.tv_analysis_group_name)
    TextView tv_analysis_group_name;

    @BindView(R.id.tv_analysis_device_name)
    TextView tv_analysis_device_name;

    @BindView(R.id.tv_analysis_property_name)
    TextView tv_analysis_property_name;

    @BindView(R.id.layout_analysis_select_condition_rl_property_name)
    ViewGroup rl_property_name;


    @BindView(R.id.tv_analysis_start_time)
    TextView tv_analysis_start_time;

    @BindView(R.id.tv_analysis_end_time)
    TextView tv_analysis_end_time;

    //10分钟
    @BindView(R.id.layout_analysis_tip_tv_minute)
    TextView tv_minute;
    //小时
    @BindView(R.id.layout_analysis_tip_tv_hour)
    TextView tv_hour;

    //线
    @BindView(R.id.act_statistic_analysis_view_real_data)
    View view_real_data;

    @BindView(R.id.act_statistic_analysis_view_history_data)
    View view_history_data;

    @BindView(R.id.act_statistic_analysis_view_info)
    View view_info;
    @BindView(R.id.act_statistic_analysis_view_map)
    View view_map;

    @BindView(R.id.act_analysis_history_ll_chart)
    ViewGroup ll_chart;


    int groupId = -1;
    int deciveID = -1;
    int attributeId = -1;
    String startDate = "";
    String endDate = "";

    String groupName = "";
    String deviceName = "";
    String attributeName = "";

    int mode = 0;

    int mChartType = 1;
    private SelectGroupDialog selectPropertyDialog;
    private SelectGroupDialog selectDeviceDialog;
    private SelectGroupDialog selectGroupDialog;
    private double lastGroupPosition;
    private double lastDevicePosition;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_statistic_analysis_new;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setActionBarTitle("统计分析");

        changeType(1);
        //mPresenter.getDefaultData();
        String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
        endDate = currentDate;

        long longTime = DateUtils.getTimeStamp(currentDate, "yyyy-MM-dd HH:mm:ss");
        startDate = DateUtils.stamp2String(longTime - 60 * 60 * 1000, "yyyy-MM-dd HH:mm:ss");

        tv_analysis_start_time.setText(startDate);
        tv_analysis_end_time.setText(endDate);

        mPresenter.getGroupData(true);
    }

    @Override
    protected AnalysisHistoryPresenter getPresenter() {
        return new AnalysisHistoryPresenter(this);
    }

    @OnClick({R.id.act_statistic_analysis_rl_real_data, R.id.act_statistic_analysis_rl_history_data,
            R.id.act_statistic_analysis_rl_map, R.id.act_statistic_analysis_rl_info,
            R.id.layout_analysis_tip_tv_minute, R.id.layout_analysis_tip_tv_hour

            , R.id.layout_analysis_select_condition_rl_group, R.id.layout_analysis_select_condition_rl_device_name
            , R.id.layout_analysis_select_condition_rl_property_name
            , R.id.tv_analysis_start_time, R.id.tv_analysis_end_time
    })
    void click(View view) {
        switch (view.getId()) {
            case R.id.act_statistic_analysis_rl_real_data:
                changeType(1);

                break;

            case R.id.act_statistic_analysis_rl_history_data:
                changeType(2);

                break;

            case R.id.act_statistic_analysis_rl_info:
                changeType(3);

                break;

            case R.id.act_statistic_analysis_rl_map:
                changeType(4);

                break;


            case R.id.layout_analysis_tip_tv_minute:
                checkModeType(0);
                break;
            case R.id.layout_analysis_tip_tv_hour:
                checkModeType(1);
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


    private void changeType(int type) {
        try {
            mChartType = type;
            getData();
            if (type == 3) {
                rl_property_name.setVisibility(View.VISIBLE);
            } else {
                rl_property_name.setVisibility(View.GONE);
            }

            switch (type) {
                case 1:
                    view_real_data.setVisibility(View.VISIBLE);
                    view_history_data.setVisibility(View.GONE);
                    view_info.setVisibility(View.GONE);
                    view_map.setVisibility(View.GONE);

                    break;

                case 2:
                    view_real_data.setVisibility(View.GONE);
                    view_history_data.setVisibility(View.VISIBLE);
                    view_info.setVisibility(View.GONE);
                    view_map.setVisibility(View.GONE);
                    break;

                case 3:
                    view_real_data.setVisibility(View.GONE);
                    view_history_data.setVisibility(View.GONE);
                    view_info.setVisibility(View.VISIBLE);
                    view_map.setVisibility(View.GONE);

                    break;
                case 4:
                    view_real_data.setVisibility(View.GONE);
                    view_history_data.setVisibility(View.GONE);
                    view_info.setVisibility(View.GONE);
                    view_map.setVisibility(View.VISIBLE);
                    break;

            }
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    @SuppressLint("ResourceAsColor")
    private void checkModeType(int type) {
        String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
        endDate = currentDate;

        long longTime = DateUtils.getTimeStamp(currentDate, "yyyy-MM-dd HH:mm:ss");


        //type==1  分钟
        if (type == 0) {
            tv_minute.setBackgroundResource(R.drawable.shape_analysis_left_chcek_bg);
            tv_minute.setTextColor(getResources().getColor(R.color.white));

            tv_hour.setTextColor(getResources().getColor(R.color.main_color));
            tv_hour.setBackgroundResource(R.drawable.shape_analysis_right_nor_bg);

            startDate = DateUtils.stamp2String(longTime - 60 * 60 * 1000, "yyyy-MM-dd HH:mm:ss");
        } else if (type == 1) {
            //小时
            tv_minute.setBackgroundResource(R.drawable.shape_analysis_left_nor_bg);
            tv_minute.setTextColor(getResources().getColor(R.color.main_color));

            tv_hour.setTextColor(getResources().getColor(R.color.white));
            tv_hour.setBackgroundResource(R.drawable.shape_analysis_right_chcek_bg);

            startDate = DateUtils.stamp2String(longTime - 24 * 60 * 60 * 1000, "yyyy-MM-dd HH:mm:ss");
        }

        tv_analysis_start_time.setText(startDate);
        tv_analysis_end_time.setText(endDate);

        mode = type;

        getData();
    }

    /**
     * 请求数据
     */
    public void getData() {
        switch (mChartType) {
            case 1:
                mPresenter.getDataHisStatisticChart(deciveID, startDate, endDate, mode);
                break;
            case 2:
                mPresenter.getTrendAnalysisChart(deciveID, startDate, endDate, mode);
                break;

            case 3:
                mPresenter.getDataCollectStatisticChart(attributeId + "", deciveID, startDate, endDate, mode);
                break;
            case 4:
                mPresenter.getFaultAnalysisChart(deciveID, startDate, endDate, mode);
                break;
        }
    }

    //获取 默认数据
    @Override
    public void getDefaultDataFinish(DefaultDeviceResponse response) {
        try {

            groupId = response.getGroupId();
            deciveID = response.getDeviceId();

            attributeId = StringUtils.toInt(response.getAttributeId());

            tv_analysis_group_name.setText(response.getGroupName());
            tv_analysis_device_name.setText(response.getDeviceName());

            String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
            endDate = currentDate;

            long longTime = DateUtils.getTimeStamp(currentDate, "yyyy-MM-dd HH:mm:ss");
            startDate = DateUtils.stamp2String(longTime - 60 * 60 * 1000, "yyyy-MM-dd HH:mm:ss");

            tv_analysis_start_time.setText(startDate);
            tv_analysis_end_time.setText(endDate);

            groupName = response.getGroupName();
            deviceName = response.getDeviceName();
            attributeName = response.getAttributeName();

            getData();

        } catch (Exception e) {
            LogUtils.w(e.toString());
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
                    if (mChartType != 3) {
                        getData();
                    }
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


    //历史分析
    @Override
    public void getDataHisStatisticChartFinish(AnalysisHistoryResponse response) {
        ll_chart.removeAllViews();

        LineChartView lineChartView = new LineChartView(this);
        lineChartView.setData(response);
        ll_chart.addView(lineChartView);
    }

    //趋势分析
    @Override
    public void getTrendAnalysisChartFinish(AnalysisHistoryResponse response) {
        ll_chart.removeAllViews();

        LineChartView lineChartView = new LineChartView(this);
        lineChartView.setData(response);
        ll_chart.addView(lineChartView);
    }

    //采集
    @Override
    public void getDataCollectStatisticChartFinish(AnalysisGatherResponse response) {
        ll_chart.removeAllViews();

        BarChartView barChartView = new BarChartView(this);
        barChartView.setData(response);
        ll_chart.addView(barChartView);
    }

    //故障
    @Override
    public void getFaultAnalysisChartFinish(List<AnalysisMalfunctionResponse> response) {
        ll_chart.removeAllViews();

        PieChartView pieChartView = new PieChartView(this);
        pieChartView.setData(response);
        ll_chart.addView(pieChartView);
    }


    @Subscribe
    public void query(AnalysisEvent event) {
        if (event != null) {
            tv_analysis_group_name.setText(event.getGroupName());
            tv_analysis_device_name.setText(event.getDeviceName());
            tv_analysis_property_name.setText(event.getAttributeName());

            tv_analysis_start_time.setText(event.getStartDate());
            tv_analysis_end_time.setText(event.getEndDate());


            groupId = event.getGroupId();
            deciveID = event.getDeciveID();
            attributeId = event.getAttributeId();

            groupName = event.getGroupName();
            deviceName = event.getDeviceName();
            attributeName = event.getAttributeName();

            startDate = event.getStartDate();
            endDate = event.getEndDate();
        }

        getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
