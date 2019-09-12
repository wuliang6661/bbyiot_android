package com.baibeiyun.bbyiot.module.findshop.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsActiveResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsShopResponse;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.findshop.contract.ShopHardwareContract;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import java.util.List;

public class ShopHardwarePresenter extends BasePresenter<ShopHardwareContract.View> implements ShopHardwareContract.Presenter {

    public ShopHardwarePresenter(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void getGoodsActiveList(int pageNum, int pageSize, final boolean isRefresh, final boolean isLoadmore) {

        if (!isRefresh && !isLoadmore) {
            mView.showLoading(null);
        }

        dataManager.getGoodsActiveList(pageNum, pageSize)
                .subscribe(new BaseSubscriber<BaseResponse<List<GoodsShopResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        ToastUtils.showToast(errorMsg);
                        mView.getGoodsActiveListFinishFail(null, isRefresh, isLoadmore);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<GoodsShopResponse>> response) {
                        mView.hideLoading();
                        LogUtils.w(response.getCode() + " msg ==" + response.getMsg());

                        if (response.getCode() == 200) {
                            mView.getGoodsActiveListFinishSuccess(response.getData(), isRefresh, isLoadmore);
                        }else {
                            mView.getGoodsActiveListFinishFail(null, isRefresh, isLoadmore);
                        }
                    }
                });
    }
}
