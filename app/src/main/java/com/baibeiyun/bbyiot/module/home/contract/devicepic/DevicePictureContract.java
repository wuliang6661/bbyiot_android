package com.baibeiyun.bbyiot.module.home.contract.devicepic;

import com.baibeiyun.bbyiot.model.Response.DevicePicResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface DevicePictureContract {

    interface Presenter extends IPresenter<View>{

        void getDevicePictureList(int pageNum, int pageSize, boolean isRefresh, boolean isLoadmore);
    }

    interface View extends IView{
        void getDevicePictureListFinish(List<DevicePicResponse> list, boolean isRefresh, boolean isLoadmore);
    }
}
