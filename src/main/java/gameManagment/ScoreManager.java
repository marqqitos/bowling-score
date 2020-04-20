package gameManagment;

import java.util.List;

import enums.ScoringEnums;
import models.Frame;

public class ScoreManager {

	public ScoreManager() {
	}

	public void setScore(List<Frame> frames) {
		for (Frame frame : frames) {
			Frame previousFrame = frame.getFrameNumber() > 1 ? frames.get(frame.getFrameNumber() - 2) : null;

			if (frame.getKnockedDownPinsFirstRoll() == ScoringEnums.STRIKE.getValue()) {
				setScoreStrike(frame, frames, previousFrame);
			} else if (frame.getKnockedDownPinsFirstRoll() + frame.getKnockedDownPinsSecondRoll() == ScoringEnums.SPARE
					.getValue()) {
				setScoreSpare(frame, frames, previousFrame);
			} else {
				setScoreNoStrikeOrSpare(frame, frames, previousFrame);
			}
		}
	}

	private void setScoreStrike(Frame frame, List<Frame> frames, Frame previousFrame) {
		if (frame.getFrameNumber() < 9) {
			Frame nextFrame = frames.get(frame.getFrameNumber());

			if (nextFrame.getKnockedDownPinsFirstRoll() == ScoringEnums.STRIKE.getValue()) {
				Frame nextTwoFrame = frames.get(nextFrame.getFrameNumber());
				frame.setScore((previousFrame != null ? previousFrame.getScore() : 0) + ScoringEnums.STRIKE.getValue()
						+ ScoringEnums.STRIKE.getValue() + (nextTwoFrame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue() ? 0 : nextTwoFrame.getKnockedDownPinsFirstRoll()));
			} else {
				frame.setScore((previousFrame != null ? previousFrame.getScore() : 0) + ScoringEnums.STRIKE.getValue()
						+ (nextFrame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue() ? 0 : nextFrame.getKnockedDownPinsFirstRoll())
						+ (nextFrame.getKnockedDownPinsSecondRoll() == ScoringEnums.FOUL.getValue() ? 0 : nextFrame.getKnockedDownPinsSecondRoll()));
			}
		} else if (frame.getFrameNumber() == 9) {
			Frame nextFrame = frames.get(frame.getFrameNumber());
			frame.setScore(previousFrame.getScore() + ScoringEnums.STRIKE.getValue()
					+ (nextFrame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue() ? 0 : nextFrame.getKnockedDownPinsFirstRoll())
					+ (nextFrame.getKnockedDownPinsSecondRoll() == ScoringEnums.FOUL.getValue() ? 0 : nextFrame.getKnockedDownPinsSecondRoll()));

		} else {
			frame.setScore(previousFrame.getScore() + ScoringEnums.STRIKE.getValue()
					+ (frame.getKnockedDownPinsSecondRoll() == ScoringEnums.FOUL.getValue() ? 0 : frame.getKnockedDownPinsSecondRoll()) 
					+ (frame.getKnockedDownPinsThirdRoll() == ScoringEnums.FOUL.getValue() ? 0 : frame.getKnockedDownPinsThirdRoll()));
		}
	}

	private void setScoreSpare(Frame frame, List<Frame> frames, Frame previousFrame) {
		if (frame.getFrameNumber() <= 9) {
			Frame nextFrame = frames.get(frame.getFrameNumber());
			frame.setScore((previousFrame != null ? previousFrame.getScore() : 0) + ScoringEnums.SPARE.getValue()
					+ (nextFrame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue() ? 0 : nextFrame.getKnockedDownPinsFirstRoll()));
		} else {
			frame.setScore(
					previousFrame.getScore() + ScoringEnums.SPARE.getValue() + (frame.getKnockedDownPinsThirdRoll() == ScoringEnums.FOUL.getValue() ? 0 : frame.getKnockedDownPinsThirdRoll()));
		}
	}

	private void setScoreNoStrikeOrSpare(Frame frame, List<Frame> frames, Frame previousFrame) {
		frame.setScore((previousFrame != null ? previousFrame.getScore() : 0)
				+ (frame.getKnockedDownPinsFirstRoll() == (ScoringEnums.FOUL.getValue()) ? 0 : frame.getKnockedDownPinsFirstRoll())
				+ (frame.getKnockedDownPinsSecondRoll() == (ScoringEnums.FOUL.getValue()) ? 0 : frame.getKnockedDownPinsSecondRoll()));
	}
}
