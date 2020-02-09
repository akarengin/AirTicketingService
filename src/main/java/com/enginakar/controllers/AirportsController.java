package com.enginakar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enginakar.models.Airports;
import com.enginakar.services.AdminServices;

@Controller
public class AirportsController {
	@Autowired
	private AdminServices adminServices;

	@RequestMapping("/airports")
	@ResponseBody
	public List<Airports> findAirports() {
		return adminServices.getAirports();
	}

	@RequestMapping("/airports1")
	@ResponseBody
	public Optional<Airports> findAirpotById(@RequestParam("id") int id) {
		return adminServices.getAirportById(id);
	}

	@PostMapping("/airports2")
	public Airports addNewAirport(@RequestBody Airports airports) {
		return adminServices.addAirport(airports);
	}

}
