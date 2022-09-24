package br.com.paulo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.paulo.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{}
