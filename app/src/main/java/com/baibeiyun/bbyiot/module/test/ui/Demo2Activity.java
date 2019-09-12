package com.baibeiyun.bbyiot.module.test.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.mine.adapter.TabFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Demo2Activity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_demo2);
        ButterKnife.bind(this);

        initTablayout();
    }

    /**
     * 初始化tab的内容
     */
    List<Fragment> frag_list = new ArrayList<>();

    private void initTablayout() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式，当前为系统默认模式
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);//
        //tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);//

        List<String> tabs = new ArrayList<>();
        tabs.add("发现1");
        tabs.add("发现2");
        tabs.add("发现3");
        frag_list.add(new ApiShowFragment());
        frag_list.add(new ApiShowFragment());
        frag_list.add(new ApiShowFragment());
        TabFragmentAdapter adapter = new TabFragmentAdapter(getSupportFragmentManager(),frag_list,tabs);

        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }
}
