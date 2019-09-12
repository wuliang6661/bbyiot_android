package com.baibeiyun.bbyiot.api;

import com.baibeiyun.bbyiot.model.Response.AnalysisGatherResponse;
import com.baibeiyun.bbyiot.model.Response.AnalysisHistoryResponse;
import com.baibeiyun.bbyiot.model.Response.AnalysisMalfunctionResponse;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
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
import com.baibeiyun.bbyiot.model.Response.HisAlarmChartResponse;
import com.baibeiyun.bbyiot.model.Response.HisAlarmListResponse;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.model.Response.LocationResponse;
import com.baibeiyun.bbyiot.model.Response.LoginResponse;
import com.baibeiyun.bbyiot.model.Response.MessageListResponse;
import com.baibeiyun.bbyiot.model.Response.MessageNumResposne;
import com.baibeiyun.bbyiot.model.Response.NumDeviceHistoryResponse;
import com.baibeiyun.bbyiot.model.Response.NumInfoResponse;
import com.baibeiyun.bbyiot.model.Response.SaveDeviceRequest;
import com.baibeiyun.bbyiot.model.Response.SwitchConfigResponse;
import com.baibeiyun.bbyiot.model.Response.SwitchInfoResponse;
import com.baibeiyun.bbyiot.model.Response.TestingResponse;
import com.baibeiyun.bbyiot.model.request.AnalysisRequest;
import com.baibeiyun.bbyiot.model.request.DeviceListRequest;
import com.baibeiyun.bbyiot.model.request.DeviceLocationRequest;
import com.baibeiyun.bbyiot.model.request.MonitoringRequest;
import com.baibeiyun.bbyiot.model.request.TestingRequest;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AppApi {


    //用户登录
    @POST("/app/user/login")
    Observable<BaseResponse<LoginResponse>> pswLogin(@Body Map<String, String> request);


    // 获取首页饼状图，在线设备状态
    @POST("/app/home/getDeviceStatusChart")
    Observable<BaseResponse<DeviceStatusResponse>> getDeviceStatusChart(@Body Map<String, String> request);

    //获取监测全部分组
    @POST("/app/home/getGroups")
    Observable<BaseResponse<List<HomeGroupsResponse>>> getHomeGroups(@Body Map<String, String> request);

    // 获取数值
    @POST("/app/deviceNumerical/getNumericalAttributes")
    Observable<BaseResponse<List<DeviceNumResponse>>> getDeviceNumList(@Body DeviceListRequest request);

    // 获取视频设备列表
    @POST("/app/deviceVideo/getVideoList")
    Observable<BaseResponse<List<DeviceVideoResponse>>> getDeviceVideoList(@Body DeviceListRequest request);

    // 获取图片设备列表
    @POST("/app/devicePic/getPicList")
    Observable<BaseResponse<List<DevicePicResponse>>> getDevicePictrueList(@Body DeviceListRequest request);

    // 获取开关设备列表
    @POST("/app/deviceSwtich/getDeviceSwtichList")
    Observable<BaseResponse<List<DeviceSwtichResponse>>> getDeviceSwtichList(@Body DeviceListRequest request);

    // 获取网关设备列表
    @POST("/app/deviceGateway/getDeviceGatewayList")
    Observable<BaseResponse<List<DeviceGatewayResponse>>> getDeviceGatewayList(@Body DeviceListRequest request);


    //----------------------  获取数值设备的实时数据和历史数据
    @POST("/app/statistic/getDataHisDataChart")
    Observable<BaseResponse<NumDeviceHistoryResponse>> getDataHisDataChart(@Body DeviceLocationRequest request);

    //----------------------  获取数值设备 ---> 基本信息
    @POST("/app/deviceNumerical/getDeviceNumerical")
    Observable<BaseResponse<NumInfoResponse>> getDeviceNumInfo(@Body DeviceLocationRequest request);


    //获取数值设备定位
    @POST("/app/deviceNumerical/getNumericalDeviceAttribute")
    Observable<BaseResponse<LocationResponse>> getDeviceLocation(@Body DeviceLocationRequest request);


    // 获取监测列表
    @POST("/app/deviceNumerical/getDeviceAttributeDatas")
    Observable<BaseResponse<List<TestingResponse>>> getTestingList(@Body TestingRequest request);


    /**
     * ------------------------  我的页面  统计分析
     */
    //获取历史分析
    @POST("/app/statistic/getDataHisStatisticChart")
    Observable<BaseResponse<AnalysisHistoryResponse>> getDataHisStatisticChart(@Body AnalysisRequest request);

    //获取趋势分析
    @POST("/app/statistic/getTrendAnalysisChart")
    Observable<BaseResponse<AnalysisHistoryResponse>> getTrendAnalysisChart(@Body AnalysisRequest request);

    //采集分析
    @POST("/app/statistic/getDataCollectStatisticChart")
    Observable<BaseResponse<AnalysisGatherResponse>> getDataCollectStatisticChart(@Body AnalysisRequest request);

    //故障分析
    @POST("/app/statistic/getFaultAnalysisChart")
    Observable<BaseResponse<List<AnalysisMalfunctionResponse>>> getFaultAnalysisChart(@Body AnalysisRequest request);


    //------------- 根据分组id 获取 设备列表
    @POST("/app/deviceNumerical/getDevicesByGroupId")
    Observable<BaseResponse<List<DeviceResponse>>> getDevicesByGroupId(@Body Map<String, Object> request);

    //------------- 根据设备id 获取 属性列表
    @POST("/app/deviceNumerical/getRealDatasByEid")
    Observable<BaseResponse<List<DevicePropertyResponse>>> getRealDatasByEid(@Body Map<String, Object> request);

    //---------------  获取默认设备
    @POST("/app/statistic/getDefaultData")
    Observable<BaseResponse<DefaultDeviceResponse>> getDefaultData(@Body Map<String, String> request);

    //---------------  获取离线、在线、告警设备
    @POST("/app/statistic/getDevicesByStatus")
    Observable<BaseResponse<List<DevicesStatusRespone>>> getDevicesByStatus(@Body Map<String, String> request);

    //网关设备 基本信息
    @POST("/app/deviceGateway/getDeviceGateway")
    Observable<BaseResponse<DeviceGatewayInfoResponse>> getDeviceGateway(@Body Map<String, String> request);


    //图片设备 基本信息
    @POST("/app/devicePic/getDevicePic")
    Observable<BaseResponse<DevicePicInfoResponse>> getDevicePic(@Body Map<String, String> request);

    //视频设备  基本信息
    @POST("/app/deviceVideo/getDeviceVideo")
    Observable<BaseResponse<DeviceVideoInfoResponse>> getDeviceVideo(@Body Map<String, String> request);

    //开关设备  基本信息
    @POST("/app/deviceSwtich/getDeviceSwtich")
    Observable<BaseResponse<SwitchInfoResponse>> getDeviceSwtich(@Body Map<String, String> request);


    //开关设备  基本信息
    @POST("/app/deviceSwtich/getDeviceSwitchs")
    Observable<BaseResponse<List<SwitchConfigResponse>>> getDeviceSwitchConfig(@Body Map<String, String> request);

    //----------  扫码激活设备    POST /app/home/activateDevice
    @POST("/app/home/activateDevice")
    Observable<BaseResponse> activateDevice(@Body Map<String, String> request);

    /**
     * ----------- 保存开关
     */
    @POST("/app/deviceSwtich/saveDeviceSwtich")
    Observable<BaseResponse> saveDeviceSwtich(@Body SaveDeviceRequest request);


    @POST("/app/deviceSwtich/openOrCloseDeviceSwtich")
    Observable<BaseResponse> openOrCloseDeviceSwtich(@Body Map<String, String> request);

    //获取历史告警列表\
    ///app/alarm/getDataHisAlarmChart
    @POST("/app/alarm/getDataHisAlarmChart")
    Observable<BaseResponse<List<HisAlarmChartResponse>>> getDataHisAlarmChart(@Body MonitoringRequest request);


    //    POST /app/alarm/getDataHisAlarmList
//            获取历史告警列表
    @POST("/app/alarm/getDataHisAlarmList")
    Observable<BaseResponse<List<HisAlarmListResponse>>> getDataHisAlarmList(@Body MonitoringRequest request);

    //    POST /app/message/getDeviceMessageList
//            获取设备消息列表
    @POST("/app/message/getDeviceMessageList")
    Observable<BaseResponse<List<MessageListResponse>>> getDeviceMessageList(@Body MonitoringRequest request);

    //    POST /app/message/readDeviceMessage
    //读取设备消息
    @POST("/app/message/readDeviceMessage")
    Observable<BaseResponse> readDeviceMessage(@Body Map<String, String> request);


    @POST("/app/message/getDeviceMessageNoReadNum")
    Observable<MessageNumResposne> getDeviceMessageNoReadNum(@Body Map<String, String> request);


    /**
     * 上传单个图片
     */
    @POST("/app/file/fileUpload")
    Observable<BaseResponse<String>> fileUpload2(@Body MultipartBody files);

    /**
     * 上传多张图片
     */
    @POST("/app/file/fileUpload")
    Observable<BaseResponse<List<String>>> fileUpload3(@Body MultipartBody files);

    @POST("/app/user/updatePassword")
    Observable<BaseResponse> changeLoginPsw(@Body Map<String, String> request);
}
