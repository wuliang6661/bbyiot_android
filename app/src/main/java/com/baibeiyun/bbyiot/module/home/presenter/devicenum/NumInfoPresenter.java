package com.baibeiyun.bbyiot.module.home.presenter.devicenum;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.NumInfoResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.devicenum.NumInfoContract;
import com.baibeiyun.bbyiot.module.home.ui.devicenum.DeviceNumDetailsActivity;

public class NumInfoPresenter extends BasePresenter<NumInfoContract.View> implements NumInfoContract.Presenter {
    public NumInfoPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getDeviceNumInfo() {
        mView.showLoading(null);

        dataManager.getDeviceNumInfo(DeviceNumDetailsActivity.mDeviceId)
                .subscribe(new BaseSubscriber<BaseResponse<NumInfoResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<NumInfoResponse> response) {
                        mView.hideLoading();
                        mView.getDeviceNumInfoFinish(response.getData());
                    }
                });
    }
}
