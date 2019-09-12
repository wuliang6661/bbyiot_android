package com.baibeiyun.bbyiot.model.Response;

public class SwitchInfoResponse {


    /**
     * code : 1232
     * dimension : 31.83
     * gatewayName : WIFI网关控制器
     * groupName : 浙江大学常州工业技术研究院园区
     * id : 5
     * longitude : 119.95
     * name : 门禁控制
     * pdate : 1560787200000
     * switchStatus : 0
     * switchType : 0
     */

    private String code;
    private double dimension;
    private String gatewayName;
    private String groupName;
    private int id;
    private double longitude;
    private String name;
    private long pdate;
    private int switchStatus;
    private int switchType;

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

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
}
