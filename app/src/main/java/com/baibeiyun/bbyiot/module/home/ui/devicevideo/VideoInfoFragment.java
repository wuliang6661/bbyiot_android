package com.baibeiyun.bbyiot.module.home.ui.devicevideo;

import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.DeviceVideoInfoResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.home.contract.devicevideo.VideoInfoContract;
import com.baibeiyun.bbyiot.module.home.presenter.devicevideo.VideoInfoPresenter;
import com.baibeiyun.bbyiot.utils.DateUtils;

import butterknife.BindView;

public class VideoInfoFragment extends BaseFragment<VideoInfoPresenter> implements VideoInfoContract.View {

    @BindView(R.id.frg_video_info_tv_video_name)
    TextView tv_video_name;


    @BindView(R.id.frg_video_info_tv_video_address)
    TextView tv_video_address;


    @BindView(R.id.frg_video_info_tv_video_group)
    TextView tv_video_group;

    @BindView(R.id.frg_video_info_tv_video_date)
    TextView tv_video_date;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_video_info;
    }

    @Override
    protected void initViewsAndEvents() {
        mPresenter.getDeviceVideo(DeviceVideoDetailsActivity.mDeviceId);
    }

    @Override
    protected VideoInfoPresenter getPresenter() {
        return new VideoInfoPresenter(getActivity());
    }

    @Override
    public void getDeviceVideoFinish(DeviceVideoInfoResponse response) {

        if (response != null) {
            try {
                tv_video_name.setText(response.getName());

                tv_video_address.setText(response.getPlayAddr());

                tv_video_group.setText(response.getGroupId()+"");

                String time = DateUtils.stamp2String(response.getUpdateTime(), "yyyy-MM-dd");
                tv_video_date.setText(time);
            } catch (Exception e) {

            }
        }
    }
}
