package com.globant.training.common;

public enum EtsyValues {

	TREASURY("Treasury");

	private String value;

	private EtsyValues(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
