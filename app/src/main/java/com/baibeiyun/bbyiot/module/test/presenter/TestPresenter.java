package com.baibeiyun.bbyiot.module.test.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.test.contract.TestContract;

public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {

    public TestPresenter(Activity mActivity) {
        super(mActivity);
    }

}
