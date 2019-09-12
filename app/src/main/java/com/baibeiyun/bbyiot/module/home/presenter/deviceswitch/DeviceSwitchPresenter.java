package com.baibeiyun.bbyiot.module.home.presenter.deviceswitch;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceSwtichResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.deviceswitch.DeviceSwitchContract;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.List;

public class DeviceSwitchPresenter extends BasePresenter<DeviceSwitchContract.View> implements DeviceSwitchContract.Presenter {

    public DeviceSwitchPresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void getDeviceSwitchList(int pageNum, int pageSize, final boolean isRefresh, final boolean isLoadmore) {
        LogUtils.w("------------------     getDeviceVideoList     ");
        if (!isRefresh && !isLoadmore) {
            mView.showLoading(null);
        }
        dataManager.getDeviceSwtichList(pageNum, pageSize)
                .subscribe(new BaseSubscriber<BaseResponse<List<DeviceSwtichResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.showMessage(errorMsg);
                        mView.getDeviceSwitchListFinish(null, isRefresh, isLoadmore);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<DeviceSwtichResponse>> deviceVideoResponses) {
                        mView.hideLoading();
                        mView.getDeviceSwitchListFinish(deviceVideoResponses.getData(), isRefresh, isLoadmore);
                    }
                });
    }

    @Override
    public void openOrCloseDeviceSwtich(String id, String openValue, String swtichStatus,int attributeId) {

        mView.showLoading(null);
        dataManager.openOrCloseDeviceSwtich(id, openValue, swtichStatus, attributeId)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {
                        mView.hideLoading();
                        ToastUtils.showToast(response.getMsg());
                    }
                });
    }
}
