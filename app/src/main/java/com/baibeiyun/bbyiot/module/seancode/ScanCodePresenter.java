package com.baibeiyun.bbyiot.module.seancode;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.utils.ToastUtils;

public class ScanCodePresenter extends BasePresenter<SeanCodeContract.View> implements SeanCodeContract.Presenter {


    public ScanCodePresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void activateDevice(String code) {
        mView.showLoading(null);
        dataManager.activateDevice(code)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.activateDeviceFinish(null);
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {
                        mView.hideLoading();
                        mView.activateDeviceFinish(response);

                    }
                });
    }
}
