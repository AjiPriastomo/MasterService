package com.testcode.masterservice.repository;

import com.testcode.masterservice.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}
