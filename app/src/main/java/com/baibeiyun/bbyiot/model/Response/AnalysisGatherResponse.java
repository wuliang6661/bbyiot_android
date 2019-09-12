package com.baibeiyun.bbyiot.model.Response;

import java.util.List;

public class AnalysisGatherResponse {


    private List<Long> xTimes;
    /**
     * attributeid : 20
     * collectCount : 120
     * createTime : 1560355218000
     * eid : 11
     * faultCount : 0
     * id : 35288
     * mode : 1
     * type : 0
     */

    private List<YDatasBean> yDatas;

    public List<Long> getXTimes() {
        return xTimes;
    }

    public void setXTimes(List<Long> xTimes) {
        this.xTimes = xTimes;
    }

    public List<YDatasBean> getYDatas() {
        return yDatas;
    }

    public void setYDatas(List<YDatasBean> yDatas) {
        this.yDatas = yDatas;
    }

    public static class YDatasBean {
        private int attributeid;
        private int collectCount;
        private long createTime;
        private int eid;
        private int faultCount;
        private int id;
        private int mode;
        private int type;

        public int getAttributeid() {
            return attributeid;
        }

        public void setAttributeid(int attributeid) {
            this.attributeid = attributeid;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getEid() {
            return eid;
        }

        public void setEid(int eid) {
            this.eid = eid;
        }

        public int getFaultCount() {
            return faultCount;
        }

        public void setFaultCount(int faultCount) {
            this.faultCount = faultCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMode() {
            return mode;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
