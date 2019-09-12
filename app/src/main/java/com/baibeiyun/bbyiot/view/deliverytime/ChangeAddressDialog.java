package com.baibeiyun.bbyiot.view.deliverytime;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.utils.DateUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更改封面对话框
 * 
 * @author ywl
 * 
 */
public class ChangeAddressDialog extends Dialog implements
		View.OnClickListener {

	private WheelView wvProvince;
	private WheelView wvCitys;
	private WheelView wvCounty;
	private View lyChangeAddress;
	private View lyChangeAddressChild;
	private TextView btnSure;
	private TextView btnCancel;

	private Context context;
	private JSONObject mJsonObj;
	private String[] mProvinceDatas;
	/**预售*/
	private List<String> listS =new ArrayList<String>();
	
	private List<String> list =new ArrayList<String>();

	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	HashMap<String, String[]> mCountyDatasMap;
	
	private ArrayList<String> arrProvinces = new ArrayList<String>();

	private ArrayList<String> arrCitys = new ArrayList<String>();
	private ArrayList<String> arrCounty = new ArrayList<String>();
	private AddressTextAdapter provinceAdapter;
	private AddressTextAdapter cityAdapter;
	private AddressTextAdapter countyAdapter;

	private String strProvince = "今天";
	private String strCity = "及时送";
	private String areas = "及时送";
	private String strCounty = "";
	private OnAddressCListener onAddressCListener;

	private int maxsize = 24;
	private int minsize = 14;
	private String strsub2;
	private String strsub1;
	//private TextView tv_yy;
	private String openTime;

	public ChangeAddressDialog(Context context,Map<String, String[]> mCitisDatasMap,String[] mProvinceDatas,HashMap<String, String[]> mCountyDatasMap) {
		super(context, R.style.DialogStyleBottom);
		this.context = context;
		this.mCitisDatasMap = mCitisDatasMap;
		this.mProvinceDatas = mProvinceDatas;
		this.mCountyDatasMap = mCountyDatasMap;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_address);
		String defVal = "";
		wvProvince = (WheelView) findViewById(R.id.wv_address_province);
		wvCitys = (WheelView) findViewById(R.id.wv_address_city);
		wvCounty = (WheelView) findViewById(R.id.wv_address_county);
		lyChangeAddress = findViewById(R.id.ly_myinfo_changeaddress);
		lyChangeAddressChild = findViewById(R.id.ly_myinfo_changeaddress_child);
		btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
		btnCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);
		//tv_yy = (TextView) findViewById(R.id.tv_yy);

		lyChangeAddress.setOnClickListener(this);
		lyChangeAddressChild.setOnClickListener(this);
		btnSure.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		
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
		
		initCounty(mCountyDatasMap.get(strCity));
		countyAdapter = new AddressTextAdapter(context, arrCounty,
				getCountyItem(strCounty), maxsize, minsize);
		wvCounty.setVisibleItems(5);
		wvCounty.setViewAdapter(countyAdapter);
		wvCounty.setCurrentItem(getCountyItem(strCounty));

		wvProvince.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
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
				
				String[] county = mCountyDatasMap.get(mCitisDatasMap.get(currentText)[0]);
				strCounty=county[0];
				initCounty(county);
				countyAdapter = new AddressTextAdapter(context, arrCounty, 0,
						maxsize, minsize);
				wvCounty.setVisibleItems(5);
				wvCounty.setViewAdapter(countyAdapter);
				wvCounty.setCurrentItem(0);
			}
		});

		wvProvince.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) provinceAdapter.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, provinceAdapter);
			}
		});

		wvCitys.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				
				String currentText = (String) cityAdapter.getItemText(wheel
						.getCurrentItem());
				strCity = currentText;
				setTextviewSize(currentText, cityAdapter);
				String[] county = mCountyDatasMap.get(currentText);
				strCounty=county[0];
				initCounty(county);
				countyAdapter = new AddressTextAdapter(context, arrCounty, 0,
						maxsize, minsize);
				wvCounty.setVisibleItems(5);
				wvCounty.setViewAdapter((WheelViewAdapter) countyAdapter);
				wvCounty.setCurrentItem(0);
				
				
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
		
		wvCounty.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) countyAdapter.getItemText(wheel
						.getCurrentItem());
				strCounty = currentText;
				setTextviewSize(currentText, countyAdapter);
			}
		});

		wvCounty.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) countyAdapter.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, countyAdapter);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v == btnSure) {
					// 判断是否超过营业时间
					onAddressCListener.onClick(strProvince, strCity,strCounty);

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
		void onClick(String province, String city, String strCounty);
	}

	/**
	 * 从文件中读取地址数据
	 */
	private void initJsonData() {
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = context.getAssets().open("city.json");
			int len = -1;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf)) != -1) {
				sb.append(new String(buf, 0, len, "utf-8"));
			}
			is.close();
			
			
			
			mJsonObj = new JSONObject(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 解析数据
	 */
	private void initDatas() {}

	/**
	 * 初始化省会
	 */
	public void initProvinces() {
		int length = mProvinceDatas.length;
		for (int i = 0; i < length; i++) {
			arrProvinces.add(mProvinceDatas[i]);
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
	 * 根据城市，生成该省会的所有县
	 * 
	 * @param
	 */
	public void initCounty(String[] county) {
		if (county != null) {
			arrCounty.clear();
			int length = county.length;
			for (int i = 0; i < length; i++) {
				arrCounty.add(county[i]);
			}
		} else {
			String[] city = mCountyDatasMap.get(mCitisDatasMap.get(0));
			arrCounty.clear();
			int length = city.length;
			for (int i = 0; i < length; i++) {
				arrCounty.add(city[i]);
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
	public void setAddress(String province, String city,String areas) {
		if (province != null && province.length() > 0) {
			this.strProvince = province;
		}
		if (city != null && city.length() > 0) {
			this.strCity = city;
		}
		if (strCounty != null && areas.length() > 0) {
			this.strCounty = areas;
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
			strProvince = "今天";
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
//			System.out.println(arrCitys.get(i));
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
				strCity = "及时送";
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
	 * 得到时间索引，没有返回默认“09：00”
	 * 
	 * @param c时间
	 * @return
	 */
	public int getCountyItem(String county) {
		int size = arrCounty.size();
		int countyIndex = 0;
		boolean nocity = true;
		for (int i = 0; i < size; i++) {
//			System.out.println(arrCounty.get(i));
			if (county.equals(arrCounty.get(i))) {
				nocity = false;
				return countyIndex;
			} else {
				countyIndex++;
			}
		}
		return countyIndex;
	}
	
	/**
	 * 切割到本地的时间
	 */
	public String getLocalTime(){
		String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:MyCallback");
		String currentTime = currentDate.substring(11,16);
		return currentTime;
		
	}
	
}