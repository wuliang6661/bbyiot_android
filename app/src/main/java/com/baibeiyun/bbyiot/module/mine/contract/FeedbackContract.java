package com.baibeiyun.bbyiot.module.mine.contract;

import com.baibeiyun.bbyiot.model.Response.FeedbackTypeResponse;
import com.baibeiyun.bbyiot.model.request.AddFeedbackRequest;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface FeedbackContract {

    interface Presenter extends IPresenter<View> {
        void getUserFeedbackType();
        void addUserFeedback(AddFeedbackRequest request);

        void fileUpload( List<String> list);
    }

    interface View extends IView {
        void getUserFeedbackTypeFinish(List<FeedbackTypeResponse> list);

        void fileUploadFinish();

        void addUserFeedbackFinish();
    }
}
