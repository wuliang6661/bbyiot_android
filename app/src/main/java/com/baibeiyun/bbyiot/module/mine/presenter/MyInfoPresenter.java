package com.baibeiyun.bbyiot.module.mine.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.mine.contract.MyInfoContract;
import com.baibeiyun.bbyiot.utils.LogUtils;

public class MyInfoPresenter extends BasePresenter<MyInfoContract.View>  implements MyInfoContract.Presenter{

    public MyInfoPresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void fileUpload(String  path) {
        mView.showLoading(null);
        dataManager.fileUpload2(path)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        LogUtils.w("fileUpload  onFail"+errorMsg);
                        mView.fileUploadFinish();
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {
                        LogUtils.w("fileUpload  onSuccess");

                        mView.fileUploadFinish();
                    }
                });
    }
}
