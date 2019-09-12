package com.baibeiyun.bbyiot.module.mine.contract;

import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface ChangePswContract {

    interface Presenter extends IPresenter<View> {
        void changeLoginPsw(String oldPsw, String newPsw);
    }

    interface View extends IView {
        void changeLoginPswFinish();
    }
}
