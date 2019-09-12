package com.baibeiyun.bbyiot.module.mine.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.HisAlarmListResponse;
import com.baibeiyun.bbyiot.module.base.addpter.MyBaseAdapter;
import com.baibeiyun.bbyiot.module.base.addpter.ViewHolder;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

public class BaojingAdapter extends MyBaseAdapter<HisAlarmListResponse> {

    private TextView tv_name;
    private TextView tv_content;
    private TextView tv_time;
    private TextView tv_state;

    public BaojingAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @SuppressLint("NewApi")
    @Override
    public void convert(ViewHolder holder, HisAlarmListResponse response, int position) {
        try {
//            tv_name = holder.getView(R.id.item_baojing_tv_name);
//            tv_content = holder.getView(R.id.item_baojing_tv_content);
            tv_time = holder.getView(R.id.item_baojing_tv_time);
            tv_state = holder.getView(R.id.item_baojing_tv_state);

            tv_time.setText(DateUtils.stamp2String(response.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));

            tv_state.setText(response.getAlarmType());
            tv_state.setTextColor(Color.parseColor(response.getColorType()));
            //tv_state.setBackground(DrawableBgUtils.getBgDrawable(mContext, response.getColorType()));

        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }
}
