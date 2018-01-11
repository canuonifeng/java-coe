package com.codeages.generic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.codeages.generic.entity.Order;
import com.codeages.generic.util.ConnectionFactory;

public class OrderDao extends BaseDao<Order>{
	public Order getOrder(Long id) throws Exception {
		Order order = null;

		Connection con = ConnectionFactory.connection();
		PreparedStatement stat = con.prepareStatement("select * from biz_order where id = ?");
		stat.setLong(1, id.longValue());
		ResultSet rs = stat.executeQuery();
		
		if (rs.next()) {
			order = mapper(rs, new Order());
		}
		
		rs.close();
		stat.close();
		con.close();
		return order;
	}

}
