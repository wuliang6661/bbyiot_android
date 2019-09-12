package com.baibeiyun.bbyiot.module.home.ui.devicevideo;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.model.Response.DeviceVideoInfoResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.home.contract.devicevideo.VideoInfoContract;
import com.baibeiyun.bbyiot.module.home.presenter.devicevideo.VideoInfoPresenter;
import com.ezvizuikit.open.EZUIError;
import com.ezvizuikit.open.EZUIKit;
import com.ezvizuikit.open.EZUIPlayer;

import java.util.Calendar;

import butterknife.BindView;

public class VideoDeviceFragment extends BaseFragment<VideoInfoPresenter> implements VideoInfoContract.View {


    @BindView(R.id.player_ui)
    EZUIPlayer playerUi;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_video_device;
    }

    @Override
    protected VideoInfoPresenter getPresenter() {
        return new VideoInfoPresenter(getActivity());
    }

    @Override
    public void getDeviceVideoFinish(DeviceVideoInfoResponse response) {

    }


    @Override
    protected void initViewsAndEvents() {
        initView();

    }


    /**
     * 初始化控件
     */
    private void initView() {
        //初始化EZUIKit
        EZUIKit.initWithAppKey(getActivity().getApplication(), IConstant.appKey);
        //设置授权token
        EZUIKit.setAccessToken("");
        //设置播放回调callback
        playerUi.setCallBack(new EZUIPlayer.EZUIPlayerCallBack() {
            @Override
            public void onPlaySuccess() {

            }

            @Override
            public void onPlayFail(EZUIError ezuiError) {

            }

            @Override
            public void onVideoSizeChange(int i, int i1) {

            }

            @Override
            public void onPrepared() {
                playerUi.startPlay();
            }

            @Override
            public void onPlayTime(Calendar calendar) {

            }

            @Override
            public void onPlayFinish() {

            }
        });
        //设置播放参数
        playerUi.setUrl("");
        //开始播放
        playerUi.startPlay();
    }



    @Override
    public void onStop() {
        super.onStop();

        //停止播放
        playerUi.stopPlay();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        playerUi.releasePlayer();
    }
}
