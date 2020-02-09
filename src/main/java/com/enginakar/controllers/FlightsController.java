package com.enginakar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enginakar.models.Flights;
import com.enginakar.services.AdminServices;
import com.enginakar.services.CustomerServices;

@Controller
public class FlightsController {
	@Autowired
	private AdminServices adminServices;

	@Autowired
	private CustomerServices customerServices;

	@RequestMapping("/flights")
	@ResponseBody
	public List<Flights> findFlights() {
		return adminServices.getFlights();
	}

	@RequestMapping("/flights1")
	@ResponseBody
	public List<Flights> findFlight(@RequestParam(value = "from") String from, @RequestParam(value = "to") String to,
			@RequestParam(value = "date") String date) throws Exception {
		return customerServices.searchFlight(from, to, date);
	}

	@PostMapping("/flights2")
	public Flights addNewFlights(@RequestBody Flights flights) {
		return adminServices.addFlights(flights);
	}

}