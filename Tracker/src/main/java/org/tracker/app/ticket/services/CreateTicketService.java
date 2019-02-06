package org.tracker.app.ticket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.app.entity.Ticket;
import org.tracker.app.repository.TicketRepository;

@Service
public class CreateTicketService {

	@Autowired
	private Ticket ticket;

	@Autowired
	private TicketRepository ticketRepository;

	public Ticket createTicket(String description) {
		ticket.setTicketNo(ticketRepository.findMaxTicketId() + 1);
		ticket.setDescription(description);
		ticket = ticketRepository.save(ticket);
		if (ticket.getTicketNo() > 0)
			return ticket;
		else
			return null;
	}
}
