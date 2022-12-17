package com.testcode.masterservice.repository;

import com.testcode.masterservice.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, Integer> {
}
