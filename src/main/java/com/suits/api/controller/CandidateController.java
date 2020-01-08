package com.suits.api.controller;

import com.suits.api.dto.CandidateDto;
import com.suits.api.dto.CandidateResponseDto;
import com.suits.api.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private CandidateService candidateService;

    @GetMapping
    @Secured({"ROLE_manager"})
    @ResponseBody
    public List<CandidateResponseDto> getCandidates() {
        return candidateService.getCandidates();
    }

    @GetMapping("/managedByMe")
    @Secured({"ROLE_interviewer"})
    @ResponseBody
    public List<CandidateResponseDto> getInterviewedByMe() {
        return candidateService.getInterviewedByMe();
    }

    @GetMapping("/interviewedByMe")
    @Secured({"ROLE_manager"})
    @ResponseBody
    public List<CandidateResponseDto> getManagedByMe() {
        return candidateService.getManagedByMe();
    }

    @PostMapping
    @Secured("ROLE_manager")
    public void addCandidate(@RequestBody final CandidateDto candidateDto) {
        candidateService.addCandidate(candidateDto);
    }
}

