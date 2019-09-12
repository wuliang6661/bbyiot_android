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
import com.baibeiyun.bbyiot.model.Response.DeviceSwtichResponse;
import com.baibeiyun.bbyiot.module.home.ui.deviceswitch.DeviceSwitchDetailsActivity;
import com.baibeiyun.bbyiot.module.home.view.KaiduDialog;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class DeviceSwitchAdapter extends BaseAdapter {


    private List<DeviceSwtichResponse> mList;
    private Context context;
    private float moveStep = 0;//托动条的移动步调
    private KaiduDialog kaiduDialog;

    public DeviceSwitchAdapter(Context context) {
        this.context = context;
    }

    public void updata(List<DeviceSwtichResponse> list) {

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

        TextView tv_switch_type, tv_name, tv_guan, tv_kai, tv_kaidu, tv_state_name, tv_property;
        ViewGroup ll_jichu;

        ImageView iv_icon;


        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_device_switch, null);

            tv_switch_type = mRootView.findViewById(R.id.item_device_switch_tv_switch_type);
            tv_property = mRootView.findViewById(R.id.item_device_switch_tv_property);

            tv_name = mRootView.findViewById(R.id.item_device_switch_tv_name);
            iv_icon = mRootView.findViewById(R.id.item_device_switch_iv_icon);

            ll_jichu = mRootView.findViewById(R.id.item_device_switch_ll_jichu);
            tv_guan = mRootView.findViewById(R.id.item_device_switch_tv_guan);
            tv_kai = mRootView.findViewById(R.id.item_device_switch_tv_kai);
            tv_kaidu = mRootView.findViewById(R.id.item_device_switch_tv_kaidu);
            tv_state_name = mRootView.findViewById(R.id.item_device_switch_tv_state_name);


        }

        public void setData(int position) {
            try {

                final DeviceSwtichResponse deviceSwtichResponse = mList.get(position);
                if (deviceSwtichResponse != null) {

                    //当前开关状态（1：开，0：关，默认为关）
                    if (deviceSwtichResponse.getSwitchStatus() == 1) {
                        tv_state_name.setText("开");
                    } else {
                        tv_state_name.setText("关");
                    }

                    //设备所属开关类型 0基础开关 1模拟开关
                    if (deviceSwtichResponse.getSwitchType() == 1) {
                        tv_switch_type.setText("模拟开关");
                        tv_switch_type.setBackgroundResource(R.drawable.shape_device_switch_type_moni);
                        tv_switch_type.setTextColor(Color.parseColor("#4957EC"));

                        ll_jichu.setVisibility(View.GONE);
                        tv_kaidu.setVisibility(View.VISIBLE);

                        tv_state_name.setText(deviceSwtichResponse.getRealData() + "%");
                        BaseApplication.loadImageView(iv_icon, deviceSwtichResponse.getImage(), R.mipmap.switch2);

                    } else if (deviceSwtichResponse.getSwitchType() == 0) {
                        tv_switch_type.setText("基础开关");
                        tv_switch_type.setBackgroundResource(R.drawable.shape_device_switch_type_jichu);
                        tv_switch_type.setTextColor(Color.parseColor("#F5A623"));

                        ll_jichu.setVisibility(View.VISIBLE);
                        tv_kaidu.setVisibility(View.GONE);

                        BaseApplication.loadImageView(iv_icon, deviceSwtichResponse.getImage(), R.mipmap.switch1);

                    } else if (deviceSwtichResponse.getSwitchType() == 2) {
                        tv_switch_type.setText("只读开关");
                        tv_switch_type.setBackgroundResource(R.drawable.shape_device_switch_type_zhidu);
                        tv_switch_type.setTextColor(Color.parseColor("#44BBEA"));

                        ll_jichu.setVisibility(View.GONE);
                        tv_kaidu.setVisibility(View.GONE);

                        BaseApplication.loadImageView(iv_icon, deviceSwtichResponse.getImage(), R.mipmap.switch3);
                    }

                    tv_name.setText(deviceSwtichResponse.getName());
                    tv_property.setText(deviceSwtichResponse.getAttributeName());
                }


                mRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, DeviceSwitchDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(DeviceSwitchDetailsActivity.KEY_IS_BEAN, deviceSwtichResponse);
                        bundle.putInt("switchType", deviceSwtichResponse.getSwitchType());
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                });

                tv_kaidu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(deviceSwtichResponse.getId() + ""
                                ,deviceSwtichResponse.getAttributeId());
                    }
                });

                tv_kai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLinsenter.openOrCloseSwtich(deviceSwtichResponse.getId() + "", null, "1"
                                ,deviceSwtichResponse.getAttributeId());
                    }
                });
                tv_guan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLinsenter.openOrCloseSwtich(deviceSwtichResponse.getId() + "",
                                null, "0",deviceSwtichResponse.getAttributeId());
                    }
                });


            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }
    }

    private void showDialog(final String id, final int attributeId) {

        kaiduDialog = new KaiduDialog(context);

        kaiduDialog.show();
        kaiduDialog.setOnLisenter(new KaiduDialog.OnLisenter() {
            @Override
            public void queding(String value) {
                mLinsenter.openOrCloseSwtich(id, value, "0",attributeId);
                kaiduDialog.dismiss();
            }
        });
    }

    OnClickLickLisenter mLinsenter;

    public interface OnClickLickLisenter {
        void openOrCloseSwtich(String id, String openValue, String swtichStatus,int attributeId);
    }

    public void setOnClickLickLisenter(OnClickLickLisenter lickLisenter) {
        mLinsenter = lickLisenter;
    }

}
