package gameManagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gameManagement.implementations.BowlerManager;
import gameManagement.implementations.ScoreManager;
import models.Bowler;

public class ScoreManagerTest {
	private static ScoreManager sm;
	private static BowlerManager pm;
	private List<String> fileLines;
	private List<Bowler> bowlers;

	@BeforeClass
	public static void setup() {
		sm = new ScoreManager();
		pm = new BowlerManager();
	}

	@Before
	public void init() {
		fileLines = new ArrayList<String>();
		bowlers = new ArrayList<Bowler>();
	}

	@Test
	public void allStrikesShouldScorePerfectGame() {
		int f = 0;
		while (f < 12) {
			fileLines.add("Carl 10");
			f++;
		}

		bowlers = pm.getBowlers(fileLines);

		for (Bowler bowler : bowlers) {
			sm.setScore(bowler.getFrames());

			for (int j = 0; j < bowler.getFrames().size(); j++) {
				assertEquals(30 * (j + 1), bowler.getFrames().get(j).getScore());
			}

			assertEquals(300, bowler.getFrames().get(9).getScore());
		}
	}
	
	@Test
	public void allFoulsShouldScoreZero() {
		int f = 0;
		while (f < 20) {
			fileLines.add("Carl F");
			f++;
		}

		bowlers = pm.getBowlers(fileLines);

		for (Bowler bowler : bowlers) {
			sm.setScore(bowler.getFrames());

			for (int j = 0; j < bowler.getFrames().size(); j++) {
				assertEquals(0, bowler.getFrames().get(j).getScore());
			}

			assertEquals(0, bowler.getFrames().get(9).getScore());
		}
	}
	
	@Test
	public void allZeroShouldScoreZero() {
		int f = 0;
		while (f < 20) {
			fileLines.add("Carl 0");
			f++;
		}

		bowlers = pm.getBowlers(fileLines);

		for (Bowler bowler : bowlers) {
			sm.setScore(bowler.getFrames());

			for (int j = 0; j < bowler.getFrames().size(); j++) {
				assertEquals(0, bowler.getFrames().get(j).getScore());
			}

			assertEquals(0, bowler.getFrames().get(9).getScore());
		}
	}

	@Test
	public void allStrikesTwoPlayerShouldScorePerfectGame() {
		int f = 0;
		while (f < 12) {
			fileLines.add("Carl 10");
			fileLines.add("Jeff 10");
			f++;
		}

		bowlers = pm.getBowlers(fileLines);

		for (Bowler bowler : bowlers) {
			sm.setScore(bowler.getFrames());

			for (int j = 0; j < bowler.getFrames().size(); j++) {
				assertEquals(30 * (j + 1), bowler.getFrames().get(j).getScore());
			}

			assertEquals(300, bowler.getFrames().get(9).getScore());
		}
	}
	
	@Test
	public void allFoulsTwoPlayerShouldScoreZero() {
		int f = 0;
		while (f < 20) {
			fileLines.add("Carl F");
			fileLines.add("Jeff F");
			f++;
		}

		bowlers = pm.getBowlers(fileLines);

		for (Bowler bowler : bowlers) {
			sm.setScore(bowler.getFrames());

			for (int j = 0; j < bowler.getFrames().size(); j++) {
				assertEquals(0, bowler.getFrames().get(j).getScore());
			}

			assertEquals(0, bowler.getFrames().get(9).getScore());
		}
	}
	
	@Test
	public void allZeroTwoPlayerShouldScoreZero() {
		int f = 0;
		while (f < 20) {
			fileLines.add("Carl 0");
			fileLines.add("Jeff 0");
			f++;
		}

		bowlers = pm.getBowlers(fileLines);

		for (Bowler bowler : bowlers) {
			sm.setScore(bowler.getFrames());

			for (int j = 0; j < bowler.getFrames().size(); j++) {
				assertEquals(0, bowler.getFrames().get(j).getScore());
			}

			assertEquals(0, bowler.getFrames().get(9).getScore());
		}
	}

	@Test
	public void allStrikesThreePlayerShouldScorePerfectGame() {
		int f = 0;
		while (f < 12) {
			fileLines.add("Carl 10");
			fileLines.add("Jeff 10");
			f++;
		}

		bowlers = pm.getBowlers(fileLines);

		for (Bowler bowler : bowlers) {
			sm.setScore(bowler.getFrames());

			for (int j = 0; j < bowler.getFrames().size(); j++) {
				assertEquals(30 * (j + 1), bowler.getFrames().get(j).getScore());
			}

			assertEquals(300, bowler.getFrames().get(9).getScore());
		}
	}

