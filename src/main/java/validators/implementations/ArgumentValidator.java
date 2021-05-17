package validators.implementations;

import java.util.List;

import validators.interfaces.ValidationRule;

/**
 * This class validates that an argument is passed in the command line
 * @author: Marcos Caccavaio
 * @version: 1.0
 */
public class ArgumentValidator implements ValidationRule {

	public ArgumentValidator() {
		
	}
	
	/**
     * Validates that an argument is passed in the command line
     * @param args Command line arguments
     * @throws Exception if argument is not passed in the command line
     */
	@Override
	public void validate(List<String> args) throws Exception {
		if (args == null || args.size() <= 0) {
			throw new Exception("Please pass a text file as an argument");
		}
	}

}
