package com.baibeiyun.bbyiot.module.home.contract.deviceswitch;

import com.baibeiyun.bbyiot.model.Response.SaveDeviceRequest;
import com.baibeiyun.bbyiot.model.Response.SwitchConfigResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface SwitchKongzhiContract {

    interface Presenter extends IPresenter<View> {
        void getDeviceSwitchConfig(int id,String attributeId);

        void saveDeviceSwtich(SaveDeviceRequest  request);


    }

    interface View extends IView {
        void getDeviceSwitchConfigFinish(List<SwitchConfigResponse>list);
        void saveDeviceSwtichFinish();
    }
}
