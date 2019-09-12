package com.baibeiyun.bbyiot.module.home.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.view.deliverytime.AbstractWheelTextAdapter;
import com.baibeiyun.bbyiot.view.deliverytime.OnWheelChangedListener;
import com.baibeiyun.bbyiot.view.deliverytime.OnWheelScrollListener;
import com.baibeiyun.bbyiot.view.deliverytime.WheelView;

import java.util.ArrayList;

/**
 * 店铺设置更改上下班时间
 */
public class SelectWorkTimeDialog extends Dialog implements
		View.OnClickListener {

	private WheelView wheelview_1;
	private WheelView wheelview_2;
	private LinearLayout ll_root;
	private LinearLayout ll_content;
	private TextView tv_ensure;
	private TextView tv_cancel;

	private Activity activity;

	// 数据源
	private ArrayList<String> data1;
	private ArrayList<String> data2;

	// 两个适配器
	private AddressTextAdapter adapter1;
	private AddressTextAdapter adapter2;

	// 两个选择的时间
	private String text1;
	private String text2;

	private OnAddressCListener onAddressCListener;

	private int maxsize = 24;
	private int minsize = 14;

	public SelectWorkTimeDialog(Activity activity, ArrayList<String> data1,
                                ArrayList<String> data2, String text1, String text2) {
		super(activity, R.style.ShareDialog);
		this.activity = activity;
		this.data1 = data1;
		this.data2 = data2;
		this.text1 = text1;
		this.text2 = text2;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_select_work_time);
		wheelview_1 = (WheelView) findViewById(R.id.wheelview_1);
		wheelview_2 = (WheelView) findViewById(R.id.wheelview_2);
		ll_root = (LinearLayout) findViewById(R.id.ll_root);
		ll_content = (LinearLayout) findViewById(R.id.ll_content);
		tv_ensure = (TextView) findViewById(R.id.tv_ensure);
		tv_cancel = (TextView) findViewById(R.id.tv_cancel);

		ll_root.setOnClickListener(this);
		ll_content.setOnClickListener(this);
		tv_ensure.setOnClickListener(this);
		tv_cancel.setOnClickListener(this);

		adapter1 = new AddressTextAdapter(activity, data1,
				data1.indexOf(text1), maxsize, minsize);
		wheelview_1.setVisibleItems(5);// 设置可见的数量
		wheelview_1.setViewAdapter(adapter1);
		wheelview_1.setCurrentItem(data1.indexOf(text1));// 设置初始值

		adapter2 = new AddressTextAdapter(activity, data2,
				data2.indexOf(text2), maxsize, minsize);
		wheelview_2.setVisibleItems(5);// 设置可见的数量
		wheelview_2.setViewAdapter(adapter2);
		wheelview_2.setCurrentItem(data2.indexOf(text2));// 设置初始值

		wheelview_1.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String currentText = (String) adapter1.getItemText(wheel
						.getCurrentItem());
				text1 = currentText;
				setTextviewSize(currentText, adapter1);
			}
		});

		wheelview_1.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) adapter1.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, adapter1);
			}
		});

		wheelview_2.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {

				String currentText = (String) adapter2.getItemText(wheel
						.getCurrentItem());
				text2 = currentText;
				setTextviewSize(currentText, adapter2);
			}
		});

		wheelview_2.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) adapter2.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, adapter2);
			}
		});

	}

	private class AddressTextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> data = new ArrayList<String>();

		protected AddressTextAdapter(Context context, ArrayList<String> list,
                                     int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem,
					maxsize, minsize);
			// super(t);
			// getSearchHistory=new ArrayList<String>();
			data.addAll(list);
			setItemTextResource(R.id.tempValue);

		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return data.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return data.get(index);
		}

	}

	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText,
			AddressTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(24);
			} else {
				textvew.setTextSize(14);
			}
		}
	}

	public void setAddresskListener(OnAddressCListener onAddressCListener) {
		this.onAddressCListener = onAddressCListener;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.tv_ensure:// 确定
			onAddressCListener.onClick(text1, text2);
			dismiss();
			break;
		case R.id.tv_cancel:// 取消
			dismiss();
			break;
		case R.id.ll_root:// 根目录
			dismiss();
			break;
		case R.id.ll_content:
			break;
		}
	}

	/**
	 * 回调接口
	 * 
	 * @author Administrator
	 * 
	 */
	public interface OnAddressCListener {
		public void onClick(String text1, String text2);
	}

}