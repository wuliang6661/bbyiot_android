package com.baibeiyun.bbyiot.module.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.bean.TestDataBean;
import com.baibeiyun.bbyiot.module.home.ui.devicegateway.DeviceGatewayActivity;
import com.baibeiyun.bbyiot.module.home.ui.devicenum.DeviceNumActivity;
import com.baibeiyun.bbyiot.module.home.ui.devicepic.DevicePictureActivity;
import com.baibeiyun.bbyiot.module.home.ui.deviceswitch.DeviceSwitchActivity;
import com.baibeiyun.bbyiot.module.home.ui.devicevideo.DeviceVideoActivity;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class DeviceAllAdapter extends BaseAdapter {

    private List<TestDataBean> mList;
    private Context context;

    public DeviceAllAdapter(Context context) {
        this.context = context;
    }

    public void updata(List<TestDataBean> list) {

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
        private final TextView tv_name;
        private final ImageView iv_icon;


        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_device_all, null);

            iv_icon = (ImageView) mRootView.findViewById(R.id.item_device_all_iv_icon);
            tv_name = (TextView) mRootView.findViewById(R.id.item_device_all_tv_name);
        }

        public void setData(int position) {
            try {

                final TestDataBean homeDataBean = mList.get(position);

                iv_icon.setImageResource(homeDataBean.getPicRes());
                tv_name.setText(homeDataBean.getLabel1());


                mRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (homeDataBean.getLabel1()) {
                            case "数值设备":
                                readyGo(DeviceNumActivity.class);
                                break;
                            case "视频设备":
                                readyGo(DeviceVideoActivity.class);
                                break;

                            case "图片设备":
                                readyGo(DevicePictureActivity.class);
                                break;

                            case "开关设备":
                                readyGo(DeviceSwitchActivity.class);
                                break;

                            case "网关设备":
                                readyGo(DeviceGatewayActivity.class);
                                break;
                        }
                    }
                });
            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }


    }

    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
