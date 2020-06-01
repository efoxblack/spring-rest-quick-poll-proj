package com.yearup.service;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yearup.domain.Vote;
import com.yearup.repository.VoteRepository;

@Service
public class VoteService {
	
	@Inject
	private VoteRepository voteRepository;
	
	public Iterable<Vote> getAllVotesByPollId(Long pollId) {
		return voteRepository.findByPoll(pollId);
	}
	
	public void createVote(Long pollId, Vote vote) {
		vote = voteRepository.save(vote);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(ServletUriComponentsBuilder.
		fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
	}

}
