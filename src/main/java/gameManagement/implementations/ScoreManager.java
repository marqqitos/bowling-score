package gameManagement.implementations;

import java.util.List;

import enums.ScoringEnums;
import gameManagement.interfaces.IScoreManager;
import models.Frame;

/**
 * This class is in charge of handling everything related to the scoring of the game
 * @author: Marcos Caccavaio
 * @version: 1.0
 */
public class ScoreManager implements IScoreManager {

	public ScoreManager() {
	}
	
	/**
	 * Sets the score for each frame
	 * @param frames list of frames of the game
	 */
	public void setScore(List<Frame> frames) {
		for (Frame frame : frames) {
			//If it is 1st frame then there is no previous frame assign null, else get the previous frame
			Frame previousFrame = frame.getFrameNumber() > 1 ? frames.get(frame.getFrameNumber() - 2) : null;

			if (frame.getKnockedDownPinsFirstRoll() == ScoringEnums.STRIKE.getValue()) {
				setScoreStrike(frame, frames, previousFrame);
			} else if (((frame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue()) ? 0 : frame.getKnockedDownPinsFirstRoll()) + frame.getKnockedDownPinsSecondRoll() == ScoringEnums.SPARE.getValue()) {
				setScoreSpare(frame, frames, previousFrame);
			} else {
				setScoreNoStrikeOrSpare(frame, frames, previousFrame);
			}
		}
	}

	/**
	 * Calculates the score if it is a strike
	 * @param frame the frame whose score should calculate
	 * @param frames the list of frames of the game
	 * @param previousFrame the previous frame of the current frame
	 */
	private void setScoreStrike(Frame frame, List<Frame> frames, Frame previousFrame) {
		//Strikes get the pinfalls of the next two attempts, 
		//if next frame 1st attempt was a strike we need to move to the next one
		
		//We are in frame 1-8
		if (frame.getFrameNumber() < 9) {
			Frame nextFrame = frames.get(frame.getFrameNumber());

			//If next frame 1st attempt was a strike we need to count the next next frame 1st attempt pinfalls
			if (nextFrame.getKnockedDownPinsFirstRoll() == ScoringEnums.STRIKE.getValue()) {
				Frame nextTwoFrame = frames.get(nextFrame.getFrameNumber());
				frame.setScore((previousFrame != null ? previousFrame.getScore() : 0) + ScoringEnums.STRIKE.getValue()
						+ ScoringEnums.STRIKE.getValue()
						+ (nextTwoFrame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue() ? 0
								: nextTwoFrame.getKnockedDownPinsFirstRoll()));
			} else {
				//Next frame didn't achieve a strike in 1st attempt, sum both attempt's pinfalls
				frame.setScore((previousFrame != null ? previousFrame.getScore() : 0) + ScoringEnums.STRIKE.getValue()
						+ (nextFrame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue() ? 0
								: nextFrame.getKnockedDownPinsFirstRoll())
						+ (nextFrame.getKnockedDownPinsSecondRoll() == ScoringEnums.FOUL.getValue() ? 0
								: nextFrame.getKnockedDownPinsSecondRoll()));
			}
		} else if (frame.getFrameNumber() == 9) {
			//We are in frame 9, so a strike here means that we have to sum 10th frame 1st and 2nd attempts
			//regardless if they are a strike or not. Frame 10 is the last one, so we can't look for frame 11
			Frame nextFrame = frames.get(frame.getFrameNumber());
			frame.setScore(previousFrame.getScore() + ScoringEnums.STRIKE.getValue()
					+ (nextFrame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue() ? 0
							: nextFrame.getKnockedDownPinsFirstRoll())
					+ (nextFrame.getKnockedDownPinsSecondRoll() == ScoringEnums.FOUL.getValue() ? 0
							: nextFrame.getKnockedDownPinsSecondRoll()));

		} else {
			//We are in frame 10 and a strike means we get extra attempt, sum it up
			frame.setScore(previousFrame.getScore() + ScoringEnums.STRIKE.getValue()
					+ (frame.getKnockedDownPinsSecondRoll() == ScoringEnums.FOUL.getValue() ? 0
							: frame.getKnockedDownPinsSecondRoll())
					+ (frame.getKnockedDownPinsThirdRoll() == ScoringEnums.FOUL.getValue() ? 0
							: frame.getKnockedDownPinsThirdRoll()));
		}
	}

	/**
	 * Calculates the score if it is a spare
	 * @param frame the frame whose score should calculate
	 * @param frames the list of frames of the game
	 * @param previousFrame the previous frame of the current frame
	 */
	private void setScoreSpare(Frame frame, List<Frame> frames, Frame previousFrame) {
		//A spare means that we take into account pinfalls of 1st attempt of next frame
		if (frame.getFrameNumber() <= 9) {
			//For frames 1-9, get 1st attempt's pinfalls
			Frame nextFrame = frames.get(frame.getFrameNumber());
			frame.setScore((previousFrame != null ? previousFrame.getScore() : 0) + ScoringEnums.SPARE.getValue()
					+ (nextFrame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue() ? 0
							: nextFrame.getKnockedDownPinsFirstRoll()));
		} else {
			//For frame 10, a spare means an extra attempt, add it
			frame.setScore(previousFrame.getScore() + ScoringEnums.SPARE.getValue()
					+ (frame.getKnockedDownPinsThirdRoll() == ScoringEnums.FOUL.getValue() ? 0
							: frame.getKnockedDownPinsThirdRoll()));
		}
	}
	
	/**
	 * Calculates the score if it is not a strike nor a spare
	 * @param frame the frame whose score should calculate
	 * @param frames the list of frames of the game
	 * @param previousFrame the previous frame of the current frame
	 */
	private void setScoreNoStrikeOrSpare(Frame frame, List<Frame> frames, Frame previousFrame) {
		//No strike or spare, sum both pinfalls
		frame.setScore((previousFrame != null ? previousFrame.getScore() : 0)
				+ (frame.getKnockedDownPinsFirstRoll() == (ScoringEnums.FOUL.getValue()) ? 0
						: frame.getKnockedDownPinsFirstRoll())
				+ (frame.getKnockedDownPinsSecondRoll() == (ScoringEnums.FOUL.getValue()) ? 0
						: frame.getKnockedDownPinsSecondRoll()));
	}
}
