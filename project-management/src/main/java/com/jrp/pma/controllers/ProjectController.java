package com.jrp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired //we don't have to manually create instance for ProjectRepository, spring framework will do it
	ProjectRepository proRepo;
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project();
		
		model.addAttribute("project", aProject);
		return "projects/new-project.html";
	}
	
	@PostMapping("/save")
	public String createProjectForm(Employee employee, Project project, Model model) {
		//this should handel saving to the database...
		proRepo.save(project);		
		//user a redirect to prevent duplicate submissions
		return "redirect:/projects/new";
	}	
}






