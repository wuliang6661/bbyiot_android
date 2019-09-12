package com.baibeiyun.bbyiot.model.Response;

public class DeviceGatewayResponse {

    /**
     * brand : 滴答
     * dataTransferType : 1
     * firmware :
     * gatewayCode : sn1013428
     * gatewayId : sn1013428
     * gatewayName : wg10
     * heartBeat :
     * id : 14
     * intervalTime : 30
     * remark :
     * serverIp : 112.124.200.87
     * serverPort : 1883
     * status : 1
     * type : 1
     * updateId : 1
     * updateTime : 1559571703000
     * userId : 1
     */

    private String brand;
    private int dataTransferType;
    private String firmware;
    private String gatewayCode;
    private String gatewayId;
    private String gatewayName;
    private String heartBeat;
    private int id;
    private int intervalTime;
    private String remark;
    private String serverIp;
    private String image;
    private int serverPort;
    private int status;
    private int type;
    private int updateId;
    private long updateTime;
    private int userId;
    private long lastHeartBeatTime;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDataTransferType() {
        return dataTransferType;
    }

    public void setDataTransferType(int dataTransferType) {
        this.dataTransferType = dataTransferType;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public long getLastHeartBeatTime() {
        return lastHeartBeatTime;
    }

    public void setLastHeartBeatTime(long lastHeartBeatTime) {
        this.lastHeartBeatTime = lastHeartBeatTime;
    }
}
