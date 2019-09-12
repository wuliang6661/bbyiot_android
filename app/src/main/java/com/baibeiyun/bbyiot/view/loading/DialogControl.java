package com.baibeiyun.bbyiot.view.loading;

public interface DialogControl {

    void hideWaitDialog();

    WaitDialog showWaitDialog();

    WaitDialog showWaitDialog(int resid);

    WaitDialog showWaitDialog(String text);
}
