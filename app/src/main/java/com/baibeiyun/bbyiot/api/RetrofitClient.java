package com.baibeiyun.bbyiot.api;


import com.baibeiyun.bbyiot.api.httpexception.ResponseConverterFactory;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.model.DataManager;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.LoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by caih on 2019/5/29.
 */
public class RetrofitClient {
    private static final RetrofitClient mInstance = new RetrofitClient();
    private final OkHttpClient okHttpClient;

    private AppApi mAppApi;

    private AppShopApi mAppShopApi;

    public static RetrofitClient getInstance() {
        return mInstance;
    }


    private RetrofitClient() {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        //添加打印请求日志
        okhttpBuilder.addInterceptor(new LoggingInterceptor()).build();
        //okhttpBuilder.addInterceptor(new HttpLoggingInterceptor()).build();


        //添加token拦截器
        //Interceptor mInterceptor = new CustomHeaderInterceptor();

        LogUtils.w("添加头信息 ----------------------------->    ");
        OkHttpClient.Builder headersBuilder = okhttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                try {
                    Request request = chain.request();
                    Request.Builder builder = request.newBuilder();

                    builder.addHeader("Authorization", DataManager.getsInstance().getToken());

                    Request build = builder.build();
                    Response response = chain.proceed(build);
                    return response;
                } catch (Exception e) {
                    LogUtils.w(e.toString());
                    return chain.proceed(chain.request());
                }
            }
        });
        if (headersBuilder != null) {
            headersBuilder.build();
        }
        LogUtils.w("添加头信息 end ----------------------------->    ");


        okHttpClient = okhttpBuilder
                //.addInterceptor(mInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS) //超时时间
                .build();

    }

    public AppApi getAppApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(IConstant.BASE_URL)
                .addConverterFactory(ResponseConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        if (mAppApi == null) {
            mAppApi = retrofit.create(AppApi.class);
        }
        return mAppApi;
    }

    public AppShopApi getAppShopApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(IConstant.BASE_SHOP_URL)
                .addConverterFactory(ResponseConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        if (mAppShopApi == null) {
            mAppShopApi = retrofit.create(AppShopApi.class);
        }
        return mAppShopApi;
    }
}
