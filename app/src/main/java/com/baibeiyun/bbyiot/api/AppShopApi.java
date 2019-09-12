package com.baibeiyun.bbyiot.api;

import com.baibeiyun.bbyiot.model.Response.AddressResponse;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.CommonResponse;
import com.baibeiyun.bbyiot.model.Response.EvaluateListResponse;
import com.baibeiyun.bbyiot.model.Response.FeedbackTypeResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsActiveResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsReviewResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsShopResponse;
import com.baibeiyun.bbyiot.model.Response.LatestResultDetailsResponse;
import com.baibeiyun.bbyiot.model.Response.LatestResultResponse;
import com.baibeiyun.bbyiot.model.Response.MessageNumResposne;
import com.baibeiyun.bbyiot.model.Response.OrderAddressResponse;
import com.baibeiyun.bbyiot.model.Response.OrderDetailsResponse;
import com.baibeiyun.bbyiot.model.Response.OrderMessageResponse;
import com.baibeiyun.bbyiot.model.Response.OrderResponse;
import com.baibeiyun.bbyiot.model.Response.PayResultResponse;
import com.baibeiyun.bbyiot.model.request.AddFeedbackRequest;
import com.baibeiyun.bbyiot.model.request.AddressRequest;
import com.baibeiyun.bbyiot.model.request.GoodsActiveRequest;
import com.baibeiyun.bbyiot.model.request.OrderEvaluateRequest;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AppShopApi {

    //@POST("hardware_webservice/goodsActive/getGoodsActiveList")
    @POST("goodsActive/getGoodsActiveList")
    Observable<BaseResponse<List<GoodsShopResponse>>> getGoodsActiveList(@Body GoodsActiveRequest request);


    @POST("/user/sysUser/info")
    Observable<BaseResponse<List<GoodsActiveResponse>>> test();

    //查询反馈类型
    @POST("userFeedbackType/getUserFeedbackType")
    Observable<BaseResponse<List<FeedbackTypeResponse>>> getUserFeedbackType();

    //提交反馈信息
    @POST("userFeedback/addUserFeedback")
    Observable<BaseResponse> addUserFeedback(@Body AddFeedbackRequest request);

    //获取地址列表
    @POST("userAddress/getUserAddressList")
    Observable<BaseResponse<List<AddressResponse>>> getUserAddressList();

    //设置默认地址
    @POST("userAddress/changeToDefault")
    Observable<BaseResponse> changeToDefault(@Body Map<String, String> map);

    //删除地址
    @POST("userAddress/deleteUserAddress")
    Observable<BaseResponse> deleteUserAddress(@Body Map<String, String> map);

    //添加收货地址
    @POST("userAddress/addUserAddress")
    Observable<BaseResponse> addUserAddress(@Body AddressRequest request);

    //修改收货地址
    @POST("userAddress/updateUserAddress")
    Observable<BaseResponse> updateUserAddress(@Body AddressRequest request);

    //获取订单列表
    @POST("userOrder/getUserOrderListByType")
    Observable<BaseResponse<List<OrderResponse>>> getUserOrderListByType(@Body Map<String, Integer> map);

    //获取平台消息
    @POST("platformNotice/getPlatformNotice")
    Observable<BaseResponse> getPlatformMessage();

    //获取订单详情
    @POST("userOrder/getUserOrderInfo")
    Observable<BaseResponse<OrderDetailsResponse>> getOrderDetails(@Body Map<String, String> map);

    //获取订单 地址
    @POST("userOrderAddress/getUserOrderAddress")
    Observable<BaseResponse<OrderAddressResponse>> getUserOrderAddress(@Body Map<String, String> map);

    //订单详情简单物流信息
    @POST("logisticsTrackingRecord/getLatestResultInfo")
    Observable<BaseResponse<LatestResultResponse>> getLatestResultInfo(@Body Map<String, String> map);

    //物流信息详情
    @POST("logisticsTrackingRecord/getLogisticsRecordInfo")
    Observable<BaseResponse<LatestResultDetailsResponse>> getLatestResultDetails(@Body Map<String, String> map);


    @POST("userOrderGoodsEvaluate/listUserOrderGoodsEvaluate")
    Observable<BaseResponse<List<EvaluateListResponse>>> getEvaluateList();


    //提交订单评价
    @POST("userOrderGoodsEvaluate/addUserOrderGoodsEvaluate")
    Observable<BaseResponse> addOrderEvaluate(@Body OrderEvaluateRequest request);

    //userOrder/updateConfirmReceipts  确认收货
    @POST("userOrder/updateConfirmReceipts")
    Observable<BaseResponse> confirmOrder(@Body Map<String, String> map);

    //userOrderRemind/addUserOrderRemind  提醒发货
    @POST("userOrderRemind/addUserOrderRemind")
    Observable<BaseResponse> tixing(@Body Map<String, String> map);


    //获取订单消息
    @POST("userOrderMessage/getUserOrderMessage")
    Observable<BaseResponse<List<OrderMessageResponse>>> getOrderMessage();

    @POST("userOrderMessage/updateUserOrderMessage")
    Observable<BaseResponse> readOrderMessage(@Body Map<String, String> map);

    @POST("userOrderMessage/getUnreadMessageNum")
    Observable<MessageNumResposne> getOrderMessageNum();

    //支付
    @POST("userOrder/wxPay")
    Observable<CommonResponse> getPayResult(@Body Map<String, Object> map);

    //支付结果
    @POST("userOrder/getOrderPayResult")
    Observable<BaseResponse<PayResultResponse>> getPaySuccessResult(@Body Map<String, Object> map);

    //分页查询有测评信息的商品
    @POST("goodsInfo/getHaveGoodsReviewList")
    Observable<BaseResponse<List<GoodsReviewResponse>>> getGoodsReviewList(@Body Map<String, Object> map);


}
