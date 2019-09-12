package com.baibeiyun.bbyiot.model.Response;

public class DeviceGatewayInfoResponse {


    /**
     * brand : 有人
     * cmdIssueIntervalTime : 300
     * dataTransferType : 2
     * dimension : 31.93
     * firmware :
     * gatewayCode : D8 B0 4C FB 45 44
     * gatewayId : D8 B0 4C FB 45 44
     * gatewayName : 有人410s
     * heartBeat : EB 0A D8 B0 4C FB 45 44
     * heartBeatIntervalTime : 300
     * id : 18
     * image : blob:http://112.124.200.87:8082/481f0c78-0d3e-4584-b545-68cefa0b9383
     * intervalTime : 30
     * longitude : 119.95
     * protocolType : 0
     * remark :
     * serverIp : 112.124.200.87
     * serverPort : 32202
     * status : 0
     * type : 1
     * updateId : 1
     * updateTime : 1561442032000
     * userId : 1
     */

    private String brand;
    private int cmdIssueIntervalTime;
    private int dataTransferType;
    private double dimension;
    private String firmware;
    private String gatewayCode;
    private String gatewayId;
    private String gatewayName;
    private String heartBeat;
    private int heartBeatIntervalTime;
    private int id;
    private String image;
    private int intervalTime;
    private double longitude;
    private int protocolType;
    private String remark;
    private String serverIp;
    private int serverPort;
    private int status;
    private int type;
    private int updateId;
    private long updateTime;
    private int userId;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCmdIssueIntervalTime() {
        return cmdIssueIntervalTime;
    }

    public void setCmdIssueIntervalTime(int cmdIssueIntervalTime) {
        this.cmdIssueIntervalTime = cmdIssueIntervalTime;
    }

    public int getDataTransferType() {
        return dataTransferType;
    }

    public void setDataTransferType(int dataTransferType) {
        this.dataTransferType = dataTransferType;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public String getGatewayCode() {
        return gatewayCode;
    }

    public void setGatewayCode(String gatewayCode) {
        this.gatewayCode = gatewayCode;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(String heartBeat) {
        this.heartBeat = heartBeat;
    }

    public int getHeartBeatIntervalTime() {
        return heartBeatIntervalTime;
    }

    public void setHeartBeatIntervalTime(int heartBeatIntervalTime) {
        this.heartBeatIntervalTime = heartBeatIntervalTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
