package com.baibeiyun.bbyiot.module.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.DevicesStatusRespone;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.view.drawable.DrawableBgUtils;

import java.util.List;

public class DeviceStateAdapter extends BaseAdapter {


    private List<DevicesStatusRespone> mList;
    private Context context;

    public DeviceStateAdapter(Context context) {
        this.context = context;
    }

    public void updata(List<DevicesStatusRespone> list) {

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ResourceType")
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
        private final TextView tv_device_name, tv_device_count, tv_device_time, tv_device_content, tv_state2, tv_state;
        View view_device_state;


        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_device_state, null);
            iv_icon = mRootView.findViewById(R.id.item_device_testing_iv_icon);

            tv_device_name = mRootView.findViewById(R.id.item_device_testing_tv_device_name);
            tv_device_content = mRootView.findViewById(R.id.item_device_testing_tv_device_content);
            tv_device_time = mRootView.findViewById(R.id.item_device_testing_tv_device_time);

            tv_state = mRootView.findViewById(R.id.item_device_testing_tv_state);
            view_device_state = mRootView.findViewById(R.id.item_device_testing_view_device_state);


            tv_device_count = mRootView.findViewById(R.id.item_device_testing_tv_device_count);
            tv_state2 = mRootView.findViewById(R.id.item_device_testing_tv_state2);

        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @SuppressLint("ResourceType")
        public void setData(int position) {
            try {

                DevicesStatusRespone devicesStatusRespone = mList.get(position);


                if (devicesStatusRespone != null) {
                    //iv_icon.setImageResource(homeDataBean.getPicRes());
                    BaseApplication.loadImageView(iv_icon, devicesStatusRespone.getAttributeImage(), R.mipmap.icon_home_device_testing_01);

                    //1在线，0离线，2告警
                    if (devicesStatusRespone.getDeviceStatus() == 1) {
                        view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_zaixian);
                        tv_state.setTextColor(Color.parseColor("#3BBE5C"));
                        tv_state.setText("在线");
                    } else if (devicesStatusRespone.getDeviceStatus() == 0) {
                        view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_lixian);
                        tv_state.setTextColor(Color.parseColor("#F34A4A"));
                        tv_state.setText("离线");
                    } else if (devicesStatusRespone.getDeviceStatus() == 2) {
                        view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_gaojing);
                        tv_state.setTextColor(Color.parseColor("#EC9421"));
                        tv_state.setText("告警");
                    }

                    tv_device_name.setText(devicesStatusRespone.getDeviceName() + "");
                    tv_device_content.setText(devicesStatusRespone.getAttributeName() == null ? "" : devicesStatusRespone.getAttributeName());
                    long updateTime = devicesStatusRespone.getUpdateTime();
                    if (updateTime != 0) {
                        String stampString = DateUtils.stamp2String(updateTime, "yyyy-MM-dd HH:mm:ss");
                        tv_device_time.setText(stampString);
                    } else {
                        tv_device_time.setVisibility(View.INVISIBLE);
                    }
                    if(devicesStatusRespone.getRealData()==0 &&devicesStatusRespone.getAttributeSymbol()==null){
                        tv_device_count.setText("");
                    }else {
                        //类型（0数值  1视频  2图片  3开关  4网关）

                        if(devicesStatusRespone.getDeviceType()  == 0)
                        {
                            tv_device_count.setText(devicesStatusRespone.getRealData() + "" + devicesStatusRespone.getAttributeSymbol());
                        }
                        else if(devicesStatusRespone.getDeviceType()  == 3)
                        {
                            tv_device_count.setText( devicesStatusRespone.getAttributeSymbol());
                        }
                        else
                        {
                            tv_device_count.setText("");
                        }

                    }

                    if (!StringUtils.isEmpty(devicesStatusRespone.getAlarmType())) {
                        tv_state2.setVisibility(View.VISIBLE);
                        tv_state2.setText(devicesStatusRespone.getAlarmType() + "");
                        tv_state2.setBackground(DrawableBgUtils.getBgDrawable(context, devicesStatusRespone.getColorType()));
                    } else {
                        tv_state2.setVisibility(View.GONE);
                    }

                }

                //tv_state2.setBackground(DrawableBgUtils.getBgDrawable(context, homeDataBean.getColorType()));
            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }


    }
}
