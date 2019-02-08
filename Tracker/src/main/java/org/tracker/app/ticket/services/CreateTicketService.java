package org.tracker.app.ticket.services;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.app.entity.AccessToken;
import org.tracker.app.entity.Ticket;
import org.tracker.app.entity.TicketStatus;
import org.tracker.app.exception.TicketCreationFailedException;
import org.tracker.app.exception.UserNotFoundException;
import org.tracker.app.repository.AccessTokenRepository;
import org.tracker.app.repository.TicketRepository;

@Service
public class CreateTicketService {

	private Ticket ticket;
	private TicketRepository ticketRepository;
	private AccessTokenRepository accessTokenRepository;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	public CreateTicketService(TicketRepository ticketRepository, AccessTokenRepository accessTokenRepository,
			Ticket ticket) {
		this.ticketRepository = ticketRepository;
		this.accessTokenRepository = accessTokenRepository;
		this.ticket = ticket;
	}

	public Ticket createTicket(String description) {
		ticket.setTicketNo(ticketRepository.findMaxTicketId() + 1);
		ticket.setDescription(description);
		ticket.setStatus(TicketStatus.NEW);

		Optional<AccessToken> token = accessTokenRepository.findById(request.getHeader("access-token"));
		if (token.isPresent()) {
			String username = token.get().getUsername();
			ticket.setCreatedBy(username);
			ticket.setAssigned(username);
		} else {
			throw new UserNotFoundException("User does not exist to assign to the ticket");
		}

		ticket = ticketRepository.save(ticket);
		if (ticket.getTicketNo() > 0)
			return ticket;
		else
			throw new TicketCreationFailedException("Unable to create a ticket");
	}
}
