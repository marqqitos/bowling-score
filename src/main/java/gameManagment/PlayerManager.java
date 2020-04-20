package gameManagment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import models.Bowler;
import models.Frame;

public class PlayerManager {
	public PlayerManager() {
	}

	public List<Bowler> getBowlers(List<String> fileLines) {
		List<Bowler> bowlers = new ArrayList<Bowler>();
		List<String> players = fileLines.stream().map(l -> l.split(" ")[0]).distinct().collect(Collectors.toList());

		for (String player : players) {
			Bowler bowler = new Bowler();
			bowler.setName(player);

			List<Frame> frames = new ArrayList<Frame>();
			List<Integer> scores = fileLines.stream().filter(l -> l.contains(player))
					.map(l -> Integer.parseInt(l.split(" ")[1])).collect(Collectors.toList());
			
			int frameCount = 0;
			
			for (Integer score : scores) {
				Frame frame = new Frame();
				frame.setFrameNumber(frameCount);
				if (frameCount < 9) {
					if (score == 10) {
						frame.setKnockedDownPinsFirstRoll(score);
						frames.add(frame);
						frameCount++;
					} else {
						if(frames.size() <= frameCount) {
							frame.setKnockedDownPinsFirstRoll(score);
							frames.add(frame);
						}
						else {
							Frame addedFrame = frames.get(frameCount);
							addedFrame.setKnockedDownPinsSecondRoll(score);
							frames.set(frameCount, addedFrame);
							frameCount++;
						}
					}
				}
				else {
					if(frames.size() <= frameCount) {
						frame.setKnockedDownPinsFirstRoll(score);
						frame.setKnockedDownPinsSecondRoll(-1);
						frames.add(frame);
					}
					else {
						Frame addedFrame = frames.get(frameCount);
						if(addedFrame.getKnockedDownPinsSecondRoll() == -1) {
							addedFrame.setKnockedDownPinsSecondRoll(score);
						}
						else if(addedFrame.getKnockedDownPinsThirdRoll() == -1) {
							addedFrame.setKnockedDownPinsThirdRoll(score);
						}
						
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
