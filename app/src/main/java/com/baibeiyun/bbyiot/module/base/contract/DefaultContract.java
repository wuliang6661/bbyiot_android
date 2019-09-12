package com.baibeiyun.bbyiot.module.base.contract;


import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface DefaultContract {

    interface View extends IView {

    }

    interface Presenter extends IPresenter<View> {
    }

}
