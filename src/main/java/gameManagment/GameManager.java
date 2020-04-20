package gameManagment;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import models.Bowler;

public class GameManager {
	private HashMap<String, HashMap<Integer, Integer>> playerScore;
	private ScoreManager scoreManager;

	public GameManager() {
		playerScore = new HashMap<String, HashMap<Integer, Integer>>();
		scoreManager = new ScoreManager();
	}

	public void getScoreboard(List<Bowler> bowlers) {
		for(Bowler bowler : bowlers) {
			scoreManager.setScore(bowler.getFrames());
		}
	}
}
