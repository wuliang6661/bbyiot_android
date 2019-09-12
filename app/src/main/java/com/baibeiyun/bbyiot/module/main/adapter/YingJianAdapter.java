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
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.module.WebActivity;
import com.baibeiyun.bbyiot.utils.LogUtils;

public class YingJianAdapter extends BaseAdapter {

    private Context context;

    String[] labelArr = {"工业网关", "智能设备", "工业传感器", "工业消防", "工业安全", "工业产线", "品牌馆"};

    int[] iconArr = {R.mipmap.icon_find_yingjian_01
            , R.mipmap.icon_find_yingjian_02
            , R.mipmap.icon_find_yingjian_03
            , R.mipmap.icon_find_yingjian_04
            , R.mipmap.icon_find_yingjian_05
            , R.mipmap.icon_find_yingjian_06
            , R.mipmap.icon_find_yingjian_07
    };

    public YingJianAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {

        return iconArr.length;
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


        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_ying_jian, null);
            iv_icon = mRootView.findViewById(R.id.item_ying_jian_iv_icon);
            tv_name = mRootView.findViewById(R.id.item_ying_jian_tv_name);
        }


        public void setData(final int position) {
            try {
                iv_icon.setImageResource(iconArr[position]);
                tv_name.setText(labelArr[position]);


                mRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebActivity.class);
                        Bundle bundle = new Bundle();
                        if (position != 6) {
                            bundle.putString(IConstant.WEB_URL, IConstant.BASE_SHOP_LIST_URL + (position + 1));
                        } else {
                            bundle.putString(IConstant.WEB_URL, IConstant.BASE_SHOP_PINPAIGUAN_URL);
                        }
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
