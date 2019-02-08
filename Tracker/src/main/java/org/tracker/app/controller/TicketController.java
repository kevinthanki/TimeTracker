package org.tracker.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tracker.app.entity.Ticket;
import org.tracker.app.ticket.services.AssignTicketService;
import org.tracker.app.ticket.services.CreateTicketService;
import org.tracker.app.ticket.services.DeleteTicketService;
import org.tracker.app.ticket.services.ListTicketService;
import org.tracker.app.ticket.services.UpdateTicketStatusService;

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
	private AssignTicketService assignTicketService;
	private UpdateTicketStatusService updateTicketStatusService;

	public static final String COLLECTION_NAME = "ticket";

	@Autowired
	public TicketController(CreateTicketService createTicketService, ListTicketService listTicketService,
			DeleteTicketService deleteTicketService, AssignTicketService assignTicketService,
			UpdateTicketStatusService updateTicketStatusService) {
		this.createTicketService = createTicketService;
		this.listTicketService = listTicketService;
		this.deleteTicketService = deleteTicketService;
		this.assignTicketService = assignTicketService;
		this.updateTicketStatusService = updateTicketStatusService;
	}

	@PostMapping
	public ResponseEntity<Void> createTicket(
			@RequestParam(name = "description", defaultValue = "No description found") String description) {

		createTicketService.createTicket(description);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(path = { "/", "", "/{ticketId}" })
	public ResponseEntity<List<Ticket>> listTickets(
			@PathVariable(value = "ticketId", name = "ticketId", required = false) Long ticketId) {
		List<Ticket> tickets = listTicketService.listTickets(ticketId);
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{ticketId}")
	public ResponseEntity<Void> deleteTicket(
			@PathVariable(value = "ticketId", name = "ticketId", required = true) Long ticketId) {
		deleteTicketService.deleteTicket(ticketId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping(path = "/assign/{ticketNo}")
	public ResponseEntity<Void> assignTicket(@PathVariable(name = "ticketId", required = true) long ticketId,
			@RequestParam(name = "assignee", required = true) String assignee,
			@RequestParam(name = "description", defaultValue = "No description found", required = true) String description) {
		assignTicketService.assignTicket(ticketId, assignee, description);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PatchMapping(path = "/{ticketId}/update/status")
	public ResponseEntity<Void> updateStatus(
			@PathVariable(value = "ticketId", name = "ticketId", required = true) long ticketId,
			@RequestParam(name = "status", required = true) long status) {
		updateTicketStatusService.updateStatus(ticketId, status);
		return null;
	}

}
