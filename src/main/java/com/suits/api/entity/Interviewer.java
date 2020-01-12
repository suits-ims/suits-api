package com.suits.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Interviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long interviewerId;

    @Column(unique = true)
    private UUID guid;

    @ManyToMany(mappedBy = "interviewers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Candidate> candidates;

    public void addCandidate(final Candidate candidate) {
        candidates.add(candidate);
        candidate.getInterviewers().add(this);
    }
}
