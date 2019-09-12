package com.baibeiyun.bbyiot.module.home.contract.devicevideo;

import com.baibeiyun.bbyiot.model.Response.DeviceVideoInfoResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface VideoInfoContract {


    interface Presenter extends IPresenter<View> {

        void getDeviceVideo(int id);
    }

    interface View extends IView {
        void getDeviceVideoFinish(DeviceVideoInfoResponse response);
    }
}
