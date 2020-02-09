package com.enginakar.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.enginakar.models.Tickets;

@Repository
public interface TicketsRepo extends JpaRepository<Tickets, Integer> {

	@Query(value = "SELECT * FROM tickets t where t.pnr = :pnr", nativeQuery = true)
	Tickets findTicketByPnr(String pnr);

}
