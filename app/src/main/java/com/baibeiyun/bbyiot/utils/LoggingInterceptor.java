package com.baibeiyun.bbyiot.utils;


import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by caih on 2019/5/6.
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();

        long t1 = System.nanoTime();//请求发起的时间

        Log.w("liugang", String.format("发送请求 %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        okhttp3.Response response = chain.proceed(request);

        long t2 = System.nanoTime();//收到响应的时间

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);


        Log.w("liugang", String.format("接收响应: [  %s  ] %n返回json:【 %n  %s  %n】 %.1fms%n%s",
                response.request().url(),
                responseBody.string(),
                (t2 - t1) / 1e6d,
                response.headers()));

        LogUtils.w("-------------------------------------------------------");

        return response;
    }
}
