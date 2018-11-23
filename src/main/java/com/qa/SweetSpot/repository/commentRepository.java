package com.qa.SweetSpot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qa.SweetSpot.mySpringBootDatabaseApp.*;

@Repository
public interface commentRepository extends JpaRepository<Comments,Long> {

	Page<Comments> findByPostId(Long postId, Pageable pageable);
	
	List<Comments> findById(@Param("id") Integer id);
}
