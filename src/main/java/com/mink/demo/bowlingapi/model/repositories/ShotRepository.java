package com.mink.demo.bowlingapi.model.repositories;

import com.mink.demo.bowlingapi.model.entities.Shot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShotRepository extends JpaRepository<Shot, Long> {

    Shot findShotsByFrameId(Long frameId);

}
