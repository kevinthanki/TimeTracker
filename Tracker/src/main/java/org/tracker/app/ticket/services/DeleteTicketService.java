package org.tracker.app.ticket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.app.repository.TicketRepository;

@Service
public class DeleteTicketService {

	@Autowired
	private TicketRepository ticketRepository;

	public boolean deleteTicket(Long ticketNo) {
		try {
			ticketRepository.deleteById(ticketNo);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
