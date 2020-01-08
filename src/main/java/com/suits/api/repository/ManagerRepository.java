package com.suits.api.repository;

import com.suits.api.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByGuid(UUID guid);
}
