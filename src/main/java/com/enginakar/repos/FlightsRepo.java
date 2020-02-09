package com.enginakar.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.enginakar.models.Flights;

@Repository
public interface FlightsRepo extends JpaRepository<Flights, Integer> {

	@Query(value = "SELECT * FROM flights F JOIN Routes R on F.routes_id = R.id JOIN airports A ON (A.id=R.airports_from)  JOIN airports B ON (B.id=R.airports_to) AND A.NAME=:from AND B.NAME=:to WHERE f.date=:date", nativeQuery = true)
	List<Flights> findFlightsByRoute(String from, String to, String date) throws Exception;
}
