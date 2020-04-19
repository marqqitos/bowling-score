package validators;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import validators.implementations.NumberOfPinsThrownValidator;

public class NumberOfPinsThrownValidatorTest {
	private static NumberOfPinsThrownValidator noptv;
	private List<String> fileLines;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setup() {
		noptv = new NumberOfPinsThrownValidator();
	}

	@Before
	public void init() {
		fileLines = new ArrayList<>();
	}

	@Test
	public void fileHasValidFormatShouldPass() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "John 3";

		fileLines.add(l1);
		fileLines.add(l2);

		noptv.validate(fileLines);
	}

	@Test
	public void fileHasNegativeScoreShouldFail() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "John -3";

		fileLines.add(l1);
		fileLines.add(l2);

		exception.expect(Exception.class);
		noptv.validate(fileLines);
	}

	@Test
	public void fileHasScoreOverTenShouldFail() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "John 15";

		fileLines.add(l1);
		fileLines.add(l2);

		exception.expect(Exception.class);
		noptv.validate(fileLines);
	}

	@Test
	public void fileHasScoreWithOtherLetterThanFShouldFail() throws Exception {
		String l1 = "Jeff C";
		String l2 = "John 5";

		fileLines.add(l1);
		fileLines.add(l2);

		exception.expect(Exception.class);
		noptv.validate(fileLines);
	}

	@Test
	public void fileHasEmptyScoreShouldFail() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "John ";

		fileLines.add(l1);
		fileLines.add(l2);

		exception.expect(Exception.class);
		noptv.validate(fileLines);
	}

}
