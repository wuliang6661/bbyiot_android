package com.baibeiyun.bbyiot.module.home.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.MessageListResponse;
import com.baibeiyun.bbyiot.model.Response.OrderMessageResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.home.contract.MessageContract;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.List;

public class MessagePresenter extends BasePresenter<MessageContract.View> implements MessageContract.Presenter {

    public MessagePresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getDeviceMessageList(int pageNum, int pageSize, final boolean isRefresh, final boolean isLoadMore) {
        if (!isLoadMore && !isRefresh) {
            mView.showLoading(null);
        }
        dataManager.getDeviceMessageList(pageNum, pageSize)
                .subscribe(new BaseSubscriber<BaseResponse<List<MessageListResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.getDeviceMessageListFinish(null, isRefresh, isLoadMore);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<MessageListResponse>> response) {
                        mView.hideLoading();
                        mView.getDeviceMessageListFinish(response.getData(), isRefresh, isLoadMore);
                    }
                });
    }

    @Override
    public void readDeviceMessage(int id) {
        mView.showLoading(null);
        dataManager.readDeviceMessage(id)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {
                        mView.hideLoading();
                        mView.readDeviceMessageFinish();
                    }
                });
    }

    @Override
    public void getPlatformMessage(int pageNum, int pageSize, final boolean isRefresh, final boolean isLoadMore) {
        if (!isLoadMore && !isRefresh) {
            mView.showLoading(null);
        }
        dataManager.getPlatformMessage()
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {
                        mView.hideLoading();
                        mView.getPlatformMessageFinish(isRefresh, isLoadMore);
                    }
                });
    }

    @Override
    public void getOrderMessageList(int pageNum, int pageSize, final boolean isRefresh, final boolean isLoadMore) {

        if (!isLoadMore && !isRefresh) {
            mView.showLoading(null);
        }
        dataManager.getOrderMessageList()
                .subscribe(new BaseSubscriber<BaseResponse<List<OrderMessageResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<OrderMessageResponse>> response) {
                        mView.hideLoading();
                        mView.getOrderMessageListFinish(response.getData(),isRefresh,isLoadMore);
                    }
                });
    }

    @Override
    public void readOrderMessage(String id) {
        mView.showLoading(null);
        dataManager.readOrderMessage(id)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {
                        mView.hideLoading();
                        mView.readOrderMessageFinish();
                    }
                });
    }


}
