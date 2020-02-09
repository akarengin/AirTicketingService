package com.enginakar.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enginakar.models.Airlines;

@Repository
public interface AirlinesRepo extends JpaRepository<Airlines, Integer> {

}
