package com.baibeiyun.bbyiot.model.Response;

public class LatestResultResponse {


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
