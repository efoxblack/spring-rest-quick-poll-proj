package com.yearup.service;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yearup.domain.Poll;
import com.yearup.repository.PollRepository;

@Service
public class PollService {
	
	@Inject
	private PollRepository pollRepository;
	
	
	public ResponseEntity<Iterable<Poll>> getAllPolls() {
		Iterable<Poll> allPolls = pollRepository.findAll();
		return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<?> createPoll(Poll poll) {
		poll = pollRepository.save(poll);
		
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
	
	public ResponseEntity<?> getPoll(Long pollId) {
		Optional<Poll> p = pollRepository.findById(pollId);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	
	public ResponseEntity<?> updatePoll(Poll poll, Long pollId) {
		Poll p = pollRepository.save(poll);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
		pollRepository.deleteById(pollId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
