package com.baibeiyun.bbyiot.model.Response;

public class HisAlarmChartResponse {


    /**
     * alarmType : 非常严重
     * colorType : #FF3300
     * num : 230
     */

    private String alarmType;
    private String colorType;
    private int num;

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
