package com.baibeiyun.bbyiot.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;


public class ToastUtils {
    private static Toast mToast;

    public ToastUtils() {
    }

    public static void showToast(String msg) {
        showToast(BaseApplication.getContext(), msg, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showToast(String content, int toast_length) {
        showToast(BaseApplication.getContext(), content, toast_length);
    }

    public synchronized static void showToast(Context mContext, String msg, int toast_length) {
        try {
            if (StringUtils.isEmpty(msg)) {
                return;
            }
            hideToast();
            if (mContext instanceof Activity) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.toast_background,
                        (ViewGroup) ((Activity) mContext).findViewById(R.id.root_toast));
                TextView tv = (TextView) view.findViewById(R.id.tv_toast);
                tv.setText(msg);
                mToast = new Toast(mContext);
                mToast.setDuration(toast_length);
                mToast.setGravity(Gravity.CENTER, 0, 0);
                mToast.setView(view);
                mToast.show();
            } else {
                if (null == mToast) {
                    mToast = Toast.makeText(mContext, msg, toast_length);
                } else {
                    mToast.setText(msg);
                }
                mToast.setGravity(Gravity.CENTER, 0, 0);
                mToast.show();
            }
        }catch (Exception  e){
            LogUtils.w(e.toString());
        }
    }

    /**
     * 隐藏显示的Toast
     */
    public static void hideToast() {
        if (null != mToast) {
            mToast.cancel();
        }
        mToast = null;
    }


}
