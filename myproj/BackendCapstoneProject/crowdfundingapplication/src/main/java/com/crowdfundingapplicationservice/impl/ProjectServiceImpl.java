package com.crowdfundingapplicationservice.impl;

import com.crowdfundingapplication.dto.InvestmentDTO;
import com.crowdfundingapplication.dto.ProjectDTO;
import com.crowdfundingapplication.entity.Investment;
import com.crowdfundingapplication.entity.Project;
import com.crowdfundingapplication.exception.ResourceNotFoundException;
import com.crowdfundingapplication.repo.ProjectRepository;
import com.crowdfundingapplication.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProjectDTO fetchProjectById(Long projectId) {
		Optional<Project> optionalProject = projectRepository.findById(projectId);
		if(optionalProject.isEmpty()) {
			throw new ResourceNotFoundException("Project Not Found");
		}
		Project project = optionalProject.get();
		return modelMapper.map(project, ProjectDTO.class);
	}

	@Override
	public List<ProjectDTO> fetchAllProjects() {
		List<Project> projects = projectRepository.findAll();
		return projects.stream().map(project -> modelMapper.map(project, ProjectDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProjectDTO createProject(ProjectDTO projectDTO) {
		Project project = modelMapper.map(projectDTO, Project.class);
		projectRepository.save(project);
		return modelMapper.map(project, ProjectDTO.class);
	}

	@Override
	public ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO) {
		Optional<Project> optionalProject = projectRepository.findById(projectId);
		if(optionalProject.isEmpty()) {
			throw new ResourceNotFoundException("Project Not Found");
		}
		Project project = optionalProject.get();
		project.setId(projectDTO.getId());
		project.setName(projectDTO.getName());
		project.setDescription(projectDTO.getDescription());
		project.setGoalAmount(projectDTO.getGoalAmount());
		project.setAmountRaised(projectDTO.getAmountRaised());
		projectRepository.save(project);
		return modelMapper.map(project, ProjectDTO.class);
	}

	@Override
	public boolean removeProject(Long projectId) {
		Optional<Project> optionalProject = projectRepository.findById(projectId);
		if(optionalProject.isEmpty()) {
			throw new ResourceNotFoundException("Project Not Found");
		}
		Project project = optionalProject.get();
		projectRepository.delete(project);
		return true;
	}
}
