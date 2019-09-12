package com.baibeiyun.bbyiot.module.home.ui;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.DevicesStatusRespone;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.home.adapter.DeviceStateAdapter;
import com.baibeiyun.bbyiot.module.home.contract.DeviceStateContract;
import com.baibeiyun.bbyiot.module.home.presenter.DeviceStatePresenter;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.view.DefindTextviewListener;
import com.baibeiyun.bbyiot.view.pullableview.PullToRefreshLayout;
import com.baibeiyun.bbyiot.view.pullableview.PullableListView;

import java.util.List;

import butterknife.BindView;

/**
 * 在线、离线、告警设备
 */
public class DeviceStateActivity extends BaseActivity<DeviceStatePresenter> implements DeviceStateContract.View {

    public static final String DEVICE_STATE = "device_state";

    @BindView(R.id.act_device_state_pulltorefreshlayout)
    PullToRefreshLayout pullToRefreshLayout;

    @BindView(R.id.act_device_state_listview)
    PullableListView listView;

    private int devcieType;

    int pageNum = 1;
    int pageSize = 10;
    int status = -1;
    private DeviceStateAdapter deviceStateAdapter;
    private DefindTextviewListener mListener;
    private EditText et_search;
    private String mQueryKeyword = null;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_device_state;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        devcieType = extras.getInt(DEVICE_STATE);
    }

    @Override
    protected void initViewsAndEvents() {
        //status (integer, optional): 设备状态（1在线，0离线，2告警） ,
        if (devcieType == 1) {
            setActionBarTitle("离线设备");
            status = 0;
        } else if (devcieType == 2) {
            setActionBarTitle("在线设备");
            status = 1;
        } else if (devcieType == 3) {
            setActionBarTitle("告警设备");
            status = 2;
        }

        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.refreshFinish(0);

                mPresenter.getDevicesByStatus(pageNum, pageSize, status, mQueryKeyword);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });


        View view_header_device_state = View.inflate(this, R.layout.view_header_device_state, null);
        et_search = view_header_device_state.findViewById(R.id.view_head_device_state_et_search);

        view_header_device_state.findViewById(R.id.iv_query)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mQueryKeyword = et_search.getText().toString();
                        if (!StringUtils.isEmpty(mQueryKeyword)) {
                            mPresenter.getDevicesByStatus(pageNum, pageSize, status, mQueryKeyword);
                        }

                    }
                });
        mListener = new DefindTextviewListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString().trim();
                if (StringUtils.isEmpty(text)) {
                    mQueryKeyword = null;
                    mPresenter.getDevicesByStatus(pageNum, pageSize, status, mQueryKeyword);
                }
            }
        };
        et_search.addTextChangedListener(mListener);

        listView.addHeaderView(view_header_device_state);

        deviceStateAdapter = new DeviceStateAdapter(this);
        listView.setAdapter(deviceStateAdapter);


        //status (integer, optional): 设备状态（1在线，0离线，2告警） ,
        mPresenter.getDevicesByStatus(pageNum, pageSize, status, mQueryKeyword);
    }

    @Override
    protected DeviceStatePresenter getPresenter() {
        return new DeviceStatePresenter(this);
    }

    @Override
    public void getDevicesByStatusFinish(List<DevicesStatusRespone> list) {
        deviceStateAdapter.updata(list);
    }
}
