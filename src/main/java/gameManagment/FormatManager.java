package gameManagment;

import java.util.List;

import models.Bowler;
import models.Frame;
import enums.ScoringEnums;

public class FormatManager {
	private ScoreManager scoreManager;

	public FormatManager() {
		scoreManager = new ScoreManager();
	}

	public void printScoreboard(List<Bowler> bowlers) {
		for(Bowler bowler : bowlers) {
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
			pinfalls = pinfalls.concat((frame.getFrameNumber() < 10 ? "\tX" : "X\t" + getFramePinfall(frame.getKnockedDownPinsSecondRoll()) + "\t"));
		}
		else if (frame.getKnockedDownPinsFirstRoll() + frame.getKnockedDownPinsSecondRoll() == ScoringEnums.SPARE.getValue()) {
			pinfalls = pinfalls.concat(frame.getKnockedDownPinsFirstRoll() + "\t/");
		}
		else {
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
				lastAttempt = lastAttempt.concat("X");
				break;
			case -1:
				lastAttempt = lastAttempt.concat("F");
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
