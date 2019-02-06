package org.tracker.app.entity;

import org.springframework.data.annotation.Id;

public class Ticket {
	@Id
	private long ticketNo;
	private String description;

	public long getTicketNo() {
		return ticketNo;
	}

	public String getDescription() {
		return description;
	}

	public void setTicketNo(long ticketNo) {
		this.ticketNo = ticketNo;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
