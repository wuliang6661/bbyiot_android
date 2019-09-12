package com.baibeiyun.bbyiot.model.request;

public class AnalysisRequest extends BaseRequest {


    /**
     * eid : 0
     * endDate : string
     * mode : 0
     * startDate : string
     */

    private int eid;
    private String attributeId;
    private String endDate;
    private int mode;
    private String startDate;

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
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

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "AnalysisRequest{" +
                "eid=" + eid +
                ", endDate='" + endDate + '\'' +
                ", mode=" + mode +
                ", startDate='" + startDate + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
