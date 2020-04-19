package factories;

import java.util.List;

import validators.implementations.FormatValidator;
import validators.implementations.NonRepeatedPlayerNameValidator;
import validators.implementations.NumberOfPinsThrownValidator;
import validators.interfaces.ValidationRule;

public class ValidationRules {
	private static List<ValidationRule> initialValidators;
	private static List<ValidationRule> gameValidators;
	
	public ValidationRules() {
		initialValidators.add(new FormatValidator());
		initialValidators.add(new NumberOfPinsThrownValidator());
		initialValidators.add(new NonRepeatedPlayerNameValidator());
	}
	
	public static List<ValidationRule> getInitialValidators() {
		return initialValidators;
	}
	
	public static List<ValidationRule> getGameValidators() {
		return gameValidators;
	}
}
