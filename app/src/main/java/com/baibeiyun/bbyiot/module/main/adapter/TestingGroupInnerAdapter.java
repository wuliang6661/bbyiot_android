package com.baibeiyun.bbyiot.module.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.TestingResponse;
import com.baibeiyun.bbyiot.module.base.addpter.MyBaseAdapter;
import com.baibeiyun.bbyiot.module.base.addpter.ViewHolder;
import com.baibeiyun.bbyiot.module.home.ui.devicenum.DeviceNumDetailsActivity;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.view.drawable.DrawableBgUtils;

public class TestingGroupInnerAdapter extends MyBaseAdapter<TestingResponse.DevicesBean> {

    private  ImageView iv_icon;
    private  TextView tv_device_name, tv_device_count, tv_device_time, tv_device_content, tv_state2, tv_state;

    View view_device_state;

    public TestingGroupInnerAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void convert(ViewHolder holder, final TestingResponse.DevicesBean devicesBean, int position) {
        iv_icon = holder.getView(R.id.item_device_testing_iv_icon);

        tv_state = holder.getView(R.id.item_device_testing_tv_state);
        view_device_state = holder.getView(R.id.item_device_testing_view_device_state);

        tv_device_name = holder.getView(R.id.item_device_testing_tv_device_name);
        tv_device_content = holder.getView(R.id.item_device_testing_tv_device_content);
        tv_device_time =holder.getView(R.id.item_device_testing_tv_device_time);


        tv_device_count = holder.getView(R.id.item_device_testing_tv_device_count);
        tv_state2 =holder.getView(R.id.item_device_testing_tv_state2);


        BaseApplication.loadImageView(iv_icon,devicesBean.getAttributeImage(),R.mipmap.icon_home_device_testing_01);

        //1在线，0离线，2告警
        if (devicesBean.getDeviceStatus() == 1) {
            view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_zaixian);
            tv_state.setTextColor(Color.parseColor("#3BBE5C"));
            tv_state.setText("在线");
        } else if (devicesBean.getDeviceStatus() == 0) {
            view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_lixian);
            tv_state.setTextColor(Color.parseColor("#F34A4A"));
            tv_state.setText("离线");
        } else if (devicesBean.getDeviceStatus() == 2) {
            view_device_state.setBackgroundResource(R.drawable.shape_deivce_state_gaojing);
            tv_state.setTextColor(Color.parseColor("#EC9421"));
            tv_state.setText("告警");
        }


        tv_device_name.setText(devicesBean.getDeviceName());
        tv_device_content.setText(devicesBean.getAttributeName());

        long updateTime = devicesBean.getUpdateTime();
        String stampString = DateUtils.stamp2String(updateTime, "yyyy-MM-dd HH:mm:ss");
        tv_device_time.setText(stampString);

        if(devicesBean.getRealData()==0 &&devicesBean.getAttributeSymbol()==null){
            tv_device_count.setText("");
        }else {
            //类型（0数值  1视频  2图片  3开关  4网关）

            if(devicesBean.getDeviceType()  == 0)
            {
                tv_device_count.setText(devicesBean.getRealData() + "" + devicesBean.getAttributeSymbol());
            }
            else if(devicesBean.getDeviceType()  == 3)
            {
                tv_device_count.setText( devicesBean.getAttributeSymbol());
            }
            else
            {
                tv_device_count.setText("");
            }

        }


        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(DeviceNumDetailsActivity.DECIVE_ID, devicesBean.getDeviceId());
                bundle.putInt(DeviceNumDetailsActivity.ATTRIBUTE_ID, devicesBean.getAttributeId());
                readyGo(DeviceNumDetailsActivity.class, bundle);
            }
        });

        tv_state2.setText(devicesBean.getAlarmType() + "");
        tv_state2.setBackground(DrawableBgUtils.getBgDrawable(mContext, devicesBean.getColorType()));

    }



    private void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(mContext, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }


}
