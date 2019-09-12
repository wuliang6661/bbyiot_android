package com.baibeiyun.bbyiot.module.main.ui;

import android.app.DatePickerDialog;
import android.icu.util.TimeUnit;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.DataManager;
import com.baibeiyun.bbyiot.model.Response.LoginResponse;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.login.ui.LoginActivity;
import com.baibeiyun.bbyiot.module.mine.ui.AddressManagerActivity;
import com.baibeiyun.bbyiot.module.mine.ui.AfterSaleActivity;
import com.baibeiyun.bbyiot.module.mine.ui.GroupActivity;
import com.baibeiyun.bbyiot.module.mine.ui.MonitoringAlarmActivity;
import com.baibeiyun.bbyiot.module.mine.ui.MyInfoActivity;
import com.baibeiyun.bbyiot.module.mine.ui.SettingActivity;
import com.baibeiyun.bbyiot.module.mine.ui.analysis.StatisticAnalysisActivityNew;
import com.baibeiyun.bbyiot.module.mine.ui.order.OrderActivity;
import com.baibeiyun.bbyiot.module.mine.ui.order.OrderEvaluationListActivity;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.BaseTimer;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.SystemTool;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.dialog.CommonDialog;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {

    private CommonDialog commonDialog;
    private LoginResponse loginBean;

    @BindView(R.id.frg_mine_tv_user_name)
    TextView tv_user_name;
    private Bundle mBundle;
    private CommonDialog serviceCenterDialog;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_mine;
    }

    @Override
    protected void initViewsAndEvents() {
        try {
            loginBean = DataManager.getsInstance().getLoginBean();
            if (loginBean != null) {
                tv_user_name.setText(loginBean.getUser().getUsername());
            }
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }


    @OnClick({R.id.frg_mine_login_out, R.id.frg_mine_tongji, R.id.frg_mine_ll_zuji
            , R.id.frg_mine_ll_group, R.id.frg_mine_ll_baojing
            , R.id.frg_mine_ll_pingjia, R.id.frg_mine_ll_setting
            , R.id.frg_mine_ll_order_all, R.id.frg_mine_ll_order_pay
            , R.id.frg_mine_ll_order_shouhuo, R.id.frg_mine_ll_order_pingjia
            , R.id.frg_mine_ll_order_shouhou
            , R.id.frg_mine_ll_address, R.id.frg_mine_ll_fuwu

            , R.id.frg_mine_iv_user_logo, R.id.frg_mine_ll_user, R.id.frg_mine_ll_bianji})
    void click(View view) {
        mBundle = new Bundle();
        switch (view.getId()) {
            case R.id.frg_mine_iv_user_logo:
            case R.id.frg_mine_ll_user:
            case R.id.frg_mine_ll_bianji:
                ActivityUtils.startActivity(getActivity(),MyInfoActivity.class);
                break;

            case R.id.frg_mine_ll_group:
                //分组管理
                ActivityUtils.startActivity(getActivity(), GroupActivity.class);
                break;
            case R.id.frg_mine_ll_baojing:
                ActivityUtils.startActivity(getActivity(),MonitoringAlarmActivity.class);
                break;
            //统计分析
            case R.id.frg_mine_tongji:
                ActivityUtils.startActivity(getActivity(),StatisticAnalysisActivityNew.class);
                break;

            case R.id.frg_mine_ll_order_all:
                mBundle.putInt(OrderActivity.KEY_ORDER_TYPE, 0);
                ActivityUtils.startActivity(getActivity(),OrderActivity.class, mBundle);
                break;

            case R.id.frg_mine_ll_order_pay:
                mBundle.putInt(OrderActivity.KEY_ORDER_TYPE, 1);
                ActivityUtils.startActivity(getActivity(),OrderActivity.class, mBundle);
                break;

            case R.id.frg_mine_ll_order_shouhuo:
                mBundle.putInt(OrderActivity.KEY_ORDER_TYPE, 3);
                ActivityUtils.startActivity(getActivity(),OrderActivity.class, mBundle);
                break;
            case R.id.frg_mine_ll_order_pingjia:
                //待评价
                mBundle.putInt(OrderActivity.KEY_ORDER_TYPE, 4);
                ActivityUtils.startActivity(getActivity(),OrderActivity.class, mBundle);
                break;

            case R.id.frg_mine_ll_order_shouhou:
                //ToastUtils.showToast("售后服务");
                ActivityUtils.startActivity(getActivity(), AfterSaleActivity.class);
                break;


            //我的足迹
            case R.id.frg_mine_ll_zuji:
                ToastUtils.showToast("我的足迹");
                break;

            case R.id.frg_mine_ll_pingjia:
                ActivityUtils.startActivity(getActivity(),OrderEvaluationListActivity.class);
                break;

            case R.id.frg_mine_ll_address:
                ActivityUtils.startActivity(getActivity(),AddressManagerActivity.class);
                break;

            case R.id.frg_mine_ll_fuwu:
                //ToastUtils.showToast("服务中心");
                Calendar dateAndTime = Calendar.getInstance(Locale.getDefault());
                int hour = dateAndTime.get(Calendar.HOUR_OF_DAY);
                if (hour < 9 || hour >= 18) {
                    ToastUtils.showToast("当前不在服务时间内");
                } else {
                    serviceCenterDialog();
                }
                break;


            case R.id.frg_mine_ll_setting:
                ActivityUtils.startActivity(getActivity(), SettingActivity.class);
                break;


            case R.id.frg_mine_login_out:
                loginOutHistroy();
                break;
        }
    }

    private void serviceCenterDialog() {
        if (serviceCenterDialog == null) {
            serviceCenterDialog = new CommonDialog(getActivity());
        }
        serviceCenterDialog.show();

        serviceCenterDialog.setTxet("拨打", "0557-1234567");

        serviceCenterDialog.setOnDialogLinsenter(new CommonDialog.OnDialogLinsenter() {
            @Override
            public void confirm() {
                SystemTool.callPhone(getActivity(), "0557-1234567");
                serviceCenterDialog.dismiss();
            }

        });
    }

    void requestPermissions() {
        SystemTool.callPhone(getActivity(), "0557-1234567");
    }

    private void loginOutHistroy() {
        if (commonDialog == null) {
            commonDialog = new CommonDialog(getActivity());
        }
        commonDialog.show();

        commonDialog.setTxet("退出登录", "确定退出当前账号，重新登录吗？");

        commonDialog.setOnDialogLinsenter(new CommonDialog.OnDialogLinsenter() {
            @Override
            public void confirm() {
                BaseTimer.getInstans().killTimer();
                DataManager.getsInstance().saveLoginState(false);
                ActivityUtils.startActivityOrFinish(getActivity(), LoginActivity.class);
            }
        });
    }

}
