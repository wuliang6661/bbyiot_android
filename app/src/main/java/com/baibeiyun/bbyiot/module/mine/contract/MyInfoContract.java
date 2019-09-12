package com.baibeiyun.bbyiot.module.mine.contract;

import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

public interface MyInfoContract {

    interface Presenter extends IPresenter<View> {
        void fileUpload(String path);
    }

    interface View extends IView {
        void fileUploadFinish();
    }
}
