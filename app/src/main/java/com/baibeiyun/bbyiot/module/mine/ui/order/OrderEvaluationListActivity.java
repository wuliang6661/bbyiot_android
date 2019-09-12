package com.baibeiyun.bbyiot.module.mine.ui.order;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.EvaluateListResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.mine.adapter.OrderEvaluationListAdapter;
import com.baibeiyun.bbyiot.module.mine.contract.OrderEvaluationContract;
import com.baibeiyun.bbyiot.module.mine.presenter.OrderEvaluationPresenter;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.ListUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.pullableview.PullToRefreshLayout;
import com.baibeiyun.bbyiot.view.pullableview.PullableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderEvaluationListActivity extends BaseActivity<OrderEvaluationPresenter> implements OrderEvaluationContract.View {

    @BindView(R.id.act_order_evaluation_refreshlayout)
    PullToRefreshLayout refreshLayout;

    @BindView(R.id.act_order_evaluation_listview)
    PullableListView listView;

    private OrderEvaluationListAdapter mOrderEvaluationListAdapter;

    List<EvaluateListResponse> mDataList = new ArrayList();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_order_evaluation_list;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("我的评价");

        mOrderEvaluationListAdapter = new OrderEvaluationListAdapter(this, R.layout.item_order_evaluation);
        listView.setAdapter(mOrderEvaluationListAdapter);

        refreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                mPresenter.getEvaluateList();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", mDataList.get(i));
                ActivityUtils.startActivity(OrderEvaluationListActivity.this, OrderEvaluationDetailsActivity.class,bundle);
            }
        });

        mPresenter.getEvaluateList();
    }

    @Override
    protected OrderEvaluationPresenter getPresenter() {
        return new OrderEvaluationPresenter(this);
    }

    @Override
    public void getEvaluateListFinish(List<EvaluateListResponse> list) {
        try {
            refreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            if (!ListUtils.isEmpty(list)) {
                mDataList.addAll(list);
            }
            mOrderEvaluationListAdapter.update(mDataList);
        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
    }
}
