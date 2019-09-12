package com.baibeiyun.bbyiot.model.Response;

import java.io.Serializable;

public class PayResultResponse  implements Serializable {

    /**
     * actualPay : 0
     * id : 0
     * payType : 0
     */

    private String actualPay;
    private long id;
    private int payType;

    public String getActualPay() {
        return actualPay;
    }

    public void setActualPay(String actualPay) {
        this.actualPay = actualPay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }
}
