package com.baibeiyun.bbyiot.model.Response;

public class DeviceResponse {

    /**
     * addressBit : 03
     * code : 03
     * collectTimeout : 2000
     * dimension : 119.95
     * gatewayId : 4
     * groupId : 1
     * id : 13
     * intervalTime : 30
     * isDelete : 0
     * longitude : 31.83
     * modelRuleDeviceId : 18
     * name : 电动阀门
     * pdate : 1559750400000
     * protocolType : 4
     * type : 0
     * updateId : 1
     * updateTime : 1559830339000
     * userId : 1
     */

    private String addressBit;
    private String code;
    private int collectTimeout;
    private double dimension;
    private int gatewayId;
    private int groupId;
    private int id;
    private int intervalTime;
    private int isDelete;
    private double longitude;
    private int modelRuleDeviceId;
    private String name;
    private long pdate;
    private int protocolType;
    private int type;
    private int updateId;
    private long updateTime;
    private int userId;

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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
