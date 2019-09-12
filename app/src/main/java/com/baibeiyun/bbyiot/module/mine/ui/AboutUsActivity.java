package com.baibeiyun.bbyiot.module.mine.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.utils.SystemTool;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {
    @BindView(R.id.act_about_us_tv_version)
    TextView tv_version;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_about_us;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("关于我们");

        tv_version.setText("当前版本：V" + SystemTool.getAppVersionName(this));
    }

    @OnClick({R.id.act_about_us_ll_updata_version})
    void clcik(View view) {
        switch (view.getId()) {
            case R.id.act_about_us_ll_updata_version:
                ToastUtils.showToast("当前已是最新版本");
                break;
        }
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }
}
