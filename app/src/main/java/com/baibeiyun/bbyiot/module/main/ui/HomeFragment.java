package com.baibeiyun.bbyiot.module.main.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.DeviceNumResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceStatusResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsActiveResponse;
import com.baibeiyun.bbyiot.model.Response.MessageNumResposne;
import com.baibeiyun.bbyiot.model.event.CommonEvent;
import com.baibeiyun.bbyiot.model.event.EventConstant;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.home.ui.DeviceStateActivity;
import com.baibeiyun.bbyiot.module.home.ui.MessageActivity;
import com.baibeiyun.bbyiot.module.login.ui.LoginActivity;
import com.baibeiyun.bbyiot.module.main.adapter.DataManagementAdapter;
import com.baibeiyun.bbyiot.module.main.adapter.DeviceAllAdapter;
import com.baibeiyun.bbyiot.module.main.adapter.DeviceTestingAdapter;
import com.baibeiyun.bbyiot.module.main.contract.HomeContract;
import com.baibeiyun.bbyiot.module.main.presenter.HomePresenter;
import com.baibeiyun.bbyiot.module.seancode.ScanCodeActivity;
import com.baibeiyun.bbyiot.module.search.SearchActity;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.BaseTimer;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.NOScrollGirdview;
import com.baibeiyun.bbyiot.view.NOScrollListView;
import com.baibeiyun.bbyiot.view.chart.HomeCircleView;
import com.baibeiyun.bbyiot.view.pullableview.PullToRefreshLayout;
import com.baibeiyun.bbyiot.view.pullableview.PullableGridView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, View.OnClickListener {

    @BindView(R.id.frg_home_pulltorefreshlayout)
    PullToRefreshLayout pullToRefreshLayout;


    @BindView(R.id.frg_home_girdview_device_hot)
    PullableGridView girdview_device_hot;

    //@BindView(R.id.frg_home_listview_device_testing)
    NOScrollListView listview_device_testing;

    //@BindView(R.id.frg_home_girdview_device_all)
    NOScrollGirdview girdview_device_all;

    NOScrollGirdview girdview_device_data;


    //@BindView(R.id.view_home_head_homecircle_view)
    HomeCircleView homecircle_view;


    //在线 比例
    //@BindView(R.id.view_header_home_tv_percent)
    TextView tv_percent;

    //@BindView(R.id.view_header_home_tv_zhengchang)
    TextView tv_zhengchang;

    //@BindView(R.id.view_header_home_tv_lixian)
    TextView tv_lixian;

    //@BindView(R.id.view_header_home_tv_gaojing)
    TextView tv_gaojing;
    //@BindView(R.id.view_home_head_tv_stat_count)
    TextView tv_stat_count;
    private DeviceTestingAdapter deviceTestingAdapter;
    private DeviceAllAdapter deviceAllAdapter;
    private DataManagementAdapter dataManagerAdapter;
//    private DeviceHotAdapter deviceHotAdapter;

    private int pageNum = 1;
    private int pageSize = 10;

    private int pageCount = 0;


    private List<GoodsActiveResponse> dataList = new ArrayList<>();
    public static int KEY_SCAN_REQUEST_CODE = 101;
    private TextView tv_sean_number;
    private View view_dit;
    private int sum;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_home_new;
    }

    @Override
    protected void initViewsAndEvents() {

        pullToRefreshLayout.setCanPullUp(true);
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                mPresenter.getDeviceStatusChart(true, false);
                //mPresenter.getDeviceNumList(true);

                pageNum = 1;

            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                if (pageCount < pageSize) {
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.NO_LOAD_MORE);
                } else {
                    pageNum++;
//                    mPresenter.getHostDecive(pageNum, pageSize, false, true);
                }
            }
        });
        /**
         * -  --- 头部控件
         */
        View view_header_home = View.inflate(getActivity(), R.layout.view_header_home_listview_head, null);

        listview_device_testing = view_header_home.findViewById(R.id.frg_home_listview_device_testing);
        view_dit = view_header_home.findViewById(R.id.view_home_head_view_dit);
        girdview_device_all = view_header_home.findViewById(R.id.frg_home_girdview_device_all);
