package com.baibeiyun.bbyiot.model.Response;

public class SwitchConfigResponse {


    /**
     * eid : 7
     * endTime : 15:32:33
     * id : 4
     * isOpen : 1
     * openValue : 0
     * startTime : 15:27:33
     * updateId : 1
     * updateTime : 1561794290000
     */

    private Integer eid;
    private String endTime;
    private String id;
    private int isOpen;
    private int openValue;
    private String startTime;
    private String updateId;
    private long updateTime;
    private String attributeId;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public int getOpenValue() {
        return openValue;
    }

    public void setOpenValue(int openValue) {
        this.openValue = openValue;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
}
