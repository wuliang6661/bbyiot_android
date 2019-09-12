package com.baibeiyun.bbyiot.application;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.SyncStateContract;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import cn.leo.messenger.MagicMessenger;

public class BaseApplication extends Application {

    private static Context mContext;
    public static long currentTime;

    public static  int messageDeviceNum = 0;
    public static  int messageOrderNum = 0;

    private static final String WX_APP_ID = "wxe9601fbdbc0b7989";
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        regToWx();
    }

    private void regToWx() {
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // 将该app注册到微信
                api.registerApp(WX_APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
        MagicMessenger.init(this);
    }


    public static Context getContext() {
        return mContext;
    }



    /**
     * 图片加载
     *
     * @param imageView
     * @param url
     */
    public static void loadImageView(final ImageView imageView, String url) {
        Glide.with(mContext)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                //.placeholder(R.mipmap.default_error2)
                //.error(R.mipmap.default_error2)
                .into(imageView);
    }

    /**
     * 图片加载
     *
     * @param imageView
     * @param url
     */
    public static void loadImageView(final ImageView imageView, String url,int res) {
        Glide.with(mContext)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .placeholder(res)
                .error(res)
                .into(imageView);
    }
}
