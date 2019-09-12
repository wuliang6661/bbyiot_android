package com.baibeiyun.bbyiot.module.mine.adapter;

import android.content.Context;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.module.base.addpter.MyBaseAdapter;
import com.baibeiyun.bbyiot.module.base.addpter.ViewHolder;

public class GroupAdapter extends MyBaseAdapter<HomeGroupsResponse> {

    public GroupAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, HomeGroupsResponse response, int position) {

        ((TextView)holder.getView(R.id.item_group_tv_group_name)).setText(response.getName());
        ((TextView)holder.getView(R.id.item_group_tv_group_addess)).setText(response.getRemark());
        ((TextView)holder.getView(R.id.item_group_tv_group_id)).setText(response.getGroupCode());
    }
}
