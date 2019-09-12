package com.baibeiyun.bbyiot.view.chart;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;


/**
 * 柱形图点击后 Y 坐标展示的数值字样
 */
public class MyMarkerViewBar extends MarkerView {
    private final TextView mTextView;
    private float textSize = 20;
    private int textColor;
    private int num = 0;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MyMarkerViewBar(Context context, int layoutResource, int num) {
        super(context, layoutResource);
        mTextView = findViewById(R.id.tv);

        this.num = num;
    }

    private void initStyle() {
        mTextView.setTextSize(textSize);
        mTextView.setTextColor(textColor);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        initStyle();

        if(num==0) {
            int y = (int) highlight.getY();
            mTextView.setText(String.valueOf(y));
        }else {
            float y = highlight.getY();
            mTextView.setText(String.valueOf(y));
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
