package com.baibeiyun.bbyiot.model.Response;

public class DevicePropertyResponse {

    /**
     * alarmType : 高温告警
     * attributeId : 19
     * attributeImage : blob:http://112.124.200.87:8081/f5ee7367-58eb-4aed-bb5c-6866f648847a
     * attributeName : 温湿度变送器（温度）
     * attributeSymbol : ℃
     * colorType : #DA5B5B
     * deviceId : 9
     * deviceName : 模拟量采集器（康耐德）
     * deviceStatus : 1
     * realData : 29.9549
     * updateTime : 1560761090000
     */

    private String alarmType;
    private int attributeId;
    private String attributeImage;
    private String attributeName;
    private String attributeSymbol;
    private String colorType;
    private int deviceId;
    private String deviceName;
    private int deviceStatus;
    private double realData;
    private long updateTime;

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

    public String getAttributeImage() {
        return attributeImage;
    }

    public void setAttributeImage(String attributeImage) {
        this.attributeImage = attributeImage;
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

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
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
}
