package com.baibeiyun.bbyiot.module.mine.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.GlideCacheUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.act_setting_tv_clear_cache)
    TextView tv_clear_cache;
    private GlideCacheUtil glideCacheUtil;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_setting;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("系统设置");

        glideCacheUtil = GlideCacheUtil.getInstance();
        if (null != glideCacheUtil) {
            tv_clear_cache.setText(glideCacheUtil.getCacheSize(this));
        }

        //获取缓存大小
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @OnClick({R.id.act_setting_ll_clear_cache, R.id.act_setting_ll_about_us, R.id.act_setting_ll_yijian,
            R.id.act_setting_ll_change_psw})
    void click(View view) {
        switch (view.getId()) {
            case R.id.act_setting_ll_clear_cache:
                //清理缓存
                clearCache();
                break;

            case R.id.act_setting_ll_about_us:
                ActivityUtils.startActivity(this, AboutUsActivity.class);
                break;

            case R.id.act_setting_ll_yijian:
                ActivityUtils.startActivity(this, FeedbackActivity.class);
                break;

            case R.id.act_setting_ll_change_psw:
                ActivityUtils.startActivity(this, ChangePswActivity.class);
                break;
        }
    }


    private void clearCache() {
        showLoading("正在清除");
        if (glideCacheUtil == null) {
            glideCacheUtil = new GlideCacheUtil();
        }
        glideCacheUtil.clearImageDiskCache(this, new GlideCacheUtil.ClearListener() {
            @Override
            public void onSuccess() {
                hideLoading();
                showMessage("清除完成");
                tv_clear_cache.setText(glideCacheUtil.getCacheSize(SettingActivity.this));
            }

            @Override
            public void onFailure() {
                hideLoading();
            }
        });
    }

}
