package com.baibeiyun.bbyiot.module.mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class SelectGroupAdapter extends BaseAdapter {

    List<String> mList;

    int selectPosition = 0;

    private Context context;

    public SelectGroupAdapter(Context context) {
        this.context = context;
    }

    public void updata(List<String> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void select(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
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
        private final TextView tv_lable;


        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_select_group, null);

            tv_lable = mRootView.findViewById(R.id.item_select_group_tv_lable);
        }

        public void setData(int position) {
            try {
                if (selectPosition == position) {
                    tv_lable.setTextColor(context.getResources().getColor(R.color.main_color));
                } else {
                    tv_lable.setTextColor(Color.parseColor("#666666"));
                }

                tv_lable.setText(mList.get(position));
            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }
    }


}
