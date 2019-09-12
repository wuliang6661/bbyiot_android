package com.baibeiyun.bbyiot.module.mine.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.FeedbackTypeResponse;
import com.baibeiyun.bbyiot.model.request.AddFeedbackRequest;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.mine.contract.FeedbackContract;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.List;

public class FeedbackPresenter extends BasePresenter<FeedbackContract.View> implements FeedbackContract.Presenter {

    public FeedbackPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getUserFeedbackType() {
        mView.showLoading(null);
        dataManager.getUserFeedbackType()
                .subscribe(new BaseSubscriber<BaseResponse<List<FeedbackTypeResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<FeedbackTypeResponse>> response) {
                        mView.hideLoading();

                        mView.getUserFeedbackTypeFinish(response.getData());
                    }
                });
    }

    @Override
    public void addUserFeedback(AddFeedbackRequest request) {
        mView.showLoading(null);
        dataManager.addUserFeedback(request)
                .subscribe(new BaseSubscriber<BaseResponse>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast("提交失败: " + errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse response) {
                        mView.hideLoading();
                        ToastUtils.showToast("提交成功");
                        mView.addUserFeedbackFinish();
                    }
                });
    }

    @Override
    public void fileUpload(List<String> list) {
        mView.showLoading(null);
        dataManager.fileUpload3(list)
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
