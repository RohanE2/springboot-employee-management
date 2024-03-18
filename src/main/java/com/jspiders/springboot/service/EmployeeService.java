package com.jspiders.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.springboot.pojo.EmployeePOJO;
import com.jspiders.springboot.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public EmployeePOJO addEmployee(EmployeePOJO employee) {
		EmployeePOJO pojo = repository.save(employee);
		return pojo;
	}

	public EmployeePOJO searchEmployee(int id) {
	EmployeePOJO pojo = repository.findById(id).orElse(null);
		return pojo;
	}

	public List<EmployeePOJO> searchAllEmployee() {
		List<EmployeePOJO> list = repository.findAll();
		return list;
	}

	public EmployeePOJO removeEmployee(int id) {
	   repository.deleteById(id);
	return null;
	}

	public EmployeePOJO updateEmployee(int id, String name, String email, long contact, String designation,
			double salary) {
		EmployeePOJO pojo = repository.findById(id).orElse(null);
		if(pojo != null) {
			pojo.setName(name);
			pojo.setEmail(email);
			pojo.setContact(contact);
			pojo.setDesignation(designation);
			pojo.setSalary(salary);
			repository.save(pojo);
		}
		return pojo;
	}
	
}
