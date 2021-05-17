package validators.implementations;

import java.util.List;

import utils.IntegerUtils;
import validators.interfaces.ValidationRule;

/**
 * This class is in charge of validating everything related to the number of pins thrown
 * @author: Marcos Caccavaio
 * @version: 1.0
 */
public class NumberOfPinsThrownValidator implements ValidationRule {

	public NumberOfPinsThrownValidator() {
	}
	
	/**
	 * check if score has a valid number of pins throwed
	 * @param fileLines a list of string containing each player and his score on the attempt
	 * @throws Exception if score is not a number, positive, lesser or equal to 10 or the letter F
	 */
	@Override
	public void validate(List<String> fileLines) throws Exception {
		for (String line : fileLines) {
			String s = line.split(" ")[1];
			//Get the score
			Integer score = IntegerUtils.tryParseInt(s);

			//Check that the score is a number between 0 and 10 or F if it's a foul
			if ((score == null || score < 0 || score > 10) && !s.equals("F")) {
				throw new Exception("Score must be a number, positive, lesser or equal to 10 or F");
			}
		}
	}

}
