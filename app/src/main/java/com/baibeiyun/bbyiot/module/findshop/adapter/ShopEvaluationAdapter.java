package com.baibeiyun.bbyiot.module.findshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.model.Response.GoodsReviewResponse;
import com.baibeiyun.bbyiot.module.WebActivity;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.ratingstar.RatingStarView;

import java.util.ArrayList;
import java.util.List;

public class ShopEvaluationAdapter extends BaseAdapter {

    private List<GoodsReviewResponse> mList = new ArrayList<>();
    private Context context;

    public ShopEvaluationAdapter(Context context) {
        this.context = context;
    }

    public void updata(List<GoodsReviewResponse> list) {
        mList.addAll(list);
        this.notifyDataSetChanged();
    }

    public List<GoodsReviewResponse> getData() {
        return mList;
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
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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
        LinearLayout mLayout;
        ImageView mIcon;
        TextView mBound;
        TextView mTitle;
        TextView mTag1;
        TextView mTag2;
        RatingStarView mRatingStar;
        TextView mRatingTxt;


        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_shophar_dware, null);
            mLayout = mRootView.findViewById(R.id.item_shophar_dware_iv_goods_bound_layout);
            mIcon = mRootView.findViewById(R.id.item_shophar_dware_iv_goods_pic);
            mBound = mRootView.findViewById(R.id.item_shophar_dware_iv_goods_bound);
            mTitle = mRootView.findViewById(R.id.item_shophar_dware_iv_goods_title);
            mTag1 = mRootView.findViewById(R.id.item_shophar_dware_iv_goods_tag1);
            mTag2 = mRootView.findViewById(R.id.item_shophar_dware_iv_goods_tag2);
            mRatingStar = mRootView.findViewById(R.id.rating_star);
            mRatingTxt = mRootView.findViewById(R.id.item_shophar_dware_tv_pingfen);
        }

        public void setData(int position) {
            try {
                final GoodsReviewResponse response = mList.get(position);
                BaseApplication.loadImageView(mIcon, response.getImage());
                mTitle.setText(response.getTitle());
                setTag1(mTag1, response.getItems());
                mTag2.setText(getTag2(response.getNames()));
                if (TextUtils.isEmpty(response.getBrand())) {
                    mLayout.setVisibility(View.GONE);
                } else {
                    mLayout.setVisibility(View.VISIBLE);
                    mBound.setText(response.getBrand());
                }

                mRatingStar.setRating(Float.parseFloat(response.getPerformance()));
                mRatingTxt.setText(response.getPerformance() + "");

                mRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(IConstant.WEB_URL, IConstant.BASE_SHOP_DETAIL_URL + response.getId());
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                });

            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }
    }

    private void setTag1(TextView textView, List<GoodsReviewResponse.ItemsBean> list) {
        SpannableStringBuilder builder = new SpannableStringBuilder("");

        for (GoodsReviewResponse.ItemsBean itemsBean : list) {
            ForegroundColorSpan txtBlack = new ForegroundColorSpan(Color.parseColor("#333333"));
            ForegroundColorSpan txtGray = new ForegroundColorSpan(Color.parseColor("#9A9A9A"));
            builder.append(itemsBean.getItemName());
            Log.d("SpannableString1", "start=" + (builder.length() - itemsBean.getItemName().length()) + "end=" + builder.length());
            builder.setSpan(txtBlack, builder.length() - itemsBean.getItemName().length(), builder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            builder.append(" ");
            builder.append(itemsBean.getReviewResult());
            Log.d("SpannableString2", "start=" + (builder.length() - itemsBean.getReviewResult().length()) + "end=" + builder.length());
            builder.setSpan(txtGray, builder.length() - itemsBean.getReviewResult().length(), builder.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            builder.append(" | ");
        }
        textView.setText(builder);
    }


    /**
     * 测评人员
     *
     * @param strings
     * @return
     */
    private String getTag2(List<String> strings) {
        if (strings == null || strings.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            builder.append(strings.get(i));
            if (i != strings.size() - 1) {
                builder.append(" | ");
            }
        }
        return builder.toString();
    }
}
