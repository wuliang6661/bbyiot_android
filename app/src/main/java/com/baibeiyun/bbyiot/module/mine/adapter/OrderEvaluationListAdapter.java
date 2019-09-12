package com.baibeiyun.bbyiot.module.mine.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.EvaluateListResponse;
import com.baibeiyun.bbyiot.module.base.addpter.MyBaseAdapter;
import com.baibeiyun.bbyiot.module.base.addpter.ViewHolder;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.view.FloatRatingBar;

public class OrderEvaluationListAdapter extends MyBaseAdapter<EvaluateListResponse> {

    private FloatRatingBar floatRatingBar;
    private TextView tv_score;
    private ImageView iv_pic;

    public OrderEvaluationListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void convert(ViewHolder holder, EvaluateListResponse response, int position) {
        try {
            floatRatingBar = holder.getView(R.id.item_order_evaluation_rating_bar);
            tv_score = holder.getView(R.id.item_order_evaluation_tv_score);

            iv_pic = holder.getView(R.id.item_order_iv_pic);

            BaseApplication.loadImageView(iv_pic, response.getImage());
            ((TextView) holder.getView(R.id.item_order_tv_goods_name)).setText(response.getGoodsName());
            ((TextView) holder.getView(R.id.item_order_tv_goods_monery)).setText(
                    (StringUtils.isEmpty(response.getUserName()) ? "" : response.getUserName())
                            + (StringUtils.isEmpty(response.getCreateDate()) ? "" : response.getCreateDate()));

            ((TextView) holder.getView(R.id.tv_content)).setText(response.getContent());

            floatRatingBar.setRate(response.getEvaluate());
            tv_score.setText(String.valueOf(response.getEvaluate()));

        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
    }
}
