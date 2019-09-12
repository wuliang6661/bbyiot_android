package com.baibeiyun.bbyiot.module.home.ui.devicegateway;

import android.os.Bundle;
import android.view.View;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.DeviceGatewayResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.home.adapter.DeviceGatewayAdapter;
import com.baibeiyun.bbyiot.module.home.contract.devicegateway.DeviceGatewayContract;
import com.baibeiyun.bbyiot.module.home.presenter.devicegateway.DeviceGatewayPresenter;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.pullableview.PullToRefreshLayout;
import com.baibeiyun.bbyiot.view.pullableview.PullableGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 网关设备
 */
public class DeviceGatewayActivity extends BaseActivity<DeviceGatewayPresenter> implements DeviceGatewayContract.View {

    @BindView(R.id.act_device_switch_PullToRefreshLayout)
    PullToRefreshLayout pullToRefreshLayout;

    @BindView(R.id.act_device_switch_gridview)
    PullableGridView gridView;

    private int pageNum = 1;
    private final int pageSize = 10;
    private DeviceGatewayAdapter deviceGatewayAdapter;

    private int pageCount = 0;

    private List<DeviceGatewayResponse> dataList = new ArrayList<>();


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_device_switch;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("网关设备");

        View view_header_device_state = View.inflate(this, R.layout.view_header_device_state, null);
        gridView.addHeaderView(view_header_device_state);

        pullToRefreshLayout.setCanPullUp(true);
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pageNum = 1;
                mPresenter.getDeviceGatewayList(pageNum, pageSize, true, false);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                LogUtils.w("pageCount == " + pageCount + " , pageSize == " + pageSize);
                if (pageCount < pageSize) {
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.NO_LOAD_MORE);
                } else {
                    pageNum++;
                    mPresenter.getDeviceGatewayList(pageNum, pageSize, false, true);
                }
            }
        });

        deviceGatewayAdapter = new DeviceGatewayAdapter(this);
        gridView.setAdapter(deviceGatewayAdapter);

        mPresenter.getDeviceGatewayList(pageNum, pageSize, false, false);
    }

    @Override
    protected DeviceGatewayPresenter getPresenter() {
        return new DeviceGatewayPresenter(this);
    }

    @Override
    public void getDeviceGatewayListFinish(List<DeviceGatewayResponse> list, boolean isRefresh, boolean isLoadmore) {
        try {

            if (list != null) {
                pageCount = list.size();
                LogUtils.w(" list.size==  " + list.size()+" ,pageCount == " +pageCount);
                if (isLoadmore) {
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                } else {
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    dataList.clear();
                }

                dataList.addAll(list);
                LogUtils.w(" dataList.size==  " + dataList.size());
                deviceGatewayAdapter.updata(dataList);
            } else {
                if (isRefresh) {
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
                } else {
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
                }
            }
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }
}
