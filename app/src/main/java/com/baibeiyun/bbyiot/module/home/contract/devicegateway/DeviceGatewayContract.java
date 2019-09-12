package com.baibeiyun.bbyiot.module.home.contract.devicegateway;

import com.baibeiyun.bbyiot.model.Response.DeviceGatewayResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface DeviceGatewayContract {

    interface Presenter extends IPresenter<View> {

        void getDeviceGatewayList(int pageNum, int pageSize, boolean isRefresh, boolean isLoadmore);
    }

    interface View extends IView {
        void getDeviceGatewayListFinish(List<DeviceGatewayResponse> list, boolean isRefresh, boolean isLoadmore);
    }
}
