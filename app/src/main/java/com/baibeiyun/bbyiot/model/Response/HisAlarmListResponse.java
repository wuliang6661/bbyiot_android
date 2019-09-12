package com.baibeiyun.bbyiot.model.Response;

public class HisAlarmListResponse {


    /**
     * alarmType : 非常严重
     * attributeid : 2
     * colorType : #FF3300
     * eid : 2
     * hasRead : 0
     * id : 533498
     * num : 1
     * remark :
     * startTime : 1563845210000
     * type : 0
     * updateTime : 1563845210000
     * value : 79.3
     */

    private String alarmType;
    private int attributeid;
    private String colorType;
    private int eid;
    private int hasRead;
    private int id;
    private int num;
    private String remark;
    private long startTime;
    private int type;
    private long updateTime;
    private String value;

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public int getAttributeid() {
        return attributeid;
    }

    public void setAttributeid(int attributeid) {
        this.attributeid = attributeid;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getHasRead() {
        return hasRead;
    }

    public void setHasRead(int hasRead) {
        this.hasRead = hasRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
