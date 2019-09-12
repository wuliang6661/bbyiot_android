package com.baibeiyun.bbyiot.module.home.presenter.devicenum;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.NumDeviceHistoryResponse;
import com.baibeiyun.bbyiot.model.request.DeviceLocationRequest;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.devicenum.NumHistoryContract;
import com.baibeiyun.bbyiot.module.home.ui.devicenum.DeviceNumDetailsActivity;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

public class NumHistoryPresenter extends BasePresenter<NumHistoryContract.View> implements NumHistoryContract.Presenter {

    public NumHistoryPresenter(Activity mActivity) {
        super(mActivity);
    }

    /**
     * type : 1 -> 实时数据
     * 2 -> 历史数据
     *
     * @param type
     */
    @Override
    public void getDataHisDataChart(final int type, String startTime, String endTime) {
        mView.showLoading(null);
        DeviceLocationRequest request = new DeviceLocationRequest();
        request.setEid(DeviceNumDetailsActivity.mDeviceId);
        request.setAttributeId(DeviceNumDetailsActivity.mAttributeId);

        if (type == 1) {
            long currentTimeStamp = DateUtils.getCurrentTimeStamp();
            request.setStartDate(DateUtils.stamp2String(currentTimeStamp - 60 * 60 * 1000, "yyyy-MM-dd HH:mm:ss"));
            request.setEndDate(DateUtils.stamp2String(currentTimeStamp, "yyyy-MM-dd HH:mm:ss"));
        } else if (type == 2) {
            request.setStartDate(startTime);
            request.setEndDate(endTime);
        }

        dataManager.getDataHisDataChart(request)
                .subscribe(new BaseSubscriber<BaseResponse<NumDeviceHistoryResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        LogUtils.w(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<NumDeviceHistoryResponse> response) {
                        mView.getDataHisDataChartFinish(type,response.getData());
                        mView.hideLoading();
                    }
                });
    }
}
