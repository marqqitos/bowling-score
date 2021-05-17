package validators.implementations;

import java.util.List;

import utils.IntegerUtils;
import validators.interfaces.ValidationRule;

/**
 * This class is in charge of validating everything related to the format of the text file
 * @author: Marcos Caccavaio
 * @version: 1.0
 */
public class FormatValidator implements ValidationRule {
	
	public FormatValidator() {
	}
	
	/**
	 * check if game file has a valid format
	 * @param fileLines a list of string containing each player and his score on the attempt
	 * @throws Exception if line format is not "Player Score", i.e. "John 5"
	 * @throws Exception if score is not a number or F
	 */
	@Override
	public void validate(List<String> fileLines) throws Exception {
		for(String line : fileLines) {
			//Line should be "Player Score"
			String[] playerAndScore = line.split(" ");
			
			//Line does not have a space
			if(playerAndScore.length <= 1) {
				throw new Exception("File must contain player name and score");
			}
			else {
				//Get score from line as string in case it's a foul which is represented by "F"
				String s = playerAndScore[1];
				//Try to parse the score if it's a number
				Integer score = IntegerUtils.tryParseInt(s);
				
				if(score == null && !s.equals("F")) {
					throw new Exception("Score must be a number or F");
				}
			}
		}
	}

}
