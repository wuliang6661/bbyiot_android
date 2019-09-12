package com.baibeiyun.bbyiot.module.mine.contract;

import com.baibeiyun.bbyiot.model.Response.EvaluateListResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface OrderEvaluationContract {

    interface Presenter extends IPresenter<View> {
        void getEvaluateList();

    }

    interface View extends IView {
        void getEvaluateListFinish(List<EvaluateListResponse> listResponses);

    }
}
