package com.baibeiyun.bbyiot.module.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.TestingResponse;
import com.baibeiyun.bbyiot.module.base.addpter.MyBaseAdapter;
import com.baibeiyun.bbyiot.module.base.addpter.ViewHolder;
import com.baibeiyun.bbyiot.view.NOScrollListView;

public class TestingGroupAdapter extends MyBaseAdapter<TestingResponse> {


    TextView tv_group_name;
    NOScrollListView listview;
    private ViewGroup ll_title;
    boolean mIsShowGroupTittle = true;

    public TestingGroupAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    public void setIsShowGroupTittle(boolean isShow) {
        mIsShowGroupTittle = isShow;
    }


    @Override
    public void convert(ViewHolder holder, TestingResponse groupData, int position) {
        tv_group_name = holder.getView(R.id.item_testing_group_tv_group_name);
        listview = holder.getView(R.id.item_testing_group_listview);
        ll_title = holder.getView(R.id.item_testing_group_ll_title);


        tv_group_name.setText(groupData.getGroupName());

        if (mIsShowGroupTittle) {
            ll_title.setVisibility(View.VISIBLE);
        } else {
            ll_title.setVisibility(View.GONE);
        }

        TestingGroupInnerAdapter adapter = new TestingGroupInnerAdapter(mContext, R.layout.item_device_state);
        listview.setAdapter(adapter);
        adapter.update(groupData.getDevices());
    }


}
