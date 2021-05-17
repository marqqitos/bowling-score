package validators;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import models.Bowler;
import validators.implementations.AttemptsValidator;

public class AttemptsValidatorTest {
	private static AttemptsValidator noav;
	private List<String> fileLines;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setup() {
		noav = new AttemptsValidator();
	}

	@Before
	public void init() {
		fileLines = new ArrayList<>();
	}

	@Test
	public void allAttemptsAreStrikesWithOnePlayerShouldPass() throws Exception {
		int i = 0;
		while (i < 12) {
			fileLines.add("Carl 10");
			i++;
		}

		noav.validate(fileLines);
	}
	
	@Test
	public void allAttemptsAreFoulsWithOnePlayerShouldPass() throws Exception {
		int i = 0;
		while (i < 20) {
			fileLines.add("Carl F");
			i++;
		}

		noav.validate(fileLines);
	}
	
	@Test
	public void allAttemptsAreZeroesWithExtraAttemptFrame10OnePlayerShouldFail() throws Exception {
		int i = 0;
		while (i < 21) {
			fileLines.add("Carl 0");
			i++;
		}
		
		exception.expect(Exception.class);
		noav.validate(fileLines);
	}
	
	@Test
	public void allAttemptsAreFoulsWithTwoPlayersShouldPass() throws Exception {
		int i = 0;
		while (i < 20) {
			fileLines.add("Carl F");
			fileLines.add("John F");			
			i++;
		}

		noav.validate(fileLines);
	}
	
	@Test
	public void allAttemptsAreZerosWithOnePlayerShouldPass() throws Exception {
		int i = 0;
		while (i < 20) {
			fileLines.add("Carl 0");
			i++;
		}

		noav.validate(fileLines);
	}

	@Test
	public void allAttemptsWithNoStrikesOrSparesWithOnePlayerShouldPass() throws Exception {
		int i = 0;
		while (i < 20) {
			fileLines.add("Carl 4");
			i++;
		}

		noav.validate(fileLines);
	}

	@Test
	public void attemptsWithStrikesAndSparesWithOnePlayerShouldPass() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "Jeff 7";
		String l3 = "Jeff 3";

		fileLines.add(l1);

		fileLines.add(l2);
		fileLines.add(l3);

		fileLines.add(l1);

		fileLines.add(l2);
		fileLines.add(l3);

		fileLines.add(l1);

		fileLines.add(l2);
		fileLines.add(l3);

		fileLines.add(l1);

		fileLines.add(l2);
		fileLines.add(l3);

