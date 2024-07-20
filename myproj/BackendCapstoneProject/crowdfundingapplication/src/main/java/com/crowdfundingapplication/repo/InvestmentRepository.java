package com.crowdfundingapplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crowdfundingapplication.entity.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
	List<Investment> findByProjectId(Long projectId);

	List<Investment> findByInvestorName(String investorName);
}
