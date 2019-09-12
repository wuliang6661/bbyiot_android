package com.baibeiyun.bbyiot.view.loading;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.utils.StringUtils;
import com.baibeiyun.bbyiot.utils.WDevice;


/**
 *  加载网络的
 * */
public class WaitDialog extends Dialog {
	private Context ctx;
	
	private TextView _messageTv;
	
	public WaitDialog(Context context) {
		super(context);
		init(context);
	}

	public WaitDialog(Context context, int defStyle) {
		super(context, defStyle);
		init(context);
	}

	protected WaitDialog(Context context, boolean cancelable, OnCancelListener listener) {
		super(context, cancelable, listener);
		init(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Window dialogWindow = getWindow();
		dialogWindow.setGravity(Gravity.CENTER);//设置dialog显示居中


		//dialogWindow.setWindowAnimations();设置动画效果
		//去除白色背景
		dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);

		//setContentView(R.layout.dialog_common);

		// true 点击外部Dialog消失
		setCanceledOnTouchOutside(false);

//		if (WDevice.isTablet(ctx)) {
//			int i = (int) WDevice.dpToPixel(ctx, 360F);
//			if (i < WDevice.getScreenWidth(ctx)) {
//				WindowManager.LayoutParams params = getWindow()
//						.getAttributes();
//				params.width = i;
//				getWindow().setAttributes(params);
//			}
//		}

	}

	public static boolean dismiss(WaitDialog dialog) {
		if (dialog != null) {
			dialog.dismiss();
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.dismiss();
	}

	public static void hide(Context context) {
		if (context instanceof DialogControl)
			((DialogControl) context).hideWaitDialog();
	}

	public static boolean hide(WaitDialog dialog) {
		if (dialog != null) {
			dialog.hide();
			return false;
		} else {
			return true;
		}
	}

	private void init(Context context) {
		ctx = context;
		setCancelable(false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_wait, null);
		_messageTv = (TextView) view.findViewById(R.id.waiting_tv);
		setContentView(view);
	}

	public static void show(Context context) {
		if (context instanceof DialogControl)
			((DialogControl) context).showWaitDialog();
	}

	public static boolean show(WaitDialog waitdialog) {
		boolean flag;
		if (waitdialog != null) {
			waitdialog.show();
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}



	public void setMessage(int message) {
		_messageTv.setText(message);
	}

	public void setMessage(String message) {
		if (StringUtils.isEmpty(message)){
			_messageTv.setText("加载中...");
			return;
		}

		_messageTv.setText(message);
	}
	
	public void hideMessage() {
	    _messageTv.setVisibility(View.GONE);
	}
}
