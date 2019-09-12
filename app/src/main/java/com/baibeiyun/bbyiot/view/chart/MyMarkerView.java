package com.baibeiyun.bbyiot.view.chart;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

/**
 * 折线图点击后 Y 坐标展示的数值字样
 */
public class MyMarkerView extends MarkerView {
    private final TextView mTextView;
    private float textSize = 16;
    private int textColor;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        mTextView = findViewById(R.id.tv);
    }

    private void initStyle() {
        if(mTextView!=null) {
            mTextView.setTextSize(textSize);
            mTextView.setTextColor(textColor);
        }
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        initStyle();
        float y = highlight.getY();
        mTextView.setText(String.valueOf(y));
        mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        invalidate();
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
