package com.baibeiyun.bbyiot.module.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.DeviceVideoResponse;
import com.baibeiyun.bbyiot.module.home.ui.devicevideo.DeviceVideoDetailsActivity;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.drawable.DrawableBgUtils;

import java.util.List;

public class DeviceVideoAdapter extends BaseAdapter {


    private List<DeviceVideoResponse> mList;
    private Context context;

    public DeviceVideoAdapter(Context context) {
        this.context = context;
    }

    public void updata(List<DeviceVideoResponse> list) {

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
        private TextView tv_device_name, tv_device_count, tv_device_time, tv_device_content, tv_type, tv_num, tv_state;

        View view_device_state;


        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_device_num, null);

            tv_num = mRootView.findViewById(R.id.item_device_num_tv_num);
            iv_icon = mRootView.findViewById(R.id.item_device_num_iv_icon);

            view_device_state = mRootView.findViewById(R.id.item_device_num_view_device_state);

            tv_state = mRootView.findViewById(R.id.item_device_num_tv_state);



            tv_device_name = mRootView.findViewById(R.id.item_device_num_tv_device_name);
            tv_device_content = mRootView.findViewById(R.id.item_device_num_tv_device_content);
            tv_device_time = mRootView.findViewById(R.id.item_device_num_tv_device_time);


            tv_type = mRootView.findViewById(R.id.item_device_num_tv_type);
            tv_device_count = mRootView.findViewById(R.id.item_device_num_tv_device_count);

        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void setData(int position) {
            try {
                final DeviceVideoResponse deviceVideoResponse = mList.get(position);
                iv_icon.setImageResource(R.mipmap.device_video);

                if (deviceVideoResponse != null) {
                    //BaseApplication.loadImageView(iv_icon,devicesBean.getAttributeImage(),R.mipmap.icon_home_device_testing_01);
                    //1在线，0离线，2告警
                    if (deviceVideoResponse.getDeviceStatus() == 1) {
                        view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_zaixian);
                        tv_state.setTextColor(Color.parseColor("#3BBE5C"));
                        tv_state.setText("在线");
                    } else if (deviceVideoResponse.getDeviceStatus() == 0) {
                        view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_lixian);
                        tv_state.setTextColor(Color.parseColor("#F34A4A"));
                        tv_state.setText("离线");
                    } else if (deviceVideoResponse.getDeviceStatus() == 2) {
                        view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_gaojing);
                        tv_state.setTextColor(Color.parseColor("#EC9421"));
                        tv_state.setText("告警");
                    }
                    tv_num.setText(""+deviceVideoResponse.getDeviceId());

                    tv_device_name.setText(deviceVideoResponse.getDeviceName());

//                    tv_device_content.setText(deviceVideoResponse.getAttributeName());
//
//                    long updateTime = deviceVideoResponse.getUpdateTime();
//                    String stampString = DateUtils.stamp2String(updateTime, "yyyy-MM-dd HH:mm:ss");
//                    tv_device_time.setText(stampString);
//
//                    tv_device_count.setText(deviceVideoResponse.getRealData() + deviceVideoResponse.getAttributeSymbol());


                    tv_type.setText(deviceVideoResponse.getAlarmType());


                    mRootView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putInt(DeviceVideoDetailsActivity.DECIVE_ID, deviceVideoResponse.getDeviceId());

                            Intent intent = new Intent(context, DeviceVideoDetailsActivity.class);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }
                    });

                }
                tv_type.setBackground(DrawableBgUtils.getBgDrawable(context, deviceVideoResponse.getColorType()));

            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }


    }
}
