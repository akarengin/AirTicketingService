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

import com.enginakar.models.Airlines;
import com.enginakar.services.AdminServices;

@Controller
public class AirlinesController {
	@Autowired
	private AdminServices adminServices;

	@Autowired
	@RequestMapping("/airlines")
	@ResponseBody
	public List<Airlines> findAirlines() {
		return adminServices.getAirlines();
	}

	@RequestMapping("/airlines1")
	@ResponseBody
	public Optional<Airlines> findAirlineById(@RequestParam("id") int id) {
		return adminServices.getAirlineById(id);
	}

	@PostMapping("/airlines2")
	public Airlines addNewAirline(@RequestBody Airlines airlines) {
		return adminServices.addAirline(airlines);
	}

}