package com.baibeiyun.bbyiot.model.request;

public class MonitoringRequest extends BaseRequest {

    /**
     * attributeId : 0
     * eid : 0
     * endDate : string
     * pageNum : 0
     * pageSize : 0
     * startDate : string
     */

    private int attributeId;
    private int eid;
    private String endDate;
    private int pageNum;
    private int pageSize;
    private String startDate;

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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
