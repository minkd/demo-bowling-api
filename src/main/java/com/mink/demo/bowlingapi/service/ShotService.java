package com.mink.demo.bowlingapi.service;

import com.mink.demo.bowlingapi.model.entities.Scorecard;
import com.mink.demo.bowlingapi.model.entities.Shot;
import com.mink.demo.bowlingapi.model.repositories.BowlerRepository;
import com.mink.demo.bowlingapi.model.repositories.GameRepository;
import com.mink.demo.bowlingapi.model.repositories.ScorecardRepository;
import com.mink.demo.bowlingapi.model.repositories.ShotRepository;
import com.mink.demo.bowlingapi.service.dto.ShotRequest;
import com.mink.demo.bowlingapi.service.dto.ShotResponse;
import com.mink.demo.bowlingapi.service.mapper.ShotMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mink.demo.bowlingapi.model.util.FrameUtil.addShotToScorecard;

@Slf4j
@Service
@Transactional
public class ShotService {

    private final ShotRepository shotRepository;

    private final GameRepository gameRepository;

    private final BowlerRepository bowlerRepository;

    private final ScorecardRepository scorecardRepository;

    private final ShotMapper shotMapper;

    @Autowired
    public ShotService(ShotRepository shotRepository, GameRepository gameRepository, BowlerRepository bowlerRepository, ScorecardRepository scorecardRepository, ShotMapper shotMapper) {
        this.shotRepository = shotRepository;
        this.gameRepository = gameRepository;
        this.bowlerRepository = bowlerRepository;
        this.scorecardRepository = scorecardRepository;
        this.shotMapper = shotMapper;
    }

    public void addShotToFrameByGameIdByBowlerId(Long gameId, Long bowlerId, ShotRequest shotRequest) {

        Scorecard scorecard = scorecardRepository.findOneByGameIdAndBowlerId(gameId, bowlerId);

        if (scorecard == null) {
            scorecard = createScorecardForBowler(gameId, bowlerId);
        }

        Shot genericShotRequest = shotMapper.shotRequestToShot(shotRequest);
        Shot framedShotRequest = addShotToScorecard(scorecard, scorecard.getFrames().size(), genericShotRequest);
        shotRepository.save(framedShotRequest);
    }

    private Scorecard createScorecardForBowler(long gameId, long bowlerId) {
        Scorecard scorecard = new Scorecard();
        scorecard.setBowler(bowlerRepository.getOne(bowlerId));
        scorecard.setGame(gameRepository.getOne(gameId));
        return scorecardRepository.save(scorecard);
    }

}
