package com.suits.api;

import com.suits.api.entity.Candidate;
import com.suits.api.repository.CandidateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InterviewManagementSystemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewManagementSystemApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(final CandidateRepository candidateRepository) {
        return (args) -> {
            candidateRepository.save(Candidate.builder().email("candidate1@email.com").build());
            candidateRepository.save(Candidate.builder().email("candidate2@email.com").build());
            candidateRepository.save(Candidate.builder().email("candidate3@email.com").build());
        };
    }
}
