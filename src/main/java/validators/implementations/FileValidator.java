package validators.implementations;

import java.util.List;

import validators.interfaces.ValidationRule;

/**
 * This class is in charge of validating everything related to the text file
 * @author: Marcos Caccavaio
 * @version: 1.0
 */
public class FileValidator implements ValidationRule {

	/**
	 * check if file has the minimum number of lines for a game to be played
	 * @param fileLines a list of string containing each player and his score on the attempt
	 * @throws Exception if game has few attempts
	 */
	@Override
	public void validate(List<String> fileLines) throws Exception {
		if(fileLines.size() < 12) {
			throw new Exception("The text file has few attempts");
		}
	}

}
