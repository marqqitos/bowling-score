package validators.implementations;

import java.util.List;

import validators.interfaces.ValidationRule;

public class FileValidator implements ValidationRule {

	@Override
	public void validate(List<String> fileLines) throws Exception {
		if(fileLines.size() < 12) {
			throw new Exception("The text file has few attempts");
		}
	}

}
