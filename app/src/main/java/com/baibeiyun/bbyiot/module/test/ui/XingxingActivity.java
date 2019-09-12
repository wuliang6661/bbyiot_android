package com.baibeiyun.bbyiot.module.test.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.example.xlhratingbar_lib.XLHRatingBar;

public class XingxingActivity extends BaseActivity {

    private XLHRatingBar xlhRatingBar;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_xingxing;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        xlhRatingBar = findViewById(R.id.ratingBar);
        xlhRatingBar.setNumStars(7);
        xlhRatingBar.setRating(5);
        xlhRatingBar.setOnRatingChangeListener(new XLHRatingBar.OnRatingChangeListener() {
            @Override
            public void onChange(float rating, int numStars) {
                Toast.makeText(getApplicationContext(), "rating:" + rating, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }
}
