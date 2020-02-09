package com.enginakar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enginakar.models.Flights;
import com.enginakar.models.Tickets;
import com.enginakar.repos.FlightsRepo;
import com.enginakar.repos.TicketsRepo;

@Service
public class CustomerServices {

	@Autowired
	FlightsRepo flightsRepo;

	@Autowired
	TicketsRepo ticketsRepo;

	public List<Flights> searchFlight(String from, String to, String date) throws Exception {
		List<Flights> flight = flightsRepo.findFlightsByRoute(from, to, date);
		if (flight.isEmpty()) {
			throw new Exception("Not found");
		}
		return flight;
	}

	public Tickets searchTicket(String pnr) throws Exception {
		Tickets ticket = ticketsRepo.findTicketByPnr(pnr);
		if (ticket == null) {
			throw new Exception("No record");
		}
		return ticket;
	}

	public Tickets buyTicket(Tickets tickets) throws Exception {
		int flightId = tickets.getFlights().getId();
		Flights flights = flightsRepo.findById(flightId).get();
		double capacity = flights.getCapacity();
		double basePrice = flights.getBasePrice();
		double soldTickets = flights.getSeatsTaken();
		if (soldTickets == capacity) {
			throw new Exception("Quota is full");
		}
		double ticketPrice = tickets.calculatePrice(soldTickets, capacity, basePrice);
		tickets.setPrice(ticketPrice);
		soldTickets = soldTickets + 1;
		flights.updateSeatsTaken(soldTickets);
		flightsRepo.save(flights);
		ticketsRepo.save(tickets);
		return tickets;
	}

	public Tickets cancelTicket(String pnr) throws Exception {
		Tickets ticket = ticketsRepo.findTicketByPnr(pnr);
		if (ticket == null) {
			throw new Exception("Please, enter pnr correctly");
		}
		Flights flights = flightsRepo.findById(ticket.getFlights().getId()).get();
		double soldTickets = flights.getSeatsTaken();
		soldTickets = soldTickets - 1;
		flights.updateSeatsTaken(soldTickets);
		flightsRepo.save(flights);
		ticketsRepo.delete(ticket);
		return ticket;
	}
}
