package com.baibeiyun.bbyiot.module.base.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.model.DataManager;
import com.baibeiyun.bbyiot.module.base.view.IView;

import org.reactivestreams.Subscription;


public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    protected Activity mActivity;
    protected V mView;

    protected DataManager dataManager;

    public BasePresenter(Activity mActivity) {
        this.mActivity = mActivity;
        this.dataManager = DataManager.getsInstance();
    }

    @Override
    public void attachView(V iView) {
        this.mView = iView;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

}
