package com.baibeiyun.bbyiot.module.home.presenter.deviceswitch;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.SaveDeviceRequest;
import com.baibeiyun.bbyiot.model.Response.SwitchConfigResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.deviceswitch.SwitchKongzhiContract;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.List;

public class SwitchKongzhiPresenter extends BasePresenter<SwitchKongzhiContract.View> implements SwitchKongzhiContract.Presenter {

    public SwitchKongzhiPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getDeviceSwitchConfig(int id, String attributeId) {
        mView.showLoading(null);
        dataManager.getDeviceSwitchConfig(id, attributeId)
                .subscribe(new BaseSubscriber<BaseResponse<List<SwitchConfigResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<SwitchConfigResponse>> response) {
                        mView.hideLoading();
                        mView.getDeviceSwitchConfigFinish(response.getData());
                    }
                });
    }

    @Override
    public void saveDeviceSwtich(SaveDeviceRequest request) {
        mView.showLoading(null);

        dataManager.saveDeviceSwtich(request)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {
                        mView.hideLoading();
                        mView.saveDeviceSwtichFinish();
                    }
                });
    }
}
