package com.baibeiyun.bbyiot.module.main.presenter;


import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsReviewResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsShopResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.main.contract.FindContract;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.List;

public class FindPresenter extends BasePresenter<FindContract.View> implements FindContract.Presenter {

    public FindPresenter(Activity mActivity) {
        super(mActivity);
    }

    /**
     * 获取热门设备
     */
    @Override
    public void getHostDecive(int pageNum, int pageSize, final boolean isRefresh, final boolean isLoadmore) {
        if (!isRefresh && !isLoadmore) {
            mView.showLoading(null);
        }

        dataManager.getGoodsActiveList(pageNum, pageSize)
                .subscribe(new BaseSubscriber<BaseResponse<List<GoodsShopResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                        mView.getHostDeciveFinish(null, isRefresh, isLoadmore);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<GoodsShopResponse>> response) {
                        mView.hideLoading();
                        LogUtils.w(response.getCode() + " msg ==" + response.getMsg());

                        mView.getHostDeciveFinish(response.getData(), isRefresh, isLoadmore);
                    }
                });
    }

    @Override
    public void getGoodsReviewList(boolean isRefresh) {
        if (!isRefresh && !mView.isShowing()) {
            mView.showLoading(null);
        }
        dataManager.getGoodsReviewList(1, 10)
                .subscribe(new BaseSubscriber<BaseResponse<List<GoodsReviewResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                        mView.getGoodsReviewList(null);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<GoodsReviewResponse>> response) {
                        mView.hideLoading();
                        LogUtils.w(response.getCode() + " msg ==" + response.getMsg());

                        mView.getGoodsReviewList(response.getData());
                    }
                });
    }

}
