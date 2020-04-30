package validators.implementations;

import java.util.List;

import utils.IntegerUtils;
import validators.interfaces.ValidationRule;

public class FormatValidator implements ValidationRule {
	
	public FormatValidator() {
	}

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
