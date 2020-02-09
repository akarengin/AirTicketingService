package com.enginakar.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name = "flights")
@Transactional
public class Flights implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// @Column(unique = true)
	private String flightNumber;

	private String date;
	private String time;
	private double capacity;
	private double basePrice;
	private double seatsTaken;

	public void setSeatsTaken(double seatsTaken) {
		this.seatsTaken = seatsTaken;
	}

	public void updateSeatsTaken(double soldTickets) {
		seatsTaken = soldTickets;
	}

	public double getSeatsTaken() {
		return seatsTaken;
	}

	public Flights() {

	}

	public Flights(String flightNumber, String date, String time, double capacity, double basePrice, Routes routes,
			Airlines airlines) {
		this.flightNumber = flightNumber;
		this.date = date;
		this.time = time;
		this.capacity = capacity;
		this.basePrice = basePrice;
		this.routes = routes;
		this.airlines = airlines;
	}

	@OneToOne
	@JoinColumn(name = "Routes_id", unique = false)
	private Routes routes;

	@ManyToOne
	@JoinColumn(name = "Airlines_id", unique = false)
	private Airlines airlines;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Airlines getAirlines() {
		return airlines;
	}

	public void setAirlines(Airlines airlines) {
		this.airlines = airlines;
	}

	public Routes getRoutes() {
		return routes;
	}

	public void setRoutes(Routes routes) {
		this.routes = routes;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

}
