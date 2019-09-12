package com.baibeiyun.bbyiot.module.findshop.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsReviewResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsShopResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.findshop.contract.ShopEvaluationContract;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.List;

public class ShopEvaluationPresenter extends BasePresenter<ShopEvaluationContract.View> implements ShopEvaluationContract.Presenter {

    public ShopEvaluationPresenter(Activity mActivity) {
        super(mActivity);
    }

//    @Override
//    public void getGoodsActiveList(int pageNum, int pageSize, final boolean isRefresh, final boolean isLoadmore) {
//
//        if (!isRefresh && !isLoadmore) {
//            mView.showLoading(null);
//        }
//
//        dataManager.getGoodsActiveList(pageNum, pageSize)
//                .subscribe(new BaseSubscriber<BaseResponse<List<GoodsShopResponse>>>() {
//                    @Override
//                    public void onFail(String errorMsg) {
//                        mView.hideLoading();
//                        mView.getGoodsActiveListFinishFail(null, isRefresh, isLoadmore);
//                        ToastUtils.showToast(errorMsg);
//                    }
//
//                    @Override
//                    public void onSuccess(BaseResponse<List<GoodsShopResponse>> response) {
//                        LogUtils.w(response.getCode() + " msg ==" + response.getMsg());
//
//                        if (response.getCode() == 200) {
//                            mView.getGoodsActiveListFinishSuccess(response.getData(), isRefresh, isLoadmore);
//                        } else {
//                            mView.getGoodsActiveListFinishFail(null, isRefresh, isLoadmore);
//                        }
//                        mView.hideLoading();
//                    }
//                });
//    }

    @Override
    public void getGoodsReviewList(int pageNum, int pageSize, final boolean isRefresh, final boolean isLoadmore) {
        if (!isRefresh && !mView.isShowing()) {
            mView.showLoading(null);
        }
        dataManager.getGoodsReviewList(pageNum, pageSize)
                .subscribe(new BaseSubscriber<BaseResponse<List<GoodsReviewResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                        mView.getGoodsReviewList(null, isRefresh, isLoadmore);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<GoodsReviewResponse>> response) {
                        mView.hideLoading();
                        LogUtils.w(response.getCode() + " msg ==" + response.getMsg());

                        mView.getGoodsReviewList(response.getData(), isRefresh, isLoadmore);
                    }
                });
    }
}
