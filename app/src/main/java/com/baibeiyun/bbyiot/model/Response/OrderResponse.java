package com.baibeiyun.bbyiot.model.Response;

import java.io.Serializable;

public class OrderResponse implements Serializable{


    /**
     * goodsInfoId : 0
     * id : 0
     * image :
     * name :
     * num : 0
     * orderNum :
     * shouldPay : 0
     * status : 0
     */

    private int goodsInfoId;
    private long id;
    private String image;
    private String name;
    private int num;
    private String orderNum;
    private String shouldPay;
    private int status;

    public int getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(int goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getShouldPay() {
        return shouldPay;
    }

    public void setShouldPay(String shouldPay) {
        this.shouldPay = shouldPay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
