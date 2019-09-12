package com.baibeiyun.bbyiot.module.test.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.bean.TestDataBean;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class TestAdapter extends BaseAdapter {

    private List<TestDataBean> mList;
    private Context context;

    public TestAdapter(Context context) {
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


        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_message, null);
        }

        public void setData(int position) {
            try {


            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }


    }
}
