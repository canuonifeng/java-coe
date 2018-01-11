package com.codeages.annotation;

@MethodCost()
public class Athlete implements People{
	@Override
	public void running(Long kilo) throws Exception {
		Thread.sleep(kilo/10);
		System.out.println("running!!!");
	}
}
