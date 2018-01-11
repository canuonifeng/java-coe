package com.codeages.generic;

import org.junit.Test;

import com.codeages.generic.dao.OrderDao;
import com.codeages.generic.dao.OrderItemDao;
import com.codeages.generic.entity.Order;
import com.codeages.generic.entity.OrderItem;

import junit.framework.Assert;

public class NoGenericTest {

	@Test
	public void testJdbc() throws Exception {
		Order order = new OrderDao().getOrder(1L);
		OrderItem orderItem = new OrderItemDao().getOrderItem(1L);
		
		Assert.assertEquals(1L, order.getId().longValue());
		
	}

}
