package com.crowdfundingapplicationservice.impl;

import com.crowdfundingapplication.dto.InvestmentDTO;
import com.crowdfundingapplication.entity.Investment;
import com.crowdfundingapplication.entity.Project;
import com.crowdfundingapplication.exception.ResourceNotFoundException;
import com.crowdfundingapplication.repo.InvestmentRepository;
import com.crowdfundingapplication.repo.ProjectRepository;
import com.crowdfundingapplication.service.InvestmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvestmentServiceImpl implements InvestmentService {

	@Autowired
	private InvestmentRepository investmentRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public InvestmentDTO fetchInvestmentById(Long investmentId) {
		Optional<Investment> optionalInvestment = investmentRepository.findById(investmentId);
		if (optionalInvestment.isEmpty()) {
			throw new ResourceNotFoundException("Investment Not Found");
		}
		Investment investment = optionalInvestment.get();
		return modelMapper.map(investment, InvestmentDTO.class);
	}

	@Override
	public List<InvestmentDTO> fetchInvestmentsByProjectId(Long projectId) {
		List<Investment> investments = investmentRepository.findByProjectId(projectId);
		return investments.stream().map(investment -> modelMapper.map(investment, InvestmentDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public InvestmentDTO createInvestment(InvestmentDTO investmentDTO) {
		Investment investment = modelMapper.map(investmentDTO, Investment.class);
		Optional<Project> optionalProject = projectRepository.findById(investmentDTO.getProjectId());
		if (optionalProject.isEmpty()) {
			throw new ResourceNotFoundException("Project Not Found");
		}
		Project project = optionalProject.get();
		investment.setProject(project);
		investmentRepository.save(investment);
		return modelMapper.map(investment, InvestmentDTO.class);
	}

	@Override
	public InvestmentDTO updateInvestment(Long investmentId, InvestmentDTO investmentDTO) {
		Optional<Investment> optionalInvestment = investmentRepository.findById(investmentId);
		if (optionalInvestment.isEmpty()) {
			throw new ResourceNotFoundException("Investment Not Found");
		}
		Investment investment = optionalInvestment.get();
		investment.setAmount(investmentDTO.getAmount());
		investment.setInvestorName(investmentDTO.getInvestorName());
		investmentRepository.save(investment);
		return modelMapper.map(investment, InvestmentDTO.class);
	}

	@Override
	public boolean removeInvestment(Long investmentId) {
		Optional<Investment> optionalInvestment = investmentRepository.findById(investmentId);
		if (optionalInvestment.isEmpty()) {
			throw new ResourceNotFoundException("Investment Not Found");
		}
		Investment investment = optionalInvestment.get();
		investmentRepository.delete(investment);
		return true;
	}

	@Override
	public List<InvestmentDTO> fetchInvestmentsByInvestorName(String investorName) {
		List<Investment> investments = investmentRepository.findByInvestorName(investorName);
		return investments.stream().map(investment -> modelMapper.map(investment, InvestmentDTO.class))
				.collect(Collectors.toList());
	}

}
