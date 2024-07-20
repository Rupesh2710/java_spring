package com.crowdfundingapplication.controller;

import com.crowdfundingapplication.dto.InvestmentDTO;
import com.crowdfundingapplication.service.InvestmentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/investments")
public class InvestmentController {

	@Autowired
	private InvestmentService investmentService;

	@GetMapping("/{investmentId}")
	public ResponseEntity<InvestmentDTO> fetchInvestmentById(@PathVariable Long investmentId) {
		return new ResponseEntity<>(investmentService.fetchInvestmentById(investmentId), HttpStatus.OK);
	}

	@GetMapping("/project/{projectId}")
	public ResponseEntity<List<InvestmentDTO>> fetchInvestmentsByProjectId(@PathVariable Long projectId) {
		List<InvestmentDTO> investments = investmentService.fetchInvestmentsByProjectId(projectId);
		return new ResponseEntity<>(investments, HttpStatus.OK);
	}

	@PostMapping(" ")
	public ResponseEntity<InvestmentDTO> createInvestment(@Valid @RequestBody InvestmentDTO investmentDTO) {
		return new ResponseEntity<>(investmentService.createInvestment(investmentDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{investmentId}")
	public ResponseEntity<InvestmentDTO> updateInvestment(@PathVariable Long investmentId,
			@Valid @RequestBody InvestmentDTO investmentDTO) {
		return new ResponseEntity<>(investmentService.updateInvestment(investmentId, investmentDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{investmentId}")
	public ResponseEntity<Void> removeInvestment(@PathVariable Long investmentId) {
		investmentService.removeInvestment(investmentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/investor/{investorName}")
	public ResponseEntity<List<InvestmentDTO>> fetchInvestmentsByInvestorName(@PathVariable String investorName) {
		List<InvestmentDTO> investments = investmentService.fetchInvestmentsByInvestorName(investorName);
		return new ResponseEntity<>(investments, HttpStatus.OK);
	}
}
