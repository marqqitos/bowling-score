package validators.interfaces;

import java.util.List;

public interface IValidationRules {
	public List<ValidationRule> getGameValidators();
	public ValidationRule getArgumentValidator();	
	public ValidationRule getFileValidator();
}