//        girdview_device_data = view_header_home.findViewById(R.id.frg_home_girdview_data_manager);
        tv_sean_number = view_header_home.findViewById(R.id.view_home_head_tv_sean_number);

        homecircle_view = view_header_home.findViewById(R.id.view_home_head_homecircle_view);
        tv_percent = view_header_home.findViewById(R.id.view_header_home_tv_percent);
        tv_zhengchang = view_header_home.findViewById(R.id.view_header_home_tv_zhengchang);
        tv_lixian = view_header_home.findViewById(R.id.view_header_home_tv_lixian);
        tv_gaojing = view_header_home.findViewById(R.id.view_header_home_tv_gaojing);
        tv_stat_count = view_header_home.findViewById(R.id.view_home_head_tv_stat_count);

        //R.id.view_home_head_ll_device_lx, R.id.view_home_head_ll_device_zc, R.id.view_home_head_ll_device_gj,
        //            R.id.view_home_head_ll_left_massage, R.id.view_head_home_rl_search
        view_header_home.findViewById(R.id.view_home_head_ll_device_lx).setOnClickListener(this);
        view_header_home.findViewById(R.id.view_home_head_ll_device_zc).setOnClickListener(this);
        view_header_home.findViewById(R.id.view_home_head_ll_device_gj).setOnClickListener(this);
        view_header_home.findViewById(R.id.view_home_head_ll_left_massage).setOnClickListener(this);
        view_header_home.findViewById(R.id.view_head_home_rl_search).setOnClickListener(this);
        view_header_home.findViewById(R.id.view_head_home_listview_ll_more).setOnClickListener(this);
//        view_header_home.findViewById(R.id.view_head_home_listview_ll_hot).setOnClickListener(this);
        view_header_home.findViewById(R.id.view_home_head_ll_right_sean).setOnClickListener(this);


        girdview_device_hot.addHeaderView(view_header_home);


        //---------------


        deviceAllAdapter = new DeviceAllAdapter(getActivity());
        dataManagerAdapter = new DataManagementAdapter(getActivity());
//        deviceHotAdapter = new DeviceHotAdapter(getActivity());
        deviceTestingAdapter = new DeviceTestingAdapter(getActivity());

        //设置最后一条有分割线
        listview_device_testing.setFooterDividersEnabled(true);
        listview_device_testing.addFooterView(View.inflate(getActivity(), R.layout.layou_line, null));
        listview_device_testing.setAdapter(deviceTestingAdapter);

        girdview_device_all.setAdapter(deviceAllAdapter);
        girdview_device_hot.setAdapter(dataManagerAdapter);


        mPresenter.getDeviceStatusChart(false, false);

        //mPresenter.getDeviceNumList(false);


        mPresenter.getDeviceMessageNoReadNum();
        mPresenter.getOrderMessageNum();
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter(getActivity());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_home_head_ll_device_lx:
                toDeviceState(1);
                break;
            case R.id.view_home_head_ll_device_zc:
                toDeviceState(2);
                break;
            case R.id.view_home_head_ll_device_gj:
                toDeviceState(3);
                break;

            case R.id.view_home_head_ll_left_massage:
                ActivityUtils.startActivity(getActivity(), MessageActivity.class);
                break;

            case R.id.view_head_home_rl_search:
                ActivityUtils.startActivity(getActivity(), SearchActity.class);
                break;

            case R.id.view_head_home_listview_ll_more:
                EventBus.getDefault().post(new CommonEvent(EventConstant.TO_JIANCE));
                break;

