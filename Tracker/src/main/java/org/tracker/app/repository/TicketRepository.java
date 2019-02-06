package org.tracker.app.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.tracker.app.entity.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, Long> {

	Ticket findByTicketNo(long ticketNo);

	default long findMaxTicketId() {
		List<Ticket> tickets = findAll(new Sort(Sort.Direction.DESC, "ticketNo"));
		if (!tickets.isEmpty())
			return tickets.get(0).getTicketNo();
		else
			return 0;
	}
}
