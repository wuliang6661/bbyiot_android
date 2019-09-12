package com.baibeiyun.bbyiot.model.Response;

public class LocationResponse {


    /**
     * sysmbol : %
     * alarmType : 无告警
     * attitudeName : 阀门开度值
     * updateTime : 1560173675000
     * realData : 0.0
     * deviceName : 电动阀门
     * dimension : 119.95
     * longitude : 31.83
     * deviceStatus : 0
     * colorType : #999
     */

    private String sysmbol;
    private String alarmType;
    private String attitudeName;
    private long updateTime;
    private double realData;
    private String deviceName;
    private double dimension;
    private double longitude;
    private int deviceStatus;
    private String colorType;

    public String getSysmbol() {
        return sysmbol;
    }

    public void setSysmbol(String sysmbol) {
        this.sysmbol = sysmbol;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAttitudeName() {
        return attitudeName;
    }

    public void setAttitudeName(String attitudeName) {
        this.attitudeName = attitudeName;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    @Override
    public String toString() {
        return "LocationResponse{" +
                "sysmbol='" + sysmbol + '\'' +
                ", alarmType='" + alarmType + '\'' +
                ", attitudeName='" + attitudeName + '\'' +
                ", updateTime=" + updateTime +
                ", realData=" + realData +
                ", deviceName='" + deviceName + '\'' +
                ", dimension=" + dimension +
                ", longitude=" + longitude +
                ", deviceStatus=" + deviceStatus +
                ", colorType='" + colorType + '\'' +
                '}';
    }
}
