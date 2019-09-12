package com.baibeiyun.bbyiot.module.mine.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.DevicePropertyResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceResponse;
import com.baibeiyun.bbyiot.model.Response.HisAlarmChartResponse;
import com.baibeiyun.bbyiot.model.Response.HisAlarmListResponse;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.model.request.MonitoringRequest;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.mine.contract.MonitoringAlarmContract;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.List;

public class MonitoringAlarmPresenter  extends BasePresenter<MonitoringAlarmContract.View> implements MonitoringAlarmContract.Presenter{

    public MonitoringAlarmPresenter(Activity mActivity) {
        super(mActivity);
    }


    /**
     * 获取分组
     */
    @Override
    public void getGroupData( final boolean isDefault) {
        mView.showLoading(null);
        dataManager.getHomeGroups()
                .subscribe(new BaseSubscriber<BaseResponse<List<HomeGroupsResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();

                        LogUtils.w(errorMsg);
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<HomeGroupsResponse>> response) {
                        mView.hideLoading();
                        mView.getGroupDataFinish(response.getData(),isDefault);

                    }
                });


    }

    /**
     * 获取设备
     */
    @Override
    public void getDeviceData(int groupID, final boolean isDefault) {
        mView.showLoading(null);
        dataManager.getDevicesByGroupId(groupID)
                .subscribe(new BaseSubscriber<BaseResponse<List<DeviceResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        LogUtils.w(errorMsg);
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<DeviceResponse>> response) {
                        mView.hideLoading();
                        mView.getDeviceDataFinish(response.getData(),isDefault);

                    }
                });
    }


    /**
     * 根据设备id获取 属性列表
     * @param deviceID
     */
    @Override
    public void getRealDatasByEid(int deviceID, final boolean isDefault) {
        mView.showLoading(null);
        dataManager.getRealDatasByEid(deviceID)
                .subscribe(new BaseSubscriber<BaseResponse<List<DevicePropertyResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        LogUtils.w(errorMsg);
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<DevicePropertyResponse>> response) {
                        mView.hideLoading();
                        mView.getRealDatasByEidFinish(response.getData(),isDefault);

                    }
                });
    }


    @Override
    public void getDataHisAlarmChart(int attributeId,int eid,String startDate,String endDate) {
        mView.showLoading(null);
        MonitoringRequest request =new MonitoringRequest();
        request.setAttributeId(attributeId);
        request.setEid(eid);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        dataManager.getDataHisAlarmChart(request)
                .subscribe(new BaseSubscriber<BaseResponse<List<HisAlarmChartResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<HisAlarmChartResponse>> response) {
                        mView.hideLoading();
                        mView.getDataHisAlarmChartFinish(response.getData());
                    }
                });
    }

    @Override
    public void getDataHisAlarmList(int attributeId,int eid,String startDate,String endDate,int pageNum,int pageSize) {
        mView.showLoading(null);
        final MonitoringRequest request =new MonitoringRequest();
        request.setAttributeId(attributeId);
        request.setEid(eid);
        request.setStartDate(startDate);
        request.setEndDate(endDate);

        request.setPageNum(pageNum);
        request.setPageSize(pageSize);
        dataManager.getDataHisAlarmList(request)
                .subscribe(new BaseSubscriber<BaseResponse<List<HisAlarmListResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<HisAlarmListResponse>> response) {
                        mView.hideLoading();
                        mView.getDataHisAlarmListFinish(response.getData());
                    }
                });
    }


}
