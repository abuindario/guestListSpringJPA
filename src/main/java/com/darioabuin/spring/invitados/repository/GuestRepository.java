package com.darioabuin.spring.invitados.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.darioabuin.spring.invitados.entities.Guest;

public interface GuestRepository extends CrudRepository<Guest, Integer> {
	@Query("SELECT g FROM Guest g WHERE g.id = :id")
	Guest getById(@Param("id") Integer id);
}
