package com.baibeiyun.bbyiot.module.mine.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.DataManager;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.login.ui.LoginActivity;
import com.baibeiyun.bbyiot.module.mine.contract.ChangePswContract;
import com.baibeiyun.bbyiot.module.mine.presenter.ChangePswPresenter;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.BaseTimer;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePswActivity extends BaseActivity<ChangePswPresenter> implements ChangePswContract.View {

    @BindView(R.id.act_change_psw_et_old_psw)
    EditText et_old_psw;

    @BindView(R.id.act_change_psw_et_new_psw)
    EditText et_new_psw;

    @BindView(R.id.act_change_psw_et_confirm_psw)
    EditText et_confirm_psw;

    private String mOldPsw;
    private String mNewPsw;
    private String mConfirmPsw;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_change_psw;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("修改密码");
    }


    @OnClick({R.id.act_change_psw_btn_confirm_edit})
    void clcik(View view) {

        mOldPsw = et_old_psw.getText().toString().trim();
        mNewPsw = et_new_psw.getText().toString().trim();
        mConfirmPsw = et_new_psw.getText().toString().trim();

        if (StringUtils.isEmpty(mOldPsw) || StringUtils.isEmpty(mNewPsw) || StringUtils.isEmpty(mConfirmPsw)) {
            ToastUtils.showToast("请将信息填写完毕后再提交");
            return;
        }

        if (!mNewPsw.equals(mConfirmPsw)) {
            ToastUtils.showToast("两次输入的密码不一致，请检查");
            return;
        }

        mPresenter.changeLoginPsw(mOldPsw, mNewPsw);
    }

    @Override
    protected ChangePswPresenter getPresenter() {
        return new ChangePswPresenter(this);
    }

    @Override
    public void changeLoginPswFinish() {
        BaseTimer.getInstans().killTimer();
        DataManager.getsInstance().saveLoginState(false);
        ActivityUtils.startActivityOrFinish(this, LoginActivity.class);
    }
}
