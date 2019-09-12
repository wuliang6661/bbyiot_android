package com.baibeiyun.bbyiot.model.Response;

public class HomeGroupsResponse {

    /**
     * groupCode : 001
     * id : 1
     * image : blob:http://112.124.200.87:8081/8bf833e5-414c-46e1-9f94-26eeae8bb2b8
     * name : 浙江大学常州工业技术研究院园区
     * remark : 园区监控
     * updateId : 1
     * updateTime : 1559698662000
     * userId : 1
     */

    private String groupCode;
    private int id;
    private String image;
    private String name;
    private String remark;
    private int updateId;
    private long updateTime;
    private int userId;

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
