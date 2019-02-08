package org.tracker.app.ticket.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.app.entity.Ticket;
import org.tracker.app.exception.TicketNotFoundException;
import org.tracker.app.repository.TicketRepository;

@Service
public class DeleteTicketService {

	@Autowired
	private TicketRepository ticketRepository;

	public void deleteTicket(Long ticketNo) {
		Optional<Ticket> ticket = ticketRepository.findById(ticketNo);
		if (ticket.isPresent()) {
			ticketRepository.deleteById(ticketNo);
		} else {
			throw new TicketNotFoundException("Ticket does not exist");
		}
	}
}
