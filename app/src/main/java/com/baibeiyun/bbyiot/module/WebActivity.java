package com.baibeiyun.bbyiot.module;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.model.DataManager;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.findshop.ui.PaySuccessActivity;
import com.baibeiyun.bbyiot.utils.PayUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;

import butterknife.BindView;
import cn.leo.messenger.MagicMessenger;
import cn.leo.messenger.MessageCallback;

import static com.baibeiyun.bbyiot.common.IConstant.WEB_URL;

public class WebActivity extends BaseActivity {


    @BindView(R.id.container)
    LinearLayout mLinearLayout;
//    @BindView(R.id.tool_bar)
//    Toolbar mToolbar;
//    @BindView(R.id.tool_bar_title)
//    TextView mTitleTextView;

    private AgentWeb mAgentWeb;
    private AlertDialog mAlertDialog;
    private String url;
    private String mOrderId;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_web;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        url = extras.getString(WEB_URL);
        if (IConstant.BASE_SHOP_SOFTWARE_LIST_URL.equals(url) || IConstant.BASE_SHOP_SEARCH_URL.equals(url)) {
            url = url + "?token=" + DataManager.getsInstance().getToken() + "&platform=android";
        } else {
            url = url + "&token=" + DataManager.getsInstance().getToken() + "&platform=android";
        }
        Log.d("url", "url ====" + url);
    }

    @Override
    protected void initViewsAndEvents() {
//        mToolbar.setTitle("");
//        this.setSupportActionBar(mToolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
//                .setWebLayout(new WebLayout(this))
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go(url);
        mAgentWeb.getJsInterfaceHolder().addJavaObject("android", new AndroidInterface(mAgentWeb, this));

        MagicMessenger.subscribe(IConstant.PAYRESULT, new MessageCallback() {
            @Override
            public void onMsgCallBack(Bundle bundle) {
                if (bundle != null) {
                    int code = bundle.getInt("code");
                    if (code == 0) {
                        finish();
                        Intent intent = new Intent(WebActivity.this, PaySuccessActivity.class);
                        Bundle b = new Bundle();
                        b.putString("id", mOrderId);
                        intent.putExtras(b);
                        startActivity(intent);
                    } else {
                        ToastUtils.showToast("支付失败");
                    }
                }
            }
        });
    }

    private com.just.agentweb.WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
            Log.i("Info", "BaseWebActivity onPageStarted");
        }
    };

    private com.just.agentweb.WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
//            if (mTitleTextView != null) {
//                mTitleTextView.setText(title);
//            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("Info", "onResult:" + requestCode + " onResult:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    public class AndroidInterface {

        private AgentWeb mAgentWeb;
        private Context mContext;
        private Handler mainHandler = new Handler(Looper.getMainLooper());

        public AndroidInterface(AgentWeb mAgentWeb, Context mContext) {
            this.mAgentWeb = mAgentWeb;
            this.mContext = mContext;
        }

        @JavascriptInterface
        public void pay(final String id) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    mOrderId = id;
                    PayUtils.pay(id, WebActivity.this);
                }
            });
        }


        @JavascriptInterface
        public void finish() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    WebActivity.this.finish();
                }
            });
        }
    }
}
