package com.baibeiyun.bbyiot.model.Response;

public class DefaultDeviceResponse {


    /**
     * attributeId :
     * attributeName :
     * deviceId : 13
     * deviceName : 电动阀门
     * groupId : 1
     * groupName : 浙江大学常州工业技术研究院园区
     */

    private String attributeId;
    private String attributeName;
    private int deviceId;
    private String deviceName;
    private int groupId;
    private String groupName;

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
