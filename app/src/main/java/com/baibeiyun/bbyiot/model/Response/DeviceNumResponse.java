package com.baibeiyun.bbyiot.model.Response;

public class DeviceNumResponse {


    /**
     * alarmType : 无告警
     * attributeId : 21
     * attributeName : 光照强度
     * attributeSymbol : Lux
     * deviceId : 12
     * deviceName : 光照度变送器
     * deviceStatus : 0
     * realData : 0.0
     * updateTime : 1559639784000
     */

    private String alarmType;
    private int attributeId;
    private String attributeName;
    private String attributeSymbol;
    private int deviceId;
    private String deviceName;
    private int deviceStatus;
    private double realData;
    private long updateTime;
    private String colorType;
    private String attributeImage;

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeSymbol() {
        return attributeSymbol;
    }

    public void setAttributeSymbol(String attributeSymbol) {
        this.attributeSymbol = attributeSymbol;
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

    public double getRealData() {
        return realData;
    }

    public void setRealData(double realData) {
        this.realData = realData;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public String getAttributeImage() {
        return attributeImage;
    }

    public void setAttributeImage(String attributeImage) {
        this.attributeImage = attributeImage;
    }
}
