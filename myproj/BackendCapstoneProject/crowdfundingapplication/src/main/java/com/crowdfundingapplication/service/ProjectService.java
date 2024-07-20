package com.crowdfundingapplication.service;

import java.util.List;

import com.crowdfundingapplication.dto.ProjectDTO;

public interface ProjectService {
	ProjectDTO createProject(ProjectDTO projectDTO);

	ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO);

	boolean removeProject(Long projectId);

	ProjectDTO fetchProjectById(Long projectId);

	List<ProjectDTO> fetchAllProjects();
}
