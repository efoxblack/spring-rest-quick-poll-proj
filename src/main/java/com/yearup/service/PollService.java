package com.yearup.service;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yearup.domain.Poll;
import com.yearup.exception.ResourceNotFoundException;
import com.yearup.repository.PollRepository;

@Service
public class PollService {
	
	@Inject
	private PollRepository pollRepository;
	
	public Iterable<Poll> getAllPolls() {
		return pollRepository.findAll();
	}
	
	public void createPoll(Poll poll) {
		poll = pollRepository.save(poll);
		
		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder
											  .fromCurrentRequest()
											  .path("/{id}")
											  .buildAndExpand(poll.getId())
											  .toUri();
		responseHeaders.setLocation(newPollUri);
	}
	
	protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
		Optional<Poll> poll = pollRepository.findById(pollId);
		if(poll == null) {
			throw new ResourceNotFoundException("Poll with id " + pollId + " not found"); 
		}
	}
	
	public Optional<Poll> getPoll(Long pollId) {
		verifyPoll(pollId);
		return pollRepository.findById(pollId);
	}
	
	public void updatePoll(Poll poll, Long pollId) {
		verifyPoll(pollId);
		pollRepository.save(poll);
	}
	
	public void deletePoll(Long pollId) {
		verifyPoll(pollId);
		pollRepository.deleteById(pollId);
	}

}
