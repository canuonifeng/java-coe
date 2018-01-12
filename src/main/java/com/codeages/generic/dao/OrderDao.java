package com.codeages.generic.dao;

import com.codeages.generic.entity.Order;

public class OrderDao extends BaseDao<Order> {

	protected String getTable() {
		return "biz_order";
	}
}
