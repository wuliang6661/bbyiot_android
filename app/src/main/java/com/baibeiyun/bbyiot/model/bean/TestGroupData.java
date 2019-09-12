package com.baibeiyun.bbyiot.model.bean;

public class TestGroupData {

    private int norImage;
    private int checkImage ;

    private String label ;


    public TestGroupData(int norImage, int checkImage, String label) {
        this.norImage = norImage;
        this.checkImage = checkImage;
        this.label = label;
    }

    public int getNorImage() {
        return norImage;
    }

    public int getCheckImage() {
        return checkImage;
    }

    public String getLabel() {
        return label;
    }


}
