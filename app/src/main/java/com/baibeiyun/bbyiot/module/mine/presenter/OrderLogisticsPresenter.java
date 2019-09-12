package com.baibeiyun.bbyiot.module.mine.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.LatestResultDetailsResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.mine.contract.OrderLogisticsContract;

public class OrderLogisticsPresenter extends BasePresenter<OrderLogisticsContract.View> implements OrderLogisticsContract.Presenter {

    public OrderLogisticsPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getLatestResultDetails(String orderId) {
        mView.showLoading(null);
        dataManager.getLatestResultDetails(orderId)
                .subscribe(new BaseSubscriber<BaseResponse<LatestResultDetailsResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<LatestResultDetailsResponse> response) {
                        mView.hideLoading();
                        mView.getLatestResultDetailsFinish(response.getData());
                    }
                });
    }
}
