package com.codeages.annotation;

import java.lang.reflect.Method;
import java.util.Date;

public class InvocationHandler implements java.lang.reflect.InvocationHandler {
	private Object object;
	public InvocationHandler(Object object) {
		this.object = object;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if(object.getClass().isAnnotationPresent(MethodCost.class)) {
			MethodCost[] methodCosts = object.getClass().getDeclaredAnnotationsByType(MethodCost.class);
			MethodCost methodCost = methodCosts[0];
			
			long start = new Date().getTime();
			Object result = method.invoke(object, args);
			
			if ("hour".equals(methodCost.value())) {
				System.out.println("cost: " + (new Date().getTime() - start));
			}else if ("minute".equals(methodCost.value())) {
				System.out.println("cost: " + ((new Date().getTime() - start)*60));
			}
			return result;
		}
		
		return method.invoke(object, args);
	}

}
