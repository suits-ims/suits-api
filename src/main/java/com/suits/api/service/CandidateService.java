package com.suits.api.service;

import com.suits.api.dto.CandidateDto;
import com.suits.api.dto.CandidateResponseDto;
import com.suits.api.entity.Candidate;
import com.suits.api.entity.Interviewer;
import com.suits.api.entity.Manager;
import com.suits.api.mapper.CandidateMapper;
import com.suits.api.repository.CandidateRepository;
import com.suits.api.repository.InterviewerRepository;
import com.suits.api.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidateService {

    private CandidateMapper candidateMapper;

    private CandidateRepository candidateRepository;
    private ManagerRepository managerRepository;
    private InterviewerRepository interviewerRepository;

    public List<CandidateResponseDto> getCandidates() {
        return candidateMapper.toCandidateResponseDtoList(candidateRepository.findAll());
    }

    public List<CandidateResponseDto> getInterviewedByMe() {
        final UUID guid = getGuid();
        return candidateMapper.toCandidateResponseDtoList(interviewerRepository.findByGuid(guid).map(Interviewer::getCandidates).orElseGet(ArrayList::new));
    }

    public List<CandidateResponseDto> getManagedByMe() {
        final UUID guid = getGuid();
        return candidateMapper.toCandidateResponseDtoList(managerRepository.findByGuid(guid).map(Manager::getCandidates).orElseGet(ArrayList::new));
    }


    public void addCandidate(final CandidateDto candidateDto) {
        Candidate candidate = candidateMapper.toCandidate(candidateDto);

        candidate.setManager(getManager());
        candidate.setInterviewers(getInterviewers(candidateDto.getInterviewers()));

        candidateRepository.save(candidate);
    }

    private List<Interviewer> getInterviewers(List<UUID> interviewers) {
        Map<UUID, Interviewer> interviewersFromDB = interviewerRepository.findAllByGuidIn(interviewers)
                .stream()
                .collect(Collectors.toMap(Interviewer::getGuid, interviewer -> interviewer));
        return interviewers.stream()
                .map(getInterviewer(interviewersFromDB))
                .collect(Collectors.toList());
    }

    private Function<UUID, Interviewer> getInterviewer(Map<UUID, Interviewer> interviewersFromDB) {
        return uuid -> interviewersFromDB.containsKey(uuid) ? interviewersFromDB.get(uuid) : Interviewer.builder().guid(uuid).build();
    }

    private Manager getManager() {
        final UUID managerGuid = getGuid();
        return managerRepository.findByGuid(managerGuid).orElseGet(() -> Manager.builder().guid(managerGuid).build());
    }

    private UUID getGuid() {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return UUID.fromString(((KeycloakPrincipal) token.getPrincipal()).getName());
    }
}
