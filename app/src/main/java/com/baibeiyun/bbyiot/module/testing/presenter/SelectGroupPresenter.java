package com.baibeiyun.bbyiot.module.testing.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.model.bean.TestGroupData;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.testing.contract.SelectGroupContract;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class SelectGroupPresenter extends BasePresenter<SelectGroupContract.View> implements SelectGroupContract.Presenter {

    public SelectGroupPresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getHomeGroups(final boolean isRefresh) {
        if (!isRefresh) {
            mView.showLoading(null);
        }

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


    public List<TestGroupData> getData() {
        List<TestGroupData> list = new ArrayList<>();

        list.add(new TestGroupData(R.mipmap.icon_select_group_nor_01, R.mipmap.icon_select_group_check_01, "全部"));
        list.add(new TestGroupData(R.mipmap.icon_select_group_nor_02, R.mipmap.icon_select_group_check_02, "车间一"));
        list.add(new TestGroupData(R.mipmap.icon_select_group_nor_03, R.mipmap.icon_select_group_check_03, "园区一"));

        list.add(new TestGroupData(R.mipmap.icon_select_group_nor_04, R.mipmap.icon_select_group_check_04, "厂区二"));
        list.add(new TestGroupData(R.mipmap.icon_select_group_nor_02, R.mipmap.icon_select_group_check_02, "车间二"));
        list.add(new TestGroupData(R.mipmap.icon_select_group_nor_03, R.mipmap.icon_select_group_check_03, "园区二"));

        list.add(new TestGroupData(R.mipmap.icon_select_group_nor_04, R.mipmap.icon_select_group_check_04, "厂区三"));
        list.add(new TestGroupData(R.mipmap.icon_select_group_nor_02, R.mipmap.icon_select_group_check_02, "车间三"));
        list.add(new TestGroupData(R.mipmap.icon_select_group_nor_03, R.mipmap.icon_select_group_check_03, "园区三"));


        return list;
    }
}
