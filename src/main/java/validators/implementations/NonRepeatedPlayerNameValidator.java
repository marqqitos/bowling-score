package validators.implementations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import validators.interfaces.ValidationRule;

public class NonRepeatedPlayerNameValidator implements ValidationRule {

	@Override
	public void validate(List<String> fileLines) throws Exception {
		HashMap<String, Integer> playersAttempts = new HashMap<String, Integer>();
		
		for(String line : fileLines) {
			String player = line.split(" ")[0];
			
			if(playersAttempts.isEmpty() || !playersAttempts.containsKey(player)) {
				playersAttempts.put(player, 1);
			}
			else {
				playersAttempts.put(player, playersAttempts.get(player) + 1);
			}
		}
		
		for(Map.Entry<String, Integer> pa : playersAttempts.entrySet()) {
			if(pa.getValue() > 21) {
				throw new Exception("There are two players with the same name or the number of attempts for a person is not correct");
			}
		}
	}
}