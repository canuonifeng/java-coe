package com.codeages.annotation;

import java.io.Serializable;

@MethodCost()
public class Athlete implements People, Serializable {
	private String name;
	
	public static final long serialVersionUID = 1L;

	@Override
	public void running(Long kilo) throws Exception {
		Thread.sleep(kilo / 10);
		System.out.println("running!!!");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
