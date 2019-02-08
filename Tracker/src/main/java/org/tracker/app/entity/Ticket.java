package org.tracker.app.entity;

import org.springframework.data.annotation.Id;

public class Ticket {
	@Id
	private long ticketNo;
	private String description;
	private TicketStatus status;
	private String createdBy;
	private String assigned;

	public TicketStatus getStatus() {
		return status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getAssigned() {
		return assigned;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

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
