package com.suits.api.service;

import com.suits.api.entity.Candidate;
import com.suits.api.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidateService {

    private CandidateRepository candidateRepository;

    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }
}
