package com.baibeiyun.bbyiot.model.Response;

public class DevicesStatusRespone {


    /**
     * deviceType : 0
     * attributeId : 23
     * attributeImage : blob:http://112.124.200.87:8081/6a2711ce-f6ab-4294-af4b-6ebea828e4a1
     * alarmType : 无告警
     * attributeName : 阀门开度值
     * updateTime : 1561947030000
     * realData : 40.0
     * deviceId : 13
     * deviceName : 电动阀门
     * attributeSymbol : %
     * deviceStatus : 1
     * colorType : #999
     */

    private int deviceType;
    private int attributeId;
    private String attributeImage;
    private String alarmType;
    private String attributeName;
    private long updateTime;
    private double realData;
    private int deviceId;
    private String deviceName;
    private String attributeSymbol;
    private int deviceStatus;
    private String colorType;

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeImage() {
        return attributeImage;
    }

    public void setAttributeImage(String attributeImage) {
        this.attributeImage = attributeImage;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public double getRealData() {
        return realData;
    }

    public void setRealData(double realData) {
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

    public String getAttributeSymbol() {
        return attributeSymbol;
    }

    public void setAttributeSymbol(String attributeSymbol) {
        this.attributeSymbol = attributeSymbol;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }
}
