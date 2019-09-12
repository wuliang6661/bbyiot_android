package com.baibeiyun.bbyiot.model.request;

public class TestingRequest extends BaseRequest {


    /**
     * groupIds : string
     * name : string
     */

    private String groupIds;
    private String name;

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
