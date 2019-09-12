package com.baibeiyun.bbyiot.module.home.presenter.devicegateway;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceGatewayInfoResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.devicegateway.DeviceInfoContract;
import com.baibeiyun.bbyiot.utils.LogUtils;

public class DeviceGatewayInfoPresenter extends BasePresenter<DeviceInfoContract.View> implements DeviceInfoContract.Presenter {


    public DeviceGatewayInfoPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getDeviceGatewayInfo(int id) {
        mView.showLoading(null);
        dataManager.getDeviceGateway(id)
                .subscribe(new BaseSubscriber<BaseResponse<DeviceGatewayInfoResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        LogUtils.w(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<DeviceGatewayInfoResponse> response) {
                        mView.hideLoading();

                        mView.getDeviceGatewayInfoFinish(response.getData());
                    }
                });
    }
}
