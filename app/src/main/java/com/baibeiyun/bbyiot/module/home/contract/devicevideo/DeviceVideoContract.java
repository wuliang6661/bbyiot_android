package com.baibeiyun.bbyiot.module.home.contract.devicevideo;

import com.baibeiyun.bbyiot.model.Response.DeviceVideoResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface DeviceVideoContract {

    interface Presenter extends IPresenter<View>{

        void getDeviceVideoList(int pageNum, int pageSize, boolean isRefresh, boolean isLoadmore);
    }

    interface View extends IView{
        void getDeviceVideoListFinish(List<DeviceVideoResponse> list, boolean isRefresh, boolean isLoadmore);
    }
}