	@Test
	public void bowlerWithAllSparesExceptLastShot() {
		int i = 0;
		while (i < 10) {
			fileLines.add("Carl 7");
			fileLines.add("Carl 3");
			i++;
		}

		fileLines.add("Carl 7");

		bowlers = pm.getBowlers(fileLines);

		for (Bowler bowler : bowlers) {
			sm.setScore(bowler.getFrames());

			for (int j = 0; j < bowler.getFrames().size(); j++) {
				assertEquals(17 * (j + 1), bowler.getFrames().get(j).getScore());
			}

			assertEquals(170, bowler.getFrames().get(9).getScore());
		}
	}

	@Test
	public void bowlerWithAllNonSpareOrStrikeShots() {
		int i = 0;
		while (i < 10) {
			fileLines.add("Carl 6");
			fileLines.add("Carl 3");
			i++;
		}

		bowlers = pm.getBowlers(fileLines);

		for (Bowler bowler : bowlers) {
			sm.setScore(bowler.getFrames());

			for (int j = 0; j < bowler.getFrames().size(); j++) {
				assertEquals(9 * (j + 1), bowler.getFrames().get(j).getScore());
			}

			assertEquals(90, bowler.getFrames().get(9).getScore());
		}
	}

	@Test
	public void twoBowlersWithAllNonSpareOrStrikeShots() {
		int i = 0;
		while (i < 10) {
			fileLines.add("Carl 6");
			fileLines.add("Carl 3");
			fileLines.add("John 6");
			fileLines.add("John 3");
			i++;
		}

		bowlers = pm.getBowlers(fileLines);

		for (Bowler bowler : bowlers) {
			sm.setScore(bowler.getFrames());

			for (int j = 0; j < bowler.getFrames().size(); j++) {
				assertEquals(9 * (j + 1), bowler.getFrames().get(j).getScore());
			}

			assertEquals(90, bowler.getFrames().get(9).getScore());
		}
	}

	@Test
	public void twoBowlersWithAllSpareExceptLastShot() {
		int i = 0;
		while (i < 10) {
			fileLines.add("Carl 7");
			fileLines.add("Carl 3");
			fileLines.add("John 7");
			fileLines.add("John 3");
			i++;
		}

		fileLines.add("Carl 7");
		fileLines.add("John 7");

		bowlers = pm.getBowlers(fileLines);

		for (Bowler bowler : bowlers) {
			sm.setScore(bowler.getFrames());

			for (int j = 0; j < bowler.getFrames().size(); j++) {
				assertEquals(17 * (j + 1), bowler.getFrames().get(j).getScore());
			}

			assertEquals(170, bowler.getFrames().get(9).getScore());
		}
	}

	@Test
	public void twoBowlersOneWithAllStrikesOtherWithNoExtraAttempt() {
		int i = 0;
		while (i < 9) {
			fileLines.add("Carl 10");
			fileLines.add("John 7");
			fileLines.add("John 3");
			i++;
		}

		fileLines.add("Carl 10");
		fileLines.add("Carl 10");
		fileLines.add("Carl 10");
		fileLines.add("John 7");
		fileLines.add("John 2");

		bowlers = pm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 2);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);
			sm.setScore(bowler.getFrames());

			if (bowler.getName().equals("Carl")) {
				for (int j = 0; j < bowler.getFrames().size(); j++) {
					assertEquals(30 * (j + 1), bowler.getFrames().get(j).getScore());
				}

				assertEquals(300, bowler.getFrames().get(9).getScore());
			}
			else if (bowler.getName().equals("John")) {
				assertEquals(162, bowler.getFrames().get(9).getScore());
			}

		}
	}
	
	@Test
	public void allFoulsAndSpareShouldScoreOneHundred() {
		int f = 0;
		while (f < 10) {
			fileLines.add("Carl F");
			fileLines.add("Carl 10");
			f++;
		}
		
		fileLines.add("Carl F");

		bowlers = pm.getBowlers(fileLines);

		for (Bowler bowler : bowlers) {
			sm.setScore(bowler.getFrames());

			for (int j = 0; j < bowler.getFrames().size(); j++) {
				assertEquals(10 * (j + 1), bowler.getFrames().get(j).getScore());
			}

			assertEquals(100, bowler.getFrames().get(9).getScore());
		}
	}
}
