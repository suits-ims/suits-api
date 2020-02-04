package com.suits.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CandidateListItemResponseDto {

    private Long candidateId;

    private String firstName;

    private String lastName;

    private String email;

    private String department;

    private LocalDate birthday;

    private LocalDate creationDate;
}
