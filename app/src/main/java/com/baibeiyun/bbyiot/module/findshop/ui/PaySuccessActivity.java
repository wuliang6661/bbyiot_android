package com.baibeiyun.bbyiot.module.findshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.PayResultResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.findshop.contract.PaySuccessContract;
import com.baibeiyun.bbyiot.module.findshop.presenter.PaySuccessPresenter;
import com.baibeiyun.bbyiot.module.main.ui.MainActivity;
import com.baibeiyun.bbyiot.module.mine.ui.order.OrderDetailsActivity;
import com.baibeiyun.bbyiot.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class PaySuccessActivity extends BaseActivity<PaySuccessPresenter> implements PaySuccessContract.View {

    @BindView(R.id.pay_success_state)
    TextView mPaySuccessState;
    @BindView(R.id.pay_success_mode)
    TextView mPaySuccessMode;
    @BindView(R.id.pay_success_money)
    TextView mPaySuccessMoney;
    @BindView(R.id.pay_success_to_order)
    TextView mPaySuccessToOrder;
    @BindView(R.id.pay_success_to_home)
    TextView mPaySuccessToHome;

    private String mOrderId;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_pay_success;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mOrderId = extras.getString("id");
    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("支付结果");

        mPresenter.getResult(mOrderId);
    }

    @Override
    protected PaySuccessPresenter getPresenter() {
        return new PaySuccessPresenter(this);
    }

    @Override
    public void getResult(PayResultResponse response) {
        if (response != null) {
            if (response.getPayType() == 0) {
                mPaySuccessMode.setText("支付宝支付");
            } else {
                mPaySuccessMode.setText("微信支付");
            }
            mPaySuccessMoney.setText("¥" + response.getActualPay());
        }
    }

    @OnClick({R.id.pay_success_to_order, R.id.pay_success_to_home})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay_success_to_order:
                Intent intent = new Intent(PaySuccessActivity.this, OrderDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(OrderDetailsActivity.KEY_ORDER_TYPE, 1);//代发货状态
                bundle.putLong(OrderDetailsActivity.KEY_ORDER_ID, Long.parseLong(mOrderId));//订单id
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
            case R.id.pay_success_to_home:
                ActivityUtils.startActivity(MainActivity.class);
                finish();
                break;
        }

    }
}
