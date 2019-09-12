package com.baibeiyun.bbyiot.module.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.MessageListResponse;
import com.baibeiyun.bbyiot.model.Response.OrderMessageResponse;
import com.baibeiyun.bbyiot.module.home.ui.MessageDetailsActivity;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;

import java.util.List;

public class MessageAdapter extends BaseAdapter {


    private List mList;
    private Context context;
    private int messageType;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void updata(List list, int messageType) {
        this.messageType = messageType;
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
        private final ViewGroup rl_look_details;

        TextView tv_message_time, tv_title, tv_content;
        View view_read_state;

        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_message, null);

            rl_look_details = mRootView.findViewById(R.id.item_message_rl_look_details);
            tv_message_time = mRootView.findViewById(R.id.item_message_tv_message_time);
            tv_title = mRootView.findViewById(R.id.item_message_tv_title);
            tv_content = mRootView.findViewById(R.id.item_message_tv_content);
            view_read_state = mRootView.findViewById(R.id.item_message_view_read_state);
        }

        public void setData(int position) {
            try {

                if (messageType == 2) {
                    final MessageListResponse dataBean = (MessageListResponse) mList.get(position);

                    String timeStr = DateUtils.stamp2String(dataBean.getUpdateTime(), "yyyy年MM月dd日 HH:mm");
                    tv_message_time.setText(timeStr);
                    tv_title.setText(dataBean.getTitle());
                    tv_content.setText(dataBean.getContent());
                    if (dataBean.getReadStatus() == 0) {
                        view_read_state.setVisibility(View.VISIBLE);
                    } else {
                        view_read_state.setVisibility(View.GONE);
                    }
                    rl_look_details.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("type", 2);
                            bundle.putSerializable("dataBean", dataBean);
                            ActivityUtils.startActivity((Activity) context, MessageDetailsActivity.class, bundle);
                        }
                    });
                } else {

                    final OrderMessageResponse dataBean = (OrderMessageResponse) mList.get(position);

                    tv_message_time.setText(StringUtils.isEmpty(dataBean.getUpdateDate()) ? "" : dataBean.getUpdateDate());
                    tv_title.setText(StringUtils.isEmpty(dataBean.getTitle()) ? "" : dataBean.getTitle());
                    tv_content.setText(StringUtils.isEmpty(dataBean.getContent()) ? "" : dataBean.getContent());
                    if (dataBean.getReadStatus() == 0) {
                        view_read_state.setVisibility(View.VISIBLE);
                    } else {
                        view_read_state.setVisibility(View.GONE);
                    }
                    rl_look_details.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("type", 1);
                            bundle.putSerializable("dataBean", dataBean);
                            ActivityUtils.startActivity((Activity) context, MessageDetailsActivity.class, bundle);
                        }
                    });
                }
            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }


    }
}
