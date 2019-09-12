package com.baibeiyun.bbyiot.model.request;

public class DeviceListRequest extends BaseRequest {


    /**
     * groupId : 0
     * name : string
     * pageNum : 0
     * pageSize : 0
     * status : 0
     */

//    private int groupId;
//    private String name ;
    private int pageNum;
    private int pageSize;
//    private int status;

//    public int getGroupId() {
//        return groupId;
//    }
//
//    public void setGroupId(int groupId) {
//        this.groupId = groupId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }

    @Override
    public String toString() {
        return "DeviceListRequest{" +
//                "groupId=" + groupId +
//                ", name='" + name + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
//                ", status=" + status +
                ", token='" + token + '\'' +
                '}';
    }
}
