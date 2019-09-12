package com.baibeiyun.bbyiot.module.main.contract;

import com.baibeiyun.bbyiot.model.Response.GoodsReviewResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsShopResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface FindContract {

    interface Presenter extends IPresenter<View> {
        void getHostDecive(int pageNum, int pageSize, boolean isRefresh, boolean isLoadmore);

        void getGoodsReviewList( boolean isRefresh);
    }

    interface View extends IView {
        void getHostDeciveFinish(List<GoodsShopResponse> list, boolean isRefresh, boolean isLoadmore);

        void getGoodsReviewList(List<GoodsReviewResponse> list);
    }
}
