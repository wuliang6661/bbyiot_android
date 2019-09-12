package com.baibeiyun.bbyiot.module.home.presenter.deviceswitch;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.SwitchInfoResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.deviceswitch.SwitchInfoContract;

public class SwitchInfoPresenter extends BasePresenter<SwitchInfoContract.View> implements SwitchInfoContract.Presenter {

    public SwitchInfoPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getDeviceSwtich(int id) {
        mView.showLoading(null);

        dataManager.getDeviceSwitch(id)
                .subscribe(new BaseSubscriber<BaseResponse<SwitchInfoResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<SwitchInfoResponse> response) {
                        mView.hideLoading();

                        mView.getDeviceSwtichFinish(response.getData());
                    }
                });
    }
}
