package validators;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import validators.implementations.ArgumentValidator;

public class ArgumentValidatorTest {
	private static ArgumentValidator av;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setup() {
		av = new ArgumentValidator();
	}
	
	@Test
	public void noArgumentPassedShouldThrowException() throws Exception{
		String[] args = new String[0];
		List<String> arguments = Arrays.asList(args);
		exception.expect(Exception.class);
		av.validate(arguments);
	}
	
	@Test
	public void argumentPassedShouldPass() throws Exception{
		String[] args = new String[] {"testFile/perfectGame.txt"};
		List<String> arguments = Arrays.asList(args);
		av.validate(arguments);
	}
}
