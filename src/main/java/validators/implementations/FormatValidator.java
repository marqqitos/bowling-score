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
			String[] playerAndScore = line.split(" ");
			
			if(playerAndScore.length <= 1) {
				throw new Exception("File must contain player name and score");
			}
			else {
				String s = playerAndScore[1];
				Integer score = IntegerUtils.tryParseInt(s);
				
				if(score == null && !s.equals("F")) {
					throw new Exception("Score must be a number or F");
				}
			}
		}
	}

}
