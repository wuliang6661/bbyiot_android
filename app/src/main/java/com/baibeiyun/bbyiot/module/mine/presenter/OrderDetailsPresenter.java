package com.baibeiyun.bbyiot.module.mine.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.LatestResultResponse;
import com.baibeiyun.bbyiot.model.Response.OrderAddressResponse;
import com.baibeiyun.bbyiot.model.Response.OrderDetailsResponse;
import com.baibeiyun.bbyiot.model.request.OrderEvaluateRequest;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.mine.contract.OrderDetailsContract;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

public class OrderDetailsPresenter extends BasePresenter<OrderDetailsContract.View> implements OrderDetailsContract.Presenter {
    public OrderDetailsPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getOrderDetails(String orderId) {
        mView.showLoading(null);
        dataManager.getOrderDetails(orderId)
                .subscribe(new BaseSubscriber<BaseResponse<OrderDetailsResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<OrderDetailsResponse> baseResponse) {
                        mView.hideLoading();
                        mView.getOrderDetailsFinish(baseResponse.getData());
                    }
                });
    }

    @Override
    public void getUserOrderAddress(String orderId) {
        mView.showLoading(null);
        dataManager.getUserOrderAddress(orderId)
                .subscribe(new BaseSubscriber<BaseResponse<OrderAddressResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<OrderAddressResponse> baseResponse) {
                        mView.hideLoading();
                        mView.getUserOrderAddressFinish(baseResponse.getData());
                    }
                });
    }

    @Override
    public void getLatestResultInfo(String orderId) {
        mView.showLoading(null);
        dataManager.getLatestResultInfo(orderId)
                .subscribe(new BaseSubscriber<BaseResponse<LatestResultResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        LogUtils.e("getLatestResultInfo == " + errorMsg);
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<LatestResultResponse> baseResponse) {
                        mView.hideLoading();
                        mView.getLatestResultInfoFinish(baseResponse.getData());
                    }
                });
    }

    @Override
    public void addUserOrderGoodsEvaluate(OrderEvaluateRequest request) {
        mView.showLoading(null);
        dataManager.addOrderEvaluate(request)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        mView.hideLoading();
                        mView.addUserOrderGoodsEvaluateFinish();
                    }
                });
    }

}
