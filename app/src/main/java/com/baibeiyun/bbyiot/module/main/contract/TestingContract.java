package com.baibeiyun.bbyiot.module.main.contract;

import com.baibeiyun.bbyiot.model.Response.TestingResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface TestingContract {

    interface Presenter extends IPresenter<View>{
        void getTestingList(boolean isRefresh,String groupId ,String name);
    }

    interface View extends IView{
        void getTestingListFinish(List<TestingResponse> list, boolean isRefresh);
    }
}
