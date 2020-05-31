package com.yearup.controller;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yearup.service.ComputeResultService;

@RestController
public class ComputeResultController {
	
	@Inject
	private ComputeResultService computeResultService;
	
	
	@RequestMapping(value="/computeresult", method=RequestMethod.GET)
	public void computeResult(@RequestParam Long pollId) {
		computeResultService.computeResult(pollId);
	}

}
