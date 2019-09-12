package com.baibeiyun.bbyiot.module.home.ui.devicepic;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.home.ui.devicenum.NumMapFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 图片设备  详情
 */
public class DevicePictureDetailsActivity extends BaseActivity {
    public final static String DECIVE_ID = "decive_id";
    @BindView(R.id.act_device_num_details_view_info)
    View view_info;

    @BindView(R.id.act_statistic_analysis_view_map)
    View view_map;
    private NumMapFragment numMapFragment;
    private PicInfoFragment picInfoFragment;

    public static int id = 0;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_device_pic_details;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        id = extras.getInt(DECIVE_ID);
    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("设备详情");

        changePage(1);
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }


    @OnClick({
            R.id.act_device_num_details_rl_map, R.id.act_device_num_details_rl_info})
    void click(View view) {
        switch (view.getId()) {


            case R.id.act_device_num_details_rl_info:
                changePage(1);

                break;

            case R.id.act_device_num_details_rl_map:
                changePage(2);

                break;
        }
    }


    private void changePage(int page) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);

        switch (page) {

            case 1:

                view_info.setVisibility(View.VISIBLE);
                view_map.setVisibility(View.GONE);

                if (picInfoFragment == null) {
                    picInfoFragment = new PicInfoFragment();
                    transaction.add(R.id.act_device_num_details_framelayout, picInfoFragment);
                } else {
                    transaction.show(picInfoFragment);
                }
                break;
            case 2:

                view_info.setVisibility(View.GONE);
                view_map.setVisibility(View.VISIBLE);

                if (numMapFragment == null) {
                    numMapFragment = new NumMapFragment();
                    transaction.add(R.id.act_device_num_details_framelayout, numMapFragment);
                } else {
                    transaction.show(numMapFragment);
                }

                break;

        }

        transaction.commitAllowingStateLoss();
    }


    private void hideFragments(FragmentTransaction transaction) {

        if (picInfoFragment != null) {
            transaction.hide(picInfoFragment);
        }

        if (numMapFragment != null) {
            transaction.hide(numMapFragment);
        }
    }

}
