package com.baibeiyun.bbyiot.module.testing.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.testing.adapter.SeclectGroupAdapter;
import com.baibeiyun.bbyiot.module.testing.contract.SelectGroupContract;
import com.baibeiyun.bbyiot.module.testing.presenter.SelectGroupPresenter;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SelectGroupActivity extends BaseActivity<SelectGroupPresenter> implements SelectGroupContract.View {

    @BindView(R.id.act_select_group_gridview)
    GridView gridView;

    private SeclectGroupAdapter seclectGroupAdapter;

    List<HomeGroupsResponse> dataList =new ArrayList<>();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_select_group;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }


    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("选择分组");

        mToolbar.inflateMenu(R.menu.addtion);
        mToolbar.getMenu().getItem(0).setTitle("确定");

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (mToolbar.getMenu().getItem(0) == item) {

                    int position = seclectGroupAdapter.getPosition();
                    if (position < 0) {
                        ToastUtils.showToast("你没有选择分组");
                    } else {
                        HomeGroupsResponse homeGroupsResponse = dataList.get(position);
                        EventBus.getDefault().post(homeGroupsResponse);
                        finish();
                    }
                    return true;
                }
                return false;
            }
        });


        seclectGroupAdapter = new SeclectGroupAdapter(this);
        gridView.setAdapter(seclectGroupAdapter);

//        seclectGroupAdapter.update(mPresenter.getData());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                seclectGroupAdapter.select(i);
            }
        });


        mPresenter.getHomeGroups(false);
    }

    @Override
    protected SelectGroupPresenter getPresenter() {
        return new SelectGroupPresenter(this);
    }

    @Override
    public void getHomeGroupsFinish(List<HomeGroupsResponse> list,boolean isRefresh) {
        dataList.clear();

        HomeGroupsResponse  homeGroupsResponse = new HomeGroupsResponse();
        homeGroupsResponse.setName("全部");
        dataList.add(homeGroupsResponse);

        if(list!=null) {
            dataList.addAll(list);
        }
        seclectGroupAdapter.updata(dataList);
    }
}
