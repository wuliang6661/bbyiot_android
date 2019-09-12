package com.baibeiyun.bbyiot.module.home.contract.devicenum;

import com.baibeiyun.bbyiot.model.Response.LocationResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface NumMapContract {

    interface Presenter extends IPresenter<View> {

        void getDeviceLocation();
    }

    interface View extends IView {
        void getDeviceLocationFinish(LocationResponse response);

    }
}