//            case R.id.view_head_home_listview_ll_hot:
//
//                ActivityUtils.startActivity(getActivity(), ShopHardwareActivity.class);
//                break;

            case R.id.view_home_head_ll_right_sean:


                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {

                    LogUtils.w("没有相机权限");
                    //ToastUtils.showToast("需要相机权限");
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, KEY_SCAN_REQUEST_CODE);
                } else {
                    ActivityUtils.startActivityForResult(getActivity(), ScanCodeActivity.class, KEY_SCAN_REQUEST_CODE);

                }
                break;

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == KEY_SCAN_REQUEST_CODE) {
            for (int i : grantResults) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted 授予权限
                    //处理授权之后逻辑
                    ActivityUtils.startActivityForResult(getActivity(), ScanCodeActivity.class, KEY_SCAN_REQUEST_CODE);
                } else {
                    // Permission Denied 权限被拒绝
                    ToastUtils.showToast("定位权限被禁用");
                }
            }
        }
    }

    void toDeviceState(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(DeviceStateActivity.DEVICE_STATE, type);
        ActivityUtils.startActivity(getActivity(), DeviceStateActivity.class, bundle);
    }

    /**
     * 获取设备状态数据
     *
     * @param response
     * @param isRefresh
     * @param isTimer
     */
    @Override
    public void getDeviceStatusChartFinish(DeviceStatusResponse response, boolean isRefresh, boolean isTimer) {

        if (response != null) {

            tv_zhengchang.setText(response.getOnlineNUm() + "");
            tv_gaojing.setText(response.getAlarmNUm() + "");
            tv_lixian.setText(response.getOfflineNum() + "");

            int deviceSum = response.getAlarmNUm() + response.getOnlineNUm() + response.getOfflineNum();
            tv_stat_count.setText(String.valueOf(deviceSum));

            int zaixian = response.getOnlineNUm() + response.getAlarmNUm();
            float percent = (float) zaixian / deviceSum;
            int percentText = (int) (percent * 100);

//            LogUtils.w("percent ==  " + percent + ",percentText == " + percentText + ",zaixian = " + zaixian
//                    + " , deviceSum == " + deviceSum + " , (zaixian / deviceSum) == " + (zaixian / deviceSum));
            tv_percent.setText(percentText + "%");

//            if(isTimer){
//                homecircle_view.setData2(response.getOnlineNUm(), response.getAlarmNUm(), response.getOfflineNum());
//            }else {
//                homecircle_view.setData(response.getOnlineNUm(), response.getAlarmNUm(), response.getOfflineNum());
//            }

            homecircle_view.setData2(response.getOnlineNUm(), response.getAlarmNUm(), response.getOfflineNum());

        }

        mPresenter.getDeviceNumList(isRefresh, isTimer);
    }

    /**
     * 全部应用
     *
     * @param list
     * @param isRefresh
     * @param isTimer
     */
    @Override
    public void getDeviceNumListFinish(List<DeviceNumResponse> list, boolean isRefresh, boolean isTimer) {
        deviceTestingAdapter.updata(list);

        if (!isTimer) {
            LogUtils.w("---------  刷新全部应用  -----");
            deviceAllAdapter.updata(mPresenter.getAllData());
            dataManagerAdapter.updata(mPresenter.getDataManager());
//            mPresenter.getHostDecive(pageNum, pageSize, isRefresh, false);
            if (isRefresh) {
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }

        BaseTimer.getInstans().startTimer(3000, new BaseTimer.TimerCallBack() {
            @Override
            public void callback() {
                mPresenter.getDeviceStatusChart(false, true);
                mPresenter.getDeviceMessageNoReadNum();
                mPresenter.getOrderMessageNum();
            }
        });

    }

    @Override
    public void getDeviceMessageNoReadNumFinish(MessageNumResposne messageNumResposne) {
        try {
            if (messageNumResposne.getCode() == 421) {
                ActivityUtils.startActivityOrFinish(getActivity(), LoginActivity.class);
            }
            BaseApplication.messageDeviceNum = messageNumResposne.getData();
            showHotSpot();
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    @Override
    public void getOrderMessageNumFinish(MessageNumResposne resposne) {
        try {
            if (resposne.getCode() == 421) {
                ActivityUtils.startActivityOrFinish(getActivity(), LoginActivity.class);
            }
            BaseApplication.messageOrderNum = resposne.getData();
            showHotSpot();
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    void showHotSpot() {
        sum = BaseApplication.messageOrderNum + BaseApplication.messageDeviceNum;
        LogUtils.w("showHotSpot  sum = " + sum);
        if (sum > 0) {
            view_dit.setVisibility(View.VISIBLE);
        } else {
            view_dit.setVisibility(View.GONE);
        }
    }
}
