package com.baibeiyun.bbyiot.module.home.ui.devicenum;

import android.os.Bundle;
import android.view.View;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.DeviceNumResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.home.adapter.DeviceNumAdapter;
import com.baibeiyun.bbyiot.module.home.contract.devicenum.DeviceNumContract;
import com.baibeiyun.bbyiot.module.home.presenter.devicenum.DeviceNumPresenter;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.pullableview.PullToRefreshLayout;
import com.baibeiyun.bbyiot.view.pullableview.PullableGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 数值设备
 */
public class DeviceNumActivity extends BaseActivity<DeviceNumPresenter> implements DeviceNumContract.View {



    @BindView(R.id.act_device_switch_PullToRefreshLayout)
    PullToRefreshLayout pullToRefreshLayout;

    @BindView(R.id.act_device_switch_gridview)
    PullableGridView gridView;

    private int pageNum = 1;
    private int pageSize = 10;

    private int pageCount = 0;



    private List<DeviceNumResponse> dataList = new ArrayList<>();

    private DeviceNumAdapter deviceNumAdapter;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_device_switch;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("数值设备");

        View view_header_device_state = View.inflate(this, R.layout.view_header_device_state, null);
        gridView.addHeaderView(view_header_device_state);

        //pullToRefreshLayout.setCanPullUp(true);
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pageNum = 1;
                mPresenter.getDeviceNumList(pageNum, pageSize, true, false);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                LogUtils.w("pageCount == " + pageCount + " , pageSize == " + pageSize);
//                if (pageCount < pageSize) {
//                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.NO_LOAD_MORE);
//                } else {
//                    pageNum++;
//                    mPresenter.getDeviceNumList(pageNum, pageSize, false, true);
//                }

                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.NO_LOAD_MORE);
            }
        });

        deviceNumAdapter = new DeviceNumAdapter(this);
        gridView.setAdapter(deviceNumAdapter);
        mPresenter.getDeviceNumList(pageNum, pageSize, false, false);


    }

    @Override
    protected DeviceNumPresenter getPresenter() {
        return new DeviceNumPresenter(this);
    }

    @Override
    public void getDeviceNumListFinish(List<DeviceNumResponse> list, boolean isRefresh, boolean isLoadmore) {
        try {

            if (list != null) {
                pageCount = list.size();
                LogUtils.w(" list.size==  " + list.size() + " ,pageCount == " + pageCount);
                if (isLoadmore) {
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                } else {
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    dataList.clear();
                }

                dataList.addAll(list);
                LogUtils.w(" dataList.size==  " + dataList.size());
                deviceNumAdapter.updata(dataList);
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
