package com.baibeiyun.bbyiot.module.mine.contract;

import com.baibeiyun.bbyiot.model.Response.OrderResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface OrderContract {

    interface Presenter extends IPresenter<View> {
        void getUserOrderListByType(int pageNum, int pageSize, int type, boolean isRefresh, boolean isLoadmore);

        void confirmOrder(String orderId);
        void tixing(String orderId);
    }

    interface View extends IView {
        void getUserOrderListFinish(List<OrderResponse> list, boolean isRefresh, boolean isLoadmore);


        void confirmOrderFinish();
        void tixingFinish();
    }
}
