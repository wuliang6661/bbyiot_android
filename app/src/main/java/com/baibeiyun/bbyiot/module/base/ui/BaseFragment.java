/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baibeiyun.bbyiot.module.base.ui;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.view.IView;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.loading.DialogControl;
import com.baibeiyun.bbyiot.view.loading.WaitDialog;

import butterknife.ButterKnife;


public abstract class BaseFragment<P extends IPresenter> extends Fragment implements DialogControl, IView {

    protected P mPresenter;

    protected View mView;
    protected Activity mContext;

    private boolean _isVisible;
    private WaitDialog _waitDialog;


    @Override
    public void onAttach(Activity activity) {
        if (activity instanceof BaseActivity) {
            mContext = activity;
        }
        super.onAttach(activity);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getContentViewLayoutID(), null);

        _isVisible = true;

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }


        initInstanceState(savedInstanceState);
        initViewsAndEvents();

        _isVisible = true;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mPresenter != null) {
            mPresenter.detachView();
        }

        _isVisible = false;
        //ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }


    protected abstract int getContentViewLayoutID();

    protected abstract void initViewsAndEvents();

    protected void initInstanceState(Bundle savedInstanceState) {
    }

    protected abstract P getPresenter();


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
                _waitDialog = getWaitDialog(getActivity(), message);
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
