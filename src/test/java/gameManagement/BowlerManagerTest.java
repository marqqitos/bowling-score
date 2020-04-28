package gameManagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import enums.ScoringEnums;
import gameManagement.implementations.BowlerManager;
import models.Bowler;
import models.Frame;

public class BowlerManagerTest {
	private static BowlerManager bm;
	private List<String> fileLines;
	private List<Bowler> bowlers;

	@BeforeClass
	public static void setup() {
		bm = new BowlerManager();
	}

	@Before
	public void init() {
		fileLines = new ArrayList<String>();
		bowlers = new ArrayList<Bowler>();
	}
	
	@Test
	public void checkFrameNumbers() {
		int i = 0;
		while (i < 12) {
			fileLines.add("Carl 10");
			i++;
		}

		bowlers = bm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 1);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			int frameCount = 0;
			for (Frame frame : bowler.getFrames()) {
				frameCount++;

				assertEquals(frameCount, frame.getFrameNumber());
			}
		}
	}

	@Test
	public void bowlerWithAllStrikes() {
		int i = 0;
		while (i < 12) {
			fileLines.add("Carl 10");
			i++;
		}

		bowlers = bm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 1);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			int frameCount = 0;
			for (Frame frame : bowler.getFrames()) {
				frameCount++;

				assertEquals(10, frame.getKnockedDownPinsFirstRoll());

				if (frameCount < 10) {
					assertEquals(ScoringEnums.NOTATTEMPTED.getValue(), frame.getKnockedDownPinsSecondRoll());
					assertEquals(ScoringEnums.NOTATTEMPTED.getValue(), frame.getKnockedDownPinsThirdRoll());
				} else {
					assertEquals(ScoringEnums.STRIKE.getValue(), frame.getKnockedDownPinsSecondRoll());
					assertEquals(ScoringEnums.STRIKE.getValue(), frame.getKnockedDownPinsThirdRoll());
				}
			}
		}
	}
	
	@Test
	public void bowlerWithAllFouls() {
		int i = 0;
		while (i < 20) {
			fileLines.add("Carl F");
			i++;
		}

		bowlers = bm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 1);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			for (Frame frame : bowler.getFrames()) {
				assertEquals(ScoringEnums.FOUL.getValue(), frame.getKnockedDownPinsFirstRoll());
				assertEquals(ScoringEnums.FOUL.getValue(), frame.getKnockedDownPinsSecondRoll());
			}
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

		bowlers = bm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 1);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			int frameCount = 0;
			for (Frame frame : bowler.getFrames()) {
				frameCount++;
				assertEquals(7, frame.getKnockedDownPinsFirstRoll());
				assertEquals(3, frame.getKnockedDownPinsSecondRoll());

				if (frameCount < 10) {
					assertEquals(ScoringEnums.NOTATTEMPTED.getValue(), frame.getKnockedDownPinsThirdRoll());
				} else {
					assertEquals(7, frame.getKnockedDownPinsThirdRoll());
				}
			}
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

		bowlers = bm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 1);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			for (Frame frame : bowler.getFrames()) {
				assertEquals(6, frame.getKnockedDownPinsFirstRoll());
				assertEquals(3, frame.getKnockedDownPinsSecondRoll());
				assertEquals(ScoringEnums.NOTATTEMPTED.getValue(), frame.getKnockedDownPinsThirdRoll());
			}
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

		bowlers = bm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 2);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			for (Frame frame : bowler.getFrames()) {
				assertEquals(6, frame.getKnockedDownPinsFirstRoll());
				assertEquals(3, frame.getKnockedDownPinsSecondRoll());
				assertEquals(ScoringEnums.NOTATTEMPTED.getValue(), frame.getKnockedDownPinsThirdRoll());
			}
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

		bowlers = bm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 2);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			int frameCount = 0;
			for (Frame frame : bowler.getFrames()) {
				frameCount++;
				assertEquals(7, frame.getKnockedDownPinsFirstRoll());
				assertEquals(3, frame.getKnockedDownPinsSecondRoll());

				if (frameCount < 10) {
					assertEquals(ScoringEnums.NOTATTEMPTED.getValue(), frame.getKnockedDownPinsThirdRoll());
				} else {
					assertEquals(7, frame.getKnockedDownPinsThirdRoll());
				}
			}
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

		bowlers = bm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 2);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			if (bowler.getName().equals("Carl")) {
				int frameCount = 0;
				for (Frame frame : bowler.getFrames()) {
					frameCount++;

					assertEquals(10, frame.getKnockedDownPinsFirstRoll());

					if (frameCount < 10) {
						assertEquals(ScoringEnums.NOTATTEMPTED.getValue(), frame.getKnockedDownPinsSecondRoll());
						assertEquals(ScoringEnums.NOTATTEMPTED.getValue(), frame.getKnockedDownPinsThirdRoll());
					} else {
						assertEquals(ScoringEnums.STRIKE.getValue(), frame.getKnockedDownPinsSecondRoll());
						assertEquals(ScoringEnums.STRIKE.getValue(), frame.getKnockedDownPinsThirdRoll());
					}
				}
			}
			
			else if(bowler.getName().equals("John")) {
				int frameCount = 0;
				for (Frame frame : bowler.getFrames()) {
					frameCount++;
					assertEquals(7, frame.getKnockedDownPinsFirstRoll());

					if (frameCount < 10) {
						assertEquals(3, frame.getKnockedDownPinsSecondRoll());
						assertEquals(ScoringEnums.NOTATTEMPTED.getValue(), frame.getKnockedDownPinsThirdRoll());
					} else {
						assertEquals(2, frame.getKnockedDownPinsSecondRoll());
					}
					
					assertEquals(ScoringEnums.NOTATTEMPTED.getValue(), frame.getKnockedDownPinsThirdRoll());
				}
			}

		}
	}
}
