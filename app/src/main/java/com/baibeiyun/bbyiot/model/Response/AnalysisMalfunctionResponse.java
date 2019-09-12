package com.baibeiyun.bbyiot.model.Response;

public class AnalysisMalfunctionResponse {


    /**
     * collectCount : 5114
     * faultCount : 166
     */

    private int collectCount;
    private int faultCount;

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public int getFaultCount() {
        return faultCount;
    }

    public void setFaultCount(int faultCount) {
        this.faultCount = faultCount;
    }
}
