package com.baibeiyun.bbyiot.model.Response;

public class NumInfoResponse {


    /**
     * addressBit : 01
     * code : 01
     * collectTimeout : 1000
     * dimension : 138.95
     * gatewayId : 5
     * gatewayName : 众山ZSD3411
     * groupId : 1
     * groupName : 浙江大学常州工业技术研究院园区
     * id : 9
     * intervalTime : 30
     * longitude : 31.82
     * modelRuleDeviceId : 7
     * name : 模拟量采集器（康耐德）
     * pdate : 1559491200000
     * protocolType : 4
     * ruleDeviceName : 模拟量采集器（康耐德）
     */

    private String addressBit;
    private String code;
    private int collectTimeout;
    private double dimension;
    private int gatewayId;
    private String gatewayName;
    private int groupId;
    private String groupName;
    private int id;
    private int intervalTime;
    private double longitude;
    private int modelRuleDeviceId;
    private String name;
    private long pdate;
    private int protocolType;
    private String ruleDeviceName;

    public String getAddressBit() {
        return addressBit;
    }

    public void setAddressBit(String addressBit) {
        this.addressBit = addressBit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCollectTimeout() {
        return collectTimeout;
    }

    public void setCollectTimeout(int collectTimeout) {
        this.collectTimeout = collectTimeout;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    public int getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(int gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getModelRuleDeviceId() {
        return modelRuleDeviceId;
    }

    public void setModelRuleDeviceId(int modelRuleDeviceId) {
        this.modelRuleDeviceId = modelRuleDeviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPdate() {
        return pdate;
    }

    public void setPdate(long pdate) {
        this.pdate = pdate;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public String getRuleDeviceName() {
        return ruleDeviceName;
    }

    public void setRuleDeviceName(String ruleDeviceName) {
        this.ruleDeviceName = ruleDeviceName;
    }
}
