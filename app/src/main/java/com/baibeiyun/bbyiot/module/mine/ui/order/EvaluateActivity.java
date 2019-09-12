package com.baibeiyun.bbyiot.module.mine.ui.order;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.LatestResultResponse;
import com.baibeiyun.bbyiot.model.Response.OrderAddressResponse;
import com.baibeiyun.bbyiot.model.Response.OrderDetailsResponse;
import com.baibeiyun.bbyiot.model.Response.OrderResponse;
import com.baibeiyun.bbyiot.model.request.OrderEvaluateRequest;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.mine.contract.OrderDetailsContract;
import com.baibeiyun.bbyiot.module.mine.presenter.OrderDetailsPresenter;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.RatingBar;
import com.google.gson.Gson;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

public class EvaluateActivity extends BaseActivity<OrderDetailsPresenter> implements OrderDetailsContract.View {

    @BindView(R.id.act_evaluate_iv_goods_pic)
    ImageView iv_goods_pic;

    @BindView(R.id.act_evaluate_tv_goods_name)
    TextView tv_goods_name;
    @BindView(R.id.act_evaluate_tv_goods_monery)
    TextView tv_goods_monery;

    @BindView(R.id.act_evaluate_tv_goods_num)
    TextView tv_goods_num;

    @BindView(R.id.act_evaluate_et_content)
    EditText et_content;

    @BindView(R.id.act_evaluate_rating_bar1)
    RatingBar rating_bar1;

    @BindView(R.id.act_evaluate_rating_bar2)
    RatingBar rating_bar2;

    @BindView(R.id.act_evaluate_rating_bar3)
    RatingBar rating_bar3;

    @BindView(R.id.act_evaluate_chcekbox)
    CheckBox chcekbox;

    @BindView(R.id.act_evaluate_tv_pingfen1)
    TextView tv_pingfen1;

    @BindView(R.id.act_evaluate_tv_pingfen2)
    TextView tv_pingfen2;

    @BindView(R.id.act_evaluate_tv_pingfen3)
    TextView tv_pingfen3;


    private OrderResponse mOrderResponse;

    private float pingjia1 = 0;
    private float pingjia2 = 0;
    private float pingjia3 = 0;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_evaluate;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mOrderResponse = (OrderResponse) extras.getSerializable("bean");
    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("评价");
        if (mOrderResponse != null) {
            tv_goods_name.setText(mOrderResponse.getName());
            //tv_goods_type.setText();
            tv_goods_num.setText("x" + mOrderResponse.getNum());
            tv_goods_monery.setText("¥ " + mOrderResponse.getShouldPay());
            if (mOrderResponse.getImage() != null) {
                String[] split = mOrderResponse.getImage().split(",");
                BaseApplication.loadImageView(iv_goods_pic, split[0]);
            }
        }

        rating_bar1.setOnStarChangeListener(new RatingBar.OnStarChangeListener() {
            @Override
            public void OnStarChanged(float selectedNumber, int position) {
                pingjia1 = selectedNumber;
                tv_pingfen1.setText(String.valueOf(pingjia1));
            }
        });

        rating_bar2.setOnStarChangeListener(new RatingBar.OnStarChangeListener() {
            @Override
            public void OnStarChanged(float selectedNumber, int position) {
                pingjia2 = selectedNumber;
                tv_pingfen2.setText(String.valueOf(pingjia2));
            }
        });

        rating_bar3.setOnStarChangeListener(new RatingBar.OnStarChangeListener() {
            @Override
            public void OnStarChanged(float selectedNumber, int position) {
                pingjia3 = selectedNumber;
                tv_pingfen3.setText(String.valueOf(pingjia3));
            }
        });

    }

    @OnClick(R.id.act_evaluate_btn_submit)
    void submit() {
        OrderEvaluateRequest request = new OrderEvaluateRequest();
        String content = et_content.getText().toString().trim();
        if (StringUtils.isEmpty(content)) {
            ToastUtils.showToast("请填写评价内容");
            return;
        }
        request.setContent(content);


        request.setPriceRationalityScore((int) pingjia3);
        request.setProductQualityScore((int) pingjia2);
        request.setServiceAttitudeScore((int) pingjia1);
        request.setOrderId(mOrderResponse.getId());
        request.setGoodsInfoId(mOrderResponse.getGoodsInfoId());
        request.setIsAnonymous(chcekbox.isChecked() ? 1 : 0);

        float sum = (pingjia1 + pingjia2 + pingjia3) / 3;
        request.setEvaluate((int) sum);


        LogUtils.e(new Gson().toJson(request));
        mPresenter.addUserOrderGoodsEvaluate(request);
    }


    @Override
    public void addUserOrderGoodsEvaluateFinish() {
        finish();
    }

    @Override
    protected OrderDetailsPresenter getPresenter() {
        return new OrderDetailsPresenter(this);
    }


    @Override
    public void getOrderDetailsFinish(OrderDetailsResponse response) {

    }

    @Override
    public void getUserOrderAddressFinish(OrderAddressResponse response) {

    }

    @Override
    public void getLatestResultInfoFinish(LatestResultResponse response) {

    }

}
