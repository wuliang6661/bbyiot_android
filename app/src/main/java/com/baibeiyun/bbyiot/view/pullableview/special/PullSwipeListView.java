package com.baibeiyun.bbyiot.view.pullableview.special;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.pullableview.Pullable;
import com.baibeiyun.bbyiot.view.pullableview.SwipeListView;


/**
 * Created by caih on 2019/5/14.
 */
@SuppressLint("ViewConstructor")
public class PullSwipeListView extends SwipeListView implements Pullable {

    public PullSwipeListView(Context context) {
        super(context);
    }

    public PullSwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullSwipeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean canPullDown() {
        try {
            if (getCount() == 0) {
                // 没有item的时候也可以下拉刷新
                return true;
            } else if (getFirstVisiblePosition() == 0
                    && getChildAt(0).getTop() >= 0) {
                // 滑到ListView的顶部了
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LogUtils.w(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean canPullUp() {
        if (getCount() == 0) {
            // 没有item的时候也可以上拉加载
            return true;
        } else if (getLastVisiblePosition() == (getCount() - 1)) {
            // 滑到底部了
            if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) != null
                    && getChildAt(
                    getLastVisiblePosition()
                            - getFirstVisiblePosition()).getBottom() <= getMeasuredHeight())
                return true;
        }
        return false;
    }
}
