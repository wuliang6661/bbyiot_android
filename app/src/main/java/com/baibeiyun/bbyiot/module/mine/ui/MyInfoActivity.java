package com.baibeiyun.bbyiot.module.mine.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.event.PictureEvent;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.mine.view.DateSelectUtil;
import com.baibeiyun.bbyiot.utils.FileUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.photo.PhotoGraphView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class MyInfoActivity extends BaseActivity {

    @BindView(R.id.act_my_info_tv_birthday)
    TextView tv_birthday;

    @BindView(R.id.act_my_info_iv_user_pic)
    ImageView iv_user_pic;

    @BindView(R.id.act_my_info_rb_sex1)
    RadioButton rb_sex1;

    @BindView(R.id.act_my_info_rb_sex2)
    RadioButton rb_sex2;
    private Bitmap bitmap;
    private PhotoGraphView photoGraphView;
    private String bitmapBase64String;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_my_info;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        setActionBarTitle("个人资料");

        mToolbar.inflateMenu(R.menu.addtion);
        mToolbar.getMenu().getItem(0).setTitle("保存");

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (mToolbar.getMenu().getItem(0) == item) {
                    ToastUtils.showToast("保存");
                    return true;
                }
                return false;
            }
        });

        rb_sex1.setChecked(true);
    }

    @OnClick({R.id.act_my_info_ll_birthday, R.id.act_my_info_iv_user_pic, R.id.act_my_info_tv_eidt_pic})
    void click(View view) {
        switch (view.getId()) {
            case R.id.act_my_info_iv_user_pic:
            case R.id.act_my_info_tv_eidt_pic:

                if (photoGraphView == null) {
                    photoGraphView = new PhotoGraphView(this);
                }
                photoGraphView.showPopWindow(iv_user_pic);
                break;

            case R.id.act_my_info_ll_birthday:
                DateSelectUtil.showDatePopu(this, new DateSelectUtil.SelectListener() {
                    @Override
                    public void onDateSelect(int year, int monthOfYear, int dayOfMonth) {
                        tv_birthday.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                        tv_birthday.setTextColor(Color.parseColor("#333333"));
                    }
                });
                break;
        }
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }


    /**
     * 获取裁剪的图片，将图片展示出来
     */
    @Subscribe
    public void savePiccture(PictureEvent event) {
        try {
            LogUtils.w("收到裁剪的图片 " + (event == null ? "event==null" : "event != null"));
            if (event != null) {
                bitmap = event.getBitmap();
                bitmapBase64String = FileUtils.bitmapToBase64(bitmap);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //将图片设置
                        if (bitmap != null) {
                            iv_user_pic.setImageBitmap(bitmap);
                        } else {
                            iv_user_pic.setImageResource(R.mipmap.icon_mine_user_logo);
                        }
                    }
                });
            }
        } catch (Exception e) {
            LogUtils.w(e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    /**
     * 接收其他页面返回的结果
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            LogUtils.w("activity回调 requestCode== " + requestCode + "，resultCode== " + resultCode);
            switch (requestCode) {
                //用户名
                case PhotoGraphView.SELECT_PIC_BY_PICK_PHOTO:// 1 相册
                    if (resultCode != 0) {
                        photoGraphView.doPhoto(requestCode, data);
                    }
                    break;

                case PhotoGraphView.SELECT_PIC_BY_TACK_PHOTO:// 2 拍照

                    try {
                        //resultCode为0的时候是返回，-1的时候拍照完成
                        if (resultCode != 0) {
                            photoGraphView.clipPicture();
                        }
                    } catch (Exception e) {
                        LogUtils.w(e.toString());
                    }
                    break;

            }

        } catch (Exception e) {
            LogUtils.w(e.getMessage());
        }

    }

    /**
     * ----------------权限回调-----------------------
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        LogUtils.w("权限 requestCode== " + requestCode);
        switch (requestCode) {
            case PhotoGraphView.PERMISSIONS_1://1//拍照-相机权限
                if (grantResults[0] != -1) {
                    LogUtils.w("--------------------");
                    photoGraphView.takePhoto();
                }
                break;
            case PhotoGraphView.PERMISSIONS_2://2//相册 --手机存储权限
                if (grantResults[0] != -1) {
                    photoGraphView.pickPhoto();
                }
                break;
            case PhotoGraphView.PERMISSIONS_3://3
                if (grantResults[0] != -1) {
                    photoGraphView.clipPicture();
                }
                break;


        }
    }
}
