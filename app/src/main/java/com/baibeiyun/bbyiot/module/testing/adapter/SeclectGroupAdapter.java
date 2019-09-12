package com.baibeiyun.bbyiot.module.testing.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;


public class SeclectGroupAdapter extends BaseAdapter {

    private List<HomeGroupsResponse> mList;
    private Context context;
    int seclectPosition = -1;

    public SeclectGroupAdapter(Context context) {
        this.context = context;
    }

    public void updata(List<HomeGroupsResponse> list) {

        this.mList = list;
        this.notifyDataSetChanged();
    }


    public void select(int position) {
        seclectPosition = position;
        notifyDataSetChanged();
    }

    public int getPosition() {
        return seclectPosition;
    }


    @Override
    public int getCount() {
        if (null != mList) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = holder.mRootView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.setData(position);

        return convertView;
    }

    class ViewHolder {

        private final ImageView iv_icon, iv_is_check;
        private final ViewGroup rl_root;
        private final TextView tv_name;
        View mRootView;

        public ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_selcet_group, null);

            rl_root = mRootView.findViewById(R.id.item_selcet_group_rl_root);
            iv_icon = mRootView.findViewById(R.id.item_selcet_group_iv_icon);
            iv_is_check = mRootView.findViewById(R.id.item_selcet_group_iv_is_check);

            tv_name = mRootView.findViewById(R.id.item_selcet_group_tv_name);
        }


        public void setData(int position) {
            try {

                HomeGroupsResponse homeGroupsResponse = mList.get(position);
                if (seclectPosition == position) {
                    if (position == 0) {
                        iv_icon.setImageResource(R.mipmap.icon_select_group_check_01);
                    } else {
                        iv_icon.setImageResource(R.mipmap.icon_select_group_check_02);
                    }

                    iv_is_check.setVisibility(View.VISIBLE);
                    rl_root.setBackgroundColor(context.getResources().getColor(R.color.transparent_main));
                    tv_name.setTextColor(Color.parseColor("#333333"));
                } else {
                    if (position == 0) {
                        iv_icon.setImageResource(R.mipmap.icon_select_group_nor_01);
                    } else {
                        iv_icon.setImageResource(R.mipmap.icon_select_group_nor_02);

                    }

                    iv_is_check.setVisibility(View.GONE);
                    rl_root.setBackgroundColor(Color.parseColor("#F7F7F7"));
                    tv_name.setTextColor(Color.parseColor("#999999"));
                }

                tv_name.setText(homeGroupsResponse.getName());


            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }


    }


}
