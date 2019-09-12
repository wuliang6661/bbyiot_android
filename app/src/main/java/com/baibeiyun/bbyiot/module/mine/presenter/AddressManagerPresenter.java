package com.baibeiyun.bbyiot.module.mine.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.AddressResponse;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.request.AddressRequest;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.mine.contract.AddressManagerContract;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.List;

public class AddressManagerPresenter extends BasePresenter<AddressManagerContract.View> implements AddressManagerContract.Presenter {

    public AddressManagerPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getUserAddressList() {

        dataManager.getUserAddressList()
                .subscribe(new BaseSubscriber<BaseResponse<List<AddressResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();

                        mView.getUserAddressListFinsh(null);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<AddressResponse>> response) {
                        mView.hideLoading();

                        mView.getUserAddressListFinsh(response.getData());
                    }
                });
    }

    @Override
    public void changeToDefault(int addressId) {
        mView.showLoading(null);
        dataManager.changeToDefault(addressId)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {
                        mView.hideLoading();
                        mView.changeToDefaultSccuess();
                    }
                });
    }

    @Override
    public void deleteUserAddress(int addressId) {
        mView.showLoading(null);
        dataManager.deleteUserAddress(addressId)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {
                        mView.hideLoading();
                        mView.deleteUserAddressSccuess();
                    }
                });
    }

    @Override
    public void addUserAddress(AddressRequest request) {
        mView.showLoading(null);
        dataManager.addUserAddress(request)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {
                        mView.hideLoading();
                        mView.addUserAddressSccuess();
                    }
                });
    }

    @Override
    public void updateUserAddress(AddressRequest request) {
        mView.showLoading(null);
        dataManager.updateUserAddress(request)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {
                        mView.hideLoading();
                        mView.updateUserAddressSccuess();
                    }
                });
    }


}
