package com.baibeiyun.bbyiot.model.request;

public class DeviceLocationRequest extends BaseRequest {

    /**
     * attributeId : 0
     * eid : 0
     * endDate : string
     * startDate : string
     */

    private int attributeId;
    private int eid;
    private int id;
    private String endDate;
    private String startDate;
    private String name;

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeviceLocationRequest{" +
                "attributeId=" + attributeId +
                ", eid=" + eid +
                ", endDate='" + endDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
