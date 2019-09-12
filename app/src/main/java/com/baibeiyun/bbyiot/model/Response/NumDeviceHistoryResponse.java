package com.baibeiyun.bbyiot.model.Response;

import java.util.List;

public class NumDeviceHistoryResponse {


    private List<Long> xTimes;

    private List<YDatasBean> yDatas;

    public static class YDatasBean {


        /**
         * attributeid : 23
         * createDate : 1560513908000
         * eid : 13
         * id : 1170662
         * type : 0
         * value :
         */

        private int attributeid;
        private long createDate;
        private int eid;
        private int id;
        private int type;
        private String value;

        public int getAttributeid() {
            return attributeid;
        }

        public void setAttributeid(int attributeid) {
            this.attributeid = attributeid;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


    public List<Long> getxTimes() {
        return xTimes;
    }

    public void setxTimes(List<Long> xTimes) {
        this.xTimes = xTimes;
    }

    public List<YDatasBean> getyDatas() {
        return yDatas;
    }

    public void setyDatas(List<YDatasBean> yDatas) {
        this.yDatas = yDatas;
    }
}
