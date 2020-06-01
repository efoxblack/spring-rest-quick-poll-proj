package com.yearup.controller;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yearup.domain.Poll;
import com.yearup.service.PollService;

@RestController
public class PollController {
	
	@Inject
	private PollService pollService;
	
	@RequestMapping(value="/polls", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Poll>> getAllPolls() {
		Iterable<Poll> allPolls = pollService.getAllPolls();
		return new ResponseEntity<>(allPolls, HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls", method=RequestMethod.POST)
	public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
		return pollService.createPoll(poll);
	}
	
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
	public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
		Optional<Poll> p = pollService.getPoll(pollId);
		return new ResponseEntity<>(p, HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
	public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
		Poll p = pollService.updatePoll(poll, pollId);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
		pollService.deletePoll(pollId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
 
}
