package com.baibeiyun.bbyiot.module.home.ui.devicepic;

import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.DevicePicInfoResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.home.contract.devicepic.PicInfoContract;
import com.baibeiyun.bbyiot.module.home.presenter.devicepic.PicInfoPresenter;

import butterknife.BindView;

public class PicInfoFragment extends BaseFragment<PicInfoPresenter> implements PicInfoContract.View {

    @BindView(R.id.frg_pic_info_tv_device_name)
    TextView tv_device_name;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_pic_info;
    }

    @Override
    protected void initViewsAndEvents() {

        mPresenter.getPicInfo(DevicePictureDetailsActivity.id);
    }

    @Override
    protected PicInfoPresenter getPresenter() {
        return new PicInfoPresenter(getActivity());
    }




    @Override
    public void getPicInfoFinish(DevicePicInfoResponse response) {
        if(response!=null){
            try{
                tv_device_name.setText(response.getName());

            }catch (Exception e){

            }
        }
    }
}
