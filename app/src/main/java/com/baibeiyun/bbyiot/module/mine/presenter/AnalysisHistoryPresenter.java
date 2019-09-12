package com.baibeiyun.bbyiot.module.mine.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.AnalysisGatherResponse;
import com.baibeiyun.bbyiot.model.Response.AnalysisHistoryResponse;
import com.baibeiyun.bbyiot.model.Response.AnalysisMalfunctionResponse;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.DefaultDeviceResponse;
import com.baibeiyun.bbyiot.model.Response.DevicePropertyResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceResponse;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.mine.contract.AnalysisHistoryContract;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.List;

public class AnalysisHistoryPresenter extends BasePresenter<AnalysisHistoryContract.View> implements AnalysisHistoryContract.Presenter {

    public AnalysisHistoryPresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void getDefaultData() {
        mView.showLoading(null);
        dataManager.getDefaultData()
                .subscribe(new BaseSubscriber<BaseResponse<DefaultDeviceResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();

                        LogUtils.w(errorMsg);
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<DefaultDeviceResponse> response) {
                        mView.hideLoading();
                        mView.getDefaultDataFinish(response.getData());

                    }
                });
    }

    /**
     * 获取分组
     */
    @Override
    public void getGroupData( final boolean isDefault) {
        mView.showLoading(null);
        dataManager.getHomeGroups()
                .subscribe(new BaseSubscriber<BaseResponse<List<HomeGroupsResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();

                        LogUtils.w(errorMsg);
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<HomeGroupsResponse>> response) {
                        mView.hideLoading();
                        mView.getGroupDataFinish(response.getData(),isDefault);

                    }
                });


    }

    /**
     * 获取设备
     */
    @Override
    public void getDeviceData(int groupID, final boolean isDefault) {
        mView.showLoading(null);
        dataManager.getDevicesByGroupId(groupID)
                .subscribe(new BaseSubscriber<BaseResponse<List<DeviceResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        LogUtils.w(errorMsg);
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<DeviceResponse>> response) {
                        mView.hideLoading();
                        mView.getDeviceDataFinish(response.getData(),isDefault);

                    }
                });
    }

    /**
     * 根据设备id获取 属性列表
     * @param deviceID
     */
    @Override
    public void getRealDatasByEid(int deviceID, final boolean isDefault) {
        mView.showLoading(null);
        dataManager.getRealDatasByEid(deviceID)
                .subscribe(new BaseSubscriber<BaseResponse<List<DevicePropertyResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        LogUtils.w(errorMsg);
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<DevicePropertyResponse>> response) {
                        mView.hideLoading();
                        mView.getRealDatasByEidFinish(response.getData(),isDefault);

                    }
                });
    }


    /**
     * 获取历史分析 --> 图表数据
     *
     * @param deviceID
     * @param startDate
     * @param endDate
     * @param mode
     */
    @Override
    public void getDataHisStatisticChart(int deviceID, String startDate, String endDate, int mode) {
        mView.showLoading(null);
        dataManager.getDataHisStatisticChart(deviceID, startDate, endDate, mode)
                .subscribe(new BaseSubscriber<BaseResponse<AnalysisHistoryResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.getDataHisStatisticChartFinish(null);
                    }

                    @Override
                    public void onSuccess(BaseResponse<AnalysisHistoryResponse> response) {
                        mView.hideLoading();
                        mView.getDataHisStatisticChartFinish(response.getData());
                    }
                });
    }

    /**
     * 获取趋势分析 --> 图表数据
     *
     * @param deviceID
     * @param startDate
     * @param endDate
     * @param mode
     */
    @Override
    public void getTrendAnalysisChart(int deviceID, String startDate, String endDate, int mode) {

        mView.showLoading(null);
        dataManager.getTrendAnalysisChart(deviceID, startDate, endDate, mode)
                .subscribe(new BaseSubscriber<BaseResponse<AnalysisHistoryResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.getTrendAnalysisChartFinish(null);
                    }

                    @Override
                    public void onSuccess(BaseResponse<AnalysisHistoryResponse> response) {
                        mView.hideLoading();
                        mView.getTrendAnalysisChartFinish(response.getData());
                    }
                });
    }


    /**
     * ------------  获取采集
     * attributeId :属性id
     */
    public void getDataCollectStatisticChart(String attributeId ,int deviceID, String startDate, String endDate, int mode) {

        mView.showLoading(null);
        dataManager.getDataCollectStatisticChart(attributeId,deviceID, startDate, endDate, mode)
                .subscribe(new BaseSubscriber<BaseResponse<AnalysisGatherResponse>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.getDataCollectStatisticChartFinish(null);
                    }

                    @Override
                    public void onSuccess(BaseResponse<AnalysisGatherResponse> response) {
                        mView.hideLoading();
                        mView.getDataCollectStatisticChartFinish(response.getData());
                    }
                });
    }


    /**
     * 获取故障
     *
     * @param deviceID
     * @param startDate
     * @param endDate
     * @param mode
     */
    public void getFaultAnalysisChart(int deviceID, String startDate, String endDate, int mode) {

        mView.showLoading(null);
        dataManager.getFaultAnalysisChart(deviceID, startDate, endDate, mode)
                .subscribe(new BaseSubscriber<BaseResponse<List<AnalysisMalfunctionResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.getFaultAnalysisChartFinish(null);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<AnalysisMalfunctionResponse>> response) {
                        mView.hideLoading();
                        mView.getFaultAnalysisChartFinish(response.getData());
                    }
                });
    }

}
