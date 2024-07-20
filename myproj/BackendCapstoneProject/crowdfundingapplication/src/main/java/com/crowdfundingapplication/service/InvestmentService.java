package com.crowdfundingapplication.service;

import java.util.List;

import com.crowdfundingapplication.dto.InvestmentDTO;

public interface InvestmentService {
	InvestmentDTO createInvestment(InvestmentDTO investmentDTO);

	InvestmentDTO updateInvestment(Long investmentId, InvestmentDTO investmentDTO);

	boolean removeInvestment(Long investmentId);

	InvestmentDTO fetchInvestmentById(Long investmentId);

	List<InvestmentDTO> fetchInvestmentsByProjectId(Long projectId);

	List<InvestmentDTO> fetchInvestmentsByInvestorName(String investorName);
}
