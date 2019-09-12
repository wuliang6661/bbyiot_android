package com.baibeiyun.bbyiot.module.home.contract.devicenum;

import com.baibeiyun.bbyiot.model.Response.NumInfoResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface NumInfoContract {

    interface Presenter extends IPresenter<View> {
        void getDeviceNumInfo();
    }

    interface View extends IView {
        void getDeviceNumInfoFinish(NumInfoResponse response);
    }
}
