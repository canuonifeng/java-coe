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
		OrderItem orderItem = new OrderItemDao().getOrderItem(2L);
		
		OrderItem orderItem1 = new OrderItemDao().get(2L,"biz_order_item");
		
		Assert.assertEquals(1L, order.getId().longValue());
		Assert.assertEquals(2L, orderItem.getId().longValue());
		Assert.assertEquals(2L, orderItem1.getId().longValue());
	}

}
