package com.suits.api.mapper;

import com.suits.api.dto.CandidateDto;
import com.suits.api.dto.CandidateResponseDto;
import com.suits.api.entity.Candidate;
import com.suits.api.entity.Interviewer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class CandidateMapper {
    public abstract Candidate toCandidate(CandidateDto candidateDto);

    public Interviewer toInterviewer(final UUID uuid) {
        return Interviewer.builder().guid(uuid).build();
    }

    public abstract List<CandidateResponseDto> toCandidateResponseDtoList(List<Candidate> candidates);

    @Mappings({@Mapping(target = "managerGuid", source = "candidate.manager.guid")})
    public abstract CandidateResponseDto toCandidateResponseDto(Candidate candidate);

    public abstract List<UUID> toInterviewerGuides(Collection<Interviewer> interviewers);

    public UUID toInterviewerGuid(Interviewer interviewer) {
        return interviewer.getGuid();
    }
}
