package com.baibeiyun.bbyiot.module.findshop.contract;

import com.baibeiyun.bbyiot.model.Response.GoodsReviewResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsShopResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface ShopEvaluationContract {

    interface Presenter extends IPresenter<View> {
        void getGoodsReviewList(int pageNum, int pageSize, boolean isRefresh, boolean isLoadmore);
    }

    interface View extends IView {
        void getGoodsReviewList(List<GoodsReviewResponse> response, boolean isRefresh, boolean isLoadmore);
    }
}
