package com.baibeiyun.bbyiot.module.findshop.contract;

import com.baibeiyun.bbyiot.model.Response.GoodsActiveResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsShopResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface ShopHardwareContract {

    interface Presenter extends IPresenter<View> {
        void getGoodsActiveList(int pageNum, int pageSize, boolean isRefresh, boolean isLoadmore);
    }

    interface View extends IView {
        void getGoodsActiveListFinishFail(List<GoodsShopResponse> response, boolean isRefresh, boolean isLoadmore);

        void getGoodsActiveListFinishSuccess(List<GoodsShopResponse> response, boolean isRefresh, boolean isLoadmore);
    }
}
