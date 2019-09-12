package com.baibeiyun.bbyiot.module.main.presenter;


import android.app.Activity;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceNumResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceStatusResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsActiveResponse;
import com.baibeiyun.bbyiot.model.Response.MessageNumResposne;
import com.baibeiyun.bbyiot.model.bean.TestDataBean;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.main.contract.HomeContract;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {


    public HomePresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void getDeviceStatusChart(final boolean isRefresh, final boolean isTimer) {
        if (!isRefresh && !isTimer) {
            mView.showLoading(null);
        }
        dataManager.getDeviceStatusChart()
                .subscribe(new BaseSubscriber<BaseResponse<DeviceStatusResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.showMessage(errorMsg);
                        mView.getDeviceStatusChartFinish(null, isRefresh, isTimer);
                    }

                    @Override
                    public void onSuccess(BaseResponse<DeviceStatusResponse> response) {
                        mView.hideLoading();
                        if (response.getCode() == 200) {
                            mView.getDeviceStatusChartFinish(response.getData(), isRefresh, isTimer);
                        } else {
                            mView.getDeviceStatusChartFinish(null, isRefresh, isTimer);
                            mView.showMessage(response.getMsg());
                        }
                    }
                });
    }

    @Override
    public void getDeviceNumList(final boolean isRefresh, final boolean isTimer) {
        if (!isRefresh && !isTimer) {
            mView.showLoading(null);
        }
        dataManager.getDeviceNumList(1, 10)
                .subscribe(new BaseSubscriber<BaseResponse<List<DeviceNumResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.showMessage(errorMsg);
                        mView.getDeviceNumListFinish(null, isRefresh, isTimer);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<DeviceNumResponse>> deviceNumResponse) {
                        mView.hideLoading();
                        mView.getDeviceNumListFinish(deviceNumResponse.getData(), isRefresh, isTimer);
                    }
                });
    }

    @Override
    public void getDeviceMessageNoReadNum() {
        dataManager.getDeviceMessageNoReadNum()
                .subscribe(new Observer<MessageNumResposne>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MessageNumResposne messageNumResposne) {
                        try {
                            mView.getDeviceMessageNoReadNumFinish(messageNumResposne);
                        } catch (Exception e) {
                            LogUtils.w(e.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getOrderMessageNum() {
        dataManager.getOrderMessageNum()
                .subscribe(new Observer<MessageNumResposne>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MessageNumResposne  messageNumResposne) {
                        try {
                            mView.getOrderMessageNumFinish(messageNumResposne);
                        } catch (Exception e) {
                            LogUtils.w(e.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * --------------------        测试接口
     */

    public void test() {
        dataManager.test()
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        LogUtils.w("e == " + errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {

                    }
                });
    }






    /**
     * --------     制造检测全部应用数据
     */
    public List<TestDataBean> getAllData() {
        List<TestDataBean> list = new ArrayList<>();
        list.add(new TestDataBean(R.mipmap.icon_home_device_all_01, "数值设备"));
        list.add(new TestDataBean(R.mipmap.icon_home_device_all_02, "视频设备"));
        list.add(new TestDataBean(R.mipmap.icon_home_device_all_03, "图片设备"));
        list.add(new TestDataBean(R.mipmap.icon_home_device_all_04, "开关设备"));
        list.add(new TestDataBean(R.mipmap.icon_home_device_all_05, "网关设备"));
        list.add(new TestDataBean(R.mipmap.icon_home_device_all_06, "硬件"));
        list.add(new TestDataBean(R.mipmap.icon_home_device_all_07, "软件"));
        list.add(new TestDataBean(R.mipmap.icon_home_device_all_08, "工业app"));

        return list;
    }

    /**
     * --------    数据管理数据
     */
    public List<TestDataBean> getDataManager() {
        List<TestDataBean> list = new ArrayList<>();
        list.add(new TestDataBean(R.mipmap.icon_mine_device_01, "分组管理"));
        list.add(new TestDataBean(R.mipmap.icon_mine_device_02, "监控报警"));
        list.add(new TestDataBean(R.mipmap.icon_mine_device_03, "统计分析"));
        list.add(new TestDataBean(R.mipmap.icon_mine_device_04, "告警模版"));

        return list;
    }

    /**
     * --------     制造检测全部应用数据
     */
    public List<TestDataBean> getHotData() {
        List<TestDataBean> list = new ArrayList<>();
        list.add(new TestDataBean(R.mipmap.icon_home_device_hot_01, "开关量模拟器"));
        list.add(new TestDataBean(R.mipmap.icon_home_device_hot_02, "Modbus网关"));
        list.add(new TestDataBean(R.mipmap.icon_home_device_hot_03, "物联网网关"));
        list.add(new TestDataBean(R.mipmap.icon_home_device_hot_04, "可编程模块"));
        list.add(new TestDataBean(R.mipmap.icon_home_device_hot_05, "GPRS-DTU"));
        list.add(new TestDataBean(R.mipmap.icon_home_device_hot_06, "4G-DTU"));
        return list;
    }
}
