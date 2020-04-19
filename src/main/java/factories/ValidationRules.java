package factories;

import java.util.List;

import validators.implementations.AttemptsValidator;
import validators.implementations.FormatValidator;
import validators.implementations.NonRepeatedPlayerNameValidator;
import validators.implementations.NumberOfPinsThrownValidator;
import validators.interfaces.ValidationRule;

public class ValidationRules {
	private static List<ValidationRule> validators;
	
	public ValidationRules() {
		validators.add(new FormatValidator());
		validators.add(new NumberOfPinsThrownValidator());
		validators.add(new NonRepeatedPlayerNameValidator());
		validators.add(new AttemptsValidator());
	}
	
	public static List<ValidationRule> get() {
		return validators;
	}
}
