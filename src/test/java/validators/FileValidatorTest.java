package validators;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import validators.implementations.FileValidator;

public class FileValidatorTest {
	private static FileValidator fv;
	private List<String> fileLines;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setup() {
		fv = new FileValidator();
	}
	
	@Before
	public void init() {
		fileLines = new ArrayList<>();
		int i = 0;
		while (i < 11) {
			fileLines.add("Carl 10");
			i++;
		}
	}
	
	@Test
	public void fileLinesWithLessThanMinimumLengthShouldThrowException() throws Exception {
		exception.expect(Exception.class);
		fv.validate(fileLines);
	}
	
	@Test
	public void fileLinesWithMinimumLengthShouldPass() throws Exception {
		fileLines.add("Carl 10");
		fv.validate(fileLines);
	}
}
