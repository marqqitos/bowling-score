package main;

import java.util.Arrays;
import java.util.List;

import appManagement.implementations.AppManager;
import appManagement.implementations.FileManager;
import appManagement.interfaces.IAppManager;
import appManagement.interfaces.IFileManager;
import gameManagement.interfaces.IBowlerManager;
import gameManagement.interfaces.IFormatManager;
import models.Bowler;
import validators.implementations.ValidationRules;
import validators.interfaces.IValidationRules;
import validators.interfaces.ValidationRule;

public class App {

	public static void main(String[] args) {
		List<String> arguments = Arrays.asList(args);
		IValidationRules vr = new ValidationRules();

		try {
			//Validate that there is at least a parameter passed to the program
			vr.getArgumentValidator().validate(arguments);

			String fileName = args[0];
			IFileManager fileManager = new FileManager();

			//Read the file and get the file lines
			List<String> fileLines = fileManager.getFileLines(fileName);
			
			//Validate that is has a minimum of lines to consider it a game
			vr.getFileValidator().validate(fileLines);

			//Apply all the game rules validations to the file lines
			for (ValidationRule rule : vr.getGameValidators()) {
				rule.validate(fileLines);
			}

			IAppManager appManager = new AppManager();
			IBowlerManager bowlerManager = appManager.getBowlerManager();
			IFormatManager formatManager = appManager.getFormatManager();

			List<Bowler> bowlers = bowlerManager.getBowlers(fileLines);
			formatManager.printScoreboard(bowlers);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
