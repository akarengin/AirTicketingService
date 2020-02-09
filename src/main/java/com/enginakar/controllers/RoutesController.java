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

import com.enginakar.models.Routes;
import com.enginakar.services.AdminServices;

@Controller
public class RoutesController {
	@Autowired
	private AdminServices adminServices;

	@RequestMapping("/routes")
	@ResponseBody
	public List<Routes> findRoutes() {
		return adminServices.getRoutes();
	}

	@RequestMapping("/routes1")
	@ResponseBody
	public Optional<Routes> findRouteById(@RequestParam("id") int id) {
		return adminServices.getRouteById(id);
	}

	@PostMapping("/routes2")
	public Routes addNewRoute(@RequestBody Routes routes) {
		return adminServices.addRoute(routes);

	}

}