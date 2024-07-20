package com.crowdfundingapplication.controller;

import com.crowdfundingapplication.dto.ProjectDTO;
import com.crowdfundingapplication.service.ProjectService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping("/{projectId}")
	public ResponseEntity<ProjectDTO> fetchProjectById(@PathVariable Long projectId) {
		return new ResponseEntity<>(projectService.fetchProjectById(projectId), HttpStatus.OK);
	}

	@GetMapping("") 
	public ResponseEntity<List<ProjectDTO>> fetchAllProjects() {
		List<ProjectDTO> projects = projectService.fetchAllProjects();
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectDTO projectDTO) {
		return new ResponseEntity<>(projectService.createProject(projectDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{projectId}")
	public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long projectId,
			@Valid @RequestBody ProjectDTO projectDTO) {
		return new ResponseEntity<>(projectService.updateProject(projectId, projectDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{projectId}")
	public ResponseEntity<Void> removeProject(@PathVariable Long projectId) {
		projectService.removeProject(projectId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
