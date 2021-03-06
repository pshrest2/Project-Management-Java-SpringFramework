package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayEmployee(Model model) {
		
		List<Employee> employees = empRepo.findAll(); 
		model.addAttribute("employeeList", employees);
		
		return "employees/list-employees.html";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee anEmployee = new Employee();
		
		model.addAttribute("employee", anEmployee);
		return "employees/new-employee.html";
	}
	
	@PostMapping("/save")
	public String createEmployeeForm(Employee employee, Model model) {
		//this should handel saving to the database...
		empRepo.save(employee);
		
		//user a redirect to prevent duplicate submissions
		return "redirect:/employee";
	}
	
}






