package gameManagment;

import java.util.List;

import models.Bowler;

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
			scoreboard.concat(i + "\t\t");
		}
		
		System.out.println(scoreboard);
	}
}
