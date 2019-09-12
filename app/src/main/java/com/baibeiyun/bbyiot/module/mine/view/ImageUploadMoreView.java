package com.baibeiyun.bbyiot.module.mine.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.mine.adapter.PhotoAdapter;
import com.baibeiyun.bbyiot.utils.BitmapUtils;
import com.baibeiyun.bbyiot.utils.ListUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.NOScrollGirdview;
import com.baibeiyun.bbyiot.view.photo.PhotoGraphMoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * 上传图片(多张一起上传)
 */
public class ImageUploadMoreView extends LinearLayout {

    Activity mActivity;
    private List<Bitmap> photoFiles;
    private List<String> pathFiles = new ArrayList<>();

    private View popupWindow_view;
    PopupWindow popupWindow;
    private PhotoAdapter photoAdapter;

    public static boolean isEidt = false;

    private PhotoGraphMoreView photoGraphView;
    private NOScrollGirdview gridView;

    private int maxNum = 6;

    public ImageUploadMoreView(Context context) {
        super(context);
        initView(context, null);
    }

    public ImageUploadMoreView(Context context, int maxNum) {
        super(context);
        this.maxNum = maxNum;
        initView(context, null);
    }


    public ImageUploadMoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ImageUploadMoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    @SuppressLint("WrongConstant")
    private void initView(Context context, AttributeSet attrs) {

        this.mActivity = (Activity) context;

        //View view = View.inflate(context, R.layout.view_image_upload, null);
        View view = LayoutInflater.from(context).inflate(R.layout.view_image_upload, this, true);
        gridView = (NOScrollGirdview) view.findViewById(R.id.gv_photo);
        gridView.setVisibility(View.VISIBLE);
        photoFiles = new ArrayList<>();


        photoAdapter = new PhotoAdapter(mActivity, maxNum);

        gridView.setAdapter(photoAdapter);
        photoAdapter.updaData(photoFiles);

        /**
         * gridView 条目点击事件
         * */
        // gridView 条目点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                LogUtils.w(position + "" + photoFiles.size());

                if (position == photoFiles.size()) {//点击的是加号
                    if (photoFiles.size() >= maxNum) {
                        ToastUtils.showToast("一次最多只能添加" + maxNum + "个");
                        return;
                    } else {
                        //showPopWindow(view);
                        if (photoGraphView == null) {
                            photoGraphView = new PhotoGraphMoreView(mActivity, maxNum);

                        }

                        photoGraphView.showPopWindow(gridView);
                    }
                }
            }
        });

        // 删除按钮点击事件
        photoAdapter.setOnDeleteClickListener(new PhotoAdapter.DeleteClickListener() {
            @Override
            public void onItemClick(int i) {
                deletePic(i);
            }
        });
    }

    public void setData(String path) {
        LogUtils.w("-------------------------setData-----------");
        if (path != null) {
            Bitmap bitmap = BitmapUtils.pathToBitmap(path);
            photoFiles.add(bitmap);
            pathFiles.add(path);

        }
        photoAdapter.updaData(photoFiles);

    }

    public void setData(Bitmap photoBean) {
        LogUtils.w("-------------------------setData-----------");
        if (photoBean != null) {
            photoFiles.add(photoBean);
        }
        photoAdapter.updaData(photoFiles);

    }

    public List<Bitmap> getPhotoFiles() {
        if (ListUtils.isEmpty(photoFiles)) {
            return new ArrayList<>();
        }
        return photoFiles;
    }

    public List<String> getPathFiles() {
        return pathFiles;
    }

    public void deletePic(int position) {
        try {
            photoFiles.remove(position);
            pathFiles.remove(position);

            photoAdapter.updaData(photoFiles);
        } catch (Exception e) {
            LogUtils.w(e.getMessage());
        }
    }

    public void clear() {
        try {
            photoFiles.clear();
            pathFiles.clear();
        } catch (Exception e) {
            LogUtils.w(e.getMessage());
        }
    }


    /**
     * 权限判断
     */
    public void takePhoto() {
        if (photoGraphView != null) {
            photoGraphView.takePhoto();
        }
    }

    public void pickPhoto() {
        if (photoGraphView != null) {
            photoGraphView.pickPhoto();
        }
    }
}
