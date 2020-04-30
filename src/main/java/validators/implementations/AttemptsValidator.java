package validators.implementations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import validators.interfaces.ValidationRule;

public class AttemptsValidator implements ValidationRule {

	@Override
	public void validate(List<String> fileLines) throws Exception {
		int numberOfPlayers = getNumberOfPlayers(fileLines);
		
		//Check for each player that they have attempted only 10 throws
		checkLessThanMinimumOrMoreThanMaximumAttemptsForPlayers(fileLines);
		
		if (numberOfPlayers > 1) {
			checkValidAttemptsForTwoOrMorePlayers(numberOfPlayers, fileLines);
		} else {
			checkValidAttemptsForSinglePlayer(fileLines);
		}
	}

	private int getNumberOfPlayers(List<String> fileLines) {
		List<String> ps = fileLines.stream().map(line -> line.split(" ")[0]).distinct().collect(Collectors.toList());

		return ps.size();
	}

	private void checkLessThanMinimumOrMoreThanMaximumAttemptsForPlayers(List<String> fileLines) throws Exception {
		HashMap<String, Integer> playersAttempts = new HashMap<String, Integer>();

		for (String line : fileLines) {
			//Get player from line
			String player = line.split(" ")[0];

			if (playersAttempts.isEmpty() || !playersAttempts.containsKey(player)) {
				//Insert player into the hash map with initial attempt
				playersAttempts.put(player, 1);
			} else {
				//Increase attempts of player by one
				playersAttempts.put(player, playersAttempts.get(player) + 1);
			}
		}

		for (Map.Entry<String, Integer> pa : playersAttempts.entrySet()) {
			//Minimum attempts in a game is 11 -> Achieved with 9 frames with strikes and no strike or spare in 10th frame
			//Maximum attempts in a game is 21 -> Achieved with no strike until 10th frame, and a strike or spare in 10th frame to get extra attempt.
			//If less -> less than 10 frames played or missing attempts
			//If more -> more than 10 frames, extra or repeated attempts
			if (pa.getValue() < 11 || pa.getValue() > 21) {
				throw new Exception(
						"There are two players with the same name or the number of attempts for a person is not correct");
			}
		}
	}

	private void checkValidAttemptsForTwoOrMorePlayers(int numberOfPlayers, List<String> fileLines) throws Exception {
		//All the players must throw before moving to the next frame
		int turn = numberOfPlayers;
		int frame = 1;
		int attempts = 0;
		String previousPlayer = "";
		int previousScore = 0;
		boolean allowedExtraAttempt = false;

		for (String line : fileLines) {
			//Get player from line
			String player = line.split(" ")[0];
			
			//If it was a foul, then score is 0, if not get the actual score for the attempt
			int score = (line.split(" ")[1]).equals("F") ? 0 : Integer.parseInt(line.split(" ")[1]);

			//If true -> all the players have had their attempts for the frame
			if (turn == 0 && !player.equals(previousPlayer)) {
				turn = numberOfPlayers;
				//Move to the next frame.
				frame++;
			}

			if (player.equals(previousPlayer)) {
				//It's the same player from previous line, so we increase his attempts for the frame.
				attempts++;
				
				if ((previousScore == 10 || (previousScore + score > 10)) && frame < 10) {
					throw new Exception(
							"There is a second attempt after an strike or both amounts add up more than ten before frame 10");
				} else if (attempts > 2 && frame < 10) {
					throw new Exception("There are more than two attempts for a player before frame 10");
				} else if (frame == 10) {
					if (attempts == 2) {
						//Extra attempt is allowed if got strike or spare
						//((previousScore + score) % 10 == 0) because you could have 2 strikes in frame 10 and extra attempt should be allowed
						allowedExtraAttempt = (previousScore == 10) || ((previousScore + score) % 10 == 0);
					} else if (attempts == 3 && !allowedExtraAttempt) {
						throw new Exception("Extra attempt not allowed");
					}
				} else if (frame > 10) {
					throw new Exception("There are more than 10 frames");
				}
			} else {
				//It's a different player's turn, reset attempts and decrease turn
				attempts = 1;
				previousPlayer = player;
				previousScore = score;
				turn--;
			}
		}

		if (frame < 10) {
			throw new Exception("There are less that 10 frames");
		}
	}

	private void checkValidAttemptsForSinglePlayer(List<String> fileLines) throws Exception {
		int frame = 1;
		int prevScore = 0;
		int attempt = 0;
		boolean allowedExtraAttempt = false;

		//Get all the scores for the player
		List<String> scores = fileLines.stream().map(line -> line.split(" ")[1]).collect(Collectors.toList());

		for (String sc : scores) {
			//If score is a foul, 0 else get the actual score
			int score = sc.equals("F") ? 0 : Integer.parseInt(sc);
			attempt++;

			if (frame < 10) {
				if (attempt >= 3) {
					throw new Exception("More than 3 attempts before frame 10");
				} else if (attempt == 2) {
					if (prevScore + score > 10) {
						throw new Exception("Invalid attempt, second attempt on a strike or both attempts score more than 10");
					} else {
						//Everything is correct, move to next frame
						attempt = 0;
						frame++;
					}
				} else {
					if (score == 10) {
						//First attempt and a strike scored, move to the next frame
						attempt = 0;
						frame++;
					} else {
						prevScore = score;
					}
				}
			} else if (frame == 10) {
				if (attempt == 1) {
					prevScore = score;
					allowedExtraAttempt = score == 10;
				} else if (attempt == 2) {
					if (prevScore != 10) {
						allowedExtraAttempt = (prevScore + score) > 0 && (prevScore + score) % 10 == 0;
					}
				} else if (attempt == 3 && !allowedExtraAttempt) {
					throw new Exception("Extra attempt not allowed");
				}
			}
			else {
				throw new Exception("There is more than 10 frames for a player");
			}
		}

		if (frame < 10) {
			throw new Exception("There are less that 10 frames");
		}
	}
}
