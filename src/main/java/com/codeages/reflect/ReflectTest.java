package com.codeages.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

public class ReflectTest {

	@Test
	public void testRelect() throws Exception {
		String className = "com.codeages.annotation.Athlete";
		Class clazz = Class.forName(className);
		Object object = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {

			if(fields[i].getName().equals("name")) {
				fields[i].setAccessible(true);
				fields[i].set(object, "王五");
			}
			
			System.out.println("name: "+ fields[i].getName());
		}
		
		Method m1 = clazz.getMethod("getName");
		System.out.println(m1.invoke(object));
		
		Method[] methods = clazz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method= methods[i];
			System.out.println("name: "+ methods[i].getName());
		}
		
		Method m = clazz.getMethod("setName", String.class);
		m.invoke(object, "李四");
		
		Method m2 = clazz.getMethod("getName");
		System.out.println(m2.invoke(object));
	}
}
