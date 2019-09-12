package com.baibeiyun.bbyiot.module.base.presenter;

import android.app.Activity;


import com.baibeiyun.bbyiot.model.DataManager;
import com.baibeiyun.bbyiot.module.base.contract.DefaultContract;



public class DefaultPresenter extends BasePresenter<DefaultContract.View> implements DefaultContract.Presenter {

    public DefaultPresenter(Activity mActivity) {
        super(mActivity);
    }


}
