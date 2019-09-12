package com.baibeiyun.bbyiot.module.home.ui.deviceswitch;

import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.SwitchInfoResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.home.contract.deviceswitch.SwitchInfoContract;
import com.baibeiyun.bbyiot.module.home.presenter.deviceswitch.SwitchInfoPresenter;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

import butterknife.BindView;

public class SwitchInfoFragment extends BaseFragment<SwitchInfoPresenter> implements SwitchInfoContract.View {

    @BindView(R.id.frg_gateway_info_tv_device_name)
    TextView tv_device_name;

    @BindView(R.id.frg_gateway_info_tv_fenlei)
    TextView tv_fenlei;

    @BindView(R.id.frg_gateway_info_tv_group)
    TextView tv_group;

    @BindView(R.id.frg_gateway_info_tv_gateway)
    TextView tv_gateway;

    @BindView(R.id.frg_gateway_info_tv_date)
    TextView tv_date;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_switch_info;
    }

    @Override
    protected void initViewsAndEvents() {
        if(DeviceSwitchDetailsActivity.response!=null) {
            mPresenter.getDeviceSwtich(DeviceSwitchDetailsActivity.response.getId());
        }
    }

    @Override
    protected SwitchInfoPresenter getPresenter() {
        return new SwitchInfoPresenter(getActivity());
    }

    @Override
    public void getDeviceSwtichFinish(SwitchInfoResponse response) {
        if (response != null) {
            try {
                if (response.getSwitchType() == 1) {
                    tv_fenlei.setText("模拟开关");
                } else if (response.getSwitchType() == 0) {
                    tv_fenlei.setText("基础开关");
                } else if (response.getSwitchType() == 2) {
                    tv_fenlei.setText("只读开关");
                }

                tv_device_name.setText(response.getName());
                //tv_fenlei.setText(response.getGroupName());

                tv_group.setText(response.getGroupName());
                tv_gateway.setText(response.getGatewayName());

                String stamp2String = DateUtils.stamp2String(response.getPdate(), "yyyy-MM-dd");
                tv_date.setText(stamp2String);

            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }
    }
}
