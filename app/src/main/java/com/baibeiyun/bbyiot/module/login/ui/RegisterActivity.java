package com.baibeiyun.bbyiot.module.login.ui;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.utils.BaseTimer;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.DefindTextviewListener;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.act_register_et_moblie)
    EditText et_moblie;

    @BindView(R.id.act_register_et_yzm)
    EditText et_yzm;

    @BindView(R.id.act_register_et_psw)
    EditText et_psw;

    @BindView(R.id.act_register_tv_send_yzm)
    TextView tv_send_yzm;

    @BindView(R.id.act_register_cb_check_box)
    CheckBox checkBox;

    @BindView(R.id.act_register_btn_register)
    Button btn_register;

    private DefindTextviewListener moblieListener;
    private DefindTextviewListener pswListener;

    private DefindTextviewListener yzmListener;

    private boolean mobliePassed;
    private boolean pswPassed;
    private boolean yzmPassed;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_register;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        try {
            // 监听,账号
            moblieListener = new DefindTextviewListener() {
                @Override
                public void afterTextChanged(Editable s) {
                    String text = s.toString();
                    if (text.length() == 11) {
                        mobliePassed = true;
                    } else {
                        mobliePassed = false;
                    }
                    changeRegisterButtonState();
                }
            };
            et_moblie.addTextChangedListener(moblieListener);

            pswListener = new DefindTextviewListener() {
                @Override
                public void afterTextChanged(Editable s) {
                    String text = s.toString();
                    if (text.length() < 6 || text.length() > 18) {
                        pswPassed = false;
                    } else {
                        pswPassed = true;
                    }
                    changeRegisterButtonState();
                }
            };
            et_psw.addTextChangedListener(pswListener);

            yzmListener = new DefindTextviewListener() {
                @Override
                public void afterTextChanged(Editable s) {
                    String text = s.toString();
                    if (text.length() >= 4) {
                        yzmPassed = true;
                    } else {
                        yzmPassed = false;
                    }
                    changeRegisterButtonState();
                }
            };
            et_yzm.addTextChangedListener(yzmListener);

        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }


    @OnClick({R.id.act_register_tv_send_yzm, R.id.act_register_btn_register,R.id.act_register_cb_check_box})
    void click(View view) {
        switch (view.getId()) {
            case R.id.act_register_tv_send_yzm:
                String userMobie = et_moblie.getText().toString().trim();
                if (StringUtils.isEmpty(userMobie) || userMobie.length() < 11) {
                    ToastUtils.showToast("请输入正确的手机号");
                    return;
                }
                //发送验证码
                startTimer(userMobie);
                break;

            case R.id.act_register_btn_register:

                break;

            case R.id.act_register_cb_check_box:
                changeRegisterButtonState();
                break;
        }
    }

    /**
     * 控制注册按钮的颜色状态
     */
    private void changeRegisterButtonState() {
        boolean isChecked = checkBox.isChecked();
        if (mobliePassed && pswPassed && yzmPassed && isChecked) {
            btn_register.setEnabled(true);
        } else {
            btn_register.setEnabled(false);
        }

        if (mobliePassed) {
            tv_send_yzm.setEnabled(true);
        } else {
            tv_send_yzm.setEnabled(false);
        }

    }

    /**
     * 发送验证码倒计时读秒
     */
    private int time = 60;
    private boolean isTiming = false;

    private void startTimer(String userMobie) {
        if (isTiming) {
            ToastUtils.showToast("短时间内不能重复获取");
            return;
        }
        if (!StringUtils.isEmpty(userMobie)) {
            //mPresenter.sendYzm(userMobie);
        } else {
            ToastUtils.showToast("服务器异常");
            return;
        }

        isTiming = true;

        /**
         * 启动定时器
         */
        BaseTimer.getInstans().startInterval(1000, new BaseTimer.TimerCallBack() {
            @Override
            public void callback() {
                if (time > 0) {
                    tv_send_yzm.setEnabled(false);
                    tv_send_yzm.setText(time + "秒");
                    //tv_send_yzm.setTextColor(getResources().getColor(R.color.color_9));
                    time--;
                }

                if (isTiming && time <= 0) {
                    isTiming = false;
                    time = 60;
                    tv_send_yzm.setText("重新发送");
                    tv_send_yzm.setEnabled(true);
                    isTiming = false;

                    if (BaseTimer.getInstans().isRunning()) {
                        BaseTimer.getInstans().killTimer();
                    }
                }

            }
        });
    }
}
