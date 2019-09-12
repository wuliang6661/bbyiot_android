package com.baibeiyun.bbyiot.module.home.contract.deviceswitch;

import com.baibeiyun.bbyiot.model.Response.SwitchInfoResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface SwitchInfoContract {


    interface Presenter extends IPresenter<View> {
        void getDeviceSwtich(int id);
    }

    interface View extends IView {
        void getDeviceSwtichFinish(SwitchInfoResponse response);
    }
}
