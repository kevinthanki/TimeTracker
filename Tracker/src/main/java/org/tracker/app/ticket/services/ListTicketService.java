package org.tracker.app.ticket.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.app.entity.Ticket;
import org.tracker.app.repository.TicketRepository;

@Service
public class ListTicketService {

	@Autowired
	private TicketRepository ticketRepository;

	public List<Ticket> listTickets(Long ticketNo) {
		List<Ticket> tickets = new ArrayList<>();
		if (ticketNo != null && ticketNo > 0) {
			Optional<Ticket> ticket = ticketRepository.findById(ticketNo);
			if (ticket.isPresent())
				tickets.add(ticket.get());
			else
				return null;
		} else {
			tickets = ticketRepository.findAll();
		}
		return tickets;
	}
}
