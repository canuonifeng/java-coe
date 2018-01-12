package com.codeages.generic;

import org.junit.Test;

import com.codeages.generic.dao.BaseDao;
import com.codeages.generic.dao.OrderDao;
import com.codeages.generic.dao.OrderItemDao;
import com.codeages.generic.entity.Order;
import com.codeages.generic.entity.OrderItem;

import junit.framework.Assert;

public class NoGenericTest {

	@Test
	public void testJdbc() throws Exception {
		Order order = (Order) getDao(OrderDao.class).get(1L);
		OrderItem orderItem = (OrderItem) getDao(OrderItemDao.class).get(2L);
		OrderItem orderItem1 = (OrderItem) getDao(OrderItemDao.class).get(2L);

		Assert.assertEquals(1L, order.getId().longValue());
		Assert.assertEquals(2L, orderItem.getId().longValue());
		Assert.assertEquals(2L, orderItem1.getId().longValue());
	}

	protected BaseDao getDao(Class<? extends BaseDao> cls) throws Exception {
		return (BaseDao)cls.newInstance();
	}
}
