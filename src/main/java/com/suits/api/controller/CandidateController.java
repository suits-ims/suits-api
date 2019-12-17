package com.suits.api.controller;

import com.suits.api.entity.Candidate;
import com.suits.api.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private CandidateService candidateService;

    @GetMapping
    public List<Candidate> getCandidates() {
        return candidateService.getCandidates();
    }
}

