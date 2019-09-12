package com.baibeiyun.bbyiot.module.mine.ui.order;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.mine.adapter.TabFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderActivity extends BaseActivity {

    public static final String KEY_ORDER_TYPE = "key_order_type";
    @BindView(R.id.act_order_tablayout)
    TabLayout tabLayout;

    @BindView(R.id.act_order_viewpager)
    ViewPager viewpager;

    private int mOrderType = 0;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_order;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mOrderType = extras.getInt(KEY_ORDER_TYPE);
    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("我的订单");
        initTablayout();
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    /**
     * 初始化tab的内容
     */
    List<Fragment> frag_list = new ArrayList<>();

    private void initTablayout() {
        //tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);//
        //tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);//

        List<String> tabs = new ArrayList<>();
        tabs.add("全部");
        tabs.add("待付款");
        tabs.add("待发货");
        tabs.add("待收货");
        tabs.add("待评价");
        tabs.add("已取消");
        tabs.add("已完成");
        //-1全部，0待付款，1待发货，2待收货，3待评价，4已完成，5已取消
        frag_list.add(OrderFragement.newInstance(0, -1));
        frag_list.add(OrderFragement.newInstance(1, 0));
        frag_list.add(OrderFragement.newInstance(2, 1));

        frag_list.add(OrderFragement.newInstance(3, 2));
        //待评价
        frag_list.add(OrderFragement.newInstance(4, 3));

        frag_list.add(OrderFragement.newInstance(4, 5));
        frag_list.add(OrderFragement.newInstance(4, 4));
        TabFragmentAdapter adapter = new TabFragmentAdapter(getSupportFragmentManager(), frag_list, tabs);

        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);

        viewpager.setCurrentItem(mOrderType);
    }
}
