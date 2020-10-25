package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		Map<String, Object> map = new HashMap<>();

		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);
//----------------------------------------------------------------------------------------------
		
		List<ChartData> projectData = proRepo.getProjectStatus();
		
		//Lets convert projectData object into a JSON structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		
		//[["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]] => Expected JSON string
		//We need to send this JSON object to our VIEW => use model to do this!
		
		model.addAttribute("projectStatusCnt", jsonString);
//----------------------------------------------------------------------------------------------	
		
		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		model.addAttribute("employeeListProjectsCnt", employeesProjectCnt);

		
		
		
		
		return "main/home";
	}
}
