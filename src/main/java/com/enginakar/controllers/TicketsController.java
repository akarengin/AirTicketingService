package com.enginakar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enginakar.models.Tickets;
import com.enginakar.services.AdminServices;
import com.enginakar.services.CustomerServices;

@Controller
public class TicketsController {
	@Autowired
	private CustomerServices customerServices;

	@Autowired
	private AdminServices adminServices;

	@RequestMapping("/tickets")
	@ResponseBody
	public List<Tickets> findTickets() {
		return adminServices.getTickets();
	}

	@RequestMapping("/tickets1")
	@ResponseBody
	public Tickets findTicket(@RequestParam(value = "pnr") String pnr) throws Exception {
		return customerServices.searchTicket(pnr);

	}

	@PostMapping("/tickets2")
	public Tickets addTicket(@RequestBody Tickets tickets) throws Exception {
		return customerServices.buyTicket(tickets);
	}

	@DeleteMapping("/tickets3")
	public Tickets deleteTicket(@RequestParam("pnr") String pnr) throws Exception {
		return customerServices.cancelTicket(pnr);
	}
}