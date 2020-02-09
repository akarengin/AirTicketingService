package com.enginakar.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "routes")
public class Routes implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "Airports_From", unique = false)
	private Airports origin;

	@OneToOne
	@JoinColumn(name = "Airports_To", unique = false)
	private Airports destination;

	public Routes() {

	}

	public Routes(Airports origin, Airports destination) {
		this.origin = origin;
		this.destination = destination;
	}

	public Airports getOrigin() {
		return origin;
	}

	public void setOrigin(Airports origin) {
		this.origin = origin;
	}

	public Airports getDestination() {
		return destination;
	}

	public void setDestination(Airports destination) {
		this.destination = destination;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
