package com.baibeiyun.bbyiot.module.home.adapter;

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
import com.baibeiyun.bbyiot.model.Response.DeviceGatewayResponse;
import com.baibeiyun.bbyiot.module.home.ui.devicegateway.DeviceGatewayDateilsActivity;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class DeviceGatewayAdapter extends BaseAdapter {


    private List<DeviceGatewayResponse> mList;
    private Context context;

    public DeviceGatewayAdapter(Context context) {
        this.context = context;
    }

    public void updata(List<DeviceGatewayResponse> list) {

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

        TextView tv_gateway_name, tv_gateway_id, tv_state,tv_time;
        ImageView iv_gateway_icon;
        View view_device_state;

        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_device_gateway, null);

            tv_gateway_name = mRootView.findViewById(R.id.item_device_gateway_tv_gateway_name);
            iv_gateway_icon = mRootView.findViewById(R.id.item_device_gateway_iv_gateway_icon);
            tv_gateway_id = mRootView.findViewById(R.id.item_device_gateway_tv_gateway_id);
            tv_state = mRootView.findViewById(R.id.item_device_gateway_tv_state);
            view_device_state = mRootView.findViewById(R.id.item_device_gateway_view_device_state);
            tv_time = mRootView.findViewById(R.id.item_device_gateway_tv_time);

        }

        public void setData(int position) {
            try {
                final DeviceGatewayResponse deviceGatewayResponse = mList.get(position);

                if (deviceGatewayResponse != null) {

                    BaseApplication.loadImageView(iv_gateway_icon,deviceGatewayResponse.getImage(),R.mipmap.icon_device_gateway_icon);
                    //1在线，0离线，2告警
                    if (deviceGatewayResponse.getStatus() == 1) {
                        view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_zaixian);
                        tv_state.setTextColor(Color.parseColor("#3BBE5C"));
                        tv_state.setText("在线");
                    } else if (deviceGatewayResponse.getStatus() == 0) {
                        view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_lixian);
                        tv_state.setTextColor(Color.parseColor("#F34A4A"));
                        tv_state.setText("离线");
                    } else if (deviceGatewayResponse.getStatus() == 2) {
                        view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_gaojing);
                        tv_state.setTextColor(Color.parseColor("#EC9421"));
                        tv_state.setText("告警");
                    }

                    tv_gateway_name.setText(deviceGatewayResponse.getGatewayName());

                    tv_gateway_id.setText(""+deviceGatewayResponse.getId());

                    long lastHeartBeatTime = deviceGatewayResponse.getLastHeartBeatTime();
                    tv_time.setText(DateUtils.stamp2String(lastHeartBeatTime,"yyyy-MM-dd HH:mm:ss"));


                    mRootView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putString(DeviceGatewayDateilsActivity.DECIVE_ID, deviceGatewayResponse.getGatewayId());

                            Intent intent = new Intent(context, DeviceGatewayDateilsActivity.class);
                            bundle.putInt(DeviceGatewayDateilsActivity.DECIVE_ID,deviceGatewayResponse.getId());
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }
                    });

                }


            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }


    }
}
