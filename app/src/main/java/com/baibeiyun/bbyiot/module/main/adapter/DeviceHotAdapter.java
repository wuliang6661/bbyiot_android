package com.baibeiyun.bbyiot.module.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.model.Response.GoodsShopResponse;
import com.baibeiyun.bbyiot.module.WebActivity;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class DeviceHotAdapter extends BaseAdapter {

    private List<GoodsShopResponse> mList;
    private Context context;
    private int currentPosition = 0;

    public DeviceHotAdapter(Context context) {
        this.context = context;
    }

    public void updata(List<GoodsShopResponse> list) {
        this.mList = list;
        this.notifyDataSetChanged();
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

        View mRootView;
        private final ImageView iv_icon;
        private final TextView tv_name;
        View view_left_line1;
        View view_left_line2;
        View view_right_line1;
        View view_right_line2;


        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_device_hot, null);

            iv_icon = (ImageView) mRootView.findViewById(R.id.item_device_hot_iv_icon);
            tv_name = (TextView) mRootView.findViewById(R.id.item_device_hot_tv_name);

            view_left_line1 = mRootView.findViewById(R.id.item_device_hot_view_left_line1);
            view_left_line2 = mRootView.findViewById(R.id.item_device_hot_view_left_line2);

            view_right_line1 = mRootView.findViewById(R.id.item_device_hot_view_right_line1);
            view_right_line2 = mRootView.findViewById(R.id.item_device_hot_view_right_line2);
        }

        public void setData(int position) {
            try {

                final GoodsShopResponse response = mList.get(position);

                if (position % 3 == 0) {
                    view_left_line1.setVisibility(View.VISIBLE);
                } else if (position % 3 == 1) {
                    view_left_line2.setVisibility(View.VISIBLE);
                    view_right_line2.setVisibility(View.VISIBLE);
                } else if (position % 3 == 2) {
                    view_right_line2.setVisibility(View.VISIBLE);
                }

                if (response != null) {
                    //iv_icon.setImageResource(homeDataBean.getPicRes());
                    BaseApplication.loadImageView(iv_icon, response.getImage());
                    tv_name.setText(response.getTitle());
                }

                mRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, WebActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(IConstant.WEB_URL, IConstant.BASE_SHOP_DETAIL_URL + response.getId());
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                });
            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }


    }
}
