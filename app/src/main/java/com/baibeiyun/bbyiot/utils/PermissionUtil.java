package com.baibeiyun.bbyiot.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;

import java.util.ArrayList;

/**
 * Created by hdc on 2016/11/9.
 *
 */
public class PermissionUtil {
    public static final int ASK_FOR_ALERT=1;
    public static final int PERMISSION_REQUEST_SINGLE = 10;
    public static final int PERMISSION_REQUEST_GROUP = 11;

    /**
     * 检查单个权限
     * @param context
     * @param permission 权限
     * @param checkPermissionSingleCallBack 检查结果监听
     */
    public static void checkPermissionSingle(Context context, String permission, CheckPermissionSingleCallBack checkPermissionSingleCallBack){
        if(context == null || TextUtils.isEmpty(permission)){
            return;
        }
        if((ContextCompat.checkSelfPermission(context, permission)== PackageManager.PERMISSION_GRANTED)){
            if(checkPermissionSingleCallBack !=null){
                checkPermissionSingleCallBack.onCheckGranted(new String[]{permission});
            }
        }else {
            if(checkPermissionSingleCallBack != null){
                checkPermissionSingleCallBack.onCheckDented(new String[]{permission});
            }
        }
    }

    /**
     * 单个权限请求
     * @param activity activity
     * @param permission 权限
     * @param requestPermissionSingleCallBack 请求结果回调接口
     */
    public static void requestPermissionSingle(BaseActivity activity, String permission, RequestPermissionSingleCallBack requestPermissionSingleCallBack){
        if(TextUtils.isEmpty(permission) || activity == null){
            return;
        }
        String[] permissions = new String[]{permission};

        activity.setPermissionCallBackSingle(requestPermissionSingleCallBack);

        if(Build.VERSION.SDK_INT>=23){
            ActivityCompat.requestPermissions(activity, permissions,PERMISSION_REQUEST_SINGLE);
        }
    }

    /**
     * 群组权限检查
     * @param context
     * @param permissions 权限集合
     * @param checkPermissionGroupCallBack 检查结果回调
     */
    public static void checkPermissionGroup(BaseActivity context, String[] permissions, CheckPermissionGroupCallBack checkPermissionGroupCallBack){
        if(context == null || permissions ==null || permissions.length==0){
            return ;
        }

        ArrayList<String> permissionListG =new ArrayList<>();
        ArrayList<String> permissionListD = new ArrayList<>();

        for(String s : permissions){
            if((ContextCompat.checkSelfPermission(context, s)== PackageManager.PERMISSION_GRANTED)){
                permissionListG.add(s);
            }else {
                permissionListD.add(s);
            }
        }

        //权限全部同意
        if(permissionListG.size() == permissions.length){
            String[] permissionsGAll =  permissionListG.toArray(new String[permissionListG.size()]);
            if(checkPermissionGroupCallBack!=null){
                checkPermissionGroupCallBack.onCheckGrantedAll(permissionsGAll);
            }
        }else {
            if(permissionListG.size()>0){
                String[] permissionsG =  permissionListG.toArray(new String[permissionListG.size()]);
                if(checkPermissionGroupCallBack!=null){
                    checkPermissionGroupCallBack.onCheckGranted(permissionsG);
                }
            }

            if(permissionListD.size()>0){
                String[] permissionD = permissionListD.toArray(new String[permissionListD.size()]);
                if(checkPermissionGroupCallBack!=null){
                    checkPermissionGroupCallBack.onCheckDented(permissionD);
                }
            }
        }

    }

    /**
     * 群组权限申请
     * @param activity BaseActivity
     * @param permissions 权限集合
     */
    public static void requestPermissionGroup(BaseActivity activity, String[] permissions, RequestPermissionGroupCallBack requestPermissionGroupCallBack){
        if(activity == null || permissions==null || permissions.length==0){
            return;
        }

        activity.setPermissionCallBackGroup(requestPermissionGroupCallBack);

        if(Build.VERSION.SDK_INT>=23){
            ActivityCompat.requestPermissions(activity, permissions,PERMISSION_REQUEST_GROUP);
        }
    }

    /**
     * 接口
     */
    public interface CheckPermissionGroupCallBack{
        void onCheckGranted(String[] permissions);
        void onCheckDented(String[] permissions);
        void onCheckGrantedAll(String[] permissions);
    }

    /**
     * 接口
     */
    public interface RequestPermissionGroupCallBack{
        void onRequestGranted(String[] permissions);
        void onRequestDented(String[] permissions);
        void onRequestGrantedAll(String[] permissions);
    }

    /**
     * 接口
     */
    public interface CheckPermissionSingleCallBack{
        void onCheckGranted(String[] permissions);
        void onCheckDented(String[] permissions);
    }

    /**
     * 接口
     */
    public interface RequestPermissionSingleCallBack{
        void onRequestGranted(String[] permissions);
        void onRequestDented(String[] permissions);
    }

    public boolean checkUsageStatsPermission(Context context){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), context.getPackageName());
            return mode == AppOpsManager.MODE_ALLOWED;
        }else {
            return true;
        }
    }

    public void askAlertPermission(Context context, Handler handler){
        if(Build.VERSION.SDK_INT>=23){
            if(!Settings.canDrawOverlays(context)){
                handler.sendEmptyMessage(ASK_FOR_ALERT);
            }
        }
    }
    public boolean checkAlertPermission(Context context){
        if(Build.VERSION.SDK_INT>=23){
            if(Settings.canDrawOverlays(context)){
               return true;
            }else {
                return false;
            }
        }else {
            return true;
        }
    }
}

