package org.tracker.app.ticket.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.app.entity.Ticket;
import org.tracker.app.exception.TicketNotFoundException;
import org.tracker.app.exception.UserNotFoundException;
import org.tracker.app.repository.TicketRepository;
import org.tracker.app.repository.UserRepository;

/**
 * 
 * @author kevin
 *
 */
@Service
public class AssignTicketService {

	private TicketRepository ticketRepository;
	private UserRepository userRepository;

	@Autowired
	public AssignTicketService(TicketRepository ticketRepository, UserRepository userRepository) {
		this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
	}

	public void assignTicket(long ticketId, String assignee, String description) {

		Optional<Ticket> ticketContainer = ticketRepository.findById(ticketId);

		if (ticketContainer.isPresent()) {
			Ticket ticket = ticketContainer.get();
			if (userRepository.existsById(assignee)) {
				ticket.setAssigned(assignee);
				ticket.setDescription(description);
				ticketRepository.save(ticket);
			} else {
				throw new UserNotFoundException("No such user found to assign the ticket");
			}

		} else {
			throw new TicketNotFoundException("Invalid ticketId provided");
		}
	}
}
