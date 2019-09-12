package com.baibeiyun.bbyiot.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by LX on 2018/5/29.
 */

public class NOScrollGirdview extends GridView {


    public NOScrollGirdview(Context context) {
        super(context);
    }

    public NOScrollGirdview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NOScrollGirdview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
