package com.baibeiyun.bbyiot.module.mine.ui;

import android.os.Bundle;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;

//售后服务
public class AfterSaleActivity extends BaseActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_after_sale;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("申请售后");
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }
}
