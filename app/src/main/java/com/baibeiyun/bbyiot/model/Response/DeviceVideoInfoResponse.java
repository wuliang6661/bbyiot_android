package com.baibeiyun.bbyiot.model.Response;

public class DeviceVideoInfoResponse {


    /**
     * code : 002
     * dimension : 120.348
     * groupId : 1
     * host : 192.168.1.102
     * id : 2
     * intervalTime : 10
     * isDelete : 0
     * longitude : 30.1856
     * modelRuleDeviceId : 19
     * name : 102摄像头
     * password : 123456
     * pdate : 1560096000000
     * playAddr : rtsp://admin:bby20190601@192.168.1.101:554/h264/ch1/main/av_stream
     * port : 80
     * protocolType : 0
     * type : 1
     * updateId : 1
     * updateTime : 1560148370000
     * userId : 1
     * username : admin
     */

    private String code;
    private double dimension;
    private int groupId;
    private String host;
    private int id;
    private int intervalTime;
    private int isDelete;
    private double longitude;
    private int modelRuleDeviceId;
    private String name;
    private String password;
    private long pdate;
    private String playAddr;
    private int port;
    private int protocolType;
    private int type;
    private int updateId;
    private long updateTime;
    private int userId;
    private String username;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPdate() {
        return pdate;
    }

    public void setPdate(long pdate) {
        this.pdate = pdate;
    }

    public String getPlayAddr() {
        return playAddr;
    }

    public void setPlayAddr(String playAddr) {
        this.playAddr = playAddr;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
