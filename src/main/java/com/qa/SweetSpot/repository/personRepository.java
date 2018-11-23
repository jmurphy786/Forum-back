package com.qa.SweetSpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.SweetSpot.mySpringBootDatabaseApp.*;

@Repository
public interface personRepository extends JpaRepository<Users,Long> {

}
