package com.baibeiyun.bbyiot.view.deliverytime;

import java.io.Serializable;
import java.util.List;

public class CitysBean implements Serializable {

	private String value;//城市id
	private String text;//城市名称
	private List<areas> areas;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<areas> getAreas() {
		return areas;
	}
	public void setAreas(List<areas> areas) {
		this.areas = areas;
	}
	
	
	
}
