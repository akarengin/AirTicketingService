package com.enginakar.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.enginakar.controllers.AirTicketingServiceApplication;
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
import com.enginakar.services.CustomerServices;

@ComponentScan(basePackages = "com.enginakar.repos")
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = AirTicketingServiceApplication.class)
public class CustomerServicesTest {

	@Autowired
	TicketsRepo ticketsRepo;

	@Autowired
	FlightsRepo flightsRepo;

	@Autowired
	RoutesRepo routesRepo;

	@Autowired
	AirportsRepo airportsRepo;

	@Autowired
	AirlinesRepo airlinesRepo;

	@Autowired
	CustomerServices customerServices;

	@BeforeEach
	@Transactional
	public void setUp() {
		Airlines airlines = new Airlines("SunExpress");
		airlinesRepo.save(airlines);
		Airlines airlines1 = new Airlines("Atlasglobal");
		airlinesRepo.save(airlines1);

		Airports airports = new Airports("Esenboğa Havalimanı", "ESB");
		Airports airports2 = new Airports("Sabiha Gökçen Havalimanı", "SAW");
		Airports airports3 = new Airports("İstanbul Havalimanı", "IST");
		airportsRepo.save(airports);
		airportsRepo.save(airports2);
		airportsRepo.save(airports3);

		Routes routes = new Routes(airports, airports2);
		Routes routes1 = new Routes(airports, airports3);
		routesRepo.save(routes);
		routesRepo.save(routes1);

		Flights flights = new Flights("ACPXYZ", "14/02/2020", "12:00", 10, 100, routes, airlines);
		Flights flights1 = new Flights("DSMANJ", "15/02/2020", "14:00", 10, 100, routes1, airlines1);
		Flights flights2 = new Flights("NCHSGB", "16/02/2020", "13:00", 1, 100, routes1, airlines);
		flightsRepo.save(flights);
		flightsRepo.save(flights1);
		flightsRepo.save(flights2);
	}

	@Test
	@Order(1)
	public void testSearchFlight() throws Exception {
		List<Flights> flights = customerServices.searchFlight("Esenboğa Havalimanı", "İstanbul Havalimanı",
				"15/02/2020");
		for (Flights flight : flights) {
			assertThat(flight.getRoutes().getOrigin().getName()).isEqualTo(new String("Esenboğa Havalimanı"));
			assertThat(flight.getRoutes().getDestination().getName()).isEqualTo(new String("İstanbul Havalimanı"));
			assertThat(flight.getDate().equals("15/02/2020"));
		}
		try {
			customerServices.searchFlight("İstanbul Havalimanı", "Esenboğa Havalimanı", "15/02/2020");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Order(2)
	public void testBuyTicket() throws Exception {
		Tickets tickets = new Tickets("Ahmet Bal", "APDXYZ", flightsRepo.getOne(1));
		Tickets tickets1 = new Tickets("Merve Doğa", "NAHCBD", flightsRepo.getOne(1));
		Tickets tickets2 = new Tickets("Senem Yıldız", "SJDHCN", flightsRepo.getOne(2));
		Tickets tickets3 = new Tickets("Fatih Doğru", "SJDMNA", flightsRepo.getOne(1));
		Tickets tickets4 = new Tickets("Ayla Filiz", "LSKMJN", flightsRepo.getOne(3));
		Tickets tickets5 = new Tickets("Mert Cem", "AKSLDC", flightsRepo.getOne(3));

		Tickets ticket = customerServices.buyTicket(tickets);
		Tickets ticket1 = customerServices.buyTicket(tickets1);
		Tickets ticket2 = customerServices.buyTicket(tickets2);
		Tickets ticket3 = customerServices.buyTicket(tickets3);
		customerServices.buyTicket(tickets4);

		assertThat(ticket).extracting(Tickets::getId).isEqualTo(1);
		assertThat(ticket).extracting(Tickets::getName).isEqualTo("Ahmet Bal");
		assertThat(ticket).extracting(Tickets::getPnr).isEqualTo("APDXYZ");
		assertThat(ticket).extracting(Tickets::getPrice).isEqualTo(100.0);

		assertThat(ticket1).extracting(Tickets::getId).isEqualTo(2);
		assertThat(ticket1).extracting(Tickets::getName).isEqualTo("Merve Doğa");
		assertThat(ticket1).extracting(Tickets::getPnr).isEqualTo("NAHCBD");
		assertThat(ticket1).extracting(Tickets::getPrice).isEqualTo(110.0);

		assertThat(ticket2).extracting(Tickets::getPrice).isEqualTo(100.0);

		assertThat(ticket3).extracting(Tickets::getPrice).isEqualTo(120.0);

		try {
			customerServices.buyTicket(tickets5);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	@Order(3)
	public void testSearchTicket() throws Exception {
		Tickets ticket = customerServices.searchTicket("SJDHCN");
		assertThat(ticket).extracting(Tickets::getName).isEqualTo("Senem Yıldız");
		try {
			customerServices.searchTicket("SJDHCP");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Order(4)
	public void testCancelTicket() throws Exception {
		Tickets ticket = customerServices.cancelTicket("NAHCBD");
		assertThat(ticket).extracting(Tickets::getName).isEqualTo("Merve Doğa");
		try {
			customerServices.searchTicket("NAHCBD");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			customerServices.cancelTicket("SJDHCO");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}