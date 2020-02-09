package com.enginakar.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Airports {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// @Column(unique = true)
	private String name;
	private String code;

	public Airports() {

	}

	public Airports(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
}
