package com.baibeiyun.bbyiot.model.Response;

public class DeviceStatusResponse {


    /**
     * alarmNUm : 0   : 告警数
     * onlineNUm : 7 ：在线
     * offlineNum : 0 ：离线
     */

    private int alarmNUm;
    private int onlineNUm;
    private int offlineNum;

    public int getAlarmNUm() {
        return alarmNUm;
    }

    public void setAlarmNUm(int alarmNUm) {
        this.alarmNUm = alarmNUm;
    }

    public int getOnlineNUm() {
        return onlineNUm;
    }

    public void setOnlineNUm(int onlineNUm) {
        this.onlineNUm = onlineNUm;
    }

    public int getOfflineNum() {
        return offlineNum;
    }

    public void setOfflineNum(int offlineNum) {
        this.offlineNum = offlineNum;
    }
}
