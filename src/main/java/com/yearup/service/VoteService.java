package com.yearup.service;

import com.yearup.domain.Vote;
import com.yearup.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public Iterable<Vote> getAllVotes(Long pollId) {
        return voteRepository.findByPoll(pollId);
    }

    public void createVote(Long pollId, Vote vote) {
        vote = voteRepository.save(vote);
    }

}
