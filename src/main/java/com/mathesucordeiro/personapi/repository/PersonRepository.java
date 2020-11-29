package com.mathesucordeiro.personapi.repository;

import com.mathesucordeiro.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
