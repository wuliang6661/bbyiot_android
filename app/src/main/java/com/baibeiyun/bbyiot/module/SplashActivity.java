package com.baibeiyun.bbyiot.module;


import android.os.Bundle;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.DataManager;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.login.ui.LoginActivity;
import com.baibeiyun.bbyiot.module.main.ui.MainActivity;
import com.baibeiyun.bbyiot.utils.ActivityUtils;

public class SplashActivity extends BaseActivity {
    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_splash;
    }

    @Override
    protected void initViewsAndEvents() {

        //startActivity(new Intent(this, DemoActivity.class));
        if (DataManager.getsInstance().isLogin()) {
            ActivityUtils.startActivity(this,MainActivity.class);
        } else {
            ActivityUtils.startActivity(this,LoginActivity.class);
        }
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }


}
