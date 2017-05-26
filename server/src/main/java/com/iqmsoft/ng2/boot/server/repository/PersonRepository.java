package com.iqmsoft.ng2.boot.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqmsoft.ng2.boot.server.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom {

}
