package com.yearup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.yearup.domain.Vote;
import com.yearup.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yearup.domain.Poll;
import com.yearup.exception.ResourceNotFoundException;
import com.yearup.repository.PollRepository;

@Service
public class PollService {
	
	@Autowired
	private PollRepository pollRepository;

	@Autowired
	private VoteRepository voteRepository;
	
	public Iterable<Poll> getAllPolls() {
		List<Poll> pollList = new ArrayList<>();
		pollRepository.findAll().forEach(pollList::add);
		return pollList;
	}
	
	public void createPoll(Poll poll) {
		pollRepository.save(poll);
	}
	
	protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
		Optional<Poll> poll = pollRepository.findById(pollId);
		if(!poll.isPresent()) {
			throw new ResourceNotFoundException("Poll with id " + pollId + " not found"); 
		}
	}
	
	public Optional<Poll> getPollById(Long pollId) {
		verifyPoll(pollId);
		return pollRepository.findById(pollId);
	}
	
	public void updatePoll(Poll poll, Long pollId) {
		verifyPoll(pollId);
		pollRepository.save(poll);
	}
	
	public void deletePoll(Long pollId) {
		verifyPoll(pollId);
		Iterable<Vote> vote = voteRepository.findByPoll(pollId);
		voteRepository.deleteAll(vote);
		pollRepository.deleteById(pollId);
	}

}
