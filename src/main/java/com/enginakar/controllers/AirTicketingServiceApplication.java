package com.enginakar.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EntityScan(basePackages = "com.enginakar.models")
@ComponentScan(basePackages = { "com.enginakar.controllers", "com.enginakar.services" })
@EnableJpaRepositories(basePackages = "com.enginakar.repos")
@SpringBootApplication
public class AirTicketingServiceApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AirTicketingServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AirTicketingServiceApplication.class, args);
	}
}
