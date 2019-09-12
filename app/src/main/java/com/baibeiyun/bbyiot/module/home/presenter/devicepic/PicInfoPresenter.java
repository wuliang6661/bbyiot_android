package com.baibeiyun.bbyiot.module.home.presenter.devicepic;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.DevicePicInfoResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.devicepic.PicInfoContract;

public class PicInfoPresenter extends BasePresenter<PicInfoContract.View> implements PicInfoContract.Presenter {

    public PicInfoPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getPicInfo(int id) {
        mView.showLoading(null);
        dataManager.getDevicePic(id)
                .subscribe(new BaseSubscriber<BaseResponse<DevicePicInfoResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<DevicePicInfoResponse> response) {
                        mView.hideLoading();

                        mView.getPicInfoFinish(response.getData());
                    }
                });
    }
}
