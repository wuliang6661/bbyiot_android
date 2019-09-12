package com.baibeiyun.bbyiot.model.Response;

import java.io.Serializable;

public class DeviceSwtichResponse implements Serializable{


    /**
     * code : 00
     * gatewayName : 有人304
     * id : 9
     * modelRuleDeviceName : 电动阀门
     * name : 电动阀门控制
     * realData : 40
     * switchStatus : 0
     * switchType : 1
     */

    private String code;
    private String gatewayName;
    private int id;
    private String modelRuleDeviceName;
    private String name;
    private int realData;
    private int switchStatus;
    private int switchType;
    private int attributeId;
    private String attributeName;

    private String image;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelRuleDeviceName() {
        return modelRuleDeviceName;
    }

    public void setModelRuleDeviceName(String modelRuleDeviceName) {
        this.modelRuleDeviceName = modelRuleDeviceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRealData() {
        return realData;
    }

    public void setRealData(int realData) {
        this.realData = realData;
    }

    public int getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(int switchStatus) {
        this.switchStatus = switchStatus;
    }

    public int getSwitchType() {
        return switchType;
    }

    public void setSwitchType(int switchType) {
        this.switchType = switchType;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
