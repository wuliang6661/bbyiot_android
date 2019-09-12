package com.baibeiyun.bbyiot.module.home.ui.deviceswitch;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.DeviceSwtichResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.home.presenter.deviceswitch.DeviceSwitchPresenter;
import com.baibeiyun.bbyiot.module.home.ui.devicenum.NumMapFragment;
import com.baibeiyun.bbyiot.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 开关设备
 */
public class DeviceSwitchDetailsActivity extends BaseActivity {
    public static  final String KEY_IS_BEAN = "key_is_bean";
    @BindView(R.id.act_device_switch_details_view_mianban)
    View view_mianban;
    @BindView(R.id.act_device_switch_details_view_info)
    View view_info;
    @BindView(R.id.act_device_switch_details_view_map)
    View view_map;

    private SwitchKongzhiFragment switchKongzhiFragment;
    private SwitchInfoFragment switchInfoFragment;
    private NumMapFragment numMapFragment;

    boolean isEdit = false;

    public static DeviceSwtichResponse response;
    public static int switchType;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_device_switch_details;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        response = (DeviceSwtichResponse) extras.getSerializable(KEY_IS_BEAN);
        LogUtils.e(response==null?"response == null":"response != null");
        switchType = extras.getInt("switchType");
    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("设备详情");

        changePage(1);

        mToolbar.inflateMenu(R.menu.addtion);
        mToolbar.getMenu().getItem(0).setTitle("保存");

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (mToolbar.getMenu().getItem(0) == item) {

                    try {
                        switchKongzhiFragment.save(isEdit);
                    } catch (Exception e) {
                        LogUtils.w(e.toString());
                    }


                    return true;
                }
                return false;
            }
        });


    }

    @OnClick({R.id.act_device_switch_details_rl_mianban, R.id.act_device_switch_details_rl_map, R.id.act_device_switch_details_rl_info})
    void click(View view) {
        switch (view.getId()) {
            case R.id.act_device_switch_details_rl_mianban:
                changePage(1);

                break;

            case R.id.act_device_switch_details_rl_info:
                changePage(2);

                break;

            case R.id.act_device_switch_details_rl_map:
                changePage(3);

                break;
        }
    }

    private void changePage(int page) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        switch (page) {
            case 1:
                view_mianban.setVisibility(View.VISIBLE);
                view_info.setVisibility(View.GONE);
                view_map.setVisibility(View.GONE);

                if (switchKongzhiFragment == null) {
                    switchKongzhiFragment = new SwitchKongzhiFragment();
                    transaction.add(R.id.act_device_num_details_framelayout, switchKongzhiFragment);
                } else {
                    transaction.show(switchKongzhiFragment);
                }
                break;

            case 2:
                view_mianban.setVisibility(View.GONE);
                view_info.setVisibility(View.VISIBLE);
                view_map.setVisibility(View.GONE);

                if (switchInfoFragment == null) {
                    switchInfoFragment = new SwitchInfoFragment();
                    transaction.add(R.id.act_device_num_details_framelayout, switchInfoFragment);
                } else {
                    transaction.show(switchInfoFragment);
                }
                break;

            case 3:
                view_mianban.setVisibility(View.GONE);
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

    @Override
    protected DeviceSwitchPresenter getPresenter() {
        return new DeviceSwitchPresenter(this);
    }


    private void hideFragments(FragmentTransaction transaction) {

        if (switchKongzhiFragment != null) {
            transaction.hide(switchKongzhiFragment);
        }


        if (switchInfoFragment != null) {
            transaction.hide(switchInfoFragment);
        }

        if (numMapFragment != null) {
            transaction.hide(numMapFragment);
        }
    }

}
