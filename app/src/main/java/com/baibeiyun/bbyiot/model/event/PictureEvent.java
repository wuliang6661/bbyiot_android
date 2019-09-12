package com.baibeiyun.bbyiot.model.event;

import android.graphics.Bitmap;

public class PictureEvent {

    private Bitmap bitmap;

    public PictureEvent(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }


    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
