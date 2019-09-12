package com.baibeiyun.bbyiot.utils;

import android.app.ActivityManager;
import android.content.Context;

import com.baibeiyun.bbyiot.application.BaseApplication;


/*
 *  @项目名：  app 
 *  @包名：    com.gddxit.smartwater.croe.utils
 *  @文件名:   AppMemoryUtils
 *  @创建者:   liugang
 *  @创建时间:  2017/4/15 20:15
 *  @描述：    app内存管理类
 */
public class AppMemoryUtils {

    /**
     * 获取手机给每个APP最大分配的内存大小
     * 返回的大小以m为单位
     */
    public static double getAppMemorySize() {
        ActivityManager activityManager = (ActivityManager) BaseApplication.getContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        return activityManager.getMemoryClass();
    }

    /**
     * 获取手机  申请的大的内存大小
     * 可以在manifest文件 application中申请===> android:largeHeap="true"
     * 返回的大小以m为单位
     */
    public static double getAppLargeMemorySize() {
        ActivityManager activityManager = (ActivityManager) BaseApplication
                .getContext().getSystemService(Context.ACTIVITY_SERVICE);
        return activityManager.getLargeMemoryClass();
    }

    /**
     * 获取当前APP已经使用的内存大小
     *返回的大小以m为单位
     * @return
     */
    public static double getAppTotalMemory() {
        long memory = Runtime.getRuntime().totalMemory();
        double totalMemory =memory / 1024 / 1024;
        return totalMemory;
    }

    /**
     * 获取当前APP还能使用的内存大小
     * 返回的大小以m为单位
     */
    public static double getAppAvailMemory() {
        double availMemory = getAppMemorySize() - getAppTotalMemory();
        LogUtils.w("当前可使用的内存=" + availMemory
                + ", MemorySize==" + getAppMemorySize()
                + ", TotalMemory== " + getAppTotalMemory()
                + ", 手机最大使用内存是=" + getAppLargeMemorySize());
        return availMemory;
    }
}
