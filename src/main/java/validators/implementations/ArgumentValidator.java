package validators.implementations;

import java.util.List;

import validators.interfaces.ValidationRule;

public class ArgumentValidator implements ValidationRule {

	public ArgumentValidator() {
		
	}
	
	@Override
	public void validate(List<String> args) throws Exception {
		if (args == null || args.size() <= 0) {
			throw new Exception("Please pass a text file as an argument");
		}
	}

}
