package com.baibeiyun.bbyiot.api;


import com.baibeiyun.bbyiot.api.httpexception.ResultException;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.module.login.ui.LoginActivity;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.BaseTimer;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.netstatus.NetUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class BaseSubscriber<T extends BaseResponse> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
//        BaseResponse baseResponse = (BaseResponse) t;
//
//
//        if (baseResponse.getCode() == 200) {
//            onSuccess(t);
//
//        } else if (baseResponse.getCode() == 421) {
//            ActivityUtils.startActivity(LoginActivity.class);
//        } else {
//            //ActivityUtils.startActivity(LoginActivity.class);
//            onFail(baseResponse.getMsg());
//        }

        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e.toString().equals("该账户不存在")) {
            ActivityUtils.startActivity(LoginActivity.class);
        }
        LogUtils.w("网络异常： " + e.toString());
        if (!NetUtils.isNetworkConnected(BaseApplication.getContext())) {
            onFail("请打开手机网络");
        } else if (e instanceof SocketTimeoutException || e instanceof TimeoutException) {
            onFail("网络请求超时，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            onFail("请求中断，请检查您的网络状态");
        } else if (e instanceof ResultException) {

            LogUtils.w("error == code!=200 code == " + ((ResultException) e).getErrCode());
            if (((ResultException) e).getErrCode() == 421) {
                ActivityUtils.startActivity(LoginActivity.class);
                BaseTimer.getInstans().killTimer();
                return;
            }
            if (((ResultException) e).getErrCode() == 401) {
                ActivityUtils.startActivity(LoginActivity.class);
                return;
            }
            onFail(((ResultException) e).getMsg());
        } else {
            onFail("" + e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }


    public abstract void onFail(String errorMsg);

    public abstract void onSuccess(T t);
}
