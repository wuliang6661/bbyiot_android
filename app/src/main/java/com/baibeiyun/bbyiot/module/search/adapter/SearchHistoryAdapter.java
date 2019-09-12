package com.baibeiyun.bbyiot.module.search.adapter;

import android.content.Context;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.base.addpter.MyBaseAdapter;
import com.baibeiyun.bbyiot.module.base.addpter.ViewHolder;

public class SearchHistoryAdapter extends MyBaseAdapter<String> {

    TextView tv_history;

    public SearchHistoryAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, String s, int position) {
        tv_history = holder.getView(R.id.item_search_history_tv_history);

        tv_history.setText(s);
    }


}
