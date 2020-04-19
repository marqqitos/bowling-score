package validators;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import validators.implementations.NonRepeatedPlayerNameValidator;

public class NonRepeatedPlayerNameValidatorTest {
	private static NonRepeatedPlayerNameValidator nrpnv;
	private List<String> fileLines;
	private static String l1;
	private static String l2;
	private static String l3;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setup() {
		nrpnv = new NonRepeatedPlayerNameValidator();
		l1 = "Carl 10";
		l2 = "Jeff 5";
		l3 = "John 8";
	}

	@Before
	public void init() {
		fileLines = new ArrayList<>();
	}

	@Test
	public void fileHasOnePlayerShouldPass() throws Exception {
		int i = 0;
		while(i < 12) {
			fileLines.add("Carl 10");
			i++;
		}
		
		nrpnv.validate(fileLines);
	}

	@Test
	public void fileHasTwoPlayersWithDifferentNameShouldPass() throws Exception {
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l2);
		
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l1);
		
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l1);
		
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l2);

		nrpnv.validate(fileLines);
	}
	

	@Test
	public void fileHasTwoPlayersWithSameNameShouldFail() throws Exception {
		int i = 0;
		while(i < 24) {
			fileLines.add("Carl 10");
			i++;
		}
		
		exception.expect(Exception.class);
		nrpnv.validate(fileLines);
	}
	
	@Test
	public void fileHasThreePlayersWithDifferentNameShouldPass() throws Exception {
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l3);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l3);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l1);
		fileLines.add(l2);
		
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l1);
		fileLines.add(l1);
		
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l3);
		fileLines.add(l1);
		
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l3);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l3);
		fileLines.add(l1);
		
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l3);
		fileLines.add(l3);
		fileLines.add(l3);

		nrpnv.validate(fileLines);
	}
	
	@Test
	public void fileHasThreePlayersTwoWithSameNameShouldFail() throws Exception {
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l1);
		
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l1);
		
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l1);
		
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l2);
		fileLines.add(l1);
		fileLines.add(l1);
		fileLines.add(l1);
		
		exception.expect(Exception.class);
		nrpnv.validate(fileLines);
	}
}
