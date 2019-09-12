package com.baibeiyun.bbyiot.module.home.contract.devicenum;

import com.baibeiyun.bbyiot.model.Response.DeviceNumResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface DeviceNumContract {

    interface Presenter extends IPresenter<View>{

        void getDeviceNumList(int pageNum, int pageSize, boolean isRefresh, boolean isLoadmore);
    }

    interface View extends IView{
        void getDeviceNumListFinish(List<DeviceNumResponse> list, boolean isRefresh, boolean isLoadmore);
    }
}
