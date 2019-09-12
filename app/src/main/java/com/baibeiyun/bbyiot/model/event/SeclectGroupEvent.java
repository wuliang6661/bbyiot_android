package com.baibeiyun.bbyiot.model.event;

public class SeclectGroupEvent {

    private int position;

    public SeclectGroupEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
