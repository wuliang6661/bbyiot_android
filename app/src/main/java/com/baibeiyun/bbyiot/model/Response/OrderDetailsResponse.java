package com.baibeiyun.bbyiot.model.Response;

import java.io.Serializable;

public class OrderDetailsResponse implements Serializable{


    /**
     * orderNum : 100589721918
     * num : 1
     * shouldPay : 200
     * actualPay : 200
     * courier : 0
     * status : 1
     * goodsInfoId : 1
     * name : 开关量模拟器
     * image : http://xiaoqiangzhizao.oss-cn-hangzhou.aliyuncs.com/xqzz/2019061119/201906111928218075052.png
     * price : null
     * isInvoice : 0
     * invoiceType : 0
     * invoiceTitle : null
     * invoiceContent : null
     * createDate : 2019-06-04T11:38:49
     * payType : 0
     * payDate : null
     * cancelDate : 2019-06-07T11:38:49
     * id : 4
     */

    private String orderNum;
    private int num;
    private double shouldPay;
    private double actualPay;

    private double courier;
    private int status;
    private int goodsInfoId;
    private String name;
    private String image;
    private String price;
    private int isInvoice;
    private int invoiceType;
    private String invoiceTitle;
    private String invoiceContent;
    private String createDate;
    private int payType;
    private String payDate;
    private String cancelDate;
    private int id;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getShouldPay() {
        return shouldPay;
    }

    public void setShouldPay(int shouldPay) {
        this.shouldPay = shouldPay;
    }

    public double getActualPay() {
        return actualPay;
    }

    public void setActualPay(int actualPay) {
        this.actualPay = actualPay;
    }

    public double getCourier() {
        return courier;
    }

    public void setCourier(double courier) {
        this.courier = courier;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(int goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(int isInvoice) {
        this.isInvoice = isInvoice;
    }

    public int getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(int invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrderDetailsResponse{" +
                "orderNum='" + orderNum + '\'' +
                ", num=" + num +
                ", shouldPay=" + shouldPay +
                ", actualPay=" + actualPay +
                ", courier=" + courier +
                ", status=" + status +
                ", goodsInfoId=" + goodsInfoId +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", isInvoice=" + isInvoice +
                ", invoiceType=" + invoiceType +
                ", invoiceTitle=" + invoiceTitle +
                ", invoiceContent=" + invoiceContent +
                ", createDate='" + createDate + '\'' +
                ", payType=" + payType +
                ", payDate=" + payDate +
                ", cancelDate='" + cancelDate + '\'' +
                ", id=" + id +
                '}';
    }
}
