package com.codeages.annotation;

@MethodCost
public class Athlete implements People{
	
	@Override
	public void running(Long kilo) throws Exception {
		Thread.sleep(kilo/10);
		System.out.println("running!!!");
	}
	
	public void running1(Long kilo) throws Exception {
		Thread.sleep(kilo/10);
		System.out.println("running111111!!!");
	}
	
	public static void main(String[] args) throws Exception {
		Class c = Class.forName(Athlete.class.getName());
		System.out.println(c.isAnnotationPresent(MethodCost.class));
	}
}
