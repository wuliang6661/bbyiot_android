package com.baibeiyun.bbyiot.view.deliverytime;

import java.io.Serializable;
import java.util.List;

public class ShengBean implements Serializable {

	private String text;// 省的名字
	private String value;// 省id
	private List<CitysBean> citys;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<CitysBean> getCitys() {
		return citys;
	}

	public void setCitys(List<CitysBean> citys) {
		this.citys = citys;
	}

}
