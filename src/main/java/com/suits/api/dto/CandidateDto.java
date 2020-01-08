package com.suits.api.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class CandidateDto {
    private Long candidateId;

    private String firstName;

    private String lastName;

    private String email;

    private String bio;

    private String department;

    private LocalDate birthday;

    private List<UUID> interviewers;
}
