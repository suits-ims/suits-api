package com.suits.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long candidateId;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String bio;

    private String department;

    private LocalDate birthday;

    @CreatedDate
    private LocalDate creationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "managerId", nullable = false)
    private Manager manager;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "candidateId")},
            inverseJoinColumns = {@JoinColumn(name = "interviewerId")})
    private List<Interviewer> interviewers;

    public void addInterviewer(final Interviewer interviewer) {
        interviewers.add(interviewer);
        interviewer.getCandidates().add(this);
    }
}
