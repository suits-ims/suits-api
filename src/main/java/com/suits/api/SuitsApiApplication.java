package com.suits.api;

import com.suits.api.entity.Candidate;
import com.suits.api.entity.Interviewer;
import com.suits.api.entity.Manager;
import com.suits.api.repository.ManagerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

import static java.util.Collections.singletonList;

@SpringBootApplication
public class SuitsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuitsApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(final ManagerRepository managerRepository) {
        return (args) -> {
            Manager manager = Manager.builder().guid(UUID.randomUUID()).build();

            Interviewer interviewer = Interviewer.builder().guid(UUID.fromString("92ca2c68-dbb6-4fac-b677-71894ba31a7d")).build();

            manager.addCandidate(Candidate.builder().email("candidate1@email.com").interviewers(singletonList(interviewer)).build());
            manager.addCandidate(Candidate.builder().email("candidate2@email.com").interviewers(singletonList(interviewer)).build());
            manager.addCandidate(Candidate.builder().email("candidate3@email.com").interviewers(singletonList(interviewer)).build());
            managerRepository.save(manager);
        };
    }
}
