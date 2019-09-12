package com.baibeiyun.bbyiot.module.mine.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.mine.adapter.GroupAdapter;
import com.baibeiyun.bbyiot.module.mine.contract.GroupContract;
import com.baibeiyun.bbyiot.module.mine.presenter.GroupPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

public class GroupActivity extends BaseActivity<GroupPresenter> implements GroupContract.View {

    @BindView(R.id.act_group_listview)
    ListView listview;
    private GroupAdapter mGroupAdapter;
    private List<HomeGroupsResponse> dataList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_group;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("分组管理");


        mGroupAdapter = new GroupAdapter(this, R.layout.item_group);
        listview.setAdapter(mGroupAdapter);

        mPresenter.getHomeGroups(false);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HomeGroupsResponse homeGroupsResponse = dataList.get(i);
                EventBus.getDefault().post(homeGroupsResponse);
                finish();
            }
        });

    }


    @Override
    protected GroupPresenter getPresenter() {
        return new GroupPresenter(this);
    }

    @Override
    public void getHomeGroupsFinish(List<HomeGroupsResponse> list, boolean isRefresh) {
        dataList = list;
        mGroupAdapter.update(list);
    }
}
