package com.baibeiyun.bbyiot.module.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.model.event.CommonEvent;
import com.baibeiyun.bbyiot.model.event.EventConstant;
import com.baibeiyun.bbyiot.module.base.BaseAppManager;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindView(R.id.act_main_group_radioGroup)
    RadioGroup radioGroup;

    //底部tab
    //首页
    @BindView(R.id.act_main_rl_radio_home)
    RelativeLayout rl_radio_home;

    @BindView(R.id.act_main_radio_home)
    RadioButton main_radio_home;

    //监测
    @BindView(R.id.act_main_rl_radio_testing)
    RelativeLayout rl_radio_testing;

    @BindView(R.id.act_main_radio_testing)
    RadioButton main_radio_testing;

    //发现
    @BindView(R.id.act_main_rl_radio_find)
    RelativeLayout rl_radio_find;

    @BindView(R.id.act_main_radio_find)
    RadioButton main_radio_find;

    //我的
    @BindView(R.id.act_main_rl_radio_rl_radio_account)
    RelativeLayout rl_radio_account;

    @BindView(R.id.act_main_radio_account)
    RadioButton main_radio_account;


    /**********
     * 底部的RadioButton数组
     ************/
    private RadioButton[] button_radios;

    private MineFragment mineFragment;
    private HomeFragment homeFragment;

    /*****
     * 上一个选中Fragment或RadioButton的下标
     ******/
    private int lastShowFragment = 0;

    private long clicktime;

    /*****
     * 当前选中Fragment或RadioButton的下标
     ******/
    private int currentShowFragment;
    private FindFragment findFragment;
    private TestingFragment testingFragment;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_main;
    }

    @Override
    protected void initViewsAndEvents() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        button_radios = new RadioButton[]{main_radio_home,
                main_radio_testing, main_radio_find,
                main_radio_account};

        changePage(0);

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }


    @OnClick({R.id.act_main_radio_home, R.id.act_main_radio_testing, R.id.act_main_radio_find,
            R.id.act_main_radio_account})
    public void mainClick(View v) {
        switch (v.getId()) {
            case R.id.act_main_radio_home:// 首页
                changePage(0);
                break;
            case R.id.act_main_radio_testing:// 监测
                changePage(1);
                break;
            case R.id.act_main_radio_find:// 发现
                changePage(2);
                break;
            case R.id.act_main_radio_account:// 个人中心
                changePage(3);
                break;

        }
    }

    private void changePage(int position) {
        try {
            currentShowFragment = position;

            if (button_radios != null) {
                button_radios[lastShowFragment].setChecked(false);
            }

            if (button_radios != null) {
                for (int i = 0; i < button_radios.length; i++) {
                    button_radios[i].setChecked(false);
                }
            }


            button_radios[position].setChecked(true);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            hideFragments(transaction);

            switch (position) {
                case 0:
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                        transaction.add(R.id.act_main_container, homeFragment);
                    } else {
                        transaction.show(homeFragment);
                    }
                    break;

                case 1:
                    if (testingFragment == null) {
                        testingFragment = new TestingFragment();
                        transaction.add(R.id.act_main_container, testingFragment);
                    } else {
                        transaction.show(testingFragment);
                    }
                    break;

                case 2:
                    if (findFragment == null) {
                        findFragment = new FindFragment();
                        transaction.add(R.id.act_main_container, findFragment);
                    } else {
                        transaction.show(findFragment);
                    }
                    break;

                case 3:
                    if (mineFragment == null) {
                        mineFragment = new MineFragment();
                        transaction.add(R.id.act_main_container, mineFragment);
                    } else {
                        transaction.show(mineFragment);
                    }
                    break;
            }
            transaction.commitAllowingStateLoss();

            lastShowFragment = position;
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    private void hideFragments(FragmentTransaction transaction) {

        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (testingFragment != null) {
            transaction.hide(testingFragment);
        }

        if (findFragment != null) {
            transaction.hide(findFragment);
        }

        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }


    @Subscribe
    public void toTestingPage(CommonEvent event) {
        if (event != null && EventConstant.TO_JIANCE.equals(event.getTag())) {
            changePage(1);
        }
    }

    @Subscribe
    public void toTestingPage2(HomeGroupsResponse event) {
        changePage(1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //扫描条形码
            if (requestCode == HomeFragment.KEY_SCAN_REQUEST_CODE) {
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        String number = bundle.getString("result");
                        if (!TextUtils.isEmpty(number)) {
                            LogUtils.w("-------number------- " + number);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        LogUtils.w("权限 requestCode== " + requestCode);
        switch (requestCode) {
            case 1:
                if (mineFragment != null) {
                    mineFragment.requestPermissions();
                }
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        changePage(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    /**
     * 双击退出app
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            outApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出APP
     */
    private void outApp() {
        if (DateUtils.getCurrentTimeStamp() - clicktime < 3000) {
            this.finish();
            BaseAppManager.getInstance().clear();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } else {
            showMessage("再按一次退出");
            clicktime = DateUtils.getCurrentTimeStamp();
        }
    }

}
