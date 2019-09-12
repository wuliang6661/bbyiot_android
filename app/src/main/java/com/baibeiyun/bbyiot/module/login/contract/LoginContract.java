package com.baibeiyun.bbyiot.module.login.contract;

import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface LoginContract {

    interface Presenter extends IPresenter<View>{

        void login(String userName, String userPsw);
    }

    interface View extends IView{
        void loginFinish(boolean isSuccess);
    }
}
