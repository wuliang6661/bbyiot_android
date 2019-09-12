package com.baibeiyun.bbyiot.module.mine.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.AddressResponse;
import com.baibeiyun.bbyiot.model.event.CommonEvent;
import com.baibeiyun.bbyiot.model.event.EventConstant;
import com.baibeiyun.bbyiot.model.request.AddressRequest;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.mine.contract.AddressManagerContract;
import com.baibeiyun.bbyiot.module.mine.presenter.AddressManagerPresenter;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressEditActivity extends BaseActivity<AddressManagerPresenter> implements AddressManagerContract.View {

    @BindView(R.id.act_address_edit_et_lxr)
    EditText et_lxr;
    @BindView(R.id.act_address_edit_et_phone)
    EditText et_phone;

    @BindView(R.id.act_address_edit_tv_address)
    TextView tv_address;

    @BindView(R.id.act_address_edit_et_address2)
    EditText et_address2;

    @BindView(R.id.act_address_edit_et_youbian)
    EditText et_youbian;

    @BindView(R.id.act_address_edit_iv_default)
    ImageView iv_default;


    private int mType = 1;

    private AddressResponse mAddressResponse;

    boolean isDefalut = false;
    private String mProvince;
    private String mCity;
    private String mDistrict;
    private String mYoubian;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_address_edit;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mType = extras.getInt("type", 1);
        mAddressResponse = (AddressResponse) extras.getSerializable("bean");
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle(mType == 1 ? "新增收货地址" : "编辑收货地址");

        try {
            if (mAddressResponse != null) {
                et_lxr.setText(mAddressResponse.getName());
                et_phone.setText(mAddressResponse.getPhone());

                mProvince = mAddressResponse.getProvince();
                mCity = mAddressResponse.getCity();
                mDistrict = mAddressResponse.getArea();
                tv_address.setText(mProvince + "-" + mCity + "-" + mDistrict);

                et_address2.setText(mAddressResponse.getAddress());
                mYoubian = mAddressResponse.getPostalCode();
                et_youbian.setText(mAddressResponse.getPostalCode());

                if (mAddressResponse.getIsDefault() == 1) {
                    isDefalut = true;
                    iv_default.setImageResource(R.mipmap.icon_address_cehck_check);
                } else {
                    isDefalut = false;
                    iv_default.setImageResource(R.mipmap.icon_address_cehck_nor);
                }

                et_lxr.setSelection(mAddressResponse.getName().length());
            }
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    @Override
    protected AddressManagerPresenter getPresenter() {
        return new AddressManagerPresenter(this);
    }

    @OnClick({R.id.act_address_edit_ll_default, R.id.act_address_edit_ll_address, R.id.act_address_edit_tv_submit})
    void clcik(View view) {
        switch (view.getId()) {
            case R.id.act_address_edit_ll_default:
                isDefalut = !isDefalut;
                if (isDefalut) {
                    iv_default.setImageResource(R.mipmap.icon_address_cehck_check);
                } else {
                    iv_default.setImageResource(R.mipmap.icon_address_cehck_nor);
                }
                break;

            case R.id.act_address_edit_ll_address:
                selectAddress();
                break;

            case R.id.act_address_edit_tv_submit:
                String lxr = et_lxr.getText().toString().trim();
                if (StringUtils.isEmpty(lxr)) {
                    ToastUtils.showToast("联系人不能为空");
                    return;
                }
                String phone = et_phone.getText().toString().trim();
                if (StringUtils.isEmpty(phone)) {
                    ToastUtils.showToast("手机号不能为空");
                    return;
                }
                if (StringUtils.isEmpty(mProvince)) {
                    ToastUtils.showToast("请选择省市区");
                    return;
                }

                String address = et_address2.getText().toString().trim();
                if (StringUtils.isEmpty(address)) {
                    ToastUtils.showToast("请填写详情地址");
                    return;
                }

                AddressRequest request = new AddressRequest();
                request.setName(lxr);
                request.setPhone(phone);
                request.setProvince(mProvince);
                request.setCity(mCity);
                request.setArea(mDistrict);
                request.setAddress(address);
                request.setPostalCode(mYoubian);
                request.setIsDefault(isDefalut ? 1 : 0);
                if (mType == 1) {
                    mPresenter.addUserAddress(request);
                } else {
                    if (mAddressResponse != null) {
                        request.setId(mAddressResponse.getId());
                    }
                    mPresenter.updateUserAddress(request);
                }


                break;
        }
    }

    @Override
    public void getUserAddressListFinsh(List<AddressResponse> list) {

    }

    @Override
    public void changeToDefaultSccuess() {

    }

    @Override
    public void deleteUserAddressSccuess() {

    }

    @Override
    public void addUserAddressSccuess() {
        ToastUtils.showToast("添加地址成功");
        EventBus.getDefault().post(new CommonEvent(EventConstant.CHANGE_ADDRESS));
        finish();
    }

    @Override
    public void updateUserAddressSccuess() {
        ToastUtils.showToast("修改地址成功");
        EventBus.getDefault().post(new CommonEvent(EventConstant.CHANGE_ADDRESS));
        finish();
    }


    private void selectAddress() {
        hideSoftKeyboard();

        CityPicker cityPicker = new CityPicker.Builder(this)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                //.titleTextColor("#696969")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("浙江省")
                .city("杭州市")
                .district("西湖区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                mProvince = citySelected[0];
                //城市
                mCity = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                mDistrict = citySelected[2];
                //邮编
                mYoubian = citySelected[3];
                //为TextView赋值
                et_youbian.setText(mYoubian);
                tv_address.setText(mProvince.trim() + "-" + mCity.trim() + "-" + mDistrict.trim());

            }
        });
    }

}
