package com.baibeiyun.bbyiot.module.mine.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.model.Response.OrderResponse;
import com.baibeiyun.bbyiot.model.event.PayFinishEvent;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.mine.adapter.OrderAdapter;
import com.baibeiyun.bbyiot.module.mine.contract.OrderContract;
import com.baibeiyun.bbyiot.module.mine.presenter.OrderPresenter;
import com.baibeiyun.bbyiot.utils.ListUtils;
import com.baibeiyun.bbyiot.utils.PayUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.pullableview.PullableListView;
import com.baibeiyun.bbyiot.view.pullableview.special.MyPullToRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

public class OrderFragement extends BaseFragment<OrderPresenter> implements OrderContract.View {

    @BindView(R.id.frg_order_refreshlayout)
    MyPullToRefreshLayout refreshLayout;
    @BindView(R.id.frg_order_listview)
    PullableListView listview;

    private int mOrderType = 0;
    private int mOrderStatus = -1;


    private int pageNum = 1;
    private final int pageSize = 10;


    List<OrderResponse> mDataList = new ArrayList<>();
    private OrderAdapter mOrderAdapter;
    private Bundle arguments;

    public OrderFragement() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_order;
    }

    @Override
    protected void initViewsAndEvents() {
        arguments = getArguments();
        if (arguments != null) {
            mOrderType = arguments.getInt(IConstant.ORDER_TYPE, 0);
            mOrderStatus = arguments.getInt(IConstant.ORDER_STATUS, -1);
        }

        mOrderAdapter = new OrderAdapter(getActivity(), R.layout.item_order);
        listview.setAdapter(mOrderAdapter);


        refreshLayout.setCanPullUp(true);
        refreshLayout.setOnRefreshListener(new MyPullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(MyPullToRefreshLayout pullToRefreshLayout) {
                pageNum = 1;
                getData(true, false);
            }

            @Override
            public void onLoadMore(MyPullToRefreshLayout pullToRefreshLayout) {
                if (pageNum * pageSize > mDataList.size()) {
                    refreshLayout.loadmoreFinish(MyPullToRefreshLayout.NO_LOAD_MORE);

                } else {
                    pageNum++;
                    getData(false, true);
                }
            }
        });
        mOrderAdapter.setOnClickLisenter(new OrderAdapter.OnClickLisenter() {
            @Override
            public void pay(OrderResponse response) {
                //支付
                PayUtils.pay(response.getId() + "", getActivity());
//                ToastUtils.showToast("支付失败");
            }

            @Override
            public void tingxing(OrderResponse response) {
                //ToastUtils.showToast("提醒发货成功");
                mPresenter.tixing(response.getId() + "");
            }

            @Override
            public void shouhuo(OrderResponse response) {
                mPresenter.confirmOrder(response.getId() + "");
            }

            @Override
            public void itemClick(View view, OrderResponse response) {
                Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(OrderDetailsActivity.KEY_ORDER_TYPE, response.getStatus());
                bundle.putLong(OrderDetailsActivity.KEY_ORDER_ID, response.getId());
                intent.putExtras(bundle);
                startActivityForResult(intent, 1000);
            }
        });

        getData(false, false);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1000) {
            pageNum = 1;
            getData(true, false);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPaySuccessEvent(PayFinishEvent event) {
        if (event.errCode == 0) {
            pageNum = 1;
            getData(true, false);
        } else {
            ToastUtils.showToast("支付失败");
        }
    }

    void getData(boolean isRefresh, boolean isLoadmore) {
        mPresenter.getUserOrderListByType(pageNum, pageSize, mOrderStatus, isRefresh, isLoadmore);
    }

    @Override
    protected OrderPresenter getPresenter() {
        return new OrderPresenter(getActivity());
    }

    public static OrderFragement newInstance(int orderType, int orderStatus) {
        OrderFragement fragment = new OrderFragement();
        Bundle argument = new Bundle();
        argument.putInt(IConstant.ORDER_TYPE, orderType);
        argument.putInt(IConstant.ORDER_STATUS, orderStatus);
        fragment.setArguments(argument);
        return fragment;
    }

    @Override
    public void getUserOrderListFinish(List<OrderResponse> list, boolean isRefresh, boolean isLoadmore) {
        try {
            if (isRefresh || pageNum == 1) {
                mDataList.clear();
                refreshLayout.refreshFinish(MyPullToRefreshLayout.SUCCEED);
            } else if (isLoadmore) {
                refreshLayout.loadmoreFinish(MyPullToRefreshLayout.SUCCEED);
            }
            if (!ListUtils.isEmpty(list)) {
                mDataList.addAll(list);
            }

            mOrderAdapter.update(mDataList);

        } catch (Exception e) {

        }
    }

    @Override
    public void confirmOrderFinish() {
        ToastUtils.showToast("确认收货成功");
        pageNum = 1;
        getData(false, false);
    }

    @Override
    public void tixingFinish() {
        ToastUtils.showToast("提醒发货成功");
        pageNum = 1;
        getData(false, false);
    }
}
