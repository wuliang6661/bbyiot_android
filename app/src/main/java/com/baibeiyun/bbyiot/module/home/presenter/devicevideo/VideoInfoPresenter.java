package com.baibeiyun.bbyiot.module.home.presenter.devicevideo;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceVideoInfoResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.devicevideo.VideoInfoContract;

public class VideoInfoPresenter extends BasePresenter<VideoInfoContract.View> implements VideoInfoContract.Presenter {

    public VideoInfoPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getDeviceVideo(int id) {
        mView.showLoading(null);
        dataManager.getDeviceVideo(id)
                .subscribe(new BaseSubscriber<BaseResponse<DeviceVideoInfoResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<DeviceVideoInfoResponse> response) {
                        mView.hideLoading();
                        mView.getDeviceVideoFinish(response.getData());
                    }
                });
    }
}
