package com.baibeiyun.bbyiot.model.request;

public class OrderEvaluateRequest {


    /**
     * content :
     * evaluate : 0
     * goodsInfoId : 0
     * isAnonymous : 0
     * orderId : 0
     * priceRationalityScore : 0
     * productQualityScore : 0
     * serviceAttitudeScore : 0
     */

    private String content;
    private int evaluate;
    private int goodsInfoId;
    private int isAnonymous;
    private long orderId;
    private int priceRationalityScore;
    private int productQualityScore;
    private int serviceAttitudeScore;

    public void setContent(String content) {
        this.content = content;
    }

    public void setEvaluate(int evaluate) {
        this.evaluate = evaluate;
    }

    public void setGoodsInfoId(int goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public void setIsAnonymous(int isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setPriceRationalityScore(int priceRationalityScore) {
        this.priceRationalityScore = priceRationalityScore;
    }

    public void setProductQualityScore(int productQualityScore) {
        this.productQualityScore = productQualityScore;
    }

    public void setServiceAttitudeScore(int serviceAttitudeScore) {
        this.serviceAttitudeScore = serviceAttitudeScore;
    }
}
