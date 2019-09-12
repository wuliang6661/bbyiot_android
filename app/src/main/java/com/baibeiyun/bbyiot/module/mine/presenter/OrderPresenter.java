package com.baibeiyun.bbyiot.module.mine.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.OrderResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.mine.contract.OrderContract;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.List;

public class OrderPresenter extends BasePresenter<OrderContract.View> implements OrderContract.Presenter {

    public OrderPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getUserOrderListByType(int pageNum, int pageSize, int type, final boolean isRefresh, final boolean isLoadmore) {
        if(!isRefresh && !isLoadmore){
            mView.showLoading(null);
        }
        dataManager.getUserOrderListByType(pageNum,pageSize,type)
                .subscribe(new BaseSubscriber<BaseResponse<List<OrderResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                        mView.getUserOrderListFinish(null,isRefresh,isLoadmore);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<OrderResponse>> response) {
                        mView.hideLoading();
                        mView.getUserOrderListFinish(response.getData(),isRefresh,isLoadmore);
                    }
                });
    }

    @Override
    public void confirmOrder(String orderId) {
        mView.showLoading(null);
        dataManager.confirmOrder(orderId)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        mView.hideLoading();
                        mView.confirmOrderFinish();
                    }
                });
    }

    @Override
    public void tixing(String orderId) {
        mView.showLoading(null);
        dataManager.tixing(orderId)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        mView.hideLoading();
                        mView.tixingFinish();
                    }
                });
    }
}
