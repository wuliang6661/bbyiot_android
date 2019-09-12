package com.baibeiyun.bbyiot.module.mine.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.EvaluateListResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.mine.contract.OrderEvaluationContract;
import com.baibeiyun.bbyiot.module.mine.contract.OrderEvaluationContract.Presenter;

import java.util.List;

public class OrderEvaluationPresenter extends BasePresenter<OrderEvaluationContract.View> implements Presenter {
    public OrderEvaluationPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getEvaluateList() {
        mView.showLoading(null);
        dataManager.getEvaluateList()
                .subscribe(new BaseSubscriber<BaseResponse<List<EvaluateListResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        mView.getEvaluateListFinish(null);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<EvaluateListResponse>> baseResponse) {
                        mView.hideLoading();
                        mView.getEvaluateListFinish(baseResponse.getData());
                    }
                });
    }
}
