package com.enginakar.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enginakar.models.Routes;

@Repository
public interface RoutesRepo extends JpaRepository<Routes, Integer> {

}
