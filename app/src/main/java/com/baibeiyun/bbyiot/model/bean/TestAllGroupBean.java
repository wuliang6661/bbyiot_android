package com.baibeiyun.bbyiot.model.bean;

import java.util.List;

public class TestAllGroupBean {

    private String label;

    private List<TestDataBean> list;
    private boolean isShowLabel = true;


    public boolean isShowLabel() {
        return isShowLabel;
    }

    public void setShowLabel(boolean showLabel) {
        isShowLabel = showLabel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TestDataBean> getList() {
        return list;
    }

    public void setList(List<TestDataBean> list) {
        this.list = list;
    }
}
