package com.jspiders.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jspiders.springboot.pojo.AdminPOJO;

public interface AdminRepository extends JpaRepository<AdminPOJO, Integer> {

	@Query("SELECT a FROM AdminPOJO a WHERE a.username = :username AND a.password = :password")
     AdminPOJO findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
