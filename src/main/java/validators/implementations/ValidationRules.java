package validators.implementations;

import java.util.ArrayList;
import java.util.List;

import validators.interfaces.IValidationRules;
import validators.interfaces.ValidationRule;

public class ValidationRules implements IValidationRules {
	private List<ValidationRule> gameValidators = new ArrayList<ValidationRule>();
	private ValidationRule argumentValidator;
	private ValidationRule fileValidator;
	
	public ValidationRules() {
		gameValidators.add(new FormatValidator());
		gameValidators.add(new NumberOfPinsThrownValidator());
		gameValidators.add(new NonRepeatedPlayerNameValidator());
		gameValidators.add(new AttemptsValidator());
		argumentValidator = new ArgumentValidator();
		fileValidator = new FileValidator();
	}
	
	
	public List<ValidationRule> getGameValidators() {
		return gameValidators;
	}
	
	public ValidationRule getArgumentValidator() {
		return argumentValidator;
	}
	
	public ValidationRule getFileValidator() {
		return fileValidator;
	}
}
