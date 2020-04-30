package gameManagement.implementations;

import java.util.List;

import org.springframework.stereotype.Component;

import models.Bowler;
import models.Frame;
import enums.ScoringEnums;
import gameManagement.interfaces.IFormatManager;
import gameManagement.interfaces.IScoreManager;

@Component
public class FormatManager implements IFormatManager {
	private IScoreManager scoreManager;

	public FormatManager(IScoreManager scoreManager) {
		this.scoreManager = scoreManager;
	}

	public void printScoreboard(List<Bowler> bowlers) {
		for(Bowler bowler : bowlers) {
			//Set the accumulated score for each frame
			scoreManager.setScore(bowler.getFrames());
		}
		
		String scoreboard = "Frame\t\t";
		
		for(int i = 1; i < 11; i++) {
			scoreboard = scoreboard.concat(i + "\t\t");
		}
		
		for(Bowler bowler : bowlers) {
			scoreboard = scoreboard.concat("\n").concat(bowler.getName()).concat("\nPinfalls");
			
			for(Frame frame : bowler.getFrames()) {
				scoreboard = scoreboard.concat(getPinfall(frame));
			}
			
			scoreboard = scoreboard.concat("Score\t\t");
			for(Frame frame : bowler.getFrames()) {
				scoreboard = scoreboard.concat(getScores(frame));
			}
		}
		
		System.out.println(scoreboard);
	}

	private String getPinfall(Frame frame) {
		String pinfalls = "\t";
		
		if(frame.getKnockedDownPinsFirstRoll() == ScoringEnums.STRIKE.getValue()) {
			//If it's a strike we need to replace the 10 with X
			pinfalls = pinfalls.concat((frame.getFrameNumber() < 10 ? "\tX" : "X\t" + getFramePinfall(frame.getKnockedDownPinsSecondRoll()) + "\t"));
		}
		else if (((frame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue()) ? 0 : frame.getKnockedDownPinsFirstRoll())+ frame.getKnockedDownPinsSecondRoll() == ScoringEnums.SPARE.getValue()) {
			//If it's a spare we need to replace the 10 with / and put F if first attempt was a foul
			pinfalls = pinfalls.concat((frame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue() ? "F" : frame.getKnockedDownPinsFirstRoll()) + "\t/");
			if(frame.getFrameNumber() == 10) pinfalls = pinfalls.concat("\t");
		}
		else {
			//We need to put F if any attempt was a foul
			pinfalls = pinfalls.concat((frame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue() ? "F" : frame.getKnockedDownPinsFirstRoll()) + "\t" + (frame.getKnockedDownPinsSecondRoll() == ScoringEnums.FOUL.getValue() ? "F" : frame.getKnockedDownPinsSecondRoll()));
		}
		
		if(frame.getFrameNumber() == 10) {			
			pinfalls = pinfalls.concat(getFramePinfall(frame.getKnockedDownPinsThirdRoll()) + "\n");
		}
		
		return pinfalls;
	}
	
	private String getFramePinfall(int pinfall) {
		String lastAttempt = "\t";
		
		switch(pinfall) {
			case 10:
				lastAttempt = "X";
				break;
			case -1:
				lastAttempt = "F";
				break;
			case -2:
				lastAttempt = lastAttempt.concat("-");
				break;
			default:
				lastAttempt = Integer.toString(pinfall);
		}
		
		return lastAttempt;
	}
		
	private String getScores(Frame frame) {
		return frame.getScore() + "\t\t";
	}
}
