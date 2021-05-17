package gameManagement.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import enums.ScoringEnums;
import gameManagement.interfaces.IBowlerManager;
import models.Bowler;
import models.Frame;

/**
 * This class is in charge of handling everything related to the Bowler class
 * @author: Marcos Caccavaio
 * @version: 1.0
 */
@Component
public class BowlerManager implements IBowlerManager {
	public BowlerManager() {
	}
	
	/**
	 * returns the bowlers of the game
	 * @param fileLines a list of string containing each player and his score on the attempt
	 */
	public List<Bowler> getBowlers(List<String> fileLines) {
		List<Bowler> bowlers = new ArrayList<Bowler>();
		
		//Get all the players from the file lines
		List<String> players = fileLines.stream().map(line -> line.split(" ")[0]).distinct().collect(Collectors.toList());

		for (String player : players) {
			Bowler bowler = new Bowler();
			bowler.setName(player);

			List<Frame> frames = new ArrayList<Frame>();
			//Get all the scores for the player
			List<Integer> scores = fileLines.stream().filter(line -> line.contains(player))
					.map(line -> line.split(" ")[1].equals("F") ? ScoringEnums.FOUL.getValue() : Integer.parseInt(line.split(" ")[1])).collect(Collectors.toList());
			
			int frameCount = 0;
			
			for (Integer score : scores) {
				Frame frame = new Frame();
				frame.setFrameNumber(frameCount + 1);
				if (frameCount < 9) {
					if (frames.size() <= frameCount && score == ScoringEnums.STRIKE.getValue()) {
						frame.setKnockedDownPinsFirstRoll(ScoringEnums.STRIKE.getValue());
						//It's a strike so there is no another attempt, move to next frame
						frames.add(frame);
						frameCount++;
					} else {
						//If it's first attempt of the frame
						if(frames.size() <= frameCount) {
							frame.setKnockedDownPinsFirstRoll(score);
							frames.add(frame);
						}
						else {
							//It's second attempt of the frame, update the frame and set the second attempt pinfalls.
							Frame addedFrame = frames.get(frameCount);
							addedFrame.setKnockedDownPinsSecondRoll(score);
							frames.set(frameCount, addedFrame);
							frameCount++;
						}
					}
				}
				else {
					//If it's first attempt of the frame
					if(frames.size() <= frameCount) {
						frame.setKnockedDownPinsFirstRoll(score);
						frame.setKnockedDownPinsSecondRoll(ScoringEnums.NOTATTEMPTED.getValue());
						frames.add(frame);
					}
					else {
						Frame addedFrame = frames.get(frameCount);
						//If it's second attempt of the frame
						if(addedFrame.getKnockedDownPinsSecondRoll() == ScoringEnums.NOTATTEMPTED.getValue()) {
							addedFrame.setKnockedDownPinsSecondRoll(score);
						}
						//If it's third attempt of the frame
						else if(addedFrame.getKnockedDownPinsThirdRoll() == ScoringEnums.NOTATTEMPTED.getValue()) {
							addedFrame.setKnockedDownPinsThirdRoll(score);
						}
						//Update the frame pinfalls.
						frames.set(frameCount, addedFrame);
					}
				}
			}
			
			bowler.setFrames(frames);
			bowlers.add(bowler);
		}
		return bowlers;
	}
}
