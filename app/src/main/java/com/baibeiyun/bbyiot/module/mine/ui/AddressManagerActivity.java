package com.baibeiyun.bbyiot.module.mine.ui;

import android.os.Bundle;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.AddressResponse;
import com.baibeiyun.bbyiot.model.event.CommonEvent;
import com.baibeiyun.bbyiot.model.event.EventConstant;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.mine.adapter.AddressManagerAdapter;
import com.baibeiyun.bbyiot.module.mine.contract.AddressManagerContract;
import com.baibeiyun.bbyiot.module.mine.presenter.AddressManagerPresenter;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.view.dialog.CommonDialog;
import com.baibeiyun.bbyiot.view.pullableview.PullToRefreshLayout;
import com.baibeiyun.bbyiot.view.pullableview.PullableListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressManagerActivity extends BaseActivity<AddressManagerPresenter> implements AddressManagerContract.View {

    @BindView(R.id.act_address_manager_refreshlayout)
    PullToRefreshLayout refreshLayout;

    @BindView(R.id.act_address_manager_listview)
    PullableListView listview;
    private AddressManagerAdapter mAddressManagerAdapter;

    List<AddressResponse> mDataList = new ArrayList<>();
    private CommonDialog deleteAddressDialog;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_address_manager;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setActionBarTitle("地址管理");

        refreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                mPresenter.getUserAddressList();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });

        mAddressManagerAdapter = new AddressManagerAdapter(this, R.layout.item_address);
        listview.setAdapter(mAddressManagerAdapter);

        mAddressManagerAdapter.setOnClickLinsenter(new AddressManagerAdapter.OnClickLinsenter() {
            @Override
            public void setDefault(AddressResponse response) {
                mPresenter.changeToDefault(response.getId());
            }

            @Override
            public void delete(AddressResponse response) {
               deleteAddressDialog(response);
            }
        });

        getData();
    }

    private void deleteAddressDialog(final AddressResponse response) {
        if(deleteAddressDialog==null) {
            deleteAddressDialog = new CommonDialog(this);
        }
        deleteAddressDialog.show();
        deleteAddressDialog.setTxet("删除地址", "确定删除当前收货地址吗？");
        deleteAddressDialog.setOnDialogLinsenter(new CommonDialog.OnDialogLinsenter() {
            @Override
            public void confirm() {
                mPresenter.deleteUserAddress(response.getId());
            }
        });

    }

    void getData() {
        showLoading(null);
        mPresenter.getUserAddressList();
    }

    @Override
    protected AddressManagerPresenter getPresenter() {
        return new AddressManagerPresenter(this);
    }

    @OnClick(R.id.act_address_manager_ll_add_address)
    void addAddress() {
        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        ActivityUtils.startActivity(AddressEditActivity.class, bundle);
    }

    @Subscribe
    public void changeAddress(CommonEvent event) {
        if (event != null && EventConstant.CHANGE_ADDRESS.equals(event.getTag())) {
            getData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getUserAddressListFinsh(List<AddressResponse> list) {
        mDataList = list;
        mAddressManagerAdapter.update(mDataList);
        refreshLayout.refreshFinish(0);
    }

    @Override
    public void changeToDefaultSccuess() {
        getData();
    }

    @Override
    public void deleteUserAddressSccuess() {
        getData();
    }

    @Override
    public void addUserAddressSccuess() {

    }

    @Override
    public void updateUserAddressSccuess() {

    }
}
