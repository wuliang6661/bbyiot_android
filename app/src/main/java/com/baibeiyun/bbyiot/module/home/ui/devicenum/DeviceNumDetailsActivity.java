package com.baibeiyun.bbyiot.module.home.ui.devicenum;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.DataManager;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.home.presenter.devicenum.DeviceNumPresenter;
import com.baibeiyun.bbyiot.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 数值设备
 */
public class DeviceNumDetailsActivity extends BaseActivity {

    public final static String DECIVE_ID = "decive_id";
    public final static String ATTRIBUTE_ID = "attribute_id";

    @BindView(R.id.act_device_num_details_view_real_data)
    View view_real_data;

    @BindView(R.id.act_device_num_details_view_history_data)
    View view_history_data;

    @BindView(R.id.act_device_num_details_view_info)
    View view_info;
    @BindView(R.id.act_statistic_analysis_view_map)
    View view_map;


    private NumRealDataFragment numRealDataFragment;
    private NumHistoryDataFragment numHistoryDataFragment;
    private NumInfoFragment numInfoFragment;
    private NumMapFragment numMapFragment;

    public static int mDeviceId;

    public static int mAttributeId;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_device_num_details;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mDeviceId = extras.getInt(DECIVE_ID);
        mAttributeId = extras.getInt(ATTRIBUTE_ID);
    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("设备详情");

        changePage(1);

        LogUtils.w("mDeviceId== " + mDeviceId + ", token \n" + DataManager.getsInstance().getToken());
    }

    @Override
    protected DeviceNumPresenter getPresenter() {
        return new DeviceNumPresenter(this);
    }


    @OnClick({R.id.act_device_num_details_rl_real_data, R.id.act_device_num_details_rl_history_data,
            R.id.act_device_num_details_rl_map, R.id.act_device_num_details_rl_info})
    void click(View view) {
        switch (view.getId()) {
            case R.id.act_device_num_details_rl_real_data:
                changePage(1);

                break;

            case R.id.act_device_num_details_rl_history_data:
                changePage(2);

                break;

            case R.id.act_device_num_details_rl_info:
                changePage(3);

                break;

            case R.id.act_device_num_details_rl_map:
                changePage(4);

                break;
        }
    }

    private void changePage(int page) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);

        switch (page) {
            case 1:
                view_real_data.setVisibility(View.VISIBLE);
                view_history_data.setVisibility(View.GONE);
                view_info.setVisibility(View.GONE);
                view_map.setVisibility(View.GONE);

                if (numRealDataFragment == null) {
                    numRealDataFragment = new NumRealDataFragment();
                    transaction.add(R.id.act_device_num_details_framelayout, numRealDataFragment);
                } else {
                    transaction.show(numRealDataFragment);
                }
                break;

            case 2:
                view_real_data.setVisibility(View.GONE);
                view_history_data.setVisibility(View.VISIBLE);
                view_info.setVisibility(View.GONE);
                view_map.setVisibility(View.GONE);

                if (numHistoryDataFragment == null) {
                    numHistoryDataFragment = new NumHistoryDataFragment();
                    transaction.add(R.id.act_device_num_details_framelayout, numHistoryDataFragment);
                } else {
                    transaction.show(numHistoryDataFragment);
                }
                break;

            case 3:
                view_real_data.setVisibility(View.GONE);
                view_history_data.setVisibility(View.GONE);
                view_info.setVisibility(View.VISIBLE);
                view_map.setVisibility(View.GONE);

                if (numInfoFragment == null) {
                    numInfoFragment = new NumInfoFragment();
                    transaction.add(R.id.act_device_num_details_framelayout, numInfoFragment);
                } else {
                    transaction.show(numInfoFragment);
                }
                break;
            case 4:
                view_real_data.setVisibility(View.GONE);
                view_history_data.setVisibility(View.GONE);
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

        if (numRealDataFragment != null) {
            transaction.hide(numRealDataFragment);
        }
        if (numHistoryDataFragment != null) {
            transaction.hide(numHistoryDataFragment);
        }

        if (numInfoFragment != null) {
            transaction.hide(numInfoFragment);
        }

        if (numMapFragment != null) {
            transaction.hide(numMapFragment);
        }
    }


}
