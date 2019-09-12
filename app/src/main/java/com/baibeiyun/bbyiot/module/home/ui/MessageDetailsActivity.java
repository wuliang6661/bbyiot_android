package com.baibeiyun.bbyiot.module.home.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.MessageListResponse;
import com.baibeiyun.bbyiot.model.Response.OrderMessageResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.home.contract.MessageContract;
import com.baibeiyun.bbyiot.module.home.presenter.MessagePresenter;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

import butterknife.BindView;

public class MessageDetailsActivity extends BaseActivity<MessagePresenter> implements MessageContract.View {

    @BindView(R.id.act_message_details_tv_title)
    TextView tv_title;
    @BindView(R.id.act_message_details_tv_time)
    TextView tv_time;
    @BindView(R.id.act_message_details_tv_content)
    TextView tv_content;


    private OrderMessageResponse response1;
    private MessageListResponse response2;
    private int mType;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_message_details;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mType = extras.getInt("type");
        if (mType == 2) {
            response2 = (MessageListResponse) extras.getSerializable("dataBean");
        } else {
            response1 = (OrderMessageResponse) extras.getSerializable("dataBean");
        }

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("消息");

        if (response2 != null) {
            mPresenter.readDeviceMessage(response2.getId());
        }

        if (response1 != null) {
            mPresenter.readOrderMessage(String.valueOf(response1.getId()));
        }
    }

    @Override
    protected MessagePresenter getPresenter() {
        return new MessagePresenter(this);
    }

    @Override
    public void getDeviceMessageListFinish(List<MessageListResponse> list, boolean isRefresh, boolean isLoadMore) {

    }

    @Override
    public void readDeviceMessageFinish() {
        try {
            if (response2 != null) {
                tv_title.setText(response2.getTitle());
                tv_content.setText(response2.getContent());
                tv_time.setText(DateUtils.stamp2String(response2.getUpdateTime(), "yyyy年MM月dd日 HH:mm"));
            }
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    @Override
    public void getPlatformMessageFinish(boolean isRefresh, boolean isLoadMore) {

    }

    @Override
    public void getOrderMessageListFinish(List<OrderMessageResponse> list, boolean isRefresh, boolean isLoadMore) {

    }

    @Override
    public void readOrderMessageFinish() {
        try {
            if (response1 != null) {
                tv_title.setText(response1.getTitle());
                tv_content.setText(response1.getContent());
                tv_time.setText(response1.getUpdateDate());
            }
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }
}
