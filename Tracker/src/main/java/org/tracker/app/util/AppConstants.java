package org.tracker.app.util;

public enum AppConstants {
	DATABASE_NAME("tracker");

	String value;

	private AppConstants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
