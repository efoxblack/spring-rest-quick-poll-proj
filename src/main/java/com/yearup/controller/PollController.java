package com.yearup.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.yearup.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yearup.domain.Poll;
import com.yearup.service.PollService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PollController {

	@Autowired
	private VoteService voteService;
	
	@Autowired
	private PollService pollService;
	
	@RequestMapping(value="/polls", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Poll>> getAllPolls() {
		Iterable<Poll> allPolls = pollService.getAllPolls();
		return new ResponseEntity<>(allPolls, HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls", method=RequestMethod.POST)
	public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
		pollService.createPoll(poll);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(poll.getId())
				.toUri();
		responseHeaders.setLocation(newPollUri);

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
	public ResponseEntity<?> getPollById(@PathVariable Long pollId) {
		Optional<Poll> p = pollService.getPollById(pollId);
		return new ResponseEntity<>(p, HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
	public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
		pollService.updatePoll(poll, pollId);
		return new ResponseEntity<>(poll, HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
		pollService.deletePoll(pollId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
 
}