		fileLines.add(l1);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l2);

		noav.validate(fileLines);
	}

	@Test
	public void allAttemptsAreStrikesWithTwoPlayersShouldPass() throws Exception {
		int i = 0;
		while (i < 12) {
			fileLines.add("Jeff 10");
			fileLines.add("John 10");
			i++;
		}

		noav.validate(fileLines);
	}

	@Test
	public void allAttemptsWithNoStrikesOrSparesWithTwoPlayersShouldPass() throws Exception {
		int i = 0;
		while (i < 20) {
			fileLines.add("Carl 4");
			fileLines.add("John 4");
			i++;
		}

		noav.validate(fileLines);
	}

	@Test
	public void attemptsWithStrikesAndSparesWithTwoOrMorePlayersShouldPass() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "Jeff 7";
		String l3 = "Jeff 3";
		String l4 = "John 10";
		String l5 = "John 7";
		String l6 = "John 3";

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l2);
		fileLines.add(l5);
		fileLines.add(l6);
		fileLines.add(l5);
		
		noav.validate(fileLines);
	}

	@Test
	public void lessThanMinimumAttemptsForOnePersonShouldFail() throws Exception {
		int i = 0;
		while (i < 10) {
			fileLines.add("Carl 10");
			i++;
		}

		exception.expect(Exception.class);
		noav.validate(fileLines);
	}

	@Test
	public void lessThanMinimumAttemptsForAtLeastOneOfTwoOrMorePlayersShouldFail() throws Exception {
		int i = 0;
		int j = 0;
		while (j < 12) {
			if(i < 10) {
				fileLines.add("Carl 10");				
			}
			
			fileLines.add("Jeff 10");
			i++;
			j++;
		}

		exception.expect(Exception.class);
		noav.validate(fileLines);
	}

	@Test
	public void moreThanMaximumAttemptsForOnePersonShouldFail() throws Exception {
		int i = 0;
		while (i < 22) {
			fileLines.add("Carl 4");
			fileLines.add("John 4");
			i++;
		}

		exception.expect(Exception.class);
		noav.validate(fileLines);
	}

	@Test
	public void moreThanMaximumAttemptsForAtLeastOneOfTwoOrMorePlayersShouldFail() throws Exception {
		int i = 0;
		int j = 0;
		while (j < 22) {
			if(i < 15) {
				fileLines.add("Carl 10");				
			}
			
			fileLines.add("Jeff 10");
			i++;
			j++;
		}

		exception.expect(Exception.class);
		noav.validate(fileLines);
	}

	@Test
	public void lessAttemptsForAPlayerShouldFail() throws Exception {
		int i = 0;
		while (i < 12) {
			fileLines.add("Carl 4");
			i++;
		}

		exception.expect(Exception.class);
		noav.validate(fileLines);
	}
	
	@Test
	public void invalidExtraAttemptInLastFrameForAPlayerShouldFail() throws Exception {
		int i = 0;
		while (i < 21) {
			fileLines.add("Carl 4");
			i++;
		}

		exception.expect(Exception.class);
		noav.validate(fileLines);
	}

	@Test
	public void moreAttemptsInAFrameWithAtLeastOneOfTwoOrMorePlayersShouldFail() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "Jeff 7";
		String l3 = "Jeff 3";
		String l4 = "John 10";
		String l5 = "John 7";
		String l6 = "John 3";
		String l7 = "Mark 10";
		String l8 = "Mark 7";
		String l9 = "Mark 3";

		fileLines.add(l1);
		fileLines.add(l4);
		fileLines.add(l7);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		fileLines.add(l7);
		fileLines.add(l8);
		fileLines.add(l9);

		exception.expect(Exception.class);
		noav.validate(fileLines);
	}

	@Test
	public void extraAttemptInLastFrameForPlayerWithNoStrikeOrSpareShouldFail() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "Jeff 7";
		String l3 = "Jeff 2";
		String l4 = "John 10";
		String l5 = "John 7";
		String l6 = "John 3";

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l2);
		fileLines.add(l5);
		fileLines.add(l6);
		fileLines.add(l5);


		exception.expect(Exception.class);
		noav.validate(fileLines);
	}

	@Test
	public void extraAttemptInLastFrameForPlayerWithStrikeOrSpareShouldPass() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "Jeff 7";
		String l3 = "Jeff 3";
		String l4 = "John 10";
		String l5 = "John 7";
		String l6 = "John 3";

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l2);
		fileLines.add(l5);
		fileLines.add(l6);
		fileLines.add(l5);

		noav.validate(fileLines);
	}

	@Test
	public void moreThanTenFramesShouldFail() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "Jeff 7";
		String l3 = "Jeff 3";
		String l4 = "John 10";
		String l5 = "John 7";
		String l6 = "John 3";

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l2);
		fileLines.add(l5);
		fileLines.add(l6);
		fileLines.add(l5);
		

		exception.expect(Exception.class);
		noav.validate(fileLines);
	}
	
	@Test
	public void lessThanTenFramesShouldFail() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "Jeff 7";
		String l3 = "Jeff 3";
		String l4 = "John 10";
		String l5 = "John 7";
		String l6 = "John 3";

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l2);
		fileLines.add(l5);
		fileLines.add(l6);
		fileLines.add(l5);
		
		exception.expect(Exception.class);
		noav.validate(fileLines);
	}


	@Test
	public void tenFramesShouldPass() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "Jeff 7";
		String l3 = "Jeff 3";
		String l4 = "John 10";
		String l5 = "John 7";
		String l6 = "John 3";

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l2);
		fileLines.add(l5);
		fileLines.add(l6);
		fileLines.add(l5);
		
		noav.validate(fileLines);
	}
	
	@Test
	public void twoAttempsAddUpMoreThanTenWithTwoPlayersShouldFail() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "Jeff 7";
		String l3 = "Jeff 7";
		String l4 = "John 10";
		String l5 = "John 7";
		String l6 = "John 7";

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);

		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l5);
		fileLines.add(l6);
		
		fileLines.add(l1);
		fileLines.add(l4);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l2);
		fileLines.add(l5);
		fileLines.add(l6);
		fileLines.add(l5);


		exception.expect(Exception.class);
		noav.validate(fileLines);
	}
	
	@Test
	public void twoAttempsAddUpMoreThanTenWithOnePlayerShouldFail() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "Jeff 7";
		String l3 = "Jeff 7";

		fileLines.add(l1);

		fileLines.add(l2);
		fileLines.add(l3);

		fileLines.add(l1);

		fileLines.add(l2);
		fileLines.add(l3);
		
		fileLines.add(l1);

		fileLines.add(l2);
		fileLines.add(l3);
		
		fileLines.add(l1);

		fileLines.add(l2);
		fileLines.add(l3);
		
		fileLines.add(l1);

		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l2);

		exception.expect(Exception.class);
		noav.validate(fileLines);
	}
	
	@Test
	public void validMatchTwoPlayersShouldPass() throws Exception {
		fileLines.add("Jeff 10");
		fileLines.add("John 3");
		fileLines.add("John 7");
		fileLines.add("Jeff 7");
		fileLines.add("Jeff 3");
		fileLines.add("John 6");
		fileLines.add("John 3");
		fileLines.add("Jeff 9");
		fileLines.add("Jeff 0");
		fileLines.add("John 10");
		fileLines.add("Jeff 10");
		fileLines.add("John 8");
		fileLines.add("John 1");
		fileLines.add("Jeff 0");
		fileLines.add("Jeff 8");
		fileLines.add("John 10");
		fileLines.add("Jeff 8");
		fileLines.add("Jeff 2");
		fileLines.add("John 10");
		fileLines.add("Jeff F");
		fileLines.add("Jeff 6");
		fileLines.add("John 9");
		fileLines.add("John 0");
		fileLines.add("Jeff 10");
		fileLines.add("John 7");
		fileLines.add("John 3");
		fileLines.add("Jeff 10");
		fileLines.add("John 4");
		fileLines.add("John 4");
		fileLines.add("Jeff 10");
		fileLines.add("Jeff 8");
		fileLines.add("Jeff 1");
		fileLines.add("John 10");
		fileLines.add("John 9");
		fileLines.add("John 0");
		
		noav.validate(fileLines);
	}
	
	@Test
	public void allFoulsAndSpareShouldPass() throws Exception {
		int f = 0;
		while (f < 10) {
			fileLines.add("Carl F");
			fileLines.add("Carl 10");
			f++;
		}
		
		fileLines.add("Carl F");

		noav.validate(fileLines);
	}
}
