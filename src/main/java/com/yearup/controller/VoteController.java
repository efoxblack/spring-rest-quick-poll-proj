package com.yearup.controller;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yearup.domain.Vote;
import com.yearup.service.VoteService;

@RestController
public class VoteController {
	
	@Inject
	private VoteService voteService;
	
	@RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
	public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
		return voteService.getAllVotesByPollId(pollId);
	}
	
	@RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.POST)
	public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
		return voteService.createVote(pollId, vote);
	}

}
