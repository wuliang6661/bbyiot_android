package com.baibeiyun.bbyiot.module.home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.SwitchConfigResponse;
import com.baibeiyun.bbyiot.module.home.ui.deviceswitch.DeviceSwitchDetailsActivity;
import com.baibeiyun.bbyiot.module.home.view.SelectWorkTimeDialog;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.IndicatorStayLayout;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.util.ArrayList;
import java.util.List;

public class DeviceNumControlAdapter extends BaseAdapter {

    private List<SwitchConfigResponse> mList;
    private Activity context;
    private String openingstart;
    private String openingend;
    private ArrayList<String> data1;
    private ArrayList<String> data2;
    private SelectWorkTimeDialog mChangeAddressDialog;


    public DeviceNumControlAdapter(Activity context) {
        this.context = context;
    }

    public void updata(List<SwitchConfigResponse> list) {

        this.mList = list;
        this.notifyDataSetChanged();
    }

    public List<SwitchConfigResponse> getList() {
        return mList;
    }

    @Override
    public int getCount() {
        if (null != mList) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = holder.mRootView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.setData(position);

        return convertView;
    }

    class ViewHolder {

        private final ImageView iv_switch;
        private final ViewGroup ll_date;
        private final IndicatorSeekBar seekbar;
        private final IndicatorStayLayout indicatorstaylayout;
        private final View tv_delete;
        View mRootView;
        private final TextView tv_no, tv_date;

        ViewHolder() {
            mRootView = View.inflate(context, R.layout.item_num_config, null);
            tv_no = mRootView.findViewById(R.id.item_num_config_tv_no);
            iv_switch = mRootView.findViewById(R.id.item_num_config_iv_switch);
            ll_date = mRootView.findViewById(R.id.item_num_config_ll_date);
            tv_date = mRootView.findViewById(R.id.item_num_config_tv_date);
            seekbar = mRootView.findViewById(R.id.item_num_config_seekbar);
            indicatorstaylayout = mRootView.findViewById(R.id.item_num_config_indicatorstaylayout);

            tv_delete = mRootView.findViewById(R.id.item_num_config_tv_delete);
        }

        public void setData(final int position) {
            try {
                String num = "";
                if (position + 1 < 10) {
                    num = "0" + (position + 1);
                } else {
                    num = (position + 1) + "";
                }
                tv_no.setText(num);


                SwitchConfigResponse switchConfigResponse = mList.get(position);

                if (DeviceSwitchDetailsActivity.switchType == 0) {
                    // ----------------  0基础开关 1模拟开关, 2只读开关
                    iv_switch.setVisibility(View.VISIBLE);
                    indicatorstaylayout.setVisibility(View.GONE);

                } else if (DeviceSwitchDetailsActivity.switchType == 1) {
                    iv_switch.setVisibility(View.GONE);
                    indicatorstaylayout.setVisibility(View.VISIBLE);
                    if (switchConfigResponse != null) {
                        seekbar.setProgress(switchConfigResponse.getOpenValue());
                    }
                } else if (DeviceSwitchDetailsActivity.switchType == 2) {
                    indicatorstaylayout.setVisibility(View.GONE);
                    iv_switch.setVisibility(View.GONE);
                }

                if (switchConfigResponse != null) {
                    tv_date.setText(switchConfigResponse.getStartTime() + "-" + switchConfigResponse.getEndTime());

                    if (switchConfigResponse.getIsOpen() == 1) {
                        iv_switch.setImageResource(R.mipmap.icon_device_switch_open);
                    } else {
                        iv_switch.setImageResource(R.mipmap.icon_device_switch_close);
                    }

                    seekbar.setProgress(switchConfigResponse.getOpenValue());
                }

                seekbar.setOnSeekChangeListener(new OnSeekChangeListener() {
                    @Override
                    public void onSeeking(SeekParams seekParams) {

                    }

                    @Override
                    public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                        mList.get(position).setOpenValue(seekBar.getProgress());
                    }
                });

                ll_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showSelectTimeDialog(position);
                    }
                });

                iv_switch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mList.get(position).getIsOpen() == 1) {
                            mList.get(position).setIsOpen(0);
                        } else if (mList.get(position).getIsOpen() == 0) {
                            mList.get(position).setIsOpen(1);
                        }
                        notifyDataSetChanged();
                    }
                });

                tv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mList.remove(position);
                        mLinsenter.delete(position);
                        notifyDataSetChanged();
                    }
                });


            } catch (Exception e) {
                LogUtils.w(e.toString());
            }
        }
    }

    void showSelectTimeDialog(final int position) {
        initData();
        mChangeAddressDialog = new SelectWorkTimeDialog(context, data1, data2,
                openingstart, openingend);
        mChangeAddressDialog.show();
        mChangeAddressDialog.setAddresskListener(new SelectWorkTimeDialog.OnAddressCListener() {
            @Override
            public void onClick(String openingstart, String openingend) {
                try {
                    mList.get(position).setStartTime(StringUtils.isEmpty(openingstart) ? "00:00" : openingstart);
                    mList.get(position).setEndTime(StringUtils.isEmpty(openingend) ? "00:00" : openingend);

                    notifyDataSetChanged();
                } catch (Exception e) {
                    LogUtils.w(e.toString());
                }
            }
        });
    }

    void initData() {
        openingstart = "";
        openingend = "";
        ArrayList<String> data = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
            if (i > 9) {
                data.add(i, i + ":00");
            } else {
                data.add(i, "0" + i + ":00");
            }
        }
        data1 = new ArrayList<String>();
        data2 = new ArrayList<String>();
        data1.addAll(data);
        data2.addAll(data);
    }

    OnClickLinsenter mLinsenter;

    public interface OnClickLinsenter {
        void delete(int postion);
    }

    public void setOnClickLinsenter(OnClickLinsenter linsenter) {
        mLinsenter = linsenter;
    }
}
