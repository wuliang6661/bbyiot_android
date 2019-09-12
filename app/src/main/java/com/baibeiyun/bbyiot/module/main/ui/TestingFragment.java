package com.baibeiyun.bbyiot.module.main.ui;

import android.annotation.SuppressLint;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.HomeGroupsResponse;
import com.baibeiyun.bbyiot.model.Response.TestingResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.main.adapter.TestingGroupAdapter;
import com.baibeiyun.bbyiot.module.main.contract.TestingContract;
import com.baibeiyun.bbyiot.module.main.presenter.TestingPresenter;
import com.baibeiyun.bbyiot.module.testing.ui.SelectGroupActivity;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.view.DefindTextviewListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

public class TestingFragment extends BaseFragment<TestingPresenter> implements TestingContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.frg_testing_listview)
    ListView listView;


    //@BindView(R.id.frg_testing_tv_seclect_group_name)
    TextView tv_seclect_group_name;

    //@BindView(R.id.frg_testing_ll_selcet_group)
    ViewGroup ll_selcet_group;

    //@BindView(R.id.view_head_device_state_tv_search)
    TextView tv_search;

    private TextView tvTitle;
    private TestingGroupAdapter testingGroupAdapter;
    private List<TestingResponse> dataList;

    private EditText et_search;
    private View view_header_testing;
    private String mSelectGroupId = null;
    private String mQueryKeyword = null;
    private DefindTextviewListener mListener;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_testing;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initViewsAndEvents() {
        try {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
            tvTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
            tvTitle.setText("监测列表");


            view_header_testing = View.inflate(getActivity(), R.layout.view_header_testing, null);
            tv_seclect_group_name = view_header_testing.findViewById(R.id.frg_testing_tv_seclect_group_name);
            ll_selcet_group = view_header_testing.findViewById(R.id.frg_testing_ll_selcet_group);
            et_search = view_header_testing.findViewById(R.id.view_head_device_state_et_search);

            view_header_testing.findViewById(R.id.iv_query)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mQueryKeyword = et_search.getText().toString();
                            if (!StringUtils.isEmpty(mQueryKeyword)) {
                                mPresenter.getTestingList(false, mSelectGroupId, mQueryKeyword);
                            }
                        }
                    });

            listView.addHeaderView(view_header_testing);

            ll_selcet_group.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityUtils.startActivity(getActivity(), SelectGroupActivity.class);
                }
            });

            mListener = new DefindTextviewListener() {
                @Override
                public void afterTextChanged(Editable editable) {
                    String text = editable.toString().trim();
                    if (StringUtils.isEmpty(text)) {
                        mQueryKeyword = null;
                        mPresenter.getTestingList(false, mSelectGroupId, mQueryKeyword);
                    }
                }
            };
            et_search.addTextChangedListener(mListener);


            testingGroupAdapter = new TestingGroupAdapter(getActivity(), R.layout.item_testing_group);

            listView.setAdapter(testingGroupAdapter);

            testingGroupAdapter.setIsShowGroupTittle(true);
            mPresenter.getTestingList(false, mSelectGroupId, mQueryKeyword);

        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    @Override
    protected TestingPresenter getPresenter() {
        return new TestingPresenter(getActivity());
    }


    @Override
    public void getTestingListFinish(List<TestingResponse> list, boolean isRefresh) {
        dataList = list;
        testingGroupAdapter.update(dataList);
    }

    @Subscribe
    public void sclectGroup(HomeGroupsResponse homeGroupsResponse) {

        if (homeGroupsResponse != null) {
            if ("全部".equals(homeGroupsResponse.getName())) {
                tv_seclect_group_name.setText("全部分组");

                testingGroupAdapter.update(dataList);

                testingGroupAdapter.setIsShowGroupTittle(true);

            } else {
                tv_seclect_group_name.setText(homeGroupsResponse.getName());

                mSelectGroupId = homeGroupsResponse.getId() + "";
                mPresenter.getTestingList(false, mSelectGroupId, mQueryKeyword);
                testingGroupAdapter.update(dataList);
                testingGroupAdapter.setIsShowGroupTittle(false);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
