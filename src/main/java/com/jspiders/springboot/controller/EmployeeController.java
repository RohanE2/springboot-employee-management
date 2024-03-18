package com.jspiders.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.jspiders.springboot.pojo.AdminPOJO;
import com.jspiders.springboot.pojo.EmployeePOJO;
import com.jspiders.springboot.response.AdminResponse;
import com.jspiders.springboot.response.EmployeeResponse;
import com.jspiders.springboot.service.AdminService;
import com.jspiders.springboot.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/add")
	public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody EmployeePOJO employee,@SessionAttribute(name = "login",required = false)AdminPOJO login){
		
		if(login != null) {
		EmployeePOJO pojo = service.addEmployee(employee);
		if(pojo != null) {
			//Success
			return new ResponseEntity<EmployeeResponse>(new EmployeeResponse("OK", "Employee data added. ", pojo, null), HttpStatus.ACCEPTED);
		}
		//failure
		return new ResponseEntity<EmployeeResponse>(new EmployeeResponse("FAIL","Employee data not added. ",null,null),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<EmployeeResponse>(new EmployeeResponse("FAIL","Please login to proceed. ",null,null),HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<EmployeeResponse> searchEmployee(@PathVariable int id,@SessionAttribute(name = "login",required = false)AdminPOJO login){
		if(login != null) {
			EmployeePOJO pojo = service.searchEmployee(id);
			if(pojo != null) {
				//Success
				return new ResponseEntity<EmployeeResponse>(new EmployeeResponse("OK", "Employee data found. ", pojo, null), HttpStatus.FOUND);
			}
			//failure
			return new ResponseEntity<EmployeeResponse>(new EmployeeResponse("FAIL","Employee data not found. ",null,null),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EmployeeResponse>(new EmployeeResponse("FAIL","Please login to proceed. ",null,null),HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/remove/{id}")
	public ResponseEntity<EmployeeResponse> removeEmployee(@PathVariable int id,@SessionAttribute(name = "login",required = false)AdminPOJO login) {
			if(login != null) {
				EmployeePOJO employee = service.searchEmployee(id);
				List<EmployeePOJO> list = service.searchAllEmployee();
				if (employee != null) {
					EmployeePOJO pojo = service.removeEmployee(id);
						// success 
						return new ResponseEntity<EmployeeResponse>(
								new EmployeeResponse("OK", "Employee data removed. ", null, list), HttpStatus.FOUND);
				}
				// failure
				return new ResponseEntity<EmployeeResponse>(
						new EmployeeResponse("FAIL", "Employee data not found. ", null, list), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<EmployeeResponse>(new EmployeeResponse("FAIL","Please login to proceed. ",null,null),HttpStatus.NOT_FOUND);
	}
	

	@PostMapping("/update/{id}/{name}/{email}/{contact}/{designation}/{salary}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable int id, @PathVariable String name,
			@PathVariable String email, @PathVariable long contact, @PathVariable String designation,
			@PathVariable double salary,@SessionAttribute(name = "login",required = false)AdminPOJO login) {
			if(login != null) {
				// Success
				EmployeePOJO pojo = service.updateEmployee(id, name, email, contact, designation, salary);
				if(pojo != null) {
				return new ResponseEntity<EmployeeResponse>(
						new EmployeeResponse("OK", "Employee data updated. ", pojo, null), HttpStatus.FOUND);
			}
			// Failure
			return new ResponseEntity<EmployeeResponse>(
					new EmployeeResponse("OK", "Employee data not found. ", null, null), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<EmployeeResponse>(new EmployeeResponse("FAIL","Please login to proceed. ",null,null),HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/createadmin")
	public ResponseEntity<AdminResponse> createAdmin(@RequestBody AdminPOJO admin) {
		AdminPOJO pojo = adminService.addAdmin(admin);
		if (pojo != null) {
			// success
			return new ResponseEntity<AdminResponse>(new AdminResponse("OK", "Account created successfully..!! "),
					HttpStatus.ACCEPTED);
		}
		// failure
		return new ResponseEntity<AdminResponse>(new AdminResponse("FAIL", "Account not created..!! "),
				HttpStatus.BAD_REQUEST);
	}
	@PostMapping( "/login/{username}/{password}")
	public ResponseEntity<AdminResponse> login(@PathVariable String username, @PathVariable String password,
			HttpServletRequest request) {
		AdminPOJO admin = adminService.login(username, password);
		if (admin != null) {
			// success
			HttpSession session = request.getSession();
			session.setAttribute("login", admin);
			return new ResponseEntity<AdminResponse>(new AdminResponse("OK", "Account Login Successfully"),
					HttpStatus.FOUND);
		}
		// failure
		return new ResponseEntity<AdminResponse>(new AdminResponse("FAIL", "Invalid Password or username"),
				HttpStatus.NOT_FOUND);

	}

	@GetMapping("/logout")
	public ResponseEntity<AdminResponse> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<AdminResponse>(new AdminResponse("OK", "Logged out Successfully..!!"),
				HttpStatus.ACCEPTED);
	}
}
