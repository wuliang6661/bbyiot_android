package com.baibeiyun.bbyiot.module.findshop.ui;

import android.os.Bundle;
import android.widget.ListView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.GoodsReviewResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.findshop.adapter.ShopEvaluationAdapter;
import com.baibeiyun.bbyiot.module.findshop.contract.ShopEvaluationContract;
import com.baibeiyun.bbyiot.module.findshop.presenter.ShopEvaluationPresenter;
import com.baibeiyun.bbyiot.view.pullableview.special.MyPullToRefreshLayout;

import java.util.List;

import butterknife.BindView;

public class ShopEvaluationActivity extends BaseActivity<ShopEvaluationPresenter> implements ShopEvaluationContract.View {

    @BindView(R.id.act_shop_hardware_listview)
    ListView listView;

    @BindView(R.id.act_shop_hardware_pulltorefreshlayout)
    MyPullToRefreshLayout pullToRefreshLayout;

    private int pageNum = 1;
    private int pageSize = 10;
    private ShopEvaluationAdapter shopHardwareAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_shop_evaluation;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("测评列表");
        shopHardwareAdapter = new ShopEvaluationAdapter(this);

        listView.setAdapter(shopHardwareAdapter);

        pullToRefreshLayout.setCanPullUp(true);
        pullToRefreshLayout.setOnRefreshListener(new MyPullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(MyPullToRefreshLayout pullToRefreshLayout) {
                pageNum = 1;
                mPresenter.getGoodsReviewList(pageNum, pageSize, true, false);
            }

            @Override
            public void onLoadMore(MyPullToRefreshLayout pullToRefreshLayout) {
                pageNum++;
                mPresenter.getGoodsReviewList(pageNum, pageSize, false, true);
            }
        });
        mPresenter.getGoodsReviewList(pageNum, pageSize, false, false);
    }

    @Override
    protected ShopEvaluationPresenter getPresenter() {
        return new ShopEvaluationPresenter(this);
    }

    @Override
    public void getGoodsReviewList(List<GoodsReviewResponse> response, boolean isRefresh, boolean isLoadmore) {
        if (response != null && response.size() > 0) {
            if (isRefresh) {
                pullToRefreshLayout.refreshFinish(MyPullToRefreshLayout.SUCCEED);
                shopHardwareAdapter.getData().clear();
            } else if (isLoadmore) {
                pullToRefreshLayout.loadmoreFinish(MyPullToRefreshLayout.SUCCEED);
            }
            shopHardwareAdapter.updata(response);
        } else {
            if (isRefresh) {
                pullToRefreshLayout.refreshFinish(MyPullToRefreshLayout.FAIL);
            } else if (isLoadmore) {
                pullToRefreshLayout.loadmoreFinish(MyPullToRefreshLayout.FAIL);
            }
        }
    }
}
