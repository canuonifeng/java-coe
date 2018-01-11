package com.codeages.annotation;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class AnnotationTest {
	
	@Test
	public void annotation() throws Exception {
		getPeople().running(20L);
	}
	
	private People getPeople() {
		Athlete people = new Athlete();
		
		InvocationHandler h = new InvocationHandler(people);
		return (People)Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {People.class}, h);
	}
}
