package com.mink.demo.bowlingapi.model.repositories;

import com.mink.demo.bowlingapi.model.entities.Frame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrameRepository extends JpaRepository<Frame, Long> {
}
