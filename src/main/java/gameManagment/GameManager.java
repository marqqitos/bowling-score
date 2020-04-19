package gameManagment;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GameManager {
	private HashMap<String, HashMap<Integer, Integer>> playerScore;
	private ScoreManager scoreManager;

	public GameManager() {
		playerScore = new HashMap<String, HashMap<Integer, Integer>>();
		scoreManager = new ScoreManager();
	}

	public void getScoreboard(List<String> fileLines) {
		List<String> players = fileLines.stream().map(l -> l.split(" ")[0]).distinct().collect(Collectors.toList());
		
		for(String player : players) {
			List<Integer> scores = fileLines.stream().filter(l -> l.contains(player)).map(l -> Integer.parseInt(l.split(" ")[0])).collect(Collectors.toList());
			playerScore.put(player, scoreManager.getFinalScore(scores));
		}
	}
}
