package com.baibeiyun.bbyiot.module.home.ui.deviceswitch;

import android.os.Bundle;
import android.view.View;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.DeviceSwtichResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.home.adapter.DeviceSwitchAdapter;
import com.baibeiyun.bbyiot.module.home.contract.deviceswitch.DeviceSwitchContract;
import com.baibeiyun.bbyiot.module.home.presenter.deviceswitch.DeviceSwitchPresenter;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.pullableview.PullToRefreshLayout;
import com.baibeiyun.bbyiot.view.pullableview.PullableGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 开关设备
 */
public class DeviceSwitchActivity extends BaseActivity<DeviceSwitchPresenter> implements DeviceSwitchContract.View {

    @BindView(R.id.act_device_switch_PullToRefreshLayout)
    PullToRefreshLayout pullToRefreshLayout;

    @BindView(R.id.act_device_switch_gridview)
    PullableGridView gridView;


    List<DeviceSwtichResponse> dataList = new ArrayList<>();
    private int pageNum = 1;
    private int pageSize = 10;
    private int pageCount = 0;
    private DeviceSwitchAdapter deviceSwitchAdapter;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_device_switch;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("开关设备");
        View view_header_device_state = View.inflate(this, R.layout.view_header_device_state, null);
        gridView.addHeaderView(view_header_device_state);

        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pageNum = 1;
                mPresenter.getDeviceSwitchList(pageNum, pageSize, true, false);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                if (pageCount < pageSize) {
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.NO_LOAD_MORE);
                } else {
                    pageNum++;
                    mPresenter.getDeviceSwitchList(pageNum, pageSize, false, true);
                }
            }
        });

        deviceSwitchAdapter = new DeviceSwitchAdapter(this);
        gridView.setAdapter(deviceSwitchAdapter);

        deviceSwitchAdapter.setOnClickLickLisenter(new DeviceSwitchAdapter.OnClickLickLisenter() {
            @Override
            public void openOrCloseSwtich(String id, String openValue, String swtichStatus,int attributeId) {
                LogUtils.w("openOrCloseSwtich   openValue == " + openValue);
                mPresenter.openOrCloseDeviceSwtich(id, openValue, swtichStatus, attributeId);
            }
        });

        mPresenter.getDeviceSwitchList(pageNum, pageSize, false, false);
    }

    @Override
    protected DeviceSwitchPresenter getPresenter() {
        return new DeviceSwitchPresenter(this);
    }


    @Override
    public void getDeviceSwitchListFinish(List<DeviceSwtichResponse> list, boolean isRefresh, boolean isLoadmore) {
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
                deviceSwitchAdapter.updata(dataList);
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

    @Override
    public void openOrCloseDeviceSwtichFinish() {
        pageNum = 1;
        mPresenter.getDeviceSwitchList(pageNum, pageSize, false, false);
    }
}
