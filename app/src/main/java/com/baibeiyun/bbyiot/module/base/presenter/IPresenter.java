package com.baibeiyun.bbyiot.module.base.presenter;


import com.baibeiyun.bbyiot.module.base.view.IView;

public interface IPresenter<T extends IView> {

    void attachView(T iView);

    void detachView();
}
