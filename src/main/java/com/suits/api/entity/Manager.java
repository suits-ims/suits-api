package com.suits.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long managerId;

    @Column(unique = true, nullable = false)
    private UUID guid;

    @Builder.Default
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Candidate> candidates = new ArrayList<>();

    public void addCandidate(final Candidate candidate) {
        candidates.add(candidate);
        candidate.setManager(this);
    }
}
