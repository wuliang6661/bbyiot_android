package com.baibeiyun.bbyiot.module.mine.contract;

import com.baibeiyun.bbyiot.model.Response.LatestResultDetailsResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface OrderLogisticsContract {

    interface Presenter extends IPresenter<View> {
        void getLatestResultDetails(String orderId);
    }

    interface View extends IView {
        void getLatestResultDetailsFinish(LatestResultDetailsResponse  response);
    }
}
