package com.baibeiyun.bbyiot.model;


import com.baibeiyun.bbyiot.api.AppApi;
import com.baibeiyun.bbyiot.api.AppShopApi;
import com.baibeiyun.bbyiot.api.RetrofitClient;
import com.baibeiyun.bbyiot.api.SchedulersCompat;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.model.Response.AddressResponse;
import com.baibeiyun.bbyiot.model.Response.AnalysisGatherResponse;
import com.baibeiyun.bbyiot.model.Response.AnalysisHistoryResponse;
import com.baibeiyun.bbyiot.model.Response.AnalysisMalfunctionResponse;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.CommonResponse;
import com.baibeiyun.bbyiot.model.Response.DefaultDeviceResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceGatewayInfoResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceGatewayResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceNumResponse;
import com.baibeiyun.bbyiot.model.Response.DevicePicInfoResponse;
import com.baibeiyun.bbyiot.model.Response.DevicePicResponse;
import com.baibeiyun.bbyiot.model.Response.DevicePropertyResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceStatusResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceSwtichResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceVideoInfoResponse;
import com.baibeiyun.bbyiot.model.Response.DeviceVideoResponse;
import com.baibeiyun.bbyiot.model.Response.DevicesStatusRespone;
import com.baibeiyun.bbyiot.model.Response.EvaluateListResponse;
import com.baibeiyun.bbyiot.model.Response.FeedbackTypeResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsReviewResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsShopResponse;
import com.baibeiyun.bbyiot.model.Response.HisAlarmChartResponse;
import com.baibeiyun.bbyiot.model.Response.HisAlarmListResponse;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.model.Response.LatestResultDetailsResponse;
import com.baibeiyun.bbyiot.model.Response.LatestResultResponse;
import com.baibeiyun.bbyiot.model.Response.LocationResponse;
import com.baibeiyun.bbyiot.model.Response.LoginResponse;
import com.baibeiyun.bbyiot.model.Response.MessageListResponse;
import com.baibeiyun.bbyiot.model.Response.MessageNumResposne;
import com.baibeiyun.bbyiot.model.Response.NumDeviceHistoryResponse;
import com.baibeiyun.bbyiot.model.Response.NumInfoResponse;
import com.baibeiyun.bbyiot.model.Response.OrderAddressResponse;
import com.baibeiyun.bbyiot.model.Response.OrderDetailsResponse;
import com.baibeiyun.bbyiot.model.Response.OrderMessageResponse;
import com.baibeiyun.bbyiot.model.Response.OrderResponse;
import com.baibeiyun.bbyiot.model.Response.PayResultResponse;
import com.baibeiyun.bbyiot.model.Response.SaveDeviceRequest;
import com.baibeiyun.bbyiot.model.Response.SwitchConfigResponse;
import com.baibeiyun.bbyiot.model.Response.SwitchInfoResponse;
import com.baibeiyun.bbyiot.model.Response.TestingResponse;
import com.baibeiyun.bbyiot.model.request.AddFeedbackRequest;
import com.baibeiyun.bbyiot.model.request.AddressRequest;
import com.baibeiyun.bbyiot.model.request.AnalysisRequest;
import com.baibeiyun.bbyiot.model.request.DeviceListRequest;
import com.baibeiyun.bbyiot.model.request.DeviceLocationRequest;
import com.baibeiyun.bbyiot.model.request.GoodsActiveRequest;
import com.baibeiyun.bbyiot.model.request.MonitoringRequest;
import com.baibeiyun.bbyiot.model.request.OrderEvaluateRequest;
import com.baibeiyun.bbyiot.model.request.TestingRequest;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.SpUtils;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class DataManager {
    private static DataManager sInstance = new DataManager();

    private static AppApi mAppApi;
    private static AppShopApi mAppShopApi;

    public static DataManager getsInstance() {
        if (mAppApi == null) {
            mAppApi = RetrofitClient.getInstance().getAppApi();
        }
        if (mAppShopApi == null) {
            mAppShopApi = RetrofitClient.getInstance().getAppShopApi();
        }
        return sInstance;
    }


    /**
     * {
     * "password": "string",
     * "username": "string"
     * }
     */
    public Observable<BaseResponse<LoginResponse>> login(String userName, String userPsw) {
        Map<String, String> requert = new HashMap<>();
        requert.put("username", userName);
        requert.put("password", userPsw);
        return mAppApi.pswLogin(requert)
                .compose(SchedulersCompat.<BaseResponse<LoginResponse>>defaultSchedulers());
    }

    /**
     * 获取首页在线设备
     *
     * @return
     */
    public Observable<BaseResponse<DeviceStatusResponse>> getDeviceStatusChart() {
        Map<String, String> requert = new HashMap<>();
        requert.put("token", getToken());
        return mAppApi.getDeviceStatusChart(requert)
                .compose(SchedulersCompat.<BaseResponse<DeviceStatusResponse>>defaultSchedulers());
    }


    public Observable<BaseResponse<List<HomeGroupsResponse>>> getHomeGroups() {
        Map<String, String> requert = new HashMap<>();
        requert.put("token", getToken());
        return mAppApi.getHomeGroups(requert)
                .compose(SchedulersCompat.<BaseResponse<List<HomeGroupsResponse>>>defaultSchedulers());
    }


    /**
     * 获取数值设备列表
     */
    public Observable<BaseResponse<List<DeviceNumResponse>>> getDeviceNumList(int pageNum, int pageSize) {

        DeviceListRequest request = new DeviceListRequest();
        request.setPageNum(pageNum);
        request.setPageSize(pageSize);

        LogUtils.w("DeviceListRequest == " + request.toString());
        return mAppApi.getDeviceNumList(request)
                .compose(SchedulersCompat.<BaseResponse<List<DeviceNumResponse>>>defaultSchedulers());
    }

    /**
     * 获取视频设备列表
     */
    public Observable<BaseResponse<List<DeviceVideoResponse>>> getDeviceVideoList(int pageNum, int pageSize) {

        DeviceListRequest request = new DeviceListRequest();
        request.setPageNum(pageNum);
        request.setPageSize(pageSize);

        LogUtils.w("DeviceListRequest == " + request.toString());
        return mAppApi.getDeviceVideoList(request)
                .compose(SchedulersCompat.<BaseResponse<List<DeviceVideoResponse>>>defaultSchedulers());
    }

    /**
     * 获取图片设备列表
     */
    public Observable<BaseResponse<List<DevicePicResponse>>> getDevicePictureList(int pageNum, int pageSize) {

        DeviceListRequest request = new DeviceListRequest();
        request.setPageNum(pageNum);
        request.setPageSize(pageSize);

        LogUtils.w("DeviceListRequest == " + request.toString());
        return mAppApi.getDevicePictrueList(request)
                .compose(SchedulersCompat.<BaseResponse<List<DevicePicResponse>>>defaultSchedulers());
    }

    /**
     * 获取开关设备列表
     */
    public Observable<BaseResponse<List<DeviceSwtichResponse>>> getDeviceSwtichList(int pageNum, int pageSize) {

        DeviceListRequest request = new DeviceListRequest();
        request.setPageNum(pageNum);
        request.setPageSize(pageSize);

        LogUtils.w("DeviceListRequest == " + request.toString());
        return mAppApi.getDeviceSwtichList(request)
                .compose(SchedulersCompat.<BaseResponse<List<DeviceSwtichResponse>>>defaultSchedulers());
    }

    /**
     * 获取网关设备列表
     */
    public Observable<BaseResponse<List<DeviceGatewayResponse>>> getDeviceGatewayList(int pageNum, int pageSize) {

        DeviceListRequest request = new DeviceListRequest();
        request.setPageNum(pageNum);
        request.setPageSize(pageSize);

        LogUtils.w("DeviceListRequest == " + request.toString());
        return mAppApi.getDeviceGatewayList(request)
                .compose(SchedulersCompat.<BaseResponse<List<DeviceGatewayResponse>>>defaultSchedulers());
    }

    /**
     * 获取数值设备定位
     */
    public Observable<BaseResponse<LocationResponse>> getDeviceLocation(int deviceId, int attributeId) {
        DeviceLocationRequest request = new DeviceLocationRequest();
        request.setAttributeId(attributeId);
        request.setEid(deviceId);

        LogUtils.w(request.toString());
        return mAppApi.getDeviceLocation(request)
                .compose(SchedulersCompat.<BaseResponse<LocationResponse>>defaultSchedulers());
    }

    /**
     * 获取数值设备详情 --->  历史数据、实时数据
     */
    public Observable<BaseResponse<NumDeviceHistoryResponse>> getDataHisDataChart(DeviceLocationRequest request) {
        LogUtils.w(request.toString());
        return mAppApi.getDataHisDataChart(request)
                .compose(SchedulersCompat.<BaseResponse<NumDeviceHistoryResponse>>defaultSchedulers());
    }

    /**
     * 获取数值设备详情 --->  基本信息
     */
    public Observable<BaseResponse<NumInfoResponse>> getDeviceNumInfo(int deciveId) {
        DeviceLocationRequest request = new DeviceLocationRequest();
        request.setId(deciveId);
        LogUtils.w(request.toString());
        return mAppApi.getDeviceNumInfo(request)
                .compose(SchedulersCompat.<BaseResponse<NumInfoResponse>>defaultSchedulers());
    }

    /**
     * // 获取监测列表
     */
    public Observable<BaseResponse<List<TestingResponse>>> getTestingList(String groupId, String name) {
        TestingRequest testingRequest = new TestingRequest();
        testingRequest.setGroupIds(groupId);
        testingRequest.setName(name);
        return mAppApi.getTestingList(testingRequest)
                .compose(SchedulersCompat.<BaseResponse<List<TestingResponse>>>defaultSchedulers());
    }


    /**
     * 历史分析
     * mode: 统计时间区间  0 分    1 小时 ,
     */
    public Observable<BaseResponse<AnalysisHistoryResponse>> getDataHisStatisticChart(int deviceID, String startDate, String endDate, int mode) {
        AnalysisRequest analysisRequest = new AnalysisRequest();
        analysisRequest.setEid(deviceID);
        analysisRequest.setStartDate(startDate);
        analysisRequest.setEndDate(endDate);
        analysisRequest.setMode(mode);

        LogUtils.w(analysisRequest.toString());

        return mAppApi.getDataHisStatisticChart(analysisRequest)
                .compose(SchedulersCompat.<BaseResponse<AnalysisHistoryResponse>>defaultSchedulers());
    }

    /**
     * 趋势分析
     */
    public Observable<BaseResponse<AnalysisHistoryResponse>> getTrendAnalysisChart(int deviceID, String startDate, String endDate, int mode) {
        AnalysisRequest analysisRequest = new AnalysisRequest();
        analysisRequest.setEid(deviceID);
        analysisRequest.setStartDate(startDate);
        analysisRequest.setEndDate(endDate);
        analysisRequest.setMode(mode);
        return mAppApi.getTrendAnalysisChart(analysisRequest)
                .compose(SchedulersCompat.<BaseResponse<AnalysisHistoryResponse>>defaultSchedulers());
    }

    /**
     * 采集分析
     */
    public Observable<BaseResponse<AnalysisGatherResponse>> getDataCollectStatisticChart(
            String attributeId, int deviceID, String startDate, String endDate, int mode) {

        AnalysisRequest analysisRequest = new AnalysisRequest();
        analysisRequest.setEid(deviceID);
        analysisRequest.setStartDate(startDate);
        analysisRequest.setAttributeId(attributeId);
        analysisRequest.setEndDate(endDate);
        analysisRequest.setMode(mode);
        LogUtils.w(analysisRequest.toString());
        return mAppApi.getDataCollectStatisticChart(analysisRequest)
                .compose(SchedulersCompat.<BaseResponse<AnalysisGatherResponse>>defaultSchedulers());
    }

    /**
     * 故障分析
     *
     * @return
     */
    public Observable<BaseResponse<List<AnalysisMalfunctionResponse>>> getFaultAnalysisChart(int deviceID, String startDate, String endDate, int mode) {
        AnalysisRequest analysisRequest = new AnalysisRequest();
        analysisRequest.setEid(deviceID);
        analysisRequest.setStartDate(startDate);
        analysisRequest.setEndDate(endDate);
        analysisRequest.setMode(mode);
        return mAppApi.getFaultAnalysisChart(analysisRequest)
                .compose(SchedulersCompat.<BaseResponse<List<AnalysisMalfunctionResponse>>>defaultSchedulers());
    }

    /**
     * 根据分组id获取设备列表
     *
     * @return
     */
    public Observable<BaseResponse<List<DeviceResponse>>> getDevicesByGroupId(int groupID) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("token", getToken());
        requestMap.put("id", groupID);
        return mAppApi.getDevicesByGroupId(requestMap)
                .compose(SchedulersCompat.<BaseResponse<List<DeviceResponse>>>defaultSchedulers());
    }

    /**
     * 根据分组id获取设备列表
     *
     * @return
     */
    public Observable<BaseResponse<List<DevicePropertyResponse>>> getRealDatasByEid(int deviceID) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("token", getToken());
        requestMap.put("id", deviceID);
        return mAppApi.getRealDatasByEid(requestMap)
                .compose(SchedulersCompat.<BaseResponse<List<DevicePropertyResponse>>>defaultSchedulers());
    }


    public Observable<BaseResponse<DefaultDeviceResponse>> getDefaultData() {
        Map<String, String> request = new HashMap<>();
        request.put("token", getToken());
        return mAppApi.getDefaultData(request)
                .compose(SchedulersCompat.<BaseResponse<DefaultDeviceResponse>>defaultSchedulers());
    }

    /**
     * 获取离线、在线、告警设备
     * status (integer, optional): 设备状态（1在线，0离线，2告警）
     */
    public Observable<BaseResponse<List<DevicesStatusRespone>>> getDevicesByStatus(int pageNum, int pageSize, int status, String name) {
        Map<String, String> request = new HashMap<>();
        request.put("token", getToken());
        request.put("pageNum", pageNum + "");
        request.put("pageSize", pageSize + "");
        request.put("status", status + "");
        request.put("name", name);
        return mAppApi.getDevicesByStatus(request)
                .compose(SchedulersCompat.<BaseResponse<List<DevicesStatusRespone>>>defaultSchedulers());
    }

    /**
     * 获取网关设备基本信息
     */
    public Observable<BaseResponse<DeviceGatewayInfoResponse>> getDeviceGateway(int id) {
        Map<String, String> request = new HashMap<>();
        request.put("token", getToken());
        request.put("id", id + "");
        return mAppApi.getDeviceGateway(request)
                .compose(SchedulersCompat.<BaseResponse<DeviceGatewayInfoResponse>>defaultSchedulers());
    }

    /**
     * 获取图片设备基本信息
     */
    public Observable<BaseResponse<DevicePicInfoResponse>> getDevicePic(int id) {
        Map<String, String> request = new HashMap<>();
        request.put("token", getToken());
        request.put("id", id + "");
        return mAppApi.getDevicePic(request)
                .compose(SchedulersCompat.<BaseResponse<DevicePicInfoResponse>>defaultSchedulers());
    }

    /**
     * 获取视频设备  基本信息
     */
    public Observable<BaseResponse<DeviceVideoInfoResponse>> getDeviceVideo(int id) {
        Map<String, String> request = new HashMap<>();
        request.put("token", getToken());
        request.put("id", id + "");
        return mAppApi.getDeviceVideo(request)
                .compose(SchedulersCompat.<BaseResponse<DeviceVideoInfoResponse>>defaultSchedulers());

    }

    /**
     * 获取开关设备  基本信息
     */
    public Observable<BaseResponse<SwitchInfoResponse>> getDeviceSwitch(int id) {
        Map<String, String> request = new HashMap<>();
        request.put("token", getToken());
        request.put("id", id + "");
        return mAppApi.getDeviceSwtich(request)
                .compose(SchedulersCompat.<BaseResponse<SwitchInfoResponse>>defaultSchedulers());

    }

    /**
     * 获取开关设备  基本信息
     */
    public Observable<BaseResponse<List<SwitchConfigResponse>>> getDeviceSwitchConfig(int id, String attributeId) {
        Map<String, String> request = new HashMap<>();
        request.put("token", getToken());
        request.put("eid", id + "");
        request.put("attributeId", attributeId);
        LogUtils.e("deviceSwtich/getDeviceSwitchs   attributeId = " + attributeId);
        return mAppApi.getDeviceSwitchConfig(request)
                .compose(SchedulersCompat.<BaseResponse<List<SwitchConfigResponse>>>defaultSchedulers());

    }

    /**
     * ----------  扫码激活设备
     */
    public Observable<BaseResponse> activateDevice(String code) {
        Map<String, String> request = new HashMap<>();
        request.put("token", getToken());
        request.put("code", code);
        return mAppApi.activateDevice(request)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());

    }

    /**
     * ----------  保存开关设备状态
     */
    public Observable<BaseResponse> saveDeviceSwtich(SaveDeviceRequest request) {
        return mAppApi.saveDeviceSwtich(request)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());

    }

    /**
     * ----------  打开或关闭开关
     */
    public Observable<BaseResponse> openOrCloseDeviceSwtich(String id, String openValue, String swtichStatus, int attributeId) {
        Map<String, String> request = new HashMap<>();
        request.put("id", id);
        request.put("openValue", openValue);
        request.put("swtichStatus", swtichStatus);
        request.put("token", getToken());
        request.put("attributeId", attributeId + "");
        return mAppApi.openOrCloseDeviceSwtich(request)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());

    }

    /**
     * //获取历史告警列表\
     */
    public Observable<BaseResponse<List<HisAlarmChartResponse>>> getDataHisAlarmChart(MonitoringRequest request) {

        return mAppApi.getDataHisAlarmChart(request)
                .compose(SchedulersCompat.<BaseResponse<List<HisAlarmChartResponse>>>defaultSchedulers());
    }

    /**
     * 获取历史告警列表
     */
    public Observable<BaseResponse<List<HisAlarmListResponse>>> getDataHisAlarmList(MonitoringRequest request) {

        return mAppApi.getDataHisAlarmList(request)
                .compose(SchedulersCompat.<BaseResponse<List<HisAlarmListResponse>>>defaultSchedulers());
    }

    /**
     * 获取设备消息列表
     *
     * @return
     */
    public Observable<BaseResponse<List<MessageListResponse>>> getDeviceMessageList(int pageNum, int pageSize) {
        MonitoringRequest request = new MonitoringRequest();
        request.setPageNum(pageNum);
        request.setPageSize(pageSize);
        return mAppApi.getDeviceMessageList(request)
                .compose(SchedulersCompat.<BaseResponse<List<MessageListResponse>>>defaultSchedulers());
    }

    public Observable<BaseResponse> readDeviceMessage(int id) {
        Map<String, String> request = new HashMap<>();
        request.put("id", id + "");
        request.put("token", getToken());
        return mAppApi.readDeviceMessage(request)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }

    public Observable<MessageNumResposne> getDeviceMessageNoReadNum() {
        Map<String, String> request = new HashMap<>();
        request.put("token", getToken());
        return mAppApi.getDeviceMessageNoReadNum(request)
                .compose(SchedulersCompat.<MessageNumResposne>defaultSchedulers());
    }

    /**
     * 图像上传--->单张图片
     *
     * @return
     */
    public Observable<BaseResponse<String>> fileUpload2(String path) {
        File file = new File(path);
        LogUtils.w("path == " + path);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("fileName", file.getName(), requestFile);
        MultipartBody body = builder.build();
        return mAppApi.fileUpload2(body)
                .compose(SchedulersCompat.<BaseResponse<String>>defaultSchedulers());
    }

    /**
     * 图像上传--->多张
     *
     * @return
     */
    public Observable<BaseResponse<List<String>>> fileUpload3(List<String> pathList) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (int i = 0; i < pathList.size(); i++) {
            File file = new File(pathList.get(i));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            //builder.addFormDataPart("fileName" + i, file.getName(), requestFile);
            builder.addFormDataPart("fileName", file.getName(), requestFile);
        }
        MultipartBody body = builder.build();
        return mAppApi.fileUpload3(body)
                .compose(SchedulersCompat.<BaseResponse<List<String>>>defaultSchedulers());
    }

    public Observable<BaseResponse> changeLoginPsw(String oldPsw, String newPsw) {
        Map<String, String> request = new HashMap<>();
        request.put("newPassword", newPsw);
        request.put("oldPassword", oldPsw);
        request.put("token", getToken());

        return mAppApi.changeLoginPsw(request)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }


    /**
     * ------------   商城模块接口
     */
    /**
     * 获取热销商品
     */
    public Observable<BaseResponse<List<GoodsShopResponse>>> getGoodsActiveList(int pageNum, int pageSize) {
        GoodsActiveRequest request = new GoodsActiveRequest();
        request.setPageNum(pageNum);
        request.setPageSize(pageSize);
        return mAppShopApi.getGoodsActiveList(request)
                .compose(SchedulersCompat.<BaseResponse<List<GoodsShopResponse>>>defaultSchedulers());
    }

    /**
     * 获取反馈列表
     *
     * @return
     */
    public Observable<BaseResponse<List<FeedbackTypeResponse>>> getUserFeedbackType() {
        return mAppShopApi.getUserFeedbackType()
                .compose(SchedulersCompat.<BaseResponse<List<FeedbackTypeResponse>>>defaultSchedulers());
    }

    public Observable<BaseResponse> addUserFeedback(AddFeedbackRequest request) {
        return mAppShopApi.addUserFeedback(request)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }


    public Observable<BaseResponse<List<AddressResponse>>> getUserAddressList() {
        return mAppShopApi.getUserAddressList()
                .compose(SchedulersCompat.<BaseResponse<List<AddressResponse>>>defaultSchedulers());
    }


    public Observable<BaseResponse> changeToDefault(int addressId) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", addressId + "");
        return mAppShopApi.changeToDefault(requestMap)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }

    public Observable<BaseResponse> deleteUserAddress(int addressId) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", addressId + "");
        return mAppShopApi.deleteUserAddress(requestMap)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }

    public Observable<BaseResponse> addUserAddress(AddressRequest request) {

        return mAppShopApi.addUserAddress(request)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }

    public Observable<BaseResponse> updateUserAddress(AddressRequest request) {
        return mAppShopApi.updateUserAddress(request)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }

    public Observable<BaseResponse<List<OrderResponse>>> getUserOrderListByType(int pageNum, int pageSize, int type) {
        Map<String, Integer> requestMap = new HashMap<>();
        requestMap.put("pageNum", pageNum);
        requestMap.put("pageSize", pageSize);
        requestMap.put("type", type);
        return mAppShopApi.getUserOrderListByType(requestMap)
                .compose(SchedulersCompat.<BaseResponse<List<OrderResponse>>>defaultSchedulers());
    }

    public Observable<BaseResponse> getPlatformMessage() {

        return mAppShopApi.getPlatformMessage()
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }

    public Observable<BaseResponse<OrderDetailsResponse>> getOrderDetails(String orderId) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", orderId);
        return mAppShopApi.getOrderDetails(requestMap)
                .compose(SchedulersCompat.<BaseResponse<OrderDetailsResponse>>defaultSchedulers());
    }

    public Observable<BaseResponse<OrderAddressResponse>> getUserOrderAddress(String orderId) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", orderId);
        return mAppShopApi.getUserOrderAddress(requestMap)
                .compose(SchedulersCompat.<BaseResponse<OrderAddressResponse>>defaultSchedulers());
    }

    public Observable<BaseResponse<LatestResultResponse>> getLatestResultInfo(String orderId) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", orderId);
        return mAppShopApi.getLatestResultInfo(requestMap)
                .compose(SchedulersCompat.<BaseResponse<LatestResultResponse>>defaultSchedulers());
    }

    /**
     * 物流详情
     *
     * @param orderId
     * @return
     */
    public Observable<BaseResponse<LatestResultDetailsResponse>> getLatestResultDetails(String orderId) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", orderId);
        return mAppShopApi.getLatestResultDetails(requestMap)
                .compose(SchedulersCompat.<BaseResponse<LatestResultDetailsResponse>>defaultSchedulers());
    }

    public Observable<BaseResponse<List<EvaluateListResponse>>> getEvaluateList() {

        return mAppShopApi.getEvaluateList()
                .compose(SchedulersCompat.<BaseResponse<List<EvaluateListResponse>>>defaultSchedulers());
    }

    public Observable<BaseResponse> addOrderEvaluate(OrderEvaluateRequest request) {
        return mAppShopApi.addOrderEvaluate(request)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }

    public Observable<BaseResponse> confirmOrder(String orderId) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", orderId);
        return mAppShopApi.confirmOrder(requestMap)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }

    public Observable<BaseResponse> tixing(String orderId) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", orderId);
        return mAppShopApi.tixing(requestMap)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }


    public Observable<BaseResponse<List<OrderMessageResponse>>> getOrderMessageList() {
        return mAppShopApi.getOrderMessage()
                .compose(SchedulersCompat.<BaseResponse<List<OrderMessageResponse>>>defaultSchedulers());
    }

    public Observable<BaseResponse> readOrderMessage(String id) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", id);
        return mAppShopApi.readOrderMessage(requestMap)
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }

    public Observable<MessageNumResposne> getOrderMessageNum() {
        return mAppShopApi.getOrderMessageNum()
                .compose(SchedulersCompat.<MessageNumResposne>defaultSchedulers());
    }


    /**
     * 测试接口
     */
    public Observable<BaseResponse> test() {

        return mAppShopApi.test()
                .compose(SchedulersCompat.<BaseResponse>defaultSchedulers());
    }


    /**
     * -----------------  项目常用存储 --------------------
     */
    public String getToken() {
        return SpUtils.getString(IConstant.KEY_LOGIN_TOKEN, "");
    }

    public void saveLoginInfo(LoginResponse response) {
        String json = new Gson().toJson(response);
        SpUtils.put(IConstant.KEY_LOGIN_BEAN, json);
    }

    public boolean isLogin() {
        return SpUtils.getBoolean(IConstant.KEY_IS_LOGIN, false);
    }

    public void saveLoginState(boolean isLogin) {
        SpUtils.put(IConstant.KEY_IS_LOGIN, isLogin);
    }


    public LoginResponse getLoginBean() {
        String loginString = SpUtils.getString(IConstant.KEY_LOGIN_BEAN, "");
        LoginResponse loginResponse = new Gson().fromJson(loginString, LoginResponse.class);
        return loginResponse;
    }

    /**
     * 支付
     *
     * @return
     */
    public Observable<CommonResponse> getPayResult(String orderId) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("id", orderId);
        return mAppShopApi.getPayResult(requestMap)
                .compose(SchedulersCompat.<CommonResponse>defaultSchedulers());
    }

    /**
     * 支付成功结果
     *
     * @return
     */
    public Observable<BaseResponse<PayResultResponse>> getPaySuccessResult(String orderId) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("id", orderId);
        return mAppShopApi.getPaySuccessResult(requestMap)
                .compose(SchedulersCompat.<BaseResponse<PayResultResponse>>defaultSchedulers());
    }

    /**
     * 分页查询有测评信息的商品
     *
     * @return
     */
    public Observable<BaseResponse<List<GoodsReviewResponse>>> getGoodsReviewList(int pageNum, int pageSize) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("pageNum", pageNum);
        requestMap.put("pageSize", pageSize);
        return mAppShopApi.getGoodsReviewList(requestMap)
                .compose(SchedulersCompat.<BaseResponse<List<GoodsReviewResponse>>>defaultSchedulers());
    }
}
