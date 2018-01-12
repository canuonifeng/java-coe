package com.codeages.generic.dao;

import com.codeages.generic.entity.OrderItem;

public class OrderItemDao extends BaseDao<OrderItem> {
	protected String getTable() {
		return "biz_order_item";
	}
}
