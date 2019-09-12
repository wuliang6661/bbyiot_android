package com.baibeiyun.bbyiot.module.mine.contract;

import com.baibeiyun.bbyiot.model.Response.DevicePropertyResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceResponse;
import com.baibeiyun.bbyiot.model.Response.HisAlarmChartResponse;
import com.baibeiyun.bbyiot.model.Response.HisAlarmListResponse;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface MonitoringAlarmContract {


    interface Presenter extends IPresenter<View> {

        void getGroupData(boolean isDefault);

        void getDeviceData(int groupID, boolean isDefault);

        void getRealDatasByEid(int deviceID, boolean isDefault);


        void getDataHisAlarmChart(int attributeId, int eid, String startDate, String endDate);


        void getDataHisAlarmList(int attributeId, int eid, String startDate, String endDate, int pageNum, int pageSize);

    }

    interface View extends IView {

        void getGroupDataFinish(List<HomeGroupsResponse> list, boolean isDefault);

        //设备列表 DeviceResponse
        void getDeviceDataFinish(List<DeviceResponse> list, boolean isDefault);

        //获取属性列表
        void getRealDatasByEidFinish(List<DevicePropertyResponse> list, boolean isDefault);


        void getDataHisAlarmChartFinish(List<HisAlarmChartResponse> list);

        void getDataHisAlarmListFinish(List<HisAlarmListResponse> list);

    }
}
