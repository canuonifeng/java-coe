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
			long start = new Date().getTime();
			Object result = method.invoke(object, args);
			System.out.println("cost: " + (new Date().getTime() - start));
			return result;
		}
		
		return method.invoke(object, args);
	}

}
