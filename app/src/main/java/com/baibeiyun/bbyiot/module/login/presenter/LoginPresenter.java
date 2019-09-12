package com.baibeiyun.bbyiot.module.login.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.LoginResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.login.contract.LoginContract;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.MD5;
import com.baibeiyun.bbyiot.utils.SpUtils;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void login(final String userName, final String userPsw) {

        mView.showLoading(null);

        String md5Psw = MD5.Md5(MD5.Md5(userPsw) + "bby");
        LogUtils.w("userName== " + userName + ",userPsw== " + userPsw + ", md5Psw == " + md5Psw);

        dataManager.login(userName, md5Psw)
                .subscribe(new BaseSubscriber<BaseResponse<LoginResponse>>() {

                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.showMessage(errorMsg);
                        mView.loginFinish(false);
                    }

                    @Override
                    public void onSuccess(BaseResponse<LoginResponse> baseResponse) {
                        mView.hideLoading();
                        if (baseResponse != null) {
                            LogUtils.w("status = " + baseResponse.getCode() + " , meassge = " + baseResponse.getMsg());
                            if (baseResponse.getCode() == 200) {

                                SpUtils.put(IConstant.KEY_LOGIN_NAME, userName);
                                SpUtils.put(IConstant.KEY_LOGIN_PSW, userPsw);
                                dataManager.saveLoginInfo(baseResponse.getData());
                                SpUtils.put(IConstant.KEY_LOGIN_TOKEN, baseResponse.getData().getToken());

                                dataManager.saveLoginState(true);
                                mView.loginFinish(true);
                            } else {
                                mView.showMessage(baseResponse.getMsg());
                                mView.loginFinish(false);
                            }
                        }

                    }
                });
    }
}
