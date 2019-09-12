package com.baibeiyun.bbyiot.module.home.contract.devicepic;

import com.baibeiyun.bbyiot.model.Response.DevicePicInfoResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface PicInfoContract {

    interface Presenter extends IPresenter<View> {

       void getPicInfo(int id);
    }

    interface View extends IView {
         void getPicInfoFinish(DevicePicInfoResponse response);
    }
}
