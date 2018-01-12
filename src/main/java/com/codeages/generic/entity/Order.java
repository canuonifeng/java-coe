package com.codeages.generic.entity;

import com.codeages.generic.annotation.Table;

@Table("biz_order")
public class Order extends BaseEntity{
	
	private String sn;
	private String title;
	private String status;
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
