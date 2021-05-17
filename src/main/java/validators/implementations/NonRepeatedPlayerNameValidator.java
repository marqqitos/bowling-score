package validators.implementations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import validators.interfaces.ValidationRule;

/**
 * This class is in charge of validating everything related to the names of the players
 * @author: Marcos Caccavaio
 * @version: 1.0
 */
public class NonRepeatedPlayerNameValidator implements ValidationRule {

	/**
	 * check if game has two or more players with the same name
	 * @param numberOfPlayers the number of players
	 * @throws Exception if game has two or more players with the same name
	 */
	@Override
	public void validate(List<String> fileLines) throws Exception {
		HashMap<String, Integer> playersAttempts = new HashMap<String, Integer>();
		
		for(String line : fileLines) {
			//Get the player from the line
			String player = line.split(" ")[0];
			
			
			if(playersAttempts.isEmpty() || !playersAttempts.containsKey(player)) {
				//Add player to hash map and set initial attempt
				playersAttempts.put(player, 1);
			}
			else {
				//Increase player attempt
				playersAttempts.put(player, playersAttempts.get(player) + 1);
			}
		}
		
		for(Map.Entry<String, Integer> pa : playersAttempts.entrySet()) {
			//Maximum attempts for a player is 21, so if there are more it's because there are two players with the same name 
			//or there are more than allowed attempts for a player
			if(pa.getValue() > 21) {
				throw new Exception("There are two players with the same name or the number of attempts for a person is not correct");
			}
		}
	}
}