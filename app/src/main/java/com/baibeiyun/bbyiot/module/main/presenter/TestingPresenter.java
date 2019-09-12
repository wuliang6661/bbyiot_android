package com.baibeiyun.bbyiot.module.main.presenter;

import android.app.Activity;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.api.BaseSubscriber;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.model.Response.TestingResponse;
import com.baibeiyun.bbyiot.model.bean.TestAllGroupBean;
import com.baibeiyun.bbyiot.model.bean.TestDataBean;
import com.baibeiyun.bbyiot.module.base.presenter.BasePresenter;
import com.baibeiyun.bbyiot.module.main.contract.TestingContract;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class TestingPresenter extends BasePresenter<TestingContract.View> implements TestingContract.Presenter {

    public TestingPresenter(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public void getTestingList(final boolean isRefresh,String groupId,String name) {
        if (!isRefresh) {
            mView.showLoading(null);
        }
        dataManager.getTestingList(groupId,name)
                .subscribe(new BaseSubscriber<BaseResponse<List<TestingResponse>>>() {
                    @Override
                    public void onFail(String errorMsg) {
                        mView.hideLoading();
                        LogUtils.w(errorMsg);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<TestingResponse>> response) {
                        mView.hideLoading();

                        mView.getTestingListFinish(response.getData(), isRefresh);
                        if (response.getData() != null) {
                            LogUtils.w("监测列表 == " + response.getData().size());
                        }
                    }
                });
    }



    public List<TestAllGroupBean> getdata() {
        List<TestAllGroupBean> list = new ArrayList<>();


        //1
        TestAllGroupBean testAllGroupBean1 = new TestAllGroupBean();
        testAllGroupBean1.setLabel("车间一");
        List<TestDataBean> list1 = new ArrayList<>();
        list1.add(new TestDataBean(R.mipmap.icon_home_device_testing_01, "气动阀门", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        list1.add(new TestDataBean(R.mipmap.icon_home_device_testing_02, "数字压力表", "HD-100C数字压力表", "2019-05-13 09:15:10", "600.00ppa", true));
        list1.add(new TestDataBean(R.mipmap.icon_home_device_testing_03, "百叶箱01", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        testAllGroupBean1.setList(list1);
        list.add(testAllGroupBean1);


        TestAllGroupBean testAllGroupBean2 = new TestAllGroupBean();
        testAllGroupBean2.setLabel("园区一");
        List<TestDataBean> list2 = new ArrayList<>();
        list2.add(new TestDataBean(R.mipmap.icon_home_device_testing_01, "气动阀门", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        list2.add(new TestDataBean(R.mipmap.icon_home_device_testing_02, "数字压力表", "HD-100C数字压力表", "2019-05-13 09:15:10", "600.00ppa", true));
        list2.add(new TestDataBean(R.mipmap.icon_home_device_testing_03, "百叶箱01", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        testAllGroupBean2.setList(list2);
        list.add(testAllGroupBean2);

        TestAllGroupBean testAllGroupBean3 = new TestAllGroupBean();
        testAllGroupBean3.setLabel("厂区二");
        List<TestDataBean> list3 = new ArrayList<>();
        list3.add(new TestDataBean(R.mipmap.icon_home_device_testing_01, "气动阀门", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        list3.add(new TestDataBean(R.mipmap.icon_home_device_testing_02, "数字压力表", "HD-100C数字压力表", "2019-05-13 09:15:10", "600.00ppa", true));
        list3.add(new TestDataBean(R.mipmap.icon_home_device_testing_03, "百叶箱01", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        testAllGroupBean3.setList(list3);
        list.add(testAllGroupBean3);


        TestAllGroupBean testAllGroupBean4 = new TestAllGroupBean();
        testAllGroupBean4.setLabel("车间二");
        List<TestDataBean> list4 = new ArrayList<>();
        list4.add(new TestDataBean(R.mipmap.icon_home_device_testing_01, "气动阀门", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        list4.add(new TestDataBean(R.mipmap.icon_home_device_testing_02, "数字压力表", "HD-100C数字压力表", "2019-05-13 09:15:10", "600.00ppa", true));
        list4.add(new TestDataBean(R.mipmap.icon_home_device_testing_03, "百叶箱01", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        testAllGroupBean4.setList(list4);
        list.add(testAllGroupBean4);

        TestAllGroupBean testAllGroupBean5 = new TestAllGroupBean();
        testAllGroupBean5.setLabel("园区二");
        List<TestDataBean> list5 = new ArrayList<>();
        list5.add(new TestDataBean(R.mipmap.icon_home_device_testing_01, "气动阀门", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        list5.add(new TestDataBean(R.mipmap.icon_home_device_testing_02, "数字压力表", "HD-100C数字压力表", "2019-05-13 09:15:10", "600.00ppa", true));
        list5.add(new TestDataBean(R.mipmap.icon_home_device_testing_03, "百叶箱01", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        testAllGroupBean5.setList(list5);
        list.add(testAllGroupBean5);

        TestAllGroupBean testAllGroupBean6 = new TestAllGroupBean();
        testAllGroupBean6.setLabel("厂区三");
        List<TestDataBean> list6 = new ArrayList<>();
        list6.add(new TestDataBean(R.mipmap.icon_home_device_testing_01, "气动阀门", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        list6.add(new TestDataBean(R.mipmap.icon_home_device_testing_02, "数字压力表", "HD-100C数字压力表", "2019-05-13 09:15:10", "600.00ppa", true));
        list6.add(new TestDataBean(R.mipmap.icon_home_device_testing_03, "百叶箱01", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        testAllGroupBean6.setList(list6);
        list.add(testAllGroupBean6);

        TestAllGroupBean testAllGroupBean7 = new TestAllGroupBean();
        testAllGroupBean7.setLabel("车间三");
        List<TestDataBean> list7 = new ArrayList<>();
        list7.add(new TestDataBean(R.mipmap.icon_home_device_testing_01, "气动阀门", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        list7.add(new TestDataBean(R.mipmap.icon_home_device_testing_02, "数字压力表", "HD-100C数字压力表", "2019-05-13 09:15:10", "600.00ppa", true));
        list7.add(new TestDataBean(R.mipmap.icon_home_device_testing_03, "百叶箱01", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        testAllGroupBean7.setList(list7);
        list.add(testAllGroupBean7);

        TestAllGroupBean testAllGroupBean8 = new TestAllGroupBean();
        testAllGroupBean8.setLabel("园区三");
        List<TestDataBean> list8 = new ArrayList<>();
        list8.add(new TestDataBean(R.mipmap.icon_home_device_testing_01, "气动阀门", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        list8.add(new TestDataBean(R.mipmap.icon_home_device_testing_02, "数字压力表", "HD-100C数字压力表", "2019-05-13 09:15:10", "600.00ppa", true));
        list8.add(new TestDataBean(R.mipmap.icon_home_device_testing_03, "百叶箱01", "二氧化碳", "2019-05-13 09:15:10", "90.00ml", true));
        testAllGroupBean8.setList(list8);
        list.add(testAllGroupBean8);


        return list;
    }

}
