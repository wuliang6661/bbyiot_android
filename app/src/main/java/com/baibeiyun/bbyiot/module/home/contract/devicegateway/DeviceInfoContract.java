package com.baibeiyun.bbyiot.module.home.contract.devicegateway;


import com.baibeiyun.bbyiot.model.Response.DeviceGatewayInfoResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface DeviceInfoContract {

    interface Presenter extends IPresenter<View> {

        void getDeviceGatewayInfo(int id);
    }

    interface View extends IView {
        void getDeviceGatewayInfoFinish(DeviceGatewayInfoResponse response);
    }

}
