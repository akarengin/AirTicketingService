package com.enginakar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enginakar.models.Airlines;
import com.enginakar.models.Airports;
import com.enginakar.models.Flights;
import com.enginakar.models.Routes;
import com.enginakar.models.Tickets;
import com.enginakar.repos.AirlinesRepo;
import com.enginakar.repos.AirportsRepo;
import com.enginakar.repos.FlightsRepo;
import com.enginakar.repos.RoutesRepo;
import com.enginakar.repos.TicketsRepo;

@Service
public class AdminServices {
	@Autowired
	AirlinesRepo airlinesRepo;

	@Autowired
	AirportsRepo airportsRepo;

	@Autowired
	RoutesRepo routesRepo;

	@Autowired
	FlightsRepo flightsRepo;

	@Autowired
	TicketsRepo ticketsRepo;

	public List<Airlines> getAirlines() {
		return airlinesRepo.findAll();
	}

	public Optional<Airlines> getAirlineById(int id) {
		return airlinesRepo.findById(id);
	}

	public Airlines addAirline(Airlines airlines) {
		airlinesRepo.save(airlines);
		return airlines;
	}

	public List<Airports> getAirports() {
		return airportsRepo.findAll();
	}

	public Optional<Airports> getAirportById(int id) {
		return airportsRepo.findById(id);
	}

	public Airports addAirport(Airports airports) {
		airportsRepo.save(airports);
		return airports;
	}

	public List<Routes> getRoutes() {
		return routesRepo.findAll();
	}

	public Optional<Routes> getRouteById(int id) {
		return routesRepo.findById(id);
	}

	public Routes addRoute(Routes routes) {
		routesRepo.save(routes);
		return routes;
	}

	public List<Flights> getFlights() {
		return flightsRepo.findAll();
	}

	public Flights addFlights(Flights flights) {
		flightsRepo.save(flights);
		return flights;
	}

	public List<Tickets> getTickets() {
		return ticketsRepo.findAll();
	}
}
