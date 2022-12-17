package com.testcode.masterservice.repository;

import com.testcode.masterservice.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficerRepository extends JpaRepository<Officer, Integer> {
}
