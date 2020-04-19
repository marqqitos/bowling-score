package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import factories.ValidationRules;
import validators.interfaces.ValidationRule;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		if (args == null || args.length <= 0) {
			System.out.println("Please pass a text file as an argument");
			return;
		}

		String fileName = args[0];

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
			List<String> fileLines = new ArrayList<>();
			fileLines = br.lines().collect(Collectors.toList());
			
			if(fileLines.size() < 12) {
				System.out.println("The text files has few attempts");
				return;
			}
			
			// check number of players
			//int numberOfPlayers = new GameOptions().getNumberOfPlayers(fileLines);

			try {
				for (ValidationRule rule : ValidationRules.getInitialValidators()) {
					rule.validate(fileLines);
				}
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
