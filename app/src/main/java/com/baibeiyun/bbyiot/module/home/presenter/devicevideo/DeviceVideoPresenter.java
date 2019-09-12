package com.baibeiyun.bbyiot.module.home.presenter.devicevideo;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceVideoResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.devicevideo.DeviceVideoContract;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class DeviceVideoPresenter extends BasePresenter<DeviceVideoContract.View> implements DeviceVideoContract.Presenter {

    public DeviceVideoPresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void getDeviceVideoList(int pageNum, int pageSize, final boolean isRefresh, final boolean isLoadmore) {
        if(!isRefresh &&!isLoadmore){
            mView.showLoading(null);
        }
        LogUtils.w("------------------     getDeviceVideoList     ");
        dataManager.getDeviceVideoList(pageNum,pageSize)
                .subscribe(new BaseSubscriber<BaseResponse<List<DeviceVideoResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.showMessage(errorMsg);
                        mView.getDeviceVideoListFinish(null, isRefresh, isLoadmore);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<DeviceVideoResponse>> deviceNumResponse) {
                        mView.hideLoading(); mView.hideLoading();
                        mView.getDeviceVideoListFinish(deviceNumResponse.getData(), isRefresh, isLoadmore);
                    }
                });
    }
}
