package com.baibeiyun.bbyiot.module.home.presenter.devicegateway;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceGatewayResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.devicegateway.DeviceGatewayContract;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class DeviceGatewayPresenter extends BasePresenter<DeviceGatewayContract.View> implements DeviceGatewayContract.Presenter {

    public DeviceGatewayPresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void getDeviceGatewayList(int pageNum, int pageSize, final boolean isRefresh, final boolean isLoadmore) {
        LogUtils.w("------------------     getDeviceVideoList     ");
        if (!isRefresh && !isLoadmore) {
            mView.showLoading(null);
        }
        dataManager.getDeviceGatewayList(pageNum, pageSize)
                .subscribe(new BaseSubscriber<BaseResponse<List<DeviceGatewayResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.showMessage(errorMsg);
                        mView.getDeviceGatewayListFinish(null, isRefresh, isLoadmore);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<DeviceGatewayResponse>> response) {
                        mView.hideLoading();
                        mView.getDeviceGatewayListFinish(response.getData(), isRefresh, isLoadmore);
                    }
                });
    }
}
