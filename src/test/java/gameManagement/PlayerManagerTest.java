package gameManagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gameManagment.PlayerManager;
import models.Bowler;
import models.Frame;

public class PlayerManagerTest {
	private static PlayerManager pm;
	private List<String> fileLines;
	private List<Bowler> bowlers;

	@BeforeClass
	public static void setup() {
		pm = new PlayerManager();
	}

	@Before
	public void init() {
		fileLines = new ArrayList<String>();
		bowlers = new ArrayList<Bowler>();
	}

	@Test
	public void bowlerWithAllStrikes() {
		int i = 0;
		while (i < 12) {
			fileLines.add("Carl 10");
			i++;
		}

		bowlers = pm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 1);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			int frameCount = 0;
			for (Frame frame : bowler.getFrames()) {
				frameCount++;

				assertEquals(10, frame.getKnockedDownPinsFirstRoll());

				if (frameCount < 10) {
					assertEquals(0, frame.getKnockedDownPinsSecondRoll());
					assertEquals(-1, frame.getKnockedDownPinsThirdRoll());
				} else {
					assertEquals(10, frame.getKnockedDownPinsSecondRoll());
					assertEquals(10, frame.getKnockedDownPinsThirdRoll());
				}
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

		bowlers = pm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 1);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			int frameCount = 0;
			for (Frame frame : bowler.getFrames()) {
				frameCount++;
				assertEquals(7, frame.getKnockedDownPinsFirstRoll());
				assertEquals(3, frame.getKnockedDownPinsSecondRoll());

				if (frameCount < 10) {
					assertEquals(-1, frame.getKnockedDownPinsThirdRoll());
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

		bowlers = pm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 1);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			for (Frame frame : bowler.getFrames()) {
				assertEquals(6, frame.getKnockedDownPinsFirstRoll());
				assertEquals(3, frame.getKnockedDownPinsSecondRoll());
				assertEquals(-1, frame.getKnockedDownPinsThirdRoll());
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

		bowlers = pm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 2);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			for (Frame frame : bowler.getFrames()) {
				assertEquals(6, frame.getKnockedDownPinsFirstRoll());
				assertEquals(3, frame.getKnockedDownPinsSecondRoll());
				assertEquals(-1, frame.getKnockedDownPinsThirdRoll());
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

		bowlers = pm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 2);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			int frameCount = 0;
			for (Frame frame : bowler.getFrames()) {
				frameCount++;
				assertEquals(7, frame.getKnockedDownPinsFirstRoll());
				assertEquals(3, frame.getKnockedDownPinsSecondRoll());

				if (frameCount < 10) {
					assertEquals(-1, frame.getKnockedDownPinsThirdRoll());
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

		bowlers = pm.getBowlers(fileLines);

		assertTrue(bowlers.size() == 2);

		for (Bowler bowler : bowlers) {
			assertTrue(bowler.getFrames().size() == 10);

			if (bowler.getName().equals("Carl")) {
				int frameCount = 0;
				for (Frame frame : bowler.getFrames()) {
					frameCount++;

					assertEquals(10, frame.getKnockedDownPinsFirstRoll());

					if (frameCount < 10) {
						assertEquals(0, frame.getKnockedDownPinsSecondRoll());
						assertEquals(-1, frame.getKnockedDownPinsThirdRoll());
					} else {
						assertEquals(10, frame.getKnockedDownPinsSecondRoll());
						assertEquals(10, frame.getKnockedDownPinsThirdRoll());
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
						assertEquals(-1, frame.getKnockedDownPinsThirdRoll());
					} else {
						assertEquals(2, frame.getKnockedDownPinsSecondRoll());
					}
					
					assertEquals(-1, frame.getKnockedDownPinsThirdRoll());
				}
			}

		}
	}
}
