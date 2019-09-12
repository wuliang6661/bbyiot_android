package com.baibeiyun.bbyiot.module.testing.contract;

import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface SelectGroupContract {

    interface Presenter extends IPresenter<View>{
        void getHomeGroups(boolean isRefresh);

    }

    interface View extends IView{
        void getHomeGroupsFinish(List<HomeGroupsResponse> list ,boolean isRefresh);
    }
}
