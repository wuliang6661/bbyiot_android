package com.baibeiyun.bbyiot.module.home.presenter.devicenum;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.LocationResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.devicenum.NumMapContract;
import com.baibeiyun.bbyiot.module.home.ui.devicenum.DeviceNumDetailsActivity;
import com.baibeiyun.bbyiot.utils.ToastUtils;

public class NumMapPresenter extends BasePresenter<NumMapContract.View>  implements  NumMapContract.Presenter {

    public NumMapPresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void getDeviceLocation() {

        mView.showLoading(null);

        dataManager.getDeviceLocation(DeviceNumDetailsActivity.mDeviceId,DeviceNumDetailsActivity.mAttributeId)
                .subscribe(new BaseSubscriber<BaseResponse<LocationResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<LocationResponse> baseResponse) {
                        mView.hideLoading();
                        mView.getDeviceLocationFinish(baseResponse.getData());

                    }
                });
    }
}
