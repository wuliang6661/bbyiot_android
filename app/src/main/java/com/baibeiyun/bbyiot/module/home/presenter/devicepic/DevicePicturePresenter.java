package com.baibeiyun.bbyiot.module.home.presenter.devicepic;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.DevicePicResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.devicepic.DevicePictureContract;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class DevicePicturePresenter extends BasePresenter<DevicePictureContract.View> implements DevicePictureContract.Presenter {

    public DevicePicturePresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void getDevicePictureList(int pageNum, int pageSize, final boolean isRefresh, final boolean isLoadmore) {
        LogUtils.w("------------------     getDeviceVideoList     ");
        if (!isRefresh && !isLoadmore) {
            mView.showLoading(null);
        }
        dataManager.getDevicePictureList(pageNum, pageSize)
                .subscribe(new BaseSubscriber<BaseResponse<List<DevicePicResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.showMessage(errorMsg);
                        mView.getDevicePictureListFinish(null, isRefresh, isLoadmore);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<DevicePicResponse>> response) {
                        mView.hideLoading();
                        mView.getDevicePictureListFinish(response.getData(), isRefresh, isLoadmore);
                    }
                });
    }
}
