package com.codeages.generic.entity;

import com.codeages.generic.annotation.Table;

@Table("biz_order_item")
public class OrderItem extends BaseEntity {
	private String title;
	private String targetType;
	private Integer num;
	private Integer targetId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

}
