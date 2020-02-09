package com.enginakar.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enginakar.models.Airports;

@Repository
public interface AirportsRepo extends JpaRepository<Airports, Integer> {

}
