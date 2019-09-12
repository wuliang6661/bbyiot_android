package com.baibeiyun.bbyiot.model.Response;

public class DevicePicResponse {


    /**
     * alarmType : 无告警
     * updateTime : -2209147200000
     * realData :
     * deviceId : 1
     * deviceName : 图片设备
     * deviceStatus : 0
     */

    private String alarmType;
    private long updateTime;
    private String realData;
    private int deviceId;
    private String deviceName;
    private int deviceStatus;
    private String colorType;

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getRealData() {
        return realData;
    }

    public void setRealData(String realData) {
        this.realData = realData;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
