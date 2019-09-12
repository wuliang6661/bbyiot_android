package com.baibeiyun.bbyiot.module.findshop.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.PayResultResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.findshop.contract.PaySuccessContract;

public class PaySuccessPresenter extends BasePresenter<PaySuccessContract.View> implements PaySuccessContract.Presenter {

    public PaySuccessPresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void getResult(String id) {
        mView.showLoading(null);
        dataManager.getPaySuccessResult(id)
                .subscribe(new BaseSubscriber<BaseResponse<PayResultResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.showMessage(errorMsg);
                        mView.getResult(null);
                    }

                    @Override
                    public void onSuccess(BaseResponse<PayResultResponse> response) {
                        if (response.getCode() == 200) {
                            mView.getResult(response.getData());
                        } else {
                            mView.getResult(null);
                            mView.showMessage(response.getMsg());
                        }
                        mView.hideLoading();
                    }
                });
    }
}
