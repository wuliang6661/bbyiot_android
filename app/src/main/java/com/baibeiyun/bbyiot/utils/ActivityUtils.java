package com.baibeiyun.bbyiot.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.module.WebActivity;

public class ActivityUtils {

    public static void startActivity(Context activity, Class<?> clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }

    public static void startActivity(Class<?> clazz) {
        Intent intent = new Intent(BaseApplication.getContext(), clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.getContext().startActivity(intent);
    }

    public static void startActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(BaseApplication.getContext(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        BaseApplication.getContext().startActivity(intent);
    }

    public static void startActivity(Activity activity, Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }

    public static void startActivityOrFinish(Activity activity, Class<?> clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void startActivityOrFinish(Activity activity, Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        activity.finish();
    }


    public static void startActivityForResult(Activity activity, Class<?> clazz, int requestCode) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Activity activity, Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }

        activity.startActivityForResult(intent, requestCode);
    }


    //携带结果返回上一页
    public static void finishByResult(Activity activity, String result, int resultCode) {
        activity.setResult(resultCode, new Intent().putExtra("result", result));
        activity.finish();
    }

    /**
     * 跳转 web
     *
     * @param url
     */
    public static void startWebActivity(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(IConstant.WEB_URL, url);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
