package com.baibeiyun.bbyiot.model.event;


public class PayFinishEvent {
    public int errCode;

    public PayFinishEvent(int errCode){
        this.errCode = errCode;
    }
}
