package com.baibeiyun.bbyiot.model.Response;

import java.util.List;

public class LatestResultDetailsResponse {


    /**
     * state : 3
     * com : yuantong
     * nu : 500306190180
     * data : [{"context":"客户 签收人 :本人签收 已签收 感谢使用圆通速递，期待再次为您服务","time":"2016-03-10 16:16:10","ftime":"2016-03-10 16:16:10"},{"context":"【江西省新余市公司】 派件人 :钟阿梅 派件中 派件员电话13030550790","time":"2016-03-10 16:09:55","ftime":"2016-03-10 16:09:55"},{"context":"【江西省新余市接驳点】 已发出 下一站 【江西省新余市公司】","time":"2016-03-10 04:03:31","ftime":"2016-03-10 04:03:31"},{"context":"【南昌转运中心】 已发出 下一站 【江西省新余市接驳点公司】","time":"2016-03-09 20:04:19","ftime":"2016-03-09 20:04:19"},{"context":"【南昌转运中心】 已收入","time":"2016-03-09 20:01:14","ftime":"2016-03-09 20:01:14"},{"context":"【辽宁省大连市公司】 已打包","time":"2016-03-08 19:47:58","ftime":"2016-03-08 19:47:58"},{"context":"【辽宁省大连市公司】 已收入","time":"2016-03-08 18:55:51","ftime":"2016-03-08 18:55:51"},{"context":"【辽宁省大连市高新园区亿成】 已发出 下一站 【辽宁省大连市公司】","time":"2016-03-08 16:22:28","ftime":"2016-03-08 16:22:28"},{"context":"【辽宁省大连市高新园区亿成公司】 已收件","time":"2016-03-08 16:19:59","ftime":"2016-03-08 16:19:59"},{"context":"【辽宁省大连市高新园区亿成公司】 已收件","time":"2016-03-08 15:56:22","ftime":"2016-03-08 15:56:22"}]
     */

    private int state;
    private String com;
    private String nu;
    private List<DataBean> data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * context : 客户 签收人 :本人签收 已签收 感谢使用圆通速递，期待再次为您服务
         * time : 2016-03-10 16:16:10
         * ftime : 2016-03-10 16:16:10
         */

        private String context;
        private String time;
        private String ftime;

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }
    }
}
