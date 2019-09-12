package com.baibeiyun.bbyiot.view.photo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.module.tools.ClipPictureActivity;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.io.File;

/**
 * 图片选择(有裁剪的、相册只能单选)
 */
public class PhotoGraphView implements View.OnClickListener {

    private PopupWindow popupWindow;
    private View popupWindow_view;
    private Activity mActivity;

    private String picPath;

    private Uri photoUri;


    //拍照-相机权限
    public static final int PERMISSIONS_1 = 1;
    //相册 --手机存储权限
    public static final int PERMISSIONS_2 = 2;
    //
    public static final int PERMISSIONS_3 = 3;

    //二维码扫码-相机权限
    public static final int PERMISSIONS_4 = 4;

    /**
     * 使用相册中的图片
     */
    public static final int SELECT_PIC_BY_PICK_PHOTO = 1;
    /**
     * 使用照相机拍照获取图片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 2;


    public PhotoGraphView(Activity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 显示popupWindow
     */
    public void showPopWindow(View v) {

        InputMethodManager inputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (mActivity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (mActivity.getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }

        popupWindow_view = initPopwindowView();
        popupWindow = new PopupWindow(popupWindow_view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setAnimationStyle(R.anim.slide_in_from_bottom);
        popupWindow.showAtLocation(v, Gravity.LEFT, 0, 0);
        popupWindow_view.setFocusable(true);
        popupWindow.setFocusable(true);
        popupWindow_view.setFocusableInTouchMode(true);

        // 点击其他地方消失
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                closePopwindow();
                return false;
            }
        });
        // 点击返回键让 popupWindow消失
        popupWindow_view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    closePopwindow();
                    return true;
                }
                return false;
            }
        });

    }

    /**
     * 隐藏popupWindow
     */
    private void closePopwindow() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow_view.setFocusable(false);
            popupWindow_view.setFocusableInTouchMode(false);
            popupWindow.setFocusable(false);
            popupWindow.dismiss();
            popupWindow = null;
            WindowManager.LayoutParams params = mActivity.getWindow().getAttributes();
            params.alpha = 1f;
            mActivity.getWindow().setAttributes(params);
        }
    }


    /***	初始化popupWindow		*/
    @SuppressLint("InflateParams")
    private View initPopwindowView() {
        View view = mActivity.getLayoutInflater().inflate(R.layout.activity_upload_img, null, false);
        view.findViewById(R.id.camera).setOnClickListener(this);
        view.findViewById(R.id.native_picture).setOnClickListener(this);
        view.findViewById(R.id.cancel).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:// 拍照
                // 激活系统图库，选择一张图片
                takePhoto();

                closePopwindow();
                break;
            case R.id.native_picture:// 相册
                pickPhoto();
                closePopwindow();
                break;
            case R.id.cancel:
                closePopwindow();
            default:
                break;
        }
    }


    /**
     * 相机 ---> 调起相机
     */
    private void camera() {
        try {
            // 执行拍照前，应该先判断SD卡是否存在
            String SDState = Environment.getExternalStorageState();
            if (SDState.equals(Environment.MEDIA_MOUNTED)) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //Uri uri = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath(), "camera.jpg"));
                BaseApplication.currentTime = System.currentTimeMillis();
                //解决华为手机不能打开相机
                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + BaseApplication.currentTime + ".jpg");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                Uri uri = FileProvider.getUriForFile(mActivity, "com.baibeiyun.bbyiot.FileProvider", file);//通过FileProvider创建一个content类型的Uri

                // 将拍取的照片保存到指定URI
                //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "camera.jpg")));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);


                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                LogUtils.w("跳转相机");
                mActivity.startActivityForResult(Intent.createChooser(intent, "标题"), SELECT_PIC_BY_TACK_PHOTO);

            } else {
                ToastUtils.showToast("内存卡不存在");
            }
        } catch (Exception e) {
            ToastUtils.showToast("错误: " + e.toString());
            LogUtils.w(e.toString());
        }
    }


    /**
     * 相册 --> 调起相册
     */
    private void photoAlbum() {
        //权限已经被授予，在这里直接写要执行的相应方法即可
        // 选择照片的时候也一样，我们用Action为Intent.ACTION_GET_CONTENT，
        // 有些人使用其他的Action但我发现在有些机子中会出问题，所以优先选择这个
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivity.startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);

    }


    /***
     * 判断是否有相册的权限---> 存储
     */
    public void pickPhoto() {
        //判断是否有存储权限，没有让用户授权
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            LogUtils.w("没有存储授权");
            ToastUtils.showToast("没有获取到相册存储权限");
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_2);
        } else {
            photoAlbum();
        }

    }

    /**
     * 相机的权限判断
     */
    public void takePhoto() {
        //判断是否有相机权限，未被授予，需要申请！
//        if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.CAMERA)) {
//
//            LogUtils.w("没有相机权限");
//            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_1);
//        } else {
//            camera();
//        }

        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(mActivity,"您申请了动态权限", Toast.LENGTH_SHORT).show();
            //如果有了相机的权限有调用相机
            camera();

        } else {
            //否则去请求相机权限
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_1);

        }

    }


    /***
     *  存储权限
     */
    public void clipPicture() {
        LogUtils.w("拍照完成，判断是否有存储权限");
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            LogUtils.w("没有存储授权");
            ToastUtils.showToast("没有获取到相册存储权限");
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_3);
        } else {
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp/" + BaseApplication.currentTime + ".jpg";
            startActivity(ClipPictureActivity.class, new String[]{"picPath"},
                    new String[]{absolutePath});
        }
    }

    /**
     * 选择图片后，获取图片的路径
     *
     * @param requestCode
     * @param data
     */
    public void doPhoto(int requestCode, Intent data) {
        if (requestCode == SELECT_PIC_BY_PICK_PHOTO) // 从相册取图片，有些手机有异常情况，请注意
        {
            if (data == null) {
                ToastUtils.showToast("选择图片文件出错");
                return;
            }
            photoUri = data.getData();
            if (photoUri == null) {
                ToastUtils.showToast("选择图片文件出错");
                return;
            }
        }

        String[] pojo = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = mActivity.managedQuery(photoUri, pojo, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
            cursor.moveToFirst();
            picPath = cursor.getString(columnIndex);
            if (Build.VERSION.SDK_INT < 14) {
                cursor.close();
            }
        } else {
            picPath = photoUri.getPath();
        }
        if (picPath == null) {
            picPath = getImagePathFromURI(photoUri);
        }

        if (picPath != null) {
            if (picPath.endsWith(".png") || picPath.endsWith(".PNG")
                    || picPath.endsWith(".jpg") || picPath.endsWith(".JPG")) {

                startActivity(ClipPictureActivity.class, new String[]{"picPath"}, new String[]{picPath});
            }
        } else {
            ToastUtils.showToast("选择图片文件出错");
        }
    }


    /**
     * 解决华为手机获取不到图片路径的问题
     *
     * @param uri
     * @return
     */
    public String getImagePathFromURI(Uri uri) {
        Cursor cursor = mActivity.getContentResolver().query(uri, null, null, null, null);
        String path = null;
        if (cursor != null) {
            cursor.moveToFirst();
            String document_id = cursor.getString(0);
            document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
            cursor.close();

            cursor = mActivity.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
            if (cursor != null) {
                cursor.moveToFirst();
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.close();
            }
        }
        return path;
    }

    // 页面带参数跳转
    public void startActivity(Class<?> className, String[] flags,
                              String[] values) {
        Intent intent = new Intent(mActivity, className);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if (flags != null && flags.length > 0) {
            for (int i = 0; i < flags.length; i++) {
                intent.putExtra(flags[i], values[i]);
            }
        }
        mActivity.startActivity(intent);
    }

}