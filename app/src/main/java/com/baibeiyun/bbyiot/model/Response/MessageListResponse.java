package com.baibeiyun.bbyiot.model.Response;

import java.io.Serializable;

public class MessageListResponse implements Serializable {


    /**
     * attributeid : 2
     * content : 百叶箱型噪声温湿度BYX-01A的噪声达到告警警戒值：一般
     * eid : 2
     * id : 6
     * readStatus : 0
     * title : 设备告警
     * type : 0
     * updateTime : 1563213840000
     * userId : 1
     */

    private int attributeid;
    private String content;
    private int eid;
    private int id;
    private int readStatus;
    private String title;
    private int type;
    private long updateTime;
    private int userId;

    public int getAttributeid() {
        return attributeid;
    }

    public void setAttributeid(int attributeid) {
        this.attributeid = attributeid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
