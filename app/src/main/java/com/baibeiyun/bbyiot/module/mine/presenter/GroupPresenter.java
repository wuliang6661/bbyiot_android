package com.baibeiyun.bbyiot.module.mine.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.mine.contract.GroupContract;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.List;

public class GroupPresenter extends BasePresenter<GroupContract.View> implements GroupContract.Presenter {

    public GroupPresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void getHomeGroups(final boolean isRefresh) {
        mView.showLoading(null);
        dataManager.getHomeGroups()
                .subscribe(new BaseSubscriber<BaseResponse<List<HomeGroupsResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();

                        LogUtils.w(errorMsg);
                        ToastUtils.showToast(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<HomeGroupsResponse>> response) {
                        mView.hideLoading();
                        mView.getHomeGroupsFinish(response.getData(),isRefresh);

                    }
                });
    }
}
