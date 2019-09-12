package com.baibeiyun.bbyiot.module.mine.contract;

import com.baibeiyun.bbyiot.model.Response.AddressResponse;
import com.baibeiyun.bbyiot.model.request.AddressRequest;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;

import java.util.List;

public interface AddressManagerContract {


    interface Presenter extends IPresenter<View> {
        void getUserAddressList();
        void changeToDefault(int addressId);
        void deleteUserAddress(int addressId);

        void addUserAddress(AddressRequest request);
        void updateUserAddress(AddressRequest request);
    }

    interface View extends IView {
        void getUserAddressListFinsh(List<AddressResponse> list);

        void changeToDefaultSccuess();
        void deleteUserAddressSccuess();

        void addUserAddressSccuess();
        void updateUserAddressSccuess();
    }
}
