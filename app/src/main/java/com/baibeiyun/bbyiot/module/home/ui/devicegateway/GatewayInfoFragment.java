package com.baibeiyun.bbyiot.module.home.ui.devicegateway;

import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.DeviceGatewayInfoResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.home.contract.devicegateway.DeviceInfoContract;
import com.baibeiyun.bbyiot.module.home.presenter.devicegateway.DeviceGatewayInfoPresenter;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

import butterknife.BindView;

public class GatewayInfoFragment extends BaseFragment<DeviceGatewayInfoPresenter> implements DeviceInfoContract.View {


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
        return R.layout.frg_gateway_info;
    }

    @Override
    protected void initViewsAndEvents() {

        LogUtils.w("DeviceGatewayDateilsActivity.id ==" + DeviceGatewayDateilsActivity.id);

        mPresenter.getDeviceGatewayInfo(DeviceGatewayDateilsActivity.id);
    }


    @Override
    protected DeviceGatewayInfoPresenter getPresenter() {
        return new DeviceGatewayInfoPresenter(getActivity());
    }


    @Override
    public void getDeviceGatewayInfoFinish(DeviceGatewayInfoResponse response) {
        if (response != null) {
            try {
                tv_device_name.setText(response.getGatewayName());

                tv_fenlei.setText(response.getBrand());
                tv_group.setText(response.getBrand());

                tv_gateway.setText(response.getBrand());

                String time = DateUtils.stamp2String(response.getUpdateTime(), "yyyy-MM-dd");
                tv_date.setText(time);

            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }

    }
}
