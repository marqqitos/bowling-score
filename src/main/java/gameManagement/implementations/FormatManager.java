package gameManagement.implementations;

import java.util.List;

import org.springframework.stereotype.Component;

import models.Bowler;
import models.Frame;
import enums.ScoringEnums;
import gameManagement.interfaces.IFormatManager;
import gameManagement.interfaces.IScoreManager;

/**
 * This class is in charge of handling everything related to the formatting of the scoreboard
 * @author: Marcos Caccavaio
 * @version: 1.0
 */
@Component
public class FormatManager implements IFormatManager {

    private IScoreManager scoreManager;

    public FormatManager(IScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    /**
	 * Prints the scoreboard
	 * @param bowlers the bowlers of the game
	 */
    public void printScoreboard(List<Bowler> bowlers) {
        for (Bowler bowler : bowlers) {
            //Set the accumulated score for each frame
            scoreManager.setScore(bowler.getFrames());
        }

        String scoreboard = "Frame\t\t";

        for (int i = 1; i < 11; i++) {
            scoreboard = scoreboard.concat(i + "\t\t");
        }

        for (Bowler bowler : bowlers) {
            scoreboard = scoreboard.concat("\n").concat(bowler.getName()).concat("\nPinfalls");

            for (Frame frame : bowler.getFrames()) {
                scoreboard = scoreboard.concat(getPinfall(frame));
            }

            scoreboard = scoreboard.concat("Score\t\t");
            for (Frame frame : bowler.getFrames()) {
                scoreboard = scoreboard.concat(getScores(frame));
            }
        }

        System.out.println(scoreboard);
    }

    /**
	 * Gets the correct format of the pinfall for the frame
	 * @param Frame the frame that should be formatted
	 */
    private String getPinfall(Frame frame) {
        String pinfalls = "\t";

        if (frame.getKnockedDownPinsFirstRoll() == ScoringEnums.STRIKE.getValue()) {
            //If it's a strike we need to replace the 10 with X
            pinfalls = pinfalls.concat((frame.getFrameNumber() < 10 ? "\tX" : "X\t" + getFramePinfall(frame.getKnockedDownPinsSecondRoll()) + "\t"));
        } else if (((frame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue()) ? 0 : frame.getKnockedDownPinsFirstRoll()) + frame.getKnockedDownPinsSecondRoll() == ScoringEnums.SPARE.getValue()) {
            //If it's a spare we need to replace the 10 with / and put F if first attempt was a foul
            pinfalls = pinfalls.concat((frame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue() ? "F" : frame.getKnockedDownPinsFirstRoll()) + "\t/");
            if (frame.getFrameNumber() == 10) {
                pinfalls = pinfalls.concat("\t");
            }
        } else {
            //We need to put F if any attempt was a foul
            pinfalls = pinfalls.concat((frame.getKnockedDownPinsFirstRoll() == ScoringEnums.FOUL.getValue() ? "F" : frame.getKnockedDownPinsFirstRoll()) + "\t" + (frame.getKnockedDownPinsSecondRoll() == ScoringEnums.FOUL.getValue() ? "F" : frame.getKnockedDownPinsSecondRoll()));
        }

        if (frame.getFrameNumber() == 10) {
            pinfalls = pinfalls.concat(getFramePinfall(frame.getKnockedDownPinsThirdRoll()) + "\n");
        }

        return pinfalls;
    }

    /**
	 * Gets the format for the last attempt of the frame
	 * @param pinfall the number of pins throwed
	 */
    private String getFramePinfall(int pinfall) {
        String lastAttempt = "\t";

        switch (pinfall) {
            case 10: //Strike
                lastAttempt = "X";
                break;
            case -1: //Foul
                lastAttempt = "F";
                break;
            case -2: //Not Attempted
                lastAttempt = lastAttempt.concat("-");
                break;
            default:
                lastAttempt = Integer.toString(pinfall);
        }

        return lastAttempt;
    }

    /**
	 * Gets the score in a correct format
	 * @param Frame the frame whose score should be formatted
	 */
    private String getScores(Frame frame) {
        return frame.getScore() + "\t\t";
    }
}
