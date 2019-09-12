package com.baibeiyun.bbyiot.module.findshop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.model.Response.GoodsShopResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.findshop.adapter.JingxuanAdapter;
import com.baibeiyun.bbyiot.module.findshop.adapter.ShopHardwareAdapter;
import com.baibeiyun.bbyiot.module.findshop.contract.ShopHardwareContract;
import com.baibeiyun.bbyiot.module.findshop.presenter.ShopHardwarePresenter;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.NOScrollGirdview;
import com.baibeiyun.bbyiot.view.pullableview.special.MyPullToRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * 硬件商城
 */
public class ShopHardwareActivity extends BaseActivity<ShopHardwarePresenter> implements ShopHardwareContract.View {

    @BindView(R.id.act_shop_hardware_listview)
    ListView listView;

    @BindView(R.id.act_shop_hardware_pulltorefreshlayout)
    MyPullToRefreshLayout pullToRefreshLayout;

    private int pageNum = 1;
    private int pageSize = 10;
    private ShopHardwareAdapter shopHardwareAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_shop_hardware;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("硬件商城");

        View view_header_shop_hardware = View.inflate(this, R.layout.view_header_shop_hardware, null);
        NOScrollGirdview girdview = view_header_shop_hardware.findViewById(R.id.view_header_shop_hardware_gridview);
        girdview.setAdapter(new JingxuanAdapter(this));
        view_header_shop_hardware.findViewById(R.id.view_head_device_state_tv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startWebActivity(ShopHardwareActivity.this, IConstant.BASE_SHOP_SEARCH_URL);
            }
        });

        listView.addHeaderView(view_header_shop_hardware);
        shopHardwareAdapter = new ShopHardwareAdapter(this);

        listView.setAdapter(shopHardwareAdapter);

        pullToRefreshLayout.setCanPullUp(true);
        pullToRefreshLayout.setOnRefreshListener(new MyPullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(MyPullToRefreshLayout pullToRefreshLayout) {
                pageNum = 1;
                mPresenter.getGoodsActiveList(pageNum, pageSize, true, false);
            }

            @Override
            public void onLoadMore(MyPullToRefreshLayout pullToRefreshLayout) {
                pageNum++;
                mPresenter.getGoodsActiveList(pageNum, pageSize, false, true);
            }
        });
        mPresenter.getGoodsActiveList(pageNum, pageSize, false, false);
    }

    @Override
    protected ShopHardwarePresenter getPresenter() {
        return new ShopHardwarePresenter(this);
    }

    @Override
    public void getGoodsActiveListFinishFail(List<GoodsShopResponse> response, boolean isRefresh, boolean isLoadmore) {
        if (isRefresh) {
            pullToRefreshLayout.refreshFinish(MyPullToRefreshLayout.FAIL);
        } else if (isLoadmore) {
            pullToRefreshLayout.loadmoreFinish(MyPullToRefreshLayout.FAIL);
        }
    }

    @Override
    public void getGoodsActiveListFinishSuccess(List<GoodsShopResponse> response, boolean isRefresh, boolean isLoadmore) {
        if (isRefresh) {
            pullToRefreshLayout.refreshFinish(MyPullToRefreshLayout.SUCCEED);
            shopHardwareAdapter.getData().clear();
        } else if (isLoadmore) {
            pullToRefreshLayout.loadmoreFinish(MyPullToRefreshLayout.SUCCEED);
        }
        if (response != null && response.size() > 0) {
            shopHardwareAdapter.updata(response);
        }
        LogUtils.w("------------   getGoodsActiveListFinish     ---");
    }
}
