package com.mink.demo.bowlingapi.service;

import com.mink.demo.bowlingapi.model.entities.Scorecard;
import com.mink.demo.bowlingapi.model.repositories.ScorecardRepository;
import com.mink.demo.bowlingapi.service.dto.ScorecardResponse;
import com.mink.demo.bowlingapi.service.mapper.ScorecardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ScorecardService {

    private final ScorecardRepository scorecardRepository;

    private final ScorecardMapper scorecardMapper;

    @Autowired
    public ScorecardService(ScorecardRepository scorecardRepository, ScorecardMapper scorecardMapper) {
        this.scorecardRepository = scorecardRepository;
        this.scorecardMapper = scorecardMapper;
    }

    public List<ScorecardResponse> getScorecardsByGameId(Long gameId) {

        List<Scorecard> scorecardsByGameId = scorecardRepository.findScorecardsByGameId(gameId);
        System.out.println(scorecardsByGameId);


        return scorecardMapper.scorecardsToScorecardResponses(scorecardRepository.findScorecardsByGameId(gameId));
    }

    public ScorecardResponse getScorecardByGameIdById(Long gameId, Long scorecardId) {
        return scorecardMapper.scorecardToScorecardResponse(scorecardRepository.findOneByGameIdAndId(gameId, scorecardId));
    }

    public void deleteScorecardGameIdById(Long gameId, Long scorecardId) {
        scorecardRepository.deleteByGameIdAndId(gameId, scorecardId);
    }

}
