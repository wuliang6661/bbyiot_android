package com.baibeiyun.bbyiot.module.home.ui.devicepic;

import android.os.Bundle;
import android.view.View;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.DevicePicResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.home.adapter.DevicePictureAdapter;
import com.baibeiyun.bbyiot.module.home.contract.devicepic.DevicePictureContract;
import com.baibeiyun.bbyiot.module.home.presenter.devicepic.DevicePicturePresenter;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.pullableview.PullToRefreshLayout;
import com.baibeiyun.bbyiot.view.pullableview.PullableGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 图片设备
 */
public class DevicePictureActivity extends BaseActivity<DevicePicturePresenter> implements DevicePictureContract.View {

    @BindView(R.id.act_device_switch_PullToRefreshLayout)
    PullToRefreshLayout pullToRefreshLayout;

    @BindView(R.id.act_device_switch_gridview)
    PullableGridView gridView;

    private int pageNum = 1;
    private int pageSize = 10;
    private int pageCount = 0;

    private List<DevicePicResponse> dataList = new ArrayList<>();
    private DevicePictureAdapter devicePictureAdapter;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_device_switch;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("图片设备");

        View view_header_device_state = View.inflate(this, R.layout.view_header_device_state, null);
        gridView.addHeaderView(view_header_device_state);

        pullToRefreshLayout.setCanPullUp(true);
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pageNum = 1;
                mPresenter.getDevicePictureList(pageNum, pageSize, true, false);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                LogUtils.w("pageCount == " + pageCount + " , pageSize == " + pageSize);
                if (pageCount < pageSize) {
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.NO_LOAD_MORE);
                } else {
                    pageNum++;
                    mPresenter.getDevicePictureList(pageNum, pageSize, false, true);
                }
            }
        });

        devicePictureAdapter = new DevicePictureAdapter(this);
        gridView.setAdapter(devicePictureAdapter);

        mPresenter.getDevicePictureList(pageNum, pageSize, false, false);
    }

    @Override
    protected DevicePicturePresenter getPresenter() {
        return new DevicePicturePresenter(this);
    }

    @Override
    public void getDevicePictureListFinish(List<DevicePicResponse> list, boolean isRefresh, boolean isLoadmore) {
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
                devicePictureAdapter.updata(dataList);
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
