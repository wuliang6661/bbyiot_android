package com.baibeiyun.bbyiot.module.mine.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.LatestResultDetailsResponse;
import com.baibeiyun.bbyiot.module.base.addpter.MyBaseAdapter;
import com.baibeiyun.bbyiot.module.base.addpter.ViewHolder;

import java.security.spec.PSSParameterSpec;

public class OrderLogisticsAdapter extends MyBaseAdapter<LatestResultDetailsResponse.DataBean> {

    public OrderLogisticsAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, LatestResultDetailsResponse.DataBean dataBean, int position) {

        ((TextView)holder.getView(R.id.item_order_logistics_tv_step_name))
                .setText(dataBean.getContext());

        ((TextView)holder.getView(R.id.item_order_logistics_tv_step_time))
                .setText(dataBean.getTime());


        if (position==0) {
            holder.getView(R.id.item_order_logistics_view_dot).setBackgroundResource(R.drawable.bg_oder_step_now);
            holder.getView(R.id.item_order_logistics_view_line_top).setVisibility(View.GONE);
        }else {
            holder.getView(R.id.item_order_logistics_view_dot).setBackgroundResource(R.drawable.bg_oder_step);
            holder.getView(R.id.item_order_logistics_view_line_top).setVisibility(View.VISIBLE);
        }

    }
}
