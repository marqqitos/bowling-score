package validators.implementations;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import validators.interfaces.ValidationRule;

public class NonRepeatedPlayerNameValidator implements ValidationRule {

	@Override
	public void validate(List<String> fileLines) throws Exception {
		String player1 = fileLines.get(0);
		if(player1.equals(fileLines.get(1))) {
			List<String> playerAttempts = fileLines.stream().filter(s -> s.contains(player1)).collect(Collectors.toList());
			
			if(playerAttempts.size() < 12 || playerAttempts.size() > 21) {
				throw new Exception("There are two players with the same name or the number of attempts for a person is not correct");
			}
		}
		else {
			Stack<String> playersTurn = new Stack<String>();
			LinkedList<String> auxPT = new LinkedList<String>();
			
			for(String line : fileLines) {
				String player = line.split(" ")[0];
				
				if(!playersTurn.contains(player)) {
					playersTurn.add(player);
				}
				else {
					String lastInserted = playersTurn.peek();
					
					if(!player.equals(lastInserted)) {
						throw new Exception("There are two or more players with the same name");
					}
					else {
						auxPT.add(playersTurn.pop());
						
						if(playersTurn.size() == 0) {
							while(!auxPT.isEmpty()) {
								playersTurn.add(auxPT.poll());
							}
						}
					}
				}
			}
		}
	}

}
