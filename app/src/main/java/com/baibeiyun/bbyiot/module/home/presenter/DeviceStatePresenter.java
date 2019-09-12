package com.baibeiyun.bbyiot.module.home.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.DevicesStatusRespone;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.DeviceStateContract;

import java.util.List;

public class DeviceStatePresenter extends BasePresenter<DeviceStateContract.View> implements DeviceStateContract.Presenter {

    public DeviceStatePresenter(Activity mActivity) {
        super(mActivity);
    }




    //status (integer, optional): 设备状态（1在线，0离线，2告警） ,
    public void getDevicesByStatus(int pageNum, int pageSize, int status,String name) {
        mView.showLoading(null);
        dataManager.getDevicesByStatus(pageNum,pageSize,status,name)
                .subscribe(new BaseSubscriber<BaseResponse<List<DevicesStatusRespone>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<DevicesStatusRespone>> response) {
                        mView.hideLoading();
                        mView.getDevicesByStatusFinish(response.getData());
                    }
                });

    }


}
