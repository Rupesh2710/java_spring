package com.crowdfundingapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crowdfundingapplication.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
