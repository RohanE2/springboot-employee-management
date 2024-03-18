package com.jspiders.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.springboot.pojo.AdminPOJO;
import com.jspiders.springboot.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository repository;

	public AdminPOJO addAdmin(AdminPOJO admin) {
		AdminPOJO pojo = repository.save(admin);
		return pojo;
	}

	public AdminPOJO login(String username, String password) {
	AdminPOJO admin  =	repository.findByUsernameAndPassword(username, password);
	return admin;
	}
	

}
