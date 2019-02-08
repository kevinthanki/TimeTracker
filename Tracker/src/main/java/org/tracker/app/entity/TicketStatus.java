package org.tracker.app.entity;

public enum TicketStatus {
	NEW(0), IN_PROGRESS(1), ON_HOLD(2), COMPLETED(3), CLOSED(4);

	private int code;

	TicketStatus(int code) {
		this.code = code;
	}

	public int getValue() {
		return code;
	}

}
