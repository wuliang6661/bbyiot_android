package com.baibeiyun.bbyiot.module.home.contract;

import com.baibeiyun.bbyiot.model.Response.DevicesStatusRespone;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface DeviceStateContract {

    interface Presenter extends IPresenter<View>{
        void getDevicesByStatus(int pageNum, int pageSize, int status,String name);
    }

    interface View extends IView{
        void getDevicesByStatusFinish(List<DevicesStatusRespone> list);
    }
}
