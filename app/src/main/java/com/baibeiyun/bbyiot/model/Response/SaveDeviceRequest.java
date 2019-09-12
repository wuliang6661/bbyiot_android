package com.baibeiyun.bbyiot.model.Response;

import com.baibeiyun.bbyiot.model.request.BaseRequest;

import java.util.List;

public class SaveDeviceRequest extends BaseRequest{


    /**
     * deviceSwitches : [{"eid":0,"endTime":"string","id":0,"isOpen":0,"openValue":0,"remark":"string","startTime":"string","updateId":0,"updateTime":"2019-07-10T05:44:25.553Z"}]
     * eid : 0
     */

    private int eid;
    private String attributeId;
    /**
     * eid : 0
     * endTime : string
     * id : 0
     * isOpen : 0
     * openValue : 0
     * remark : string
     * startTime : string
     * updateId : 0
     * updateTime : 2019-07-10T05:44:25.553Z
     */

    private List<DeviceSwitchesBean> deviceSwitches;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public List<DeviceSwitchesBean> getDeviceSwitches() {
        return deviceSwitches;
    }

    public void setDeviceSwitches(List<DeviceSwitchesBean> deviceSwitches) {
        this.deviceSwitches = deviceSwitches;
    }

    public static class DeviceSwitchesBean {
        private int eid;
        private String endTime;
        private String id;
        private int isOpen;
        private int openValue;
        private String remark;
        private String startTime;
        private String updateId;
        private String updateTime;
        private String attributeId;

        public int getEid() {
            return eid;
        }

        public void setEid(int eid) {
            this.eid = eid;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIsOpen() {
            return isOpen;
        }

        public void setIsOpen(int isOpen) {
            this.isOpen = isOpen;
        }

        public int getOpenValue() {
            return openValue;
        }

        public void setOpenValue(int openValue) {
            this.openValue = openValue;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getUpdateId() {
            return updateId;
        }

        public void setUpdateId(String updateId) {
            this.updateId = updateId;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getAttributeId() {
            return attributeId;
        }

        public void setAttributeId(String attributeId) {
            this.attributeId = attributeId;
        }
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }
}
