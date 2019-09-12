package com.baibeiyun.bbyiot.module.home.ui.devicevideo;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.DeviceVideoInfoResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.home.contract.devicevideo.VideoInfoContract;
import com.baibeiyun.bbyiot.module.home.presenter.devicevideo.VideoInfoPresenter;

public class VideoDeviceFragment extends BaseFragment<VideoInfoPresenter> implements VideoInfoContract.View {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_video_device;
    }

    @Override
    protected void initViewsAndEvents() {


    }

    @Override
    protected VideoInfoPresenter getPresenter() {
        return new VideoInfoPresenter(getActivity());
    }

    @Override
    public void getDeviceVideoFinish(DeviceVideoInfoResponse response) {

    }
}
