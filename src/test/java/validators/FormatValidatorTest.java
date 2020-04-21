package validators;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import validators.implementations.FormatValidator;

public class FormatValidatorTest {
	private static FormatValidator fv;
	private List<String> fileLines;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setup() {
		fv = new FormatValidator();
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
		
		fv.validate(fileLines);
	}
	
	@Test
	public void fileHasFoulAttemptShouldPass() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "John F";
		
		fileLines.add(l1);
		fileLines.add(l2);
		
		fv.validate(fileLines);
	}

	@Test
	public void fileHasLineWithNoSpacesShouldFail() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "John3";
		
		fileLines.add(l1);
		fileLines.add(l2);
		
		exception.expect(Exception.class);
		fv.validate(fileLines);
	}
	
	@Test
	public void fileHasLineWithLettersWhereScoreShouldBeShouldFail() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "John gs";
		
		fileLines.add(l1);
		fileLines.add(l2);
		
		exception.expect(Exception.class);
		fv.validate(fileLines);
	}
	
	@Test
	public void fileHasLineWithNoScoreShouldFail() throws Exception {
		String l1 = "Jeff";
		String l2 = "John 5";
		
		fileLines.add(l1);
		fileLines.add(l2);
		
		exception.expect(Exception.class);
		fv.validate(fileLines);
	}
	
	@Test
	public void fileHasLineWithNumberAndLettersWhereScoreShouldBeShouldFail() throws Exception {
		String l1 = "Jeff 10";
		String l2 = "John 3fs";
		
		fileLines.add(l1);
		fileLines.add(l2);
		
		exception.expect(Exception.class);
		fv.validate(fileLines);
	}
	
	@Test
	public void fileHasEmptyLineShouldFail() throws Exception {
		String l1 = "";
		String l2 = "John 3fs";
		
		fileLines.add(l1);
		fileLines.add(l2);
		
		exception.expect(Exception.class);
		fv.validate(fileLines);
	}
	
	@Test
	public void fileHasEmptyNameButHasScoreShouldFail() throws Exception {
		String l1 = "10";
		String l2 = "John 3fs";
		
		fileLines.add(l1);
		fileLines.add(l2);
		
		exception.expect(Exception.class);
		fv.validate(fileLines);
	}
}
