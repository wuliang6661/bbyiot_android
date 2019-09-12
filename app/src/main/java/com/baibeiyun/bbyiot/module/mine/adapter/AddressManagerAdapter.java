package com.baibeiyun.bbyiot.module.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.AddressResponse;
import com.baibeiyun.bbyiot.module.base.addpter.MyBaseAdapter;
import com.baibeiyun.bbyiot.module.base.addpter.ViewHolder;
import com.baibeiyun.bbyiot.module.mine.ui.AddressEditActivity;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

public class AddressManagerAdapter extends MyBaseAdapter<AddressResponse> {

    private ImageView iv_pic, iv_isdefault;
    private TextView tv_name, tv_mobile, tv_default, tv_address;
    private Bundle bundle;

    public AddressManagerAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, final AddressResponse response, int position) {
        try {
            iv_pic = holder.getView(R.id.item_address_iv_pic);
            iv_isdefault = holder.getView(R.id.item_address_iv_isdefault);
            tv_name = holder.getView(R.id.item_address_tv_name);
            tv_mobile = holder.getView(R.id.item_address_tv_mobile);
            tv_default = holder.getView(R.id.item_address_tv_default);
            tv_address = holder.getView(R.id.item_address_tv_address);


            if (response.getIsDefault() == 1) {
                iv_pic.setImageResource(R.mipmap.icon_address_default);
                iv_isdefault.setImageResource(R.mipmap.icon_address_cehck_check);
                tv_default.setVisibility(View.VISIBLE);
            } else {
                iv_pic.setImageResource(R.mipmap.icon_address_default_not);
                iv_isdefault.setImageResource(R.mipmap.icon_address_cehck_nor);
                tv_default.setVisibility(View.GONE);
            }

            tv_name.setText(response.getName());
            tv_mobile.setText(response.getPhone());
            StringBuffer sb = new StringBuffer();
            sb.append(response.getProvince())
                    .append(response.getCity())
                    .append(response.getArea())
                    .append(response.getAddress());

            tv_address.setText(sb.toString());

            holder.getView(R.id.item_address_ll_default).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(response.getIsDefault()!=1){
                        mLinsenter.setDefault(response);
                    }
                }
            });

            holder.getView(R.id.item_address_ll_edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bundle = new Bundle();
                    bundle.putInt("type", 2);
                    bundle.putSerializable("bean", response);
                    ActivityUtils.startActivity((Activity) mContext,AddressEditActivity.class, bundle);
                }
            });

            holder.getView(R.id.item_address_ll_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mLinsenter.delete(response);
                }
            });
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    OnClickLinsenter mLinsenter;
    public interface OnClickLinsenter{
        void setDefault(AddressResponse response);
        void delete(AddressResponse response);
    }

    public void setOnClickLinsenter(OnClickLinsenter linsenter) {
        this.mLinsenter = linsenter;
    }
}
