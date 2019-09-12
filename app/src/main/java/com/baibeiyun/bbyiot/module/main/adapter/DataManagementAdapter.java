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
import com.baibeiyun.bbyiot.module.mine.ui.GroupActivity;
import com.baibeiyun.bbyiot.module.mine.ui.MonitoringAlarmActivity;
import com.baibeiyun.bbyiot.module.mine.ui.analysis.StatisticAnalysisActivityNew;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class DataManagementAdapter extends BaseAdapter {

    private List<TestDataBean> mList;
    private Context context;

    public DataManagementAdapter(Context context) {
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
        DataManagementAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new DataManagementAdapter.ViewHolder();
            convertView = holder.mRootView;
            convertView.setTag(holder);
        } else {
            holder = (DataManagementAdapter.ViewHolder) convertView.getTag();
        }

        holder.setData(position);

        return convertView;
    }

    class ViewHolder {

        View mRootView;
        private final ImageView iv_icon;
        private final TextView tv_name;


        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_data_manager, null);
            iv_icon = mRootView.findViewById(R.id.item_ying_jian_iv_icon);
            tv_name = mRootView.findViewById(R.id.item_ying_jian_tv_name);
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
                            case "分组管理":
                                readyGo(GroupActivity.class);
                                break;
                            case "监控报警":
                                readyGo(MonitoringAlarmActivity.class);
                                break;

                            case "统计分析":
                                readyGo(StatisticAnalysisActivityNew.class);
                                break;

                            case "告警模版":
                                //TODO 暂无 之前我的页面中没有设置跳转页面
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
