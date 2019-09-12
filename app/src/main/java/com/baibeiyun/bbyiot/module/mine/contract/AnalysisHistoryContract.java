package com.baibeiyun.bbyiot.module.mine.contract;

import com.baibeiyun.bbyiot.model.Response.AnalysisGatherResponse;
import com.baibeiyun.bbyiot.model.Response.AnalysisHistoryResponse;
import com.baibeiyun.bbyiot.model.Response.AnalysisMalfunctionResponse;
import com.baibeiyun.bbyiot.model.Response.DefaultDeviceResponse;
import com.baibeiyun.bbyiot.model.Response.DevicePropertyResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceResponse;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface AnalysisHistoryContract{

    interface Presenter extends IPresenter<View> {

        void getDefaultData();
        void getGroupData(boolean isDefault);
        void getDeviceData(int groupID,boolean isDefault);

        void getRealDatasByEid(int deviceID,boolean isDefault);

        //获取 历史图表数据
        void getDataHisStatisticChart(int deviceID, String startDate, String endDate, int mode);

        // 获取趋势
        void getTrendAnalysisChart(int deviceID, String startDate, String endDate, int mode);


        // 采集
        void getDataCollectStatisticChart(String attributeId,int deviceID, String startDate, String endDate, int mode);


        // 故障
        void getFaultAnalysisChart(int deviceID, String startDate, String endDate, int mode);
    }

    interface View extends IView {

        void getDefaultDataFinish(DefaultDeviceResponse response);

        void getGroupDataFinish(List<HomeGroupsResponse> list,boolean isDefault);

        //设备列表 DeviceResponse
        void getDeviceDataFinish(List<DeviceResponse> list,boolean isDefault);

        //获取属性列表
        void getRealDatasByEidFinish(List<DevicePropertyResponse> list,boolean isDefault);



        //获取历史分析图表数据
        void getDataHisStatisticChartFinish(AnalysisHistoryResponse response);

        void getTrendAnalysisChartFinish(AnalysisHistoryResponse response);


        void getDataCollectStatisticChartFinish(AnalysisGatherResponse response);

        void getFaultAnalysisChartFinish(List<AnalysisMalfunctionResponse> response);


    }
}
