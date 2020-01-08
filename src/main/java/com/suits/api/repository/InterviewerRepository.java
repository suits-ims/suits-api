package com.suits.api.repository;

import com.suits.api.entity.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {
    List<Interviewer> findAllByGuidIn(List<UUID> guids);

    Optional<Interviewer> findByGuid(UUID guid);
}
