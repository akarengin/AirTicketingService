package com.enginakar.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Tickets implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(unique = true)
	private String pnr;
	private double price;

	@ManyToOne
	@JoinColumn(name = "Flights_id", unique = false)
	private Flights flights;

	public Tickets() {

	}

	public Tickets(String name, String pnr, Flights flights) {
		this.name = name;
		this.pnr = pnr;
		this.flights = flights;
	}

	public double calculatePrice(double soldTickets, double capacity, double basePrice) {
		double quota = soldTickets / capacity;
		if (quota < 0.1) {
			price = basePrice;
		} else {
			if ((quota * 100) % 10 == 0) {
				price = basePrice + (basePrice * quota);
			} else {
				int rise = ((int) (quota / 10)) * 10;
				price = basePrice + (basePrice * rise) / basePrice;
			}
		}
		return price;

	}

	public Flights getFlights() {
		return flights;
	}

	public void setFlights(Flights flights) {
		this.flights = flights;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}