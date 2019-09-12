package com.baibeiyun.bbyiot.module.main.contract;

import com.baibeiyun.bbyiot.model.Response.DeviceNumResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceStatusResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsActiveResponse;
import com.baibeiyun.bbyiot.model.Response.MessageNumResposne;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface HomeContract {

    interface Presenter extends IPresenter<View>{
        void getDeviceStatusChart(boolean isRefresh,boolean isTimer);

        void getDeviceNumList( boolean isRefresh,boolean isTimer);

//        void getHostDecive(int pageNum, int pageSize,  boolean isRefresh,  boolean isLoadmore);

        void getDeviceMessageNoReadNum();
        void getOrderMessageNum();
    }

    interface View extends IView{
        void getDeviceStatusChartFinish(DeviceStatusResponse response,boolean isRefresh,boolean isTimer);

        void getDeviceNumListFinish(List<DeviceNumResponse> list, boolean isRefresh,boolean isTimer);

//        void getHostDeciveFinish(List<GoodsActiveResponse> list, boolean isRefresh, boolean isLoadmore);

        void getDeviceMessageNoReadNumFinish(MessageNumResposne messageNumResposne);

        void getOrderMessageNumFinish(MessageNumResposne messageNumResposne);
    }
}
