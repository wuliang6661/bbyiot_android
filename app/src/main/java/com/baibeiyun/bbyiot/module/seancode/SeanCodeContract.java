package com.baibeiyun.bbyiot.module.seancode;

import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface SeanCodeContract {

    interface View extends IView {
        void activateDeviceFinish(BaseResponse response);
    }

    interface Presenter extends IPresenter<View> {
        void activateDevice(String code);
    }
}
