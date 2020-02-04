package com.suits.api.controller;

import com.suits.api.dto.CandidateListItemResponseDto;
import com.suits.api.dto.CandidateResponseDto;
import com.suits.api.dto.NewCandidateDto;
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
    public List<CandidateListItemResponseDto> getCandidates() {
        return candidateService.getCandidates();
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_manager"})
    @ResponseBody
    public CandidateResponseDto getCandidateById(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
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
    public Long addCandidate(@RequestBody final NewCandidateDto candidate) {
        return candidateService.addCandidate(candidate);
    }
}

