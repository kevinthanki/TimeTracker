package org.tracker.app.ticket.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.app.entity.Ticket;
import org.tracker.app.entity.TicketStatus;
import org.tracker.app.exception.TicketNotFoundException;
import org.tracker.app.repository.TicketRepository;

/**
 * 
 * @author kevin
 *
 */
@Service
public class UpdateTicketStatusService {

	@Autowired
	private TicketRepository ticketRepository;

	public void updateStatus(long ticketId, long status) {
		Optional<Ticket> ticketContainer = ticketRepository.findById(ticketId);
		if (ticketContainer.isPresent()) {
			Ticket ticket = ticketContainer.get();
			ticket.setStatus(TicketStatus.valueOf(String.valueOf(status)));
			ticket.setDescription(ticket.getDescription() + System.lineSeparator()
					+ "Ticket status has been changed from " + TicketStatus.valueOf(String.valueOf(status)));
			ticketRepository.save(ticket);
		} else {
			throw new TicketNotFoundException("No such ticket found");
		}
	}
}
