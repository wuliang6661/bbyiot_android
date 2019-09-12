package com.baibeiyun.bbyiot.module.home.ui.deviceswitch;

import android.app.DatePickerDialog;
import android.view.View;
import android.view.ViewGroup;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.SaveDeviceRequest;
import com.baibeiyun.bbyiot.model.Response.SwitchConfigResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.home.adapter.DeviceNumControlAdapter;
import com.baibeiyun.bbyiot.module.home.contract.deviceswitch.SwitchKongzhiContract;
import com.baibeiyun.bbyiot.module.home.presenter.deviceswitch.SwitchKongzhiPresenter;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.pullableview.SwipeListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SwitchKongzhiFragment extends BaseFragment<SwitchKongzhiPresenter> implements SwitchKongzhiContract.View {


    @BindView(R.id.frg_switch_kongzhi_rl_add)
    ViewGroup rl_add;

    @BindView(R.id.frg_switch_kongzhi_listview)
    SwipeListView listview;

    private DeviceNumControlAdapter deviceNumControlAdapter;

    List<SwitchConfigResponse> mDataList = new ArrayList();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_switch_kongzhi;
    }

    @Override
    protected void initViewsAndEvents() {
        deviceNumControlAdapter = new DeviceNumControlAdapter(getActivity());
        listview.setAdapter(deviceNumControlAdapter);

        deviceNumControlAdapter.setOnClickLinsenter(new DeviceNumControlAdapter.OnClickLinsenter() {
            @Override
            public void delete(int postion) {
                listview.hiddenRightCurrent();
            }
        });

        if (DeviceSwitchDetailsActivity.response != null) {
            mPresenter.getDeviceSwitchConfig(DeviceSwitchDetailsActivity.response.getId(), DeviceSwitchDetailsActivity.response.getAttributeId() + "");
        }
    }


    public void save(boolean isEdit) {
        List<SwitchConfigResponse> list = deviceNumControlAdapter.getList();

        SaveDeviceRequest request = new SaveDeviceRequest();
        if (DeviceSwitchDetailsActivity.response != null) {
            request.setEid(DeviceSwitchDetailsActivity.response.getId());
        }
        List<SaveDeviceRequest.DeviceSwitchesBean> requsetList = new ArrayList();
        if (list != null) {
            for (SwitchConfigResponse response : list) {
                SaveDeviceRequest.DeviceSwitchesBean bean = new SaveDeviceRequest.DeviceSwitchesBean();
                /**
                 * private int eid;
                 private String endTime;
                 private String id;
                 private int isOpen;
                 private int openValue;
                 private String remark;
                 private String startTime;
                 private int updateId;
                 private String updateTime;
                 */
                bean.setEid(response.getEid());
                bean.setEndTime(response.getEndTime());
                bean.setId(response.getId());
                bean.setIsOpen(response.getIsOpen());
                bean.setOpenValue(response.getOpenValue());
                bean.setRemark("");
                bean.setStartTime(response.getStartTime());
                bean.setUpdateId(response.getUpdateId());
                String currentDate = DateUtils.getCurrentTimeStamp() + "";
                bean.setUpdateTime(currentDate);

                if (DeviceSwitchDetailsActivity.response != null) {
                    bean.setAttributeId(DeviceSwitchDetailsActivity.response.getAttributeId()+"");
                }


                requsetList.add(bean);
            }
        }
        request.setDeviceSwitches(requsetList);
        if (DeviceSwitchDetailsActivity.response != null) {
            request.setAttributeId(DeviceSwitchDetailsActivity.response.getAttributeId()+"");
        }
        String toJson = new Gson().toJson(request);
        LogUtils.w("提交的参数  " + toJson);
        mPresenter.saveDeviceSwtich(request);
    }

    @Override
    protected SwitchKongzhiPresenter getPresenter() {
        return new SwitchKongzhiPresenter(getActivity());
    }


    @OnClick({R.id.frg_switch_kongzhi_rl_add})
    void click(View view) {
        switch (view.getId()) {
            case R.id.frg_switch_kongzhi_rl_add:
                //添加
                SwitchConfigResponse switchConfigResponse = new SwitchConfigResponse();
                switchConfigResponse.setStartTime("10:00");
                switchConfigResponse.setEndTime("12:00");
                switchConfigResponse.setIsOpen(1);
                switchConfigResponse.setOpenValue(0);
                if (DeviceSwitchDetailsActivity.response != null) {
                    switchConfigResponse.setEid(DeviceSwitchDetailsActivity.response.getId());
                }
                mDataList.add(switchConfigResponse);

                deviceNumControlAdapter.updata(mDataList);
                break;
        }
    }

    @Override
    public void getDeviceSwitchConfigFinish(List<SwitchConfigResponse> list) {
        if (list != null) {
            mDataList.addAll(list);
        }

        deviceNumControlAdapter.updata(mDataList);

    }

    @Override
    public void saveDeviceSwtichFinish() {
        ToastUtils.showToast("保存成功");
        getActivity().finish();
    }
}
