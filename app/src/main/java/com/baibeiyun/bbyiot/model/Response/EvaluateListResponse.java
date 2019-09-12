package com.baibeiyun.bbyiot.model.Response;

import java.io.Serializable;

public class EvaluateListResponse implements Serializable {


    /**
     * orderId : 1
     * goodsInfoId : 1
     * isAnonymous : 1
     * evaluate : 5
     * serviceAttitudeScore : 5
     * productQualityScore : 5
     * priceRationalityScore : 5
     * content : 好，很好，非常好
     * image : http://xiaoqiangzhizao.oss-cn-hangzhou.aliyuncs.com/xqzz/2019061119/201906111928218075052.png
     * price : 0.0
     * title : 开关量模拟器
     * goodsName : 开关量模拟器
     * num : 1
     * userName : 孙中赛11
     * userId : 1
     * createDate : null
     * id : 1
     */

    private int orderId;
    private int goodsInfoId;
    private int isAnonymous;
    private int evaluate;
    private int serviceAttitudeScore;
    private int productQualityScore;
    private int priceRationalityScore;
    private String content;
    private String image;
    private double price;
    private String title;
    private String goodsName;
    private int num;
    private String userName;
    private int userId;
    private String createDate;
    private int id;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(int goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public int getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(int isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public int getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(int evaluate) {
        this.evaluate = evaluate;
    }

    public int getServiceAttitudeScore() {
        return serviceAttitudeScore;
    }

    public void setServiceAttitudeScore(int serviceAttitudeScore) {
        this.serviceAttitudeScore = serviceAttitudeScore;
    }

    public int getProductQualityScore() {
        return productQualityScore;
    }

    public void setProductQualityScore(int productQualityScore) {
        this.productQualityScore = productQualityScore;
    }

    public int getPriceRationalityScore() {
        return priceRationalityScore;
    }

    public void setPriceRationalityScore(int priceRationalityScore) {
        this.priceRationalityScore = priceRationalityScore;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}