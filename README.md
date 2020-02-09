# Air Ticketing Service 

This application enables to buy tickets, searching for your tickets and canceling them if you wish. Every time quota exceeded by 10%, ticket price increases 10%.

## Prerequisites

* JAVA 8
* Eclipse, IntelliJ IDEA etc.
* Postman

## Built With

To run this project, these softwares will be needed:

* Maven - Dependency Management
* Spring Boot - Used to develop Spring-based Java application 
* Hibernate - Object-relational mapping tool 
* H2 - In memory database
* Junit - Unit testing
* JSON - Restful Web Service
* TomCat -Server 

## Usage

- Run this application as SpringBootApp
- Use Postman for HTTP requests in JSON format 
- Open H2 in memory database in a web browser to display or manipulate data in SQL tables


## Code Example

    @DeleteMapping("/tickets3")
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
	
	
For example, in this method, SpringBoot annotations provide us with making HTTP requests by Postman. To delete a ticket from database, JPA repository finds ticket entity that has requested pnr and tickets are drawn from the H2 database shown in SQL tables if you type http://localhost:8084/h2-console in a web browser. 8084 is the port number specified in the application.properties file. 


## Running the tests

- To run test class, the project can be run as JUnit Test 
- Test class will be automatically launched which can be find in src/test/java/com.enginakar.tests folder

## Authors

* Engin Akar