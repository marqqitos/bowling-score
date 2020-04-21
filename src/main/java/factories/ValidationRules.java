package factories;

import java.util.ArrayList;
import java.util.List;

import validators.implementations.AttemptsValidator;
import validators.implementations.FormatValidator;
import validators.implementations.NonRepeatedPlayerNameValidator;
import validators.implementations.NumberOfPinsThrownValidator;
import validators.interfaces.ValidationRule;

public class ValidationRules {
	
	public static List<ValidationRule> get() {
		List<ValidationRule> validators = new ArrayList<ValidationRule>();
		
		validators.add(new FormatValidator());
		validators.add(new NumberOfPinsThrownValidator());
		validators.add(new NonRepeatedPlayerNameValidator());
		validators.add(new AttemptsValidator());
		
		return validators;
	}
}
