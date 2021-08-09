package com.digitalinnovation.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalinnovation.personapi.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
