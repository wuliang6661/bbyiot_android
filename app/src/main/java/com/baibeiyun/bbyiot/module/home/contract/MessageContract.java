package com.baibeiyun.bbyiot.module.home.contract;

import com.baibeiyun.bbyiot.model.Response.MessageListResponse;
import com.baibeiyun.bbyiot.model.Response.OrderMessageResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface MessageContract {


    interface Presenter extends IPresenter<View> {
        void getDeviceMessageList(int pageNum, int pageSize, boolean isRefresh, boolean isLoadMore);

        void readDeviceMessage(int id);

        void getPlatformMessage(int pageNum, int pageSize, boolean isRefresh, boolean isLoadMore);


        void getOrderMessageList(int pageNum, int pageSize, boolean isRefresh, boolean isLoadMore);

        void readOrderMessage(String id);
    }

    interface View extends IView {
        void getDeviceMessageListFinish(List<MessageListResponse> list, boolean isRefresh, boolean isLoadMore);

        void readDeviceMessageFinish();

        void getPlatformMessageFinish(boolean isRefresh, boolean isLoadMore);

        void getOrderMessageListFinish(List<OrderMessageResponse> list, boolean isRefresh, boolean isLoadMore);

        void readOrderMessageFinish();

    }
}
