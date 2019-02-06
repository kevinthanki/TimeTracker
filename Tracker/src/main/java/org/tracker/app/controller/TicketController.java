package org.tracker.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tracker.app.entity.Ticket;
import org.tracker.app.response.WebResponse;
import org.tracker.app.ticket.services.CreateTicketService;
import org.tracker.app.ticket.services.DeleteTicketService;
import org.tracker.app.ticket.services.ListTicketService;

/**
 * 
 * @author kevin
 *
 */
@RestController
@RequestMapping(path = "/ticket")
public class TicketController {

	private CreateTicketService createTicketService;
	private ListTicketService listTicketService;
	private DeleteTicketService deleteTicketService;

	@Autowired
	public TicketController(CreateTicketService createTicketService, ListTicketService listTicketService,
			DeleteTicketService deleteTicketService) {
		this.createTicketService = createTicketService;
		this.listTicketService = listTicketService;
		this.deleteTicketService = deleteTicketService;
	}

	@PostMapping(path = "/create")
	public WebResponse createTicket(
			@RequestParam(name = "description", defaultValue = "No description found") String description) {

		Ticket ticket = createTicketService.createTicket(description);
		if (ticket != null) {
			return new WebResponse(ticket, true, null);
		} else {
			return new WebResponse(null, false, "Unable to create the Ticket");
		}
	}

	@GetMapping(path = { "/list/{ticketNo}", "/list" })
	public WebResponse listTickets(
			@PathVariable(value = "ticketNo", name = "ticketNo", required = false) Long ticketNo) {
		List<Ticket> tickets = listTicketService.listTickets(ticketNo);
		if (tickets != null) {
			if (tickets.isEmpty()) {
				return new WebResponse(tickets, true, "No tickets available.");
			} else {
				return new WebResponse(tickets, true, null);
			}
		} else {
			return new WebResponse(null, false, "Ticket does not exist");
		}
	}

	@DeleteMapping(path = "/delete/{ticketNo}")
	public WebResponse deleteTicket(
			@PathVariable(value = "ticketNo", name = "ticketNo", required = true) Long ticketNo) {
		boolean deleted = deleteTicketService.deleteTicket(ticketNo);
		if (deleted) {
			return new WebResponse(null, true, "Ticket has been removed");
		} else {
			return new WebResponse(null, false, "Unable to remove the ticket.");
		}
	}

}
