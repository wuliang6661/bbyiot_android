package com.baibeiyun.bbyiot.module.base.view;

/**
 * Created by wutl@zrq on 16/5/31.
 */
public interface IView {

    void showLoading(String msg);

    void hideLoading();

    void showMessage(String msg);

    boolean isShowing();

}
