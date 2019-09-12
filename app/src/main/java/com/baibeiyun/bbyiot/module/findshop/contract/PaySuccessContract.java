package com.baibeiyun.bbyiot.module.findshop.contract;

import com.baibeiyun.bbyiot.model.Response.PayResultResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface PaySuccessContract {

    interface Presenter extends IPresenter<View> {
        void getResult(String id);
    }

    interface View extends IView {
        void getResult(PayResultResponse response);
    }
}
