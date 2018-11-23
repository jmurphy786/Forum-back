package com.qa.SweetSpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qa.SweetSpot.mySpringBootDatabaseApp.*;

@Repository
public interface postRepository extends JpaRepository<Posts,Long> {
	List<Posts> findById(@Param("id") Integer id);
}
