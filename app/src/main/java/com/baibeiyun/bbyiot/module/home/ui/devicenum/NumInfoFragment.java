package com.baibeiyun.bbyiot.module.home.ui.devicenum;

import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.NumInfoResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.home.contract.devicenum.NumInfoContract;
import com.baibeiyun.bbyiot.module.home.presenter.devicenum.NumInfoPresenter;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

import butterknife.BindView;

public class NumInfoFragment extends BaseFragment<NumInfoPresenter> implements NumInfoContract.View {

    @BindView(R.id.frg_num_info_tv_decive_name)
    TextView tv_decive_name;

    @BindView(R.id.frg_num_info_tv_decive_no)
    TextView tv_decive_no;

    @BindView(R.id.frg_num_info_tv_decive_id)
    TextView tv_decive_id;

    @BindView(R.id.frg_num_info_tv_decive_group)
    TextView tv_decive_group;

    @BindView(R.id.frg_num_info_tv_decive_wangguan)
    TextView tv_decive_wangguan;

    @BindView(R.id.frg_num_info_tv_decive_xieyi)
    TextView tv_decive_xieyi;

    @BindView(R.id.frg_num_info_tv_decive_caiji)
    TextView tv_decive_caiji;

    @BindView(R.id.frg_num_info_tv_decive_date)
    TextView tv_decive_date;


    @BindView(R.id.frg_num_info_tv_decive_location)
    TextView tv_decive_location;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_num_info;
    }

    @Override
    protected void initViewsAndEvents() {

        mPresenter.getDeviceNumInfo();


    }

    @Override
    protected NumInfoPresenter getPresenter() {
        return new NumInfoPresenter(getActivity());
    }


    @Override
    public void getDeviceNumInfoFinish(NumInfoResponse response) {
        try {
            if (response != null) {
                tv_decive_name.setText(response.getName());
                tv_decive_no.setText(response.getCode() + "");

                tv_decive_id.setText(response.getId() + "");
                tv_decive_group.setText(response.getGroupName());

                tv_decive_wangguan.setText(response.getGatewayName());

                tv_decive_xieyi.setText(getProtocolName(response.getProtocolType()));

                tv_decive_caiji.setText(response.getIntervalTime() + " ");

                tv_decive_date.setText(DateUtils.stamp2String(response.getPdate(), "yyyy-MM-dd HH:mm:ss"));

                tv_decive_location.setText(response.getDimension() + " , " + response.getLongitude());

            }
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    //(0：mqtt，1：coap,2：ModbusTCP，3：ModbusRTU，4：TCP）
    String getProtocolName(int protocolType) {
        switch (protocolType) {
            case 0:
                return "mqtt";

            case 1:
                return "coap";

            case 2:
                return "ModbusTCP";

            case 3:
                return "ModbusRTU";
            case 4:
                return "TCP";

            default:
                return "";
        }
    }
}
