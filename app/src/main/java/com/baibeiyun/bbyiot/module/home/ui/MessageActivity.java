package com.baibeiyun.bbyiot.module.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.MessageListResponse;
import com.baibeiyun.bbyiot.model.Response.OrderMessageResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.home.adapter.MessageAdapter;
import com.baibeiyun.bbyiot.module.home.contract.MessageContract;
import com.baibeiyun.bbyiot.module.home.presenter.MessagePresenter;
import com.baibeiyun.bbyiot.utils.ListUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.pullableview.PullToRefreshLayout;
import com.baibeiyun.bbyiot.view.pullableview.PullableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity<MessagePresenter> implements MessageContract.View {

    @BindView(R.id.act_message_view_line_device_msg)
    View view_line_device_msg;

    @BindView(R.id.act_message_view_line_pingtai_msg)
    View view_line_pingtai_msg;
    @BindView(R.id.act_message_view_dit_device_msg)
    View view_dit_device_msg;

    @BindView(R.id.act_message_view_dit_pingtai_msg)
    View view_dit_pingtai_msg;

    @BindView(R.id.act_message_pullablelistview)
    PullableListView listView;

    @BindView(R.id.act_message_pulltorefreshlayout)
    PullToRefreshLayout refreshLayout;

    private MessageAdapter messageAdapter;

    private List<MessageListResponse> mDeviceMessageList = new ArrayList();
    private List<OrderMessageResponse> mPlatformMessageList = new ArrayList();

    int pageNumPlatform = 1;
    int pageNumDevice = 1;

    int pageSize = 10;
    int pageType = 1;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_message;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("消息");


        refreshLayout.setCanPullUp(true);
        refreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                if (pageType == 2) {
                    pageNumDevice = 1;
                    mDeviceMessageList.clear();
                    messageAdapter.updata(mDeviceMessageList, pageType);
                    mPresenter.getDeviceMessageList(pageNumDevice, pageSize, true, false);
                } else {
                    pageNumPlatform = 1;
                    mPlatformMessageList.clear();
                    messageAdapter.updata(mPlatformMessageList, pageType);
                    mPresenter.getOrderMessageList(pageNumPlatform, pageSize, true, false);
                }
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                try {
                    if (pageType == 2) {
                        if (mPlatformMessageList.size() < pageNumPlatform * pageSize) {
                            refreshLayout.loadmoreFinish(PullToRefreshLayout.NO_LOAD_MORE);
                        } else {
                            pageNumPlatform++;
                            mPresenter.getDeviceMessageList(pageNumPlatform, pageSize, false, true);
                        }
                    } else {
                        if (mPlatformMessageList.size() < pageNumPlatform * pageSize) {
                            refreshLayout.loadmoreFinish(PullToRefreshLayout.NO_LOAD_MORE);
                        } else {
                            pageNumPlatform++;
                            mPresenter.getOrderMessageList(pageNumPlatform, pageSize, false, true);
                        }
                    }
                } catch (Exception e) {
                    LogUtils.w(e.toString());
                }

            }
        });

        messageAdapter = new MessageAdapter(this);
        listView.setAdapter(messageAdapter);

        changeLayoutType(1);


    }

    @Override
    protected MessagePresenter getPresenter() {
        return new MessagePresenter(this);
    }


    @OnClick({R.id.act_message_rl_pingtai_msg, R.id.act_message_rl_device_msg})
    void click(View view) {
        switch (view.getId()) {
            case R.id.act_message_rl_pingtai_msg:
                changeLayoutType(1);
                break;

            case R.id.act_message_rl_device_msg:
                changeLayoutType(2);
                break;
        }
    }

    private void changeLayoutType(int type) {
        pageType = type;
        if (type == 1) {
            view_line_pingtai_msg.setVisibility(View.VISIBLE);
            view_line_device_msg.setVisibility(View.GONE);


            pageNumPlatform = 1;
            mPlatformMessageList.clear();
            messageAdapter.updata(mPlatformMessageList, pageType);
            mPresenter.getOrderMessageList(pageNumPlatform, pageSize, false, false);
        } else if (type == 2) {
            view_line_pingtai_msg.setVisibility(View.GONE);
            view_line_device_msg.setVisibility(View.VISIBLE);


            pageNumDevice = 1;
            mDeviceMessageList.clear();
            messageAdapter.updata(mDeviceMessageList, pageType);
            mPresenter.getDeviceMessageList(pageNumDevice, pageSize, false, false);
        }

        if (BaseApplication.messageDeviceNum > 0) {
            view_dit_device_msg.setVisibility(View.VISIBLE);
        } else {
            view_dit_device_msg.setVisibility(View.GONE);
        }

        if (BaseApplication.messageOrderNum > 0) {
            view_dit_pingtai_msg.setVisibility(View.VISIBLE);
        } else {
            view_dit_pingtai_msg.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        changeLayoutType(pageType);
    }

    @Override
    public void getDeviceMessageListFinish(List<MessageListResponse> list, boolean isRefresh, boolean isLoadMore) {
        try {
            if (isRefresh) {
                refreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            } else if (isLoadMore) {
                refreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }

            if (!ListUtils.isEmpty(list)) {

                mDeviceMessageList.addAll(list);
            }

            messageAdapter.updata(mDeviceMessageList, 2);
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    @Override
    public void readDeviceMessageFinish() {

    }

    @Override
    public void getPlatformMessageFinish(boolean isRefresh, boolean isLoadMore) {
        try {
            if (isRefresh) {
                refreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            } else if (isLoadMore) {
                refreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }

//            if (!ListUtils.isEmpty(list)) {
//                mPlatformMessageList.addAll(list);
//            }

            messageAdapter.updata(mPlatformMessageList, 2);
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    @Override
    public void getOrderMessageListFinish(List<OrderMessageResponse> list, boolean isRefresh, boolean isLoadMore) {
        try {
            if (isRefresh) {
                refreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            } else if (isLoadMore) {
                refreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
            if (!ListUtils.isEmpty(list)) {
                mPlatformMessageList.addAll(list);
            }
            messageAdapter.updata(mPlatformMessageList, 1);
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    @Override
    public void readOrderMessageFinish() {

    }
}
