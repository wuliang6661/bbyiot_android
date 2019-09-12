package com.baibeiyun.bbyiot.view.deliverytime;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.utils.DateUtils;
import com.baibeiyun.bbyiot.utils.SpUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更改封面对话框
 * 
 * @author ywl
 * 
 */
public class ChangeTimeDialog extends Dialog implements
		View.OnClickListener {

	private WheelView wvProvince;
	private WheelView wvCitys;
	private View lyChangeAddress;
	private View lyChangeAddressChild;
	private TextView btnSure;
	private TextView btnCancel;

	private Context context;
	private JSONObject mJsonObj;
	private String[] mProvinceDatas;
	/** 预售 */
	private List<String> listS = new ArrayList<String>();

	private List<String> list = new ArrayList<String>();

	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	// private Map<String, String[]> mCitisDatasMapp = new HashMap<String,
	// String[]>();
	/** 城市标题 */
	private LinearLayout ll_city;
	/** 时间标题 */
	private LinearLayout ll_time;

	private ArrayList<String> arrProvinces = new ArrayList<String>();

	private ArrayList<String> arrCitys = new ArrayList<String>();
	// private AddressTextAdapter provinceAdapter;
	private AddressTextAdapter provinceAdapter;
	private AddressTextAdapter cityAdapter;

	private String strProvince = "";
	private String strCity = "";
	private OnAddressCListener onAddressCListener;
	/** 是（城市）否（时间） */
	private Boolean tagBoolean;
	private int maxsize = 24;
	private int minsize = 14;
	private String strsub2;
	private String strsub1;
	private TextView tv_yy;
	private String openTime;
	private PropertiesCityEntity propertiesCityEntity;

	public ChangeTimeDialog(Context context,
			Map<String, String[]> mCitisDatasMap, String[] mProvinceDatas,
			Boolean tagBoolean) {
		super(context, R.style.ShareDialog);
		this.context = context;
		this.mCitisDatasMap = mCitisDatasMap;
		this.mProvinceDatas = mProvinceDatas;
		this.tagBoolean = tagBoolean;

	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_myinfo_changeaddress);

		// if(mCitisDatasMap==null&&mProvinceDatas==null){
		//
		//
		// for(int i=0;i<propertiesCityEntity.getD().size();i++){
		//
		// for(int
		// j=0;j<propertiesCityEntity.getD().get(i).getCitys().size();j++){
		//
		// }
		// }
		//
		//
		//
		// }

		String defVal = "";
		openTime = SpUtils.getString( "opentime",
				defVal);
		// .toString();
		wvProvince = (WheelView) findViewById(R.id.wv_address_province);
		wvCitys = (WheelView) findViewById(R.id.wv_address_city);
		lyChangeAddress = findViewById(R.id.ly_myinfo_changeaddress);
		lyChangeAddressChild = findViewById(R.id.ly_myinfo_changeaddress_child);
		btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
		btnCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);
		tv_yy = (TextView) findViewById(R.id.tv_yy);
		ll_city = (LinearLayout) findViewById(R.id.ll_city);
		ll_time = (LinearLayout) findViewById(R.id.ll_times);

		if (tagBoolean) {
			ll_city.setVisibility(View.VISIBLE);
			ll_time.setVisibility(View.GONE);
		} else {
			ll_city.setVisibility(View.GONE);
			ll_time.setVisibility(View.VISIBLE);
		}

		lyChangeAddress.setOnClickListener(this);
		lyChangeAddressChild.setOnClickListener(this);
		btnSure.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		tv_yy.setText("（超市营业时间： " + openTime + " ）");
		// if(true){ //正常
		// initJsonData();//取文件
		// initDatas();//解析数据
		// }else{
		// //预售

		// initJsonDataTow();//取文件
		// initDatasTow();//解析数据

		// }

		initProvinces();

		provinceAdapter = new AddressTextAdapter(context, arrProvinces,
				getProvinceItem(strProvince), maxsize, minsize);
		wvProvince.setVisibleItems(5);
		wvProvince.setViewAdapter(provinceAdapter);
		wvProvince.setCurrentItem(getProvinceItem(strProvince));

		initCitys(mCitisDatasMap.get(strProvince));
		cityAdapter = new AddressTextAdapter(context, arrCitys,
				getCityItem(strCity), maxsize, minsize);
		wvCitys.setVisibleItems(5);
		wvCitys.setViewAdapter(cityAdapter);
		wvCitys.setCurrentItem(getCityItem(strCity));

		wvProvince.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) provinceAdapter.getItemText(wheel
						.getCurrentItem());
				strProvince = currentText;
				setTextviewSize(currentText, provinceAdapter);
				String[] citys = mCitisDatasMap.get(currentText);
				initCitys(citys);
				cityAdapter = new AddressTextAdapter(context, arrCitys, 0,
						maxsize, minsize);
				wvCitys.setVisibleItems(5);
				wvCitys.setViewAdapter(cityAdapter);
				wvCitys.setCurrentItem(0);
			}
		});

		wvProvince.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) provinceAdapter.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, provinceAdapter);
			}
		});

		wvCitys.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) cityAdapter.getItemText(wheel
						.getCurrentItem());
				strCity = currentText;
				setTextviewSize(currentText, cityAdapter);
			}
		});

		wvCitys.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) cityAdapter.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, cityAdapter);
			}
		});
	}

	private class AddressTextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected AddressTextAdapter(Context context, ArrayList<String> list,
				int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem,
					maxsize, minsize);
			// super(t);
			this.list = list;
			setItemTextResource(R.id.tempValue);

		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
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

	private String strsub3;
	private boolean a;
	private String strsub11;
	private String strsub22;

	@Override
	public void onClick(View v) {

		if (v == btnSure) {
			String openTimea = openTime.replace(":", "");
			openTimea = openTimea.replace("-", "");
			String citya = strCity.replace(":", "");
			if (citya.equals("立即配送")) {
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
				Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
				String str = formatter.format(curDate);
				strsub22 = str.replace(":", "");
			} else {
				citya = citya.replace("-", "");

				// 后四位
				if (citya.length() >= 4) {// 判断是否长度大于等于4
					strsub22 = citya.substring(citya.length() - 4,
							citya.length());// 截取两个数字之间的部分
				}

			}
			if (openTimea.length() >= 4) {// 判断是否长度大于等于4
				strsub11 = openTimea.substring(openTimea.length() - 4,
						openTimea.length());// 截取两个数字之间的部分
			}
			//

			if (strsub22.equals("及时送达")) {
				strsub22 = strsub11;
			} else if (strProvince.equals("今天")
					&& (Integer.parseInt(strsub22) >= Integer
							.parseInt(strsub11))) {
				// 判断是否超过营业时间
				ToastUtils.showToast( "超过营业时间，默认第二天及时送达");
			}
			if (onAddressCListener != null) {
				// 判断是否超过营业时间
				onAddressCListener.onClick(strProvince, strCity);
			}

		} else if (v == btnCancel) {

		} else if (v == lyChangeAddressChild) {
			return;
		} else {
			dismiss();
		}
		dismiss();

	}

	/**
	 * 回调接口
	 * 
	 * @author Administrator
	 * 
	 */
	public interface OnAddressCListener {
		void onClick(String province, String city);
	}

	/**
	 * 从文件中读取地址数据
	 */
	private void initJsonData() {
	}

	/**
	 * 初始化省会
	 */
	public void initProvinces() {
		int length = mProvinceDatas.length;
		// int length = listS.size();
		for (int i = 0; i < length; i++) {
			arrProvinces.add(mProvinceDatas[i]);
			// arrProvinces.add(listS.get(i));
		}
	}

	/**
	 * 根据省会，生成该省会的所有城市
	 * 
	 * @param citys
	 */
	public void initCitys(String[] citys) {
		if (citys != null) {
			arrCitys.clear();
			int length = citys.length;
			for (int i = 0; i < length; i++) {
				arrCitys.add(citys[i]);
			}
		} else {
			String[] city = mCitisDatasMap.get(mProvinceDatas[0]);
			arrCitys.clear();
			int length = city.length;
			for (int i = 0; i < length; i++) {
				arrCitys.add(city[i]);
			}
		}
		if (arrCitys != null && arrCitys.size() > 0
				&& !arrCitys.contains(strCity)) {
			strCity = arrCitys.get(0);
		}
	}

	/**
	 * 初始化地点
	 * 
	 * @param province
	 * @param city
	 */
	public void setAddress(String province, String city) {
		if (province != null && province.length() > 0) {
			this.strProvince = province;
		}
		if (city != null && city.length() > 0) {
			this.strCity = city;
		}
	}

	/**
	 * 返回他、日期索引，没有就返回默认“今天”
	 * 
	 * @param province
	 * @return
	 */
	public int getProvinceItem(String province) {
		int size = arrProvinces.size();
		int provinceIndex = 0;
		boolean noprovince = true;
		for (int i = 0; i < size; i++) {
			if (province.equals(arrProvinces.get(i))) {
				noprovince = false;
				return provinceIndex;
			} else {
				provinceIndex++;
			}
		}
		if (noprovince) {
			// strProvince = "今天";
			return 22;
		}
		return provinceIndex;
	}

	/**
	 * 得到时间索引，没有返回默认“09：00”
	 * 
	 * @param
	 * @return
	 */
	public int getCityItem(String city) {
		int size = arrCitys.size();
		int cityIndex = 0;
		boolean nocity = true;
		for (int i = 0; i < size; i++) {
			// System.out.println(arrCitys.get(i));
			if (city.equals(arrCitys.get(i))) {
				nocity = false;
				return cityIndex;
			} else {
				cityIndex++;
			}
		}
		// 判断是否是今天
		if (strProvince.equals("今天")) {
			if (nocity) {
				strCity = "及时送达";
				return 0;
			}
		} else {
			if (nocity) {
				strCity = "9:00-10:00";
				return 0;
			}
		}

		return cityIndex;
	}

	/**
	 * 切割到本地的时间
	 */
	public String getLocalTime() {
		String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:MyCallback");
		String currentTime = currentDate.substring(11, 16);
		return currentTime;

	}

}