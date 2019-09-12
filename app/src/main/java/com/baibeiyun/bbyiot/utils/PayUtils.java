package com.baibeiyun.bbyiot.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.baibeiyun.bbyiot.model.DataManager;
import com.baibeiyun.bbyiot.model.Response.CommonResponse;
import com.baibeiyun.bbyiot.module.login.ui.LoginActivity;
import com.google.gson.internal.LinkedTreeMap;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 支付工具类
 */
public class PayUtils {

    public static boolean isPaying = false;

    public static void pay(String orderID, final Activity activity) {
        if (isPaying) {
            return;
        }
        isPaying = true;
        DataManager.getsInstance().getPayResult(orderID).subscribe(new Observer<CommonResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CommonResponse commonResponse) {
                if (commonResponse.code != 200) {
                    ToastUtils.showToast(activity, commonResponse.message);
                    return;
                }
                if (commonResponse.code == 401) {
                    ActivityUtils.startActivity(activity, LoginActivity.class);
                    return;
                }
                final LinkedTreeMap<String, String> data = (LinkedTreeMap) commonResponse.data;
                if (data == null) {
                    ToastUtils.showToast(activity, commonResponse.message);
                    return;
                }
                Log.d("payUtils", commonResponse.code + commonResponse.data.toString());
                final IWXAPI iwxapi = WXAPIFactory.createWXAPI(activity, null); //初始化微信api
                iwxapi.registerApp(data.get("appid")); //注册appid
                if (!iwxapi.isWXAppInstalled()) {
                    ToastUtils.showToast("请您先安装微信客户端！");
                    return;
                }
                //一定注意要放在子线程
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayReq request = new PayReq(); //调起微信的对象
                        //这几个参数的值，正是上面我们说的统一下单接口后返回来的字段，我们对应填上去即可
                        request.appId = data.get("appid");
                        request.partnerId = data.get("partnerid");
                        request.prepayId = data.get("prepayid");
                        request.packageValue = "Sign=WXPay";
                        request.nonceStr = data.get("noncestr");
                        request.timeStamp = data.get("timestamp");
                        request.sign = data.get("sign");
                        iwxapi.sendReq(request);//发送调起微信的请求
                    }
                };
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }

            @Override
            public void onError(Throwable e) {
                if (!TextUtils.isEmpty(e.getMessage())) {
                    ToastUtils.showToast(e.getMessage());
                }
            }

            @Override
            public void onComplete() {
                isPaying = false;
            }
        });
    }
}
