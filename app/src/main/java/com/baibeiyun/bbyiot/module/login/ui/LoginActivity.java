package com.baibeiyun.bbyiot.module.login.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.module.base.BaseAppManager;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.login.contract.LoginContract;
import com.baibeiyun.bbyiot.module.login.presenter.LoginPresenter;
import com.baibeiyun.bbyiot.module.main.ui.MainActivity;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.SpUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.DefindTextviewListener;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    //账号
    @BindView(R.id.act_login_new_et_account)
    EditText et_account;

    @BindView(R.id.act_login_new_et_psw)
    EditText et_psw;


    @BindView(R.id.act_login_new_btn_login)
    Button btn_login;


    @BindView(R.id.act_login_new_tv_forget_psw)
    TextView tv_forget_psw;

    private DefindTextviewListener accountListener;
    private DefindTextviewListener pswListener;


    private boolean accountPassed = false;// 电话号码是否不为空 并且长度大于等于1

    private boolean pswPassed = false;// 密码

    //双击点击退出，记录上次点击时间
    private double clicktime;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_login;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        try {
            // 监听,账号
//            accountListener = new DefindTextviewListener() {
//                @Override
//                public void afterTextChanged(Editable s) {
//                    super.afterTextChanged(s);
//                    String text = s.toString();
//                    if (text.length() > 0) {
//                        accountPassed = true;
//                    } else {
//                        accountPassed = false;
//                    }
//                    changeLoginButtonState();
//                }
//            };
//            et_account.addTextChangedListener(accountListener);
//
//            pswListener = new DefindTextviewListener() {
//                @Override
//                public void afterTextChanged(Editable s) {
//                    super.afterTextChanged(s);
//                    String text = s.toString();
//                    if (text.length() < 1 || text.length() > 18) {
//                        pswPassed = false;
//                    } else {
//                        pswPassed = true;
//                    }
//                    changeLoginButtonState();
//                }
//            };
//            et_psw.addTextChangedListener(pswListener);


            String userName = SpUtils.getString(IConstant.KEY_LOGIN_NAME, "");
            if (!StringUtils.isEmpty(userName)) {
                et_account.setText(userName);
                et_account.setSelection(userName.length());
            }
            String userPsw = SpUtils.getString(IConstant.KEY_LOGIN_PSW, "");
            if (!StringUtils.isEmpty(userPsw)) {
                et_psw.setText(userPsw);
            }

        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }


    @OnClick({R.id.act_login_im_xx, R.id.act_login_new_tv_forget_psw, R.id.act_login_new_btn_login,
            R.id.act_login_new_tv_xieyi
    })
    void click(View view) {
        switch (view.getId()) {
            case R.id.act_login_im_xx:
                et_account.setText("");
                break;


            //忘记密码
            case R.id.act_login_new_tv_forget_psw:
                Bundle bundle = new Bundle();
                //bundle.putInt(EditLoginPwdActivity.KEY_EDIT_PSW_TYPE, 1);
                //readyGo(EditLoginPwdActivity.class, bundle);
                break;

            //协议 -->
            case R.id.act_login_new_tv_xieyi:
//                String url = IConstant.H5_URL + "h5/html/disclaimer.html";
//                Bundle bundlexieyi = new Bundle();
//                bundlexieyi.putString(WebViewActivity.KEY_TITLE, "服务协议");
//                bundlexieyi.putString(WebViewActivity.KEY_URL, url);
//                readyGo(WebViewActivity.class, bundlexieyi);
                break;

            //登录
            case R.id.act_login_new_btn_login:
                String userName = et_account.getText().toString().trim();

                String psw = et_psw.getText().toString().trim();
                if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(psw)) {
                    ToastUtils.showToast("账号或密码不能为空");
                    return;
                }
                mPresenter.login(userName, psw);

                //readyGo(DemoActivity.class);
                break;
        }
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }


    /**
     * 控制注册按钮的颜色状态
     */
    private void changeLoginButtonState() {
        //密码登录
        if (accountPassed && pswPassed) {
            btn_login.setEnabled(true);
        } else {
            btn_login.setEnabled(false);
        }
    }


    @Override
    public void loginFinish(boolean isSuccess) {
        if(isSuccess){
            ActivityUtils.startActivity(this,MainActivity.class);
        }
        //readyGo(DemoActivity.class);
    }


    /**
     * 双击退出app
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            outApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出APP
     */
    private void outApp() {
        if (DateUtils.getCurrentTimeStamp() - clicktime < 3000) {
            this.finish();
            BaseAppManager.getInstance().clear();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } else {
            showMessage("再按一次退出");
            clicktime = DateUtils.getCurrentTimeStamp();
        }
    }

}
