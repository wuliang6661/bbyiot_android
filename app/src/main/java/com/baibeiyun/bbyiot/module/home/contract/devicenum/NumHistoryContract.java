package com.baibeiyun.bbyiot.module.home.contract.devicenum;

import com.baibeiyun.bbyiot.model.Response.NumDeviceHistoryResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface NumHistoryContract {

    interface Presenter extends IPresenter<View> {

        void getDataHisDataChart(int type,String startTime,String endTime);

    }

    interface View extends IView {

        void getDataHisDataChartFinish(int type,NumDeviceHistoryResponse response);

    }
}
