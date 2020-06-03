package com.yearup.controller;

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

import com.yearup.domain.Vote;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class VoteController {

	@Autowired
	private VoteService voteService;

	@RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Vote>> getAllPolls(@PathVariable Long pollId) {
		Iterable<Vote> v = voteService.getAllVotes(pollId);
		return new ResponseEntity<>(v, HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.POST)
	public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
		voteService.createVote(pollId, vote);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

}
