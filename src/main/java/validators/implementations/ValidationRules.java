package validators.implementations;

import java.util.ArrayList;
import java.util.List;

import validators.interfaces.IValidationRules;
import validators.interfaces.ValidationRule;

/**
 * This class hold all of the Validators that will be in charge of checking that the file passed is okay
 * @author: Marcos Caccavaio
 * @version: 1.0
 */
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
