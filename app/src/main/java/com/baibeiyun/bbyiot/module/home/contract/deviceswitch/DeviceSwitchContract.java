package com.baibeiyun.bbyiot.module.home.contract.deviceswitch;

import com.baibeiyun.bbyiot.model.Response.DeviceSwtichResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;


public interface DeviceSwitchContract {

    interface Presenter extends IPresenter<View>{

        void getDeviceSwitchList(int pageNum, int pageSize, boolean isRefresh, boolean isLoadmore);

        void openOrCloseDeviceSwtich(String id,String openValue ,String swtichStatus,int attributeId);
    }

    interface View extends IView{
        void getDeviceSwitchListFinish(List<DeviceSwtichResponse> list, boolean isRefresh, boolean isLoadmore);

        void openOrCloseDeviceSwtichFinish();
    }
}
