package validators.implementations;

import java.util.List;

import org.apache.commons.validator.routines.IntegerValidator;

import validators.interfaces.ValidationRule;

public class NumberOfPinsThrownValidator implements ValidationRule {

	public NumberOfPinsThrownValidator() {
	}
	
	@Override
	public void validate(List<String> fileLines) throws Exception {
		for (String line : fileLines) {
			String s = line.split(" ")[1];
			Integer score = IntegerValidator.getInstance().validate(s);

			if ((score == null || score < 0 || score > 10) && !s.equals("F")) {
				throw new Exception("Score must be a number, positive, lesser or equal to 10 or F");
			}
		}
	}

}
