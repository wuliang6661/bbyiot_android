package com.baibeiyun.bbyiot.module.home.presenter.devicenum;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceNumResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.devicenum.DeviceNumContract;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class DeviceNumPresenter extends BasePresenter<DeviceNumContract.View> implements DeviceNumContract.Presenter {

    public DeviceNumPresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void getDeviceNumList(int pageNum, int pageSize, final boolean isRefresh, final boolean isLoadmore) {
        LogUtils.w("------------------     getDeviceVideoList     ");
        if(!isRefresh &&!isLoadmore){
            mView.showLoading(null);
        }

        dataManager.getDeviceNumList(pageNum,pageSize)
                .subscribe(new BaseSubscriber<BaseResponse<List<DeviceNumResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.showMessage(errorMsg);
                        mView.getDeviceNumListFinish(null, isRefresh, isLoadmore);
                }

                    @Override
                    public void onSuccess(BaseResponse<List<DeviceNumResponse>> deviceNumResponse) {
                        mView.hideLoading();
                        mView.getDeviceNumListFinish(deviceNumResponse.getData(), isRefresh, isLoadmore);
                    }
                });
    }
}
