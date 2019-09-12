package com.baibeiyun.bbyiot.module.mine.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.mine.contract.ChangePswContract;
import com.baibeiyun.bbyiot.utils.MD5;
import com.baibeiyun.bbyiot.utils.ToastUtils;

public class ChangePswPresenter extends BasePresenter<ChangePswContract.View> implements ChangePswContract.Presenter {

    public ChangePswPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void changeLoginPsw(String oldPsw, String newPsw) {
        mView.showLoading(null);
        String oldPswMd5 = MD5.Md5(MD5.Md5(oldPsw) + "bby");
        String newPswMd5 = MD5.Md5(MD5.Md5(newPsw) + "bby");
        dataManager.changeLoginPsw(oldPswMd5, newPswMd5)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast("修改失败：" + errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        mView.hideLoading();
                        ToastUtils.showToast("修改成功请重新登录");
                        mView.changeLoginPswFinish();
                    }
                });

    }
}
