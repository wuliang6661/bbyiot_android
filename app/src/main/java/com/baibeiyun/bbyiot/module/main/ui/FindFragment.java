package com.baibeiyun.bbyiot.module.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.model.Response.GoodsReviewResponse;
import com.baibeiyun.bbyiot.model.Response.GoodsShopResponse;
import com.baibeiyun.bbyiot.module.WebActivity;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.findshop.ui.ShopEvaluationActivity;
import com.baibeiyun.bbyiot.module.findshop.ui.ShopHardwareActivity;
import com.baibeiyun.bbyiot.module.main.adapter.DeviceHotAdapter;
import com.baibeiyun.bbyiot.module.main.adapter.FindAdapter;
import com.baibeiyun.bbyiot.module.main.adapter.YingJianAdapter;
import com.baibeiyun.bbyiot.module.main.contract.FindContract;
import com.baibeiyun.bbyiot.module.main.presenter.FindPresenter;
import com.baibeiyun.bbyiot.utils.ActivityUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.view.CustomGalleryView;
import com.baibeiyun.bbyiot.view.NOScrollGirdview;
import com.baibeiyun.bbyiot.view.pullableview.PullToRefreshLayout;
import com.baibeiyun.bbyiot.view.pullableview.PullableListView;
import com.baibeiyun.bbyiot.view.pullableview.special.MyPullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FindFragment extends BaseFragment<FindPresenter> implements FindContract.View {

    @butterknife.BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    private android.widget.TextView tvTitle;

    @BindView(R.id.frg_find_listview)
    PullableListView listView;

    @BindView(R.id.frg_find_mypulltorefreshlayout)
    MyPullToRefreshLayout pullToRefreshLayout;

    private CustomGalleryView customGalleryView;
    private NOScrollGirdview girdview;
    private NOScrollGirdview girdviewHot;
    private LinearLayout ll_dot;
    private DeviceHotAdapter deviceHotAdapter;
    private int pageNum = 1;
    private int pageSize = 10;

    private int pageCount = 0;
    private List<GoodsShopResponse> dataList = new ArrayList<>();

    private LinearLayout mGoodsReviewsLayoutFirst;
    private RelativeLayout mGoodsReviewsLayoutSecond;
    private RelativeLayout mGoodsReviewsLayoutThird;
    private TextView mGoodsReviewsNameFirst;
    //    private TextView mGoodsReviewsHintFirst;
    private ImageView mGoodsReviewsImgFirst;
    private TextView mGoodsReviewsNameSecond;
    //    private TextView mGoodsReviewsHintSecond;
    private ImageView mGoodsReviewsImgSecond;
    private TextView mGoodsReviewsNameThird;
    //    private TextView mGoodsReviewsHintThird;
    private ImageView mGoodsReviewsImgThird;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_find;
    }

    @Override
    protected void initViewsAndEvents() {
        try {
            tvTitle = mToolbar.findViewById(R.id.tv_title);
            tvTitle.setText("发现");

            View view_header_find = View.inflate(getActivity(), R.layout.view_header_find, null);
            customGalleryView = view_header_find.findViewById(R.id.view_head_find_callery_view);
            ll_dot = (LinearLayout) view_header_find.findViewById(R.id.view_head_find_lt_dot);
            girdview = view_header_find.findViewById(R.id.view_hrader_find_girdview);
            girdviewHot = view_header_find.findViewById(R.id.view_hrader_find_girdview_hot);

            //硬件测评
            mGoodsReviewsLayoutFirst = view_header_find.findViewById(R.id.goods_reviews_layout_first);
            mGoodsReviewsLayoutSecond = view_header_find.findViewById(R.id.goods_reviews_layout_second);
            mGoodsReviewsLayoutThird = view_header_find.findViewById(R.id.goods_reviews_layout_third);
            mGoodsReviewsNameFirst = view_header_find.findViewById(R.id.goods_reviews_name_first);
//            mGoodsReviewsHintFirst = view_header_find.findViewById(R.id.goods_reviews_hint_first);
            mGoodsReviewsImgFirst = view_header_find.findViewById(R.id.goods_reviews_img_first);
            mGoodsReviewsNameSecond = view_header_find.findViewById(R.id.goods_reviews_name_second);
//            mGoodsReviewsHintSecond = view_header_find.findViewById(R.id.goods_reviews_hint_second);
            mGoodsReviewsImgSecond = view_header_find.findViewById(R.id.goods_reviews_img_second);
            mGoodsReviewsNameThird = view_header_find.findViewById(R.id.goods_reviews_name_third);
//            mGoodsReviewsHintThird = view_header_find.findViewById(R.id.goods_reviews_hint_third);
            mGoodsReviewsImgThird = view_header_find.findViewById(R.id.goods_reviews_img_third);

            //搜索
            view_header_find.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityUtils.startWebActivity(getContext(), IConstant.BASE_SHOP_SEARCH_URL);
                }
            });
            view_header_find.findViewById(R.id.view_hrader_find_hot).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityUtils.startActivity(getActivity(), ShopHardwareActivity.class);
                }
            });
            view_header_find.findViewById(R.id.view_hrader_find_rl_yingjian).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityUtils.startActivity(getActivity(), ShopHardwareActivity.class);
                }
            });

            //<!---软件--->
            view_header_find.findViewById(R.id.web_software_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.startWebActivity(getContext(), IConstant.BASE_SHOP_SOFTWARE_LIST_URL);
                }
            });
            view_header_find.findViewById(R.id.icon_find_ruanjian_pingtai).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.startWebActivity(getContext(), IConstant.BASE_SHOP_SOFTWARE_URL + "1");
                }
            });
            view_header_find.findViewById(R.id.icon_find_ruanjian_fangan).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.startWebActivity(getContext(), IConstant.BASE_SHOP_SOFTWARE_URL + "2");
                }
            });
            view_header_find.findViewById(R.id.icon_find_ruanjian_gongye).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.startWebActivity(getContext(), IConstant.BASE_SHOP_SOFTWARE_URL + "3");
                }
            });
            //<!---硬件测评--->
            view_header_find.findViewById(R.id.shop_evaluation_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.startActivity(getActivity(), ShopEvaluationActivity.class);
                }
            });
            deviceHotAdapter = new DeviceHotAdapter(getActivity());
            girdviewHot.setAdapter(deviceHotAdapter);
            girdview.setAdapter(new YingJianAdapter(getActivity()));

            listView.addHeaderView(view_header_find);

            pullToRefreshLayout.setOnRefreshListener(new MyPullToRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh(MyPullToRefreshLayout pullToRefreshLayout) {
                    mPresenter.getHostDecive(1, 8, false, true);
                    mPresenter.getGoodsReviewList(true);
                    pullToRefreshLayout.refreshFinish(MyPullToRefreshLayout.SUCCEED);
                }

                @Override
                public void onLoadMore(MyPullToRefreshLayout pullToRefreshLayout) {
                    if (pageCount < pageSize) {
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.NO_LOAD_MORE);
                    } else {
                        pageNum++;
                        mPresenter.getHostDecive(pageNum, pageSize, false, true);
                    }
                }
            });
            FindAdapter adapter = new FindAdapter(getActivity());
            listView.setAdapter(adapter);

            mPresenter.getHostDecive(pageNum, pageSize, false, false);
            mPresenter.getGoodsReviewList(false);
            loadTopBanner(null);

        } catch (Exception e) {
            com.baibeiyun.bbyiot.utils.LogUtils.w(e.toString());
        }
    }

    @Override
    protected FindPresenter getPresenter() {
        return new FindPresenter(getActivity());
    }


    /**
     * 启动轮播图
     */
    private int[] defaultImageList = new int[]{R.mipmap.icon_find_banner01, R.mipmap.icon_find_banner01, R.mipmap.icon_find_banner01};

    private void loadTopBanner(String[] str) {

        customGalleryView.start(getActivity(), str,
                defaultImageList, 3000, ll_dot, R.mipmap.banner_point_press,
                R.mipmap.banner_empty_point);

        /**
         * 添加ViewPager点击事件的监听
         * */
        customGalleryView.setCustomGalleryOnItemClickListener(new CustomGalleryView.CustomGalleryOnItemClickListener() {
            @Override
            public void onItemClick(int curIndex) {
                try {

                } catch (Exception e) {
                    LogUtils.w(e.getMessage());
                }

            }
        });
    }

    /**
     * 热销商品
     *
     * @param list
     * @param isRefresh
     * @param isLoadmore
     */
    @Override
    public void getHostDeciveFinish(List<GoodsShopResponse> list, boolean isRefresh, boolean isLoadmore) {
        try {

            if (list != null) {
                pageCount = list.size();
                LogUtils.w(" list.size==  " + list.size() + " ,pageCount == " + pageCount);
                if (isLoadmore) {
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                } else {
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
                dataList.clear();
                dataList.addAll(list);
                LogUtils.w(" dataList.size==  " + dataList.size());
                deviceHotAdapter.updata(dataList);
            } else {
                if (isRefresh) {
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
                } else {
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
                }
            }


        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    @Override
    public void getGoodsReviewList(final List<GoodsReviewResponse> list) {
        try {
            if (list != null) {
                if (list.get(0) != null) {
                    mGoodsReviewsNameFirst.setText(list.get(0).getName());
//                    mGoodsReviewsHintFirst.setText(list.get(0).getTitle());
                    BaseApplication.loadImageView(mGoodsReviewsImgFirst, list.get(0).getImage());
                    mGoodsReviewsLayoutFirst.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startWeb(list.get(0).getId());
                        }
                    });
                }
                if (list.get(1) != null) {
                    mGoodsReviewsNameSecond.setText(list.get(1).getName());
//                    mGoodsReviewsHintSecond.setText(list.get(1).getTitle());
                    BaseApplication.loadImageView(mGoodsReviewsImgSecond, list.get(1).getImage());
                    mGoodsReviewsLayoutSecond.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startWeb(list.get(1).getId());
                        }
                    });
                }
                if (list.get(2) != null) {
                    mGoodsReviewsNameThird.setText(list.get(2).getName());
//                    mGoodsReviewsHintThird.setText(list.get(2).getTitle());
                    BaseApplication.loadImageView(mGoodsReviewsImgThird, list.get(2).getImage());
                    mGoodsReviewsLayoutThird.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startWeb(list.get(2).getId());
                        }
                    });
                }
            }
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    private void startWeb(long id) {
        Intent intent = new Intent(getActivity(), WebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(IConstant.WEB_URL, IConstant.BASE_SHOP_DETAIL_URL + id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
