package com.mink.demo.bowlingapi.model.util;

import com.google.common.collect.Lists;
import com.mink.demo.bowlingapi.model.entities.BowlType;
import com.mink.demo.bowlingapi.model.entities.Frame;
import com.mink.demo.bowlingapi.model.entities.Scorecard;
import com.mink.demo.bowlingapi.model.entities.Shot;

import java.util.List;

public class FrameUtil {


    private static int STRIKE = 10;
    private static int SPARE = 10;
    private static int MAX_FRAMES = 10;
    private static int FIRST_SHOT = 1;
    private static int SECOND_SHOT = 2;

    public static BowlType getType(Frame frame) {

        if (getShot(frame, FIRST_SHOT) == STRIKE) {
            return BowlType.STRIKE;
        } else if (getShot(frame, FIRST_SHOT) + getShot(frame, SECOND_SHOT) == SPARE) {
            return BowlType.SPARE;
        } else if (getShot(frame, FIRST_SHOT) + getShot(frame, SECOND_SHOT) < SPARE) {
            return BowlType.OPEN;
        }

        return BowlType.IN_PROGRESS;
    }

    public static int getShot(Frame frame, int shot) {
        if (frame != null) {
            List<Shot> shots = frame.getShots();
            if (shots != null && shots.size() >= shot) {
                return shots.get(shot - 1).getPins();
            }
        }

        return 0;
    }

    public static boolean inProgress(Frame frame) {
        return frame == null || frame.getShots().size() == 1 && frame.getShots().get(0).getPins() != STRIKE;

    }

    public static int getScoreForFrame(Frame frame) {

        int scoreResult = 0;

        if (frame.getFrameNumber() != MAX_FRAMES) {
            if (getType(frame).equals(BowlType.OPEN)) {
                scoreResult += getShot(frame, FIRST_SHOT) + getShot(frame, SECOND_SHOT);
            } else if (getType(frame).equals(BowlType.SPARE)) {
                scoreResult += getShot(frame, FIRST_SHOT) + getShot(frame, SECOND_SHOT);
                scoreResult += getShot(frame.getNextFrame(), FIRST_SHOT);
            } else if (getType(frame).equals(BowlType.STRIKE)) {
                scoreResult += getShot(frame, FIRST_SHOT);
                if (getType(frame.getNextFrame()).equals(BowlType.STRIKE)) {
                    scoreResult += getShot(frame.getNextFrame(), FIRST_SHOT);
                    // If there is a next frame get second ball from it
                    if (frame.getNextFrame().getNextFrame() != null) {
                        scoreResult += getShot(frame.getNextFrame().getNextFrame(), FIRST_SHOT);
                        // The 9th frame will get pins from 2nd shot
                    } else if (frame.getFrameNumber() == MAX_FRAMES - 1) {
                        scoreResult += getShot(frame.getNextFrame(), SECOND_SHOT);
                    }
                } else {
                    scoreResult += getShot(frame.getNextFrame(), FIRST_SHOT) + getShot(frame.getNextFrame(), SECOND_SHOT);
                }
            }
        } else {
            List<Shot> shots = frame.getShots();
            for (Shot shot : shots) {
                scoreResult += shot.getPins();
            }
        }
        return scoreResult;

    }

    public static Shot addShotToScorecard(Scorecard scorecard, int frameNumber, Shot shot) {

        // Check if current frame is full
        if (frameNumber != 10 && (frameNumber == 0 ||
                scorecard.getFrames().get(frameNumber - 1) == null ||
                scorecard.getFrames().get(frameNumber - 1).getShots().get(0).getPins() == 10)) {

            List<Shot> shots = Lists.newArrayList(shot);
            Frame frame = new Frame();
            frame.setShots(shots);
            frame.setScorecard(scorecard);
            frame.setFrameNumber(frameNumber + 1);
            shot.setFrame(frame);

            // Set next frame on previous to the newly created frame
            if (frameNumber > 0) {
                List<Frame> frames = scorecard.getFrames();
                frames.get(frames.size() - 1).setNextFrame(frame);
            }

        } else {

            Frame currentFrame = scorecard.getFrames().get(frameNumber - 1);

            if (frameNumber < 10 && currentFrame.getShots().get(0).getPins() + shot.getPins() > 10) {
                throw new RuntimeException("Pins cannot exceed 10 for a given frame");
            }

            if ((frameNumber == 10 && currentFrame.getShots().get(0).getPins() + shot.getPins() < 10) ||
                    (frameNumber == 10 && currentFrame.getShots().size() == 3)) {
                throw new RuntimeException("Game is complete, cannot add more frames");
            }

            currentFrame.getShots().add(shot);
            shot.setFrame(currentFrame);
        }

        return shot;

    }


}
