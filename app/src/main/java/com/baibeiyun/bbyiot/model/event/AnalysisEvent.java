package com.baibeiyun.bbyiot.model.event;

import java.io.Serializable;

public class AnalysisEvent implements Serializable{
    int groupId = -1;
    int deciveID = -1;
    int attributeId = -1;
    String startDate = "";
    String endDate = "";

    String groupName = "";
    String deviceName = "";
    String attributeName = "";

    int type ;


    public int getGroupId() {
        return groupId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getDeciveID() {
        return deciveID;
    }

    public void setDeciveID(int deciveID) {
        this.deciveID = deciveID;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
