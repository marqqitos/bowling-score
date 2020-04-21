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
			String player = line.split(" ")[0];

			if (playersAttempts.isEmpty() || !playersAttempts.containsKey(player)) {
				playersAttempts.put(player, 1);
			} else {
				playersAttempts.put(player, playersAttempts.get(player) + 1);
			}
		}

		for (Map.Entry<String, Integer> pa : playersAttempts.entrySet()) {
			if (pa.getValue() < 11 || pa.getValue() > 21) {
				throw new Exception(
						"There are two players with the same name or the number of attempts for a person is not correct");
			}
		}
	}

	private void checkValidAttemptsForTwoOrMorePlayers(int numberOfPlayers, List<String> fileLines) throws Exception {
		int turn = numberOfPlayers;
		int frame = 1;
		int attempts = 0;
		String previousPlayer = "";
		int previousScore = 0;
		boolean allowedExtraAttempt = false;

		for (String line : fileLines) {
			String player = line.split(" ")[0];
			int score = (line.split(" ")[1]).equals("F") ? 0 : Integer.parseInt(line.split(" ")[1]);

			if (turn == 0 && !player.equals(previousPlayer)) {
				turn = numberOfPlayers;
				frame++;
			}

			if (player.equals(previousPlayer)) {
				attempts++;
				if ((previousScore == 10 || (previousScore + score > 10)) && frame < 10) {
					throw new Exception(
							"There is a second attempt after an strike or both amounts add up more than ten before frame 10");
				} else if (attempts > 2 && frame < 10) {
					throw new Exception("There are more than two attempts for a player before frame 10");
				} else if (frame == 10) {
					if (attempts == 2) {
						allowedExtraAttempt = (previousScore == 10) || ((previousScore + score) % 10 == 0);
					} else if (attempts == 3 && !allowedExtraAttempt) {
						throw new Exception("Extra attempt not allowed");
					}
				} else if (frame > 10) {
					throw new Exception("There are more than 10 frames");
				}
			} else {
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

		List<String> scores = fileLines.stream().map(line -> line.split(" ")[1]).collect(Collectors.toList());

		for (String sc : scores) {
			int score = sc.equals("F") ? 0 : Integer.parseInt(sc);
			attempt++;

			if (frame < 10) {
				if (attempt >= 3) {
					throw new Exception("More than 3 attempts before frame 10");
				} else if (attempt == 2) {
					if (score == 10 || (prevScore + score > 10)) {
						throw new Exception("Invalid attempt");
					} else {
						attempt = 0;
						frame++;
					}
				} else {
					if (score == 10) {
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
						allowedExtraAttempt = (prevScore + score) % 10 == 0;
					}
				} else if (attempt == 3 && !allowedExtraAttempt) {
					throw new Exception("Extra attempt not allowed");
				}
			}
		}

		if (frame < 10) {
			throw new Exception("There are less that 10 frames");
		}
	}
}
