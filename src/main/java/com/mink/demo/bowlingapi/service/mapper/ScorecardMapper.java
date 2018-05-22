package com.mink.demo.bowlingapi.service.mapper;

import com.mink.demo.bowlingapi.model.entities.Frame;
import com.mink.demo.bowlingapi.model.entities.Scorecard;
import com.mink.demo.bowlingapi.service.dto.FrameResponse;
import com.mink.demo.bowlingapi.service.dto.ScorecardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.mink.demo.bowlingapi.model.util.FrameUtil.getScoreForFrame;

@Component
public class ScorecardMapper {



    @Autowired
    private ShotMapper shotMapper;

    public ScorecardResponse scorecardToScorecardResponse(Scorecard scorecard) {

        int totalScore = 0;
        ScorecardResponse scorecardResponse = new ScorecardResponse();

        List<FrameResponse> frameResponses = new ArrayList<>();
        for (Frame frame : scorecard.getFrames()) {
            FrameResponse frameResponse = new FrameResponse();
            int frameScore = getScoreForFrame(frame);
            totalScore += frameScore;
            frameResponse.setFrame(frame.getFrameNumber());
            frameResponse.setScore(totalScore);
            frameResponse.setShots(shotMapper.shotsToShotResponses(frame.getShots()));
            frameResponses.add(frameResponse);
        }

        scorecardResponse.setFrameResponses(frameResponses);
        scorecardResponse.setBowlerName(scorecard.getBowler().getName());
        scorecardResponse.setTotal(totalScore);

        return scorecardResponse;
    }

    public List<ScorecardResponse> scorecardsToScorecardResponses(List<Scorecard> scorecards) {
        List<ScorecardResponse> scorecardResponses = new ArrayList<>();
        for (Scorecard scorecard : scorecards) {
            scorecardResponses.add(scorecardToScorecardResponse(scorecard));
        }
        return scorecardResponses;
    }






}
