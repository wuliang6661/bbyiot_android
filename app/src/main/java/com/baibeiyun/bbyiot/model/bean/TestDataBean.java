package com.baibeiyun.bbyiot.model.bean;

public class TestDataBean {

    private int picRes;
    private String label1;
    private String label2;
    private String label3;
    private String label4;

    private boolean isZaiXian;

    public TestDataBean(int picRes, String label1) {
        this.picRes = picRes;
        this.label1 = label1;
    }

    public TestDataBean(int picRes, String label1, String label2, String label3, String label4, boolean isZaiXian) {
        this.picRes = picRes;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.isZaiXian = isZaiXian;
    }

    public int getPicRes() {
        return picRes;
    }

    public void setPicRes(int picRes) {
        this.picRes = picRes;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getLabel3() {
        return label3;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    public String getLabel4() {
        return label4;
    }

    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    public boolean isZaiXian() {
        return isZaiXian;
    }

    public void setZaiXian(boolean zaiXian) {
        isZaiXian = zaiXian;
    }
}
