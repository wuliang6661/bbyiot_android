package com.baibeiyun.bbyiot.module.mine.contract;

import com.baibeiyun.bbyiot.model.Response.LatestResultResponse;
import com.baibeiyun.bbyiot.model.Response.OrderAddressResponse;
import com.baibeiyun.bbyiot.model.Response.OrderDetailsResponse;
import com.baibeiyun.bbyiot.model.request.OrderEvaluateRequest;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface OrderDetailsContract {

    interface Presenter extends IPresenter<View> {
        void getOrderDetails(String orderId);

        void getUserOrderAddress(String orderId);

        void getLatestResultInfo(String orderId);

        //提交评价
        void addUserOrderGoodsEvaluate(OrderEvaluateRequest request);
    }

    interface View extends IView {
        void getOrderDetailsFinish(OrderDetailsResponse response);

        void getUserOrderAddressFinish(OrderAddressResponse response);

        void getLatestResultInfoFinish(LatestResultResponse response);

        void addUserOrderGoodsEvaluateFinish();

    }
}
