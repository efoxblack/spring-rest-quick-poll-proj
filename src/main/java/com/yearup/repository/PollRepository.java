package com.yearup.repository;

import org.springframework.data.repository.CrudRepository;

import com.yearup.domain.Poll;

public interface PollRepository extends CrudRepository<Poll, Long> {

}
