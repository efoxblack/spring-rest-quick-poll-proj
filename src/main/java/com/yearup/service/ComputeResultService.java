package com.yearup.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yearup.domain.Vote;
import com.yearup.dto.OptionCount;
import com.yearup.dto.VoteResult;
import com.yearup.repository.VoteRepository;

@Service
public class ComputeResultService {
	
	@Inject
	private VoteRepository voteRepository;
	
	public void computeResult(Long pollId) {
		VoteResult voteResult = new VoteResult();
		Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
		
		int totalVotes = 0;
		Map<Long, OptionCount> tempMap = new HashMap<>();
		for(Vote v: allVotes) {
			totalVotes++;
			// Get the OptionCount corresponding to this Option
			OptionCount optionCount = tempMap.get(v.getOption().getId());
			if(optionCount == null) {
				optionCount = new OptionCount();
				optionCount.setOptionId(v.getOption().getId());
				tempMap.put(v.getOption().getId(), optionCount);
			}
			optionCount.setCount(optionCount.getCount() + 1);
		}
		voteResult.setTotalVotes(totalVotes);
		voteResult.setResults(tempMap.values());
	}
}
