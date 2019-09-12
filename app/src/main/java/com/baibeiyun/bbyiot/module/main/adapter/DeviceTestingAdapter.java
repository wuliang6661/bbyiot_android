package com.baibeiyun.bbyiot.module.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.DeviceNumResponse;
import com.baibeiyun.bbyiot.module.home.ui.devicenum.DeviceNumDetailsActivity;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class DeviceTestingAdapter extends BaseAdapter {

    private List<DeviceNumResponse> mList;
    private Context context;
    private int currentPosition = 0;

    public DeviceTestingAdapter(Context context) {
        this.context = context;
    }

    public void updata(List<DeviceNumResponse> list) {
        this.mList = list;
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        if (null != mList) {
            if (mList.size() > 3) {
                return 3;
            }
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
        private final TextView tv_device_name, tv_device_count, tv_device_time, tv_device_content, tv_state;

        View view_device_state;

        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_device_testing, null);

            iv_icon = mRootView.findViewById(R.id.item_device_testing_iv_icon);

            tv_device_name = mRootView.findViewById(R.id.item_device_testing_tv_device_name);
            tv_device_content = mRootView.findViewById(R.id.item_device_testing_tv_device_content);
            tv_device_time = mRootView.findViewById(R.id.item_device_testing_tv_device_time);


            tv_state = mRootView.findViewById(R.id.item_device_testing_tv_state);
            view_device_state = mRootView.findViewById(R.id.item_device_testing_view_device_state);


            tv_device_count = mRootView.findViewById(R.id.item_device_testing_tv_device_count);
        }

        public void setData(int position) {
            try {

                final DeviceNumResponse deviceNumResponse = mList.get(position);
                if (deviceNumResponse != null) {

                    BaseApplication.loadImageView(iv_icon,deviceNumResponse.getAttributeImage(),R.mipmap.icon_home_device_testing_01);
                    //1在线，0离线，2告警
                    if (deviceNumResponse.getDeviceStatus() == 1) {
                        view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_zaixian);
                        tv_state.setTextColor(Color.parseColor("#3BBE5C"));
                        tv_state.setText("在线");
                    } else if (deviceNumResponse.getDeviceStatus() == 0) {
                        view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_lixian);
                        tv_state.setTextColor(Color.parseColor("#F34A4A"));
                        tv_state.setText("离线");
                    } else if (deviceNumResponse.getDeviceStatus() == 2) {
                        view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_gaojing);
                        tv_state.setTextColor(Color.parseColor("#EC9421"));
                        tv_state.setText("告警");
                    }
                }

                tv_device_name.setText(deviceNumResponse.getDeviceName());
                tv_device_content.setText(deviceNumResponse.getAttributeName());
                long updateTime = deviceNumResponse.getUpdateTime();
                String stampString = DateUtils.stamp2String(updateTime, "yyyy-MM-dd HH:mm:ss");
                tv_device_time.setText(stampString);

                tv_device_count.setText(deviceNumResponse.getRealData() + deviceNumResponse.getAttributeSymbol());

                mRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(DeviceNumDetailsActivity.DECIVE_ID, deviceNumResponse.getDeviceId());
                        bundle.putInt(DeviceNumDetailsActivity.ATTRIBUTE_ID, deviceNumResponse.getAttributeId());
                        readyGo(DeviceNumDetailsActivity.class, bundle);
                    }
                });
            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }
    }

    private void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
