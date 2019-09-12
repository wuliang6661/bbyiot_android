package com.baibeiyun.bbyiot.module.mine.ui.order;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.LatestResultResponse;
import com.baibeiyun.bbyiot.model.Response.OrderAddressResponse;
import com.baibeiyun.bbyiot.model.Response.OrderDetailsResponse;
import com.baibeiyun.bbyiot.model.event.PayFinishEvent;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.mine.contract.OrderDetailsContract;
import com.baibeiyun.bbyiot.module.mine.presenter.OrderDetailsPresenter;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.PayUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailsActivity extends BaseActivity<OrderDetailsPresenter> implements OrderDetailsContract.View {

    @BindView(R.id.item_order_tv_bottom_monery)
    TextView tv_bottom_monery;

    @BindView(R.id.act_order_details_iv_order_state)
    ImageView iv_order_state;

    @BindView(R.id.act_order_details_tv_order_state)
    TextView tv_order_state;
    @BindView(R.id.act_order_details_tv_order_describe)
    TextView tv_order_describe;

    @BindView(R.id.act_order_details_ll_Logistics_info)
    View ll_Logistics_info;

    @BindView(R.id.act_order_details_tv_logistics_step)
    TextView tv_logistics_step;

    @BindView(R.id.act_order_details_tv_logistics_time)
    TextView tv_logistics_time;

    @BindView(R.id.act_order_details_tv_total_money)
    TextView tv_total_money;
    @BindView(R.id.act_order_details_tv_freight)
    TextView tv_freight;

    @BindView(R.id.act_order_details_tv_invoice_type)
    TextView tv_invoice_type;
    @BindView(R.id.act_order_details_tv_invoice_name)
    TextView tv_invoice_name;
    @BindView(R.id.act_order_details_tv_invoice_content)
    TextView tv_invoice_content;

    @BindView(R.id.act_order_details_tv_order_no)
    TextView tv_order_no;
    @BindView(R.id.act_order_details_tv_submit_time)
    TextView tv_submit_time;
    @BindView(R.id.act_order_details_tv_pay_type)
    TextView tv_pay_type;

    @BindView(R.id.act_order_details_tv_pay_time)
    TextView tv_pay_time;
    @BindView(R.id.act_order_details_ll_pay_time)
    View ll_pay_time;

    @BindView(R.id.item_address_iv_pic)
    ImageView iv_pic;


    @BindView(R.id.item_address_tv_name)
    TextView tv_name;
    @BindView(R.id.item_address_tv_mobile)
    TextView tv_mobile;
    @BindView(R.id.item_address_tv_default)
    TextView tv_default;
    @BindView(R.id.item_address_tv_address)
    TextView tv_address;


    @BindView(R.id.act_order_details_iv_goods_pic)
    ImageView iv_goods_pic;
    @BindView(R.id.act_order_details_tv_goods_name)
    TextView tv_goods_name;
    @BindView(R.id.act_order_details_tv_goods_type)
    TextView tv_goods_type;
    @BindView(R.id.act_order_details_tv_goods_monery)
    TextView tv_goods_monery;
    @BindView(R.id.act_order_details_tv_goods_num)
    TextView tv_goods_num;


    @BindView(R.id.item_order_tv_right_1)
    TextView tv_right_1;
    @BindView(R.id.item_order_tv_right_2)
    TextView tv_right_2;
    @BindView(R.id.item_order_tv_right_3)
    TextView tv_right_3;
    @BindView(R.id.item_order_tv_right_4)
    TextView tv_right_4;

    @BindView(R.id.item_order_ll_invoice_content)
    ViewGroup ll_invoice_content;
    @BindView(R.id.item_order_ll_invoice_type)
    ViewGroup ll_invoice_type;
    @BindView(R.id.item_order_ll_invoice_rise)
    ViewGroup ll_invoice_rise;


    public static final String KEY_ORDER_TYPE = "key_order_type";
    public static final String KEY_ORDER_ID = "key_order_id";

    private int mOrderType;
    private long mOrderId;
    private OrderDetailsResponse mOrderDetailsResponse;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_order_details_pay;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mOrderType = extras.getInt(KEY_ORDER_TYPE);
        mOrderId = extras.getLong(KEY_ORDER_ID);
        LogUtils.w("------  mOrderType == " + mOrderType);
    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("订单详情");

        mPresenter.getOrderDetails(String.valueOf(mOrderId));
        mPresenter.getUserOrderAddress(String.valueOf(mOrderId));
        mPresenter.getLatestResultInfo(String.valueOf(mOrderId));

        switch (mOrderType) {
            case 0:
                // "待付款";
                iv_order_state.setImageResource(R.mipmap.icon_order_pay);
                tv_bottom_monery.setVisibility(View.VISIBLE);
                tv_right_1.setVisibility(View.VISIBLE);
                tv_right_2.setVisibility(View.VISIBLE);
                tv_right_3.setVisibility(View.GONE);
                tv_right_4.setVisibility(View.GONE);

                tv_right_1.setText("立即付款");
                tv_right_2.setText("取消订单");

                tv_order_state.setText("等待付款");


                break;
            case 1:
                // "待发货";
                iv_order_state.setImageResource(R.mipmap.icon_order_fahuo);
                tv_bottom_monery.setVisibility(View.VISIBLE);
                tv_right_1.setVisibility(View.VISIBLE);
                tv_right_2.setVisibility(View.GONE);
                tv_right_3.setVisibility(View.GONE);
                tv_right_4.setVisibility(View.GONE);
                tv_right_1.setText("提醒发货");

                tv_order_state.setText("等待发货");
                break;
            case 2:
                //"待收货";
                iv_order_state.setImageResource(R.mipmap.icon_order_fahuo);
                tv_bottom_monery.setVisibility(View.VISIBLE);
                tv_right_1.setVisibility(View.VISIBLE);
                tv_right_2.setVisibility(View.GONE);
                tv_right_3.setVisibility(View.GONE);
                tv_right_4.setVisibility(View.GONE);
                tv_right_1.setText("确认收货");

                tv_order_state.setText("等待收货");

                ll_Logistics_info.setVisibility(View.VISIBLE);
                tv_logistics_step.setText("");
                break;
            case 3:
                //待评价
                iv_order_state.setImageResource(R.mipmap.icon_order_fahuo);
                tv_bottom_monery.setVisibility(View.VISIBLE);
                tv_right_1.setVisibility(View.VISIBLE);
                tv_right_2.setVisibility(View.GONE);
                tv_right_3.setVisibility(View.GONE);
                tv_right_4.setVisibility(View.GONE);
                tv_right_1.setText("去评价");

                tv_order_state.setText("待评价");

                break;
            case 4:
                // "已完成";
                iv_order_state.setImageResource(R.mipmap.icon_order_finish);

                tv_bottom_monery.setVisibility(View.GONE);
                tv_right_1.setVisibility(View.VISIBLE);
                tv_right_2.setVisibility(View.VISIBLE);
                tv_right_3.setVisibility(View.VISIBLE);
                tv_right_4.setVisibility(View.VISIBLE);

                tv_right_4.setText("删除订单");
                tv_right_3.setText("申请售后");
                tv_right_2.setText("评价商品");
                tv_right_1.setText("再次购买");

                tv_order_state.setText("交易完成");
                tv_order_describe.setVisibility(View.VISIBLE);

                break;

            case 5:
                // "已取消";
                iv_order_state.setImageResource(R.mipmap.icon_order_colse);
                tv_bottom_monery.setVisibility(View.GONE);
                tv_right_1.setVisibility(View.VISIBLE);
                tv_right_2.setVisibility(View.VISIBLE);
                tv_right_3.setVisibility(View.GONE);
                tv_right_4.setVisibility(View.GONE);

                tv_right_1.setText("立即付款");
                tv_right_2.setText("删除订单");
                ll_invoice_type.setVisibility(View.GONE);
                ll_invoice_rise.setVisibility(View.GONE);
                ll_invoice_content.setVisibility(View.GONE);

                tv_order_state.setText("交易关闭");
                break;
        }


    }

    @OnClick({R.id.act_order_details_ll_Logistics_info,
            R.id.item_order_tv_right_1, R.id.item_order_tv_right_2
            , R.id.item_order_tv_right_3, R.id.item_order_tv_right_4
    })
    void click(View view) {
        switch (view.getId()) {
            case R.id.act_order_details_ll_Logistics_info:
                Bundle bundle = new Bundle();
                bundle.putString("OrderId", mOrderId + "");
                bundle.putSerializable("bean", mOrderDetailsResponse);
                ActivityUtils.startActivity(this, OrderLogisticsActivity.class, bundle);
                break;
            case R.id.item_order_tv_right_1:
                if (mOrderId == 0) {
                    return;
                }
                if (mOrderType == 0 || mOrderType == 4) {
                    PayUtils.pay(mOrderId + "", this);
                }
            case R.id.item_order_tv_right_2:
            case R.id.item_order_tv_right_3:
            case R.id.item_order_tv_right_4:

                break;
        }
    }

    @Override
    protected OrderDetailsPresenter getPresenter() {
        return new OrderDetailsPresenter(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPaySuccessEvent(PayFinishEvent event) {
        if (event.errCode == 0) {
            setResult(RESULT_OK);
            finish();
        } else {
            ToastUtils.showToast("支付失败");
        }
    }

    @Override
    public void getOrderDetailsFinish(OrderDetailsResponse response) {
        try {
            mOrderDetailsResponse = response;
            //商品信息
            BaseApplication.loadImageView(iv_goods_pic, response.getImage());
            tv_goods_name.setText(response.getName());
            tv_goods_type.setText("");
            tv_goods_monery.setText("￥" + response.getPrice());
            tv_goods_num.setText("x" + response.getNum());


            //订单信息
            tv_total_money.setText(String.valueOf("￥" + response.getShouldPay()));
            tv_freight.setText(String.valueOf("￥" + response.getCourier()));

            tv_invoice_type.setText(response.getInvoiceType() == 0 ? "电子发票" : "纸质发票");
            tv_invoice_name.setText(response.getInvoiceTitle());
            tv_invoice_content.setText(response.getInvoiceContent());

            tv_order_no.setText(response.getOrderNum());
            if (!StringUtils.isEmpty(response.getCreateDate())) {
                tv_submit_time.setText(response.getCreateDate().replace("T", " "));
            }

            tv_pay_type.setText(response.getPayType() == 0 ? "支付宝" : "微信");
            if (!StringUtils.isEmpty(response.getPayDate())) {
                tv_pay_time.setText(response.getPayDate().replace("T", " "));
            } else {
                ll_pay_time.setVisibility(View.GONE);
            }

            tv_bottom_monery.setText("支付金额：￥" + response.getActualPay());
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    /**
     * 订单地址
     *
     * @param response
     */
    @Override
    public void getUserOrderAddressFinish(OrderAddressResponse response) {
        try {
            if (response.getIsDefault() == 1) {
                iv_pic.setImageResource(R.mipmap.icon_address_default);
                tv_default.setVisibility(View.VISIBLE);
            } else {
                iv_pic.setImageResource(R.mipmap.icon_address_default_not);
                tv_default.setVisibility(View.GONE);
            }
            tv_name.setText(response.getName());
            tv_mobile.setText(response.getPhone());
            StringBuffer sb = new StringBuffer();
            sb.append(response.getProvince())
                    .append(response.getCity())
                    .append(response.getArea())
                    .append(response.getAddress());

            tv_address.setText(sb.toString());
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    //订单 物流简单信息
    @Override
    public void getLatestResultInfoFinish(LatestResultResponse response) {
        try {
            LogUtils.w("---- getLatestResultInfoFinish");
            tv_logistics_step.setText(response.getContext());
            tv_logistics_time.setText(response.getTime());

        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
    }

    @Override
    public void addUserOrderGoodsEvaluateFinish() {

    }


}
