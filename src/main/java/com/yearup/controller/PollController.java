package com.yearup.controller;

import java.util.Optional;

import javax.inject.Inject;

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
	public Iterable<Poll> getAllPolls() {
		return pollService.getAllPolls();
	}
	
	@RequestMapping(value="/polls", method=RequestMethod.POST)
	public void createPoll(@RequestBody Poll poll) {
		pollService.createPoll(poll);
	}
	
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
	public Optional<Poll> getPoll(@PathVariable Long pollId) {
		return pollService.getPoll(pollId);
	}
	
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
	public void updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
		pollService.updatePoll(poll, pollId);
	}
	
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
	public void deletePoll(@PathVariable Long pollId) {
		pollService.deletePoll(pollId);
	}
	
 
}
