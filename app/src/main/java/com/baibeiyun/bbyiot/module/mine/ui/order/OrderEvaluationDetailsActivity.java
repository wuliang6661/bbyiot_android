package com.baibeiyun.bbyiot.module.mine.ui.order;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.EvaluateListResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.utils.ListUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.view.FloatRatingBar;

import java.io.Serializable;

import butterknife.BindView;

public class OrderEvaluationDetailsActivity extends BaseActivity {

    @BindView(R.id.item_order_iv_pic)
    ImageView iv_pic;

    @BindView(R.id.item_order_tv_goods_name)
    TextView tv_goods_name;

    @BindView(R.id.tv_content)
    TextView tv_content;

    @BindView(R.id.tv_name_and_time)
    TextView tv_name_and_time;


    @BindView(R.id.tv_pingfen)
    TextView tv_pingfen;


    @BindView(R.id.act_evaluate_details_rating_bar1)
    FloatRatingBar rating_bar1;

    @BindView(R.id.act_evaluate_details_rating_bar2)
    FloatRatingBar rating_bar2;

    @BindView(R.id.act_evaluate_details_rating_bar3)
    FloatRatingBar rating_bar3;

    @BindView(R.id.act_evaluate_details_tv_score1)
    TextView tv_score1;
    @BindView(R.id.act_evaluate_details_tv_score2)
    TextView tv_score2;
    @BindView(R.id.act_evaluate_details_tv_score3)
    TextView tv_score3;


    private EvaluateListResponse mEvaluateListResponse;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_order_evaluation_details;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mEvaluateListResponse = (EvaluateListResponse) extras.getSerializable("bean");
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("评价详情");

        try {
            if (mEvaluateListResponse != null) {
                BaseApplication.loadImageView(iv_pic, mEvaluateListResponse.getImage());
                tv_goods_name.setText(StringUtils.isEmpty(mEvaluateListResponse.getGoodsName()) ? "" : mEvaluateListResponse.getGoodsName());
                tv_content.setText(String.valueOf(mEvaluateListResponse.getContent()));

                tv_pingfen.setText(String.valueOf(mEvaluateListResponse.getEvaluate()));

                rating_bar1.setRate(mEvaluateListResponse.getServiceAttitudeScore());
                rating_bar2.setRate(mEvaluateListResponse.getProductQualityScore());
                rating_bar3.setRate(mEvaluateListResponse.getPriceRationalityScore());

                tv_score1.setText(String.valueOf(mEvaluateListResponse.getServiceAttitudeScore()));
                tv_score2.setText(String.valueOf(mEvaluateListResponse.getProductQualityScore()));
                tv_score3.setText(String.valueOf(mEvaluateListResponse.getPriceRationalityScore()));

                tv_name_and_time.setText((StringUtils.isEmpty(mEvaluateListResponse.getUserName()) ? "" : mEvaluateListResponse.getUserName())
                        + (StringUtils.isEmpty(mEvaluateListResponse.getCreateDate()) ? "" : mEvaluateListResponse.getCreateDate()));
            }
        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }
}
