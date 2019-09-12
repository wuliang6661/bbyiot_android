package com.baibeiyun.bbyiot.model.Response;

public class DeviceLocationResponse   {


    /**
     * device : 0
     * deviceId : 13
     * deviceName : 电动阀门
     * deviceStatus : 1
     * dimension : 119.95
     * longitude : 31.83
     */

    private int device;
    private int deviceId;
    private String deviceName;
    private int deviceStatus;
    private double dimension;
    private double longitude;

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
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
}
