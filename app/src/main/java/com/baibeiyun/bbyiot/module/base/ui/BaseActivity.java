package com.baibeiyun.bbyiot.module.base.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.base.BaseAppManager;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.PermissionUtil;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.utils.WDevice;
import com.baibeiyun.bbyiot.view.loading.DialogControl;
import com.baibeiyun.bbyiot.view.loading.WaitDialog;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements DialogControl, IView {

    protected P mPresenter;
    protected Activity mActivity;


    private boolean _isVisible;
    protected WaitDialog _waitDialog;

    protected Toolbar mToolbar;
    protected TextView tvTitle;

    private float mScreenDensity;
    private int mScreenHeight;
    private int mScreenWidth;

    private String TAG_LOG;

    protected InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            Bundle extras = getIntent().getExtras();
            if (null != extras) {
                getBundleExtras(extras);
            }

            mActivity = this;
            TAG_LOG = this.getClass().getSimpleName();

            BaseAppManager.getInstance().addActivity(this);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            mScreenDensity = displayMetrics.density;
            mScreenHeight = displayMetrics.heightPixels;
            mScreenWidth = displayMetrics.widthPixels;


            if (getContentViewLayoutID() != 0) {
                setContentView(getContentViewLayoutID());
            } else {
                throw new IllegalArgumentException("You must return a right contentView layout resource Id");
            }

            mPresenter = getPresenter();
            beforeSetContentView();

            initViewsAndEvents();


            if (!isTaskRoot()) {
                Intent intent = getIntent();
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                    finish();
                    return;
                }
            }
            inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            _isVisible = true;

        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }


    @Override
    public void setContentView(int layoutResID) {
        try {
            super.setContentView(layoutResID);
            ButterKnife.bind(this);

            _isVisible = true;
            mToolbar = ButterKnife.findById(this, R.id.common_toolbar);
            if (null != mToolbar) {
                mToolbar.setTitle("");

                //mToolbar.setNavigationIcon(R.mipmap.icon_common_back);
                mToolbar.setNavigationIcon(R.mipmap.ic_back_left_white);
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                tvTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
            }
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    public void setActionBarTitle(String msg) {
        if (tvTitle != null && msg != null) {
            tvTitle.setText(msg);
        }
    }


    @Override
    protected void onDestroy() {
        WDevice.hideSoftKeyboard(this, getCurrentFocus());

        _isVisible = false;
        hideWaitDialog();
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected void beforeSetContentView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    private PermissionUtil.RequestPermissionGroupCallBack permissionCallBackGroup; //子类设置permissionCallBack用来监听
    private PermissionUtil.RequestPermissionSingleCallBack permissionCallBackSingle;//单独申请权限

    public void setPermissionCallBackSingle(PermissionUtil.RequestPermissionSingleCallBack permissionCallBackSingle) {
        this.permissionCallBackSingle = permissionCallBackSingle;
    }

    public void setPermissionCallBackGroup(PermissionUtil.RequestPermissionGroupCallBack permissionCallBackGroup) {
        this.permissionCallBackGroup = permissionCallBackGroup;
    }


    protected abstract int getContentViewLayoutID();

    protected abstract void getBundleExtras(Bundle extras);

    protected abstract void initViewsAndEvents();

    protected abstract P getPresenter();


    /**
     * 隐藏键盘
     */
    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public boolean isShowing() {
        if (_waitDialog != null) {
            return _waitDialog.isShowing();
        }
        return false;
    }

    @Override
    public void showLoading(String msg) {
        showWaitDialog(msg);
    }

    @Override
    public void hideLoading() {
        hideWaitDialog();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public WaitDialog showWaitDialog() {
        return showWaitDialog(R.string.common_loading_message);
    }

    @Override
    public WaitDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    @Override
    public WaitDialog showWaitDialog(String message) {
        if (_isVisible) {
            if (_waitDialog == null) {
                _waitDialog = getWaitDialog(this, message);
            }
            if (_waitDialog != null) {
                _waitDialog.setMessage(message);
                _waitDialog.show();
            }
            return _waitDialog;
        }
        return null;
    }

    @Override
    public void hideWaitDialog() {
        if (_isVisible && _waitDialog != null) {
            try {
                _waitDialog.dismiss();
                _waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private WaitDialog getWaitDialog(Activity activity, String message) {
        WaitDialog dialog = null;
        try {
            dialog = new WaitDialog(activity, R.style.dialog_waiting);
            dialog.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dialog;
    }


}
