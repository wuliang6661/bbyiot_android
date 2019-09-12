package com.baibeiyun.bbyiot.module.mine.ui.order;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.LatestResultDetailsResponse;
import com.baibeiyun.bbyiot.model.Response.OrderDetailsResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.mine.adapter.OrderLogisticsAdapter;
import com.baibeiyun.bbyiot.module.mine.contract.OrderLogisticsContract;
import com.baibeiyun.bbyiot.module.mine.presenter.OrderLogisticsPresenter;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

import butterknife.BindView;

//物流详情
public class OrderLogisticsActivity extends BaseActivity<OrderLogisticsPresenter> implements OrderLogisticsContract.View {

    @BindView(R.id.act_order_logistics_listview)
    ListView listView;

    private String mOrderId;
    private OrderLogisticsAdapter mOrderLogisticsAdapter;
    private OrderDetailsResponse mOrderDetailsResponse;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_order_logistics;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mOrderId = extras.getString("OrderId");
        mOrderDetailsResponse = (OrderDetailsResponse) extras.getSerializable("bean");
    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("物流详情");
        mPresenter.getLatestResultDetails(mOrderId);

        View view_header = View.inflate(this, R.layout.item_order_logistics_top, null);
        ImageView iv_pic = view_header.findViewById(R.id.item_order_logistics_top_iv_pic);
        TextView tv_num = view_header.findViewById(R.id.item_order_logistics_top_tv_num);
        TextView tv_order_no = view_header.findViewById(R.id.item_order_logistics_top_tv_order_no);


        if (mOrderDetailsResponse != null) {
            BaseApplication.loadImageView(iv_pic, mOrderDetailsResponse.getImage());
            tv_num.setText("共" + mOrderDetailsResponse.getNum() + "件商品");

            tv_order_no.setText(mOrderDetailsResponse.getOrderNum());
        }

        listView.addHeaderView(view_header);
        mOrderLogisticsAdapter = new OrderLogisticsAdapter(this, R.layout.item_order_logistics);
        listView.setAdapter(mOrderLogisticsAdapter);
    }

    @Override
    protected OrderLogisticsPresenter getPresenter() {
        return new OrderLogisticsPresenter(this);
    }


    @Override
    public void getLatestResultDetailsFinish(LatestResultDetailsResponse response) {
        try {
            List<LatestResultDetailsResponse.DataBean> dataBeanList = response.getData();

            mOrderLogisticsAdapter.update(dataBeanList);
        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
    }
}
