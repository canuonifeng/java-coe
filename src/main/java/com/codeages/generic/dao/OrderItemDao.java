package com.codeages.generic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.codeages.generic.entity.OrderItem;
import com.codeages.generic.util.ConnectionFactory;

public class OrderItemDao extends BaseDao<OrderItem>{
	public OrderItem getOrderItem(Long id) throws Exception {
		OrderItem orderItem = null;
		Connection con = ConnectionFactory.connection();
		PreparedStatement stat = con.prepareStatement("select * from biz_order_item where id = ?");
		stat.setLong(1, id.longValue());
		ResultSet rs = stat.executeQuery();
		
		if (rs.next()) {
			orderItem = mapper(rs, new OrderItem());
		}
		
		rs.close();
		stat.close();
		con.close();
		return orderItem;
	}
}
