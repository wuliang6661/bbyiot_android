package com.baibeiyun.bbyiot.module.mine.contract;

import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface GroupContract {
    interface Presenter extends IPresenter<View>{
        void getHomeGroups(boolean isRefresh);

    }

    interface View extends IView{
        void getHomeGroupsFinish(List<HomeGroupsResponse> list , boolean isRefresh);
    }
}
