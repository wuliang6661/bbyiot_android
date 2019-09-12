package com.baibeiyun.bbyiot.model.Response;

import java.io.Serializable;

public class OrderMessageResponse implements Serializable{

    /**
     * userId : 1
     * orderId : 1
     * type : 0
     * title : 发货提醒
     * content : 您的订单已发货
     * readStatus : 1
     * createDate : 2019-08-01T15:09:11
     * updateDate : 2019-08-02T10:53:03
     * remark : null
     * id : 1
     */

    private int userId;
    private int orderId;
    private int type;
    private String title;
    private String content;
    private int readStatus;
    private String createDate;
    private String updateDate;
    private Object remark;
    private int id;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
