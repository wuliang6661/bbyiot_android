package com.baibeiyun.bbyiot.module.base.addpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int layoutId;

    public MyBaseAdapter(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    public void update(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //初始化ViewHolder,使用通用的ViewHolder，ViewHolder的初始化咯
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, layoutId, position);

        convert(holder, getItem(position), position);
        return holder.getConvertView();
    }

    //将convert方法公布出去
    public abstract void convert(ViewHolder holder, T t, int position);
}
