package com.baibeiyun.bbyiot.model.Response;

public class DeviceVideoResponse {


    /**
     * alarmType : 无告警
     * deviceId : 1
     * deviceName : 名称
     * deviceStatus : 0
     * realData :
     */

    private String alarmType;
    private int deviceId;
    private String deviceName;
    private int deviceStatus;
    private String realData;

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

    public String getRealData() {
        return realData;
    }

    public void setRealData(String realData) {
        this.realData = realData;
    }
}
