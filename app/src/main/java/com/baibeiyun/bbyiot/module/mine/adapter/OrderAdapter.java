package com.baibeiyun.bbyiot.module.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.OrderResponse;
import com.baibeiyun.bbyiot.module.base.addpter.MyBaseAdapter;
import com.baibeiyun.bbyiot.module.base.addpter.ViewHolder;
import com.baibeiyun.bbyiot.module.mine.ui.order.EvaluateActivity;
import com.baibeiyun.bbyiot.module.mine.ui.order.OrderActivity;
import com.baibeiyun.bbyiot.module.mine.ui.order.OrderDetailsActivity;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

public class OrderAdapter extends MyBaseAdapter<OrderResponse> {

    private TextView tv_order_no, tv_order_state, tv_goods_name, tv_goods_type, tv_goods_num, tv_goods_sum_money;
    private TextView tv_click;
    private ImageView iv_pic;

    public OrderAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, final OrderResponse orderResponse, int position) {

        try {
            iv_pic = holder.getView(R.id.item_order_iv_pic);
            tv_order_no = holder.getView(R.id.item_order_tv_order_no);
            tv_order_state = holder.getView(R.id.item_order_tv_order_state);
            tv_goods_name = holder.getView(R.id.item_order_tv_goods_name);
            tv_goods_type = holder.getView(R.id.item_order_tv_goods_type);
            tv_goods_num = holder.getView(R.id.item_order_tv_goods_num);
            tv_goods_sum_money = holder.getView(R.id.item_order_tv_goods_sum_money);
            tv_click = holder.getView(R.id.item_order_tv_click);


            if (orderResponse != null) {
                final int orderStatus = orderResponse.getStatus();

                tv_order_no.setText("订单编号：" + orderResponse.getOrderNum());
                tv_order_state.setText(getStatusString(orderStatus));

                tv_goods_name.setText(orderResponse.getName());
                //tv_goods_type.setText();
                tv_goods_num.setText("x" + orderResponse.getNum());
                tv_goods_sum_money.setText("¥ " + orderResponse.getShouldPay());

                if (orderResponse.getImage() != null) {
                    String[] split = orderResponse.getImage().split(",");
                    BaseApplication.loadImageView(iv_pic, split[0]);
                }

                //（-1全部，0待付款，1待发货，2待收货，3待评价，4已完成，5已取消
                switch (orderStatus) {
                    case 0:
                        // "待付款";
                        tv_click.setText("去支付");
                        tv_click.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        // "待发货";
                        tv_click.setText("提醒发货");
                        tv_click.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        //"待收货";
                        tv_click.setText("确认收货");
                        tv_click.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        // 3待评价
                        tv_click.setText("去评价");
                        tv_click.setVisibility(View.VISIBLE);
                        break;

                    case 4:
                        // "已完成";
                        tv_click.setVisibility(View.GONE);
                        break;
                    case 5:
                        // "已取消";
                        tv_click.setVisibility(View.GONE);
                        break;
                }

                tv_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (orderStatus) {
                            case 0:
                                // "待付款";
                                mOnClickLisenter.pay(orderResponse);
                                break;
                            case 1:
                                // "待发货";
                                mOnClickLisenter.tingxing(orderResponse);
                                break;
                            case 2:
                                //"待收货";
                                mOnClickLisenter.shouhuo(orderResponse);
                                break;
                            case 3:
                                // 3待评价
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("bean", orderResponse);
                                ActivityUtils.startActivity((Activity) mContext, EvaluateActivity.class, bundle);
                                break;

                            case 4:
                                // "已完成";
                                tv_click.setVisibility(View.GONE);
                                break;
                            case 5:
                                // "已取消";
                                tv_click.setVisibility(View.GONE);
                                break;
                        }
                    }
                });

                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnClickLisenter.itemClick(view, orderResponse);
                    }
                });

            }
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }

    }

    //（-1全部，0待付款，1待发货，2待收货，3待评价，4已完成，5已取消
    private String getStatusString(int type) {
        switch (type) {
            case 0:
                return "待付款";
            case 1:
                return "待发货";
            case 2:
                return "待收货";
            case 3:
                return "待评价";
            case 4:
                return "已完成";
            case 5:
                return "已取消";
        }
        return "";
    }


    OnClickLisenter mOnClickLisenter;

    public interface OnClickLisenter {
        void pay(OrderResponse response);

        void tingxing(OrderResponse response);

        void shouhuo(OrderResponse response);

        void itemClick(View view, OrderResponse response);
    }

    public void setOnClickLisenter(OnClickLisenter lisenter) {
        this.mOnClickLisenter = lisenter;
    }
}
