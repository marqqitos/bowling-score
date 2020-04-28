package main;

import java.util.Arrays;
import java.util.List;

import fileManagement.implementations.FileManager;
import fileManagement.interfaces.IFileManager;
import gameManagement.implementations.BowlerManager;
import gameManagement.implementations.FormatManager;
import gameManagement.implementations.ScoreManager;
import gameManagement.interfaces.IBowlerManager;
import gameManagement.interfaces.IFormatManager;
import gameManagement.interfaces.IScoreManager;
import models.Bowler;
import validators.implementations.ValidationRules;
import validators.interfaces.IValidationRules;
import validators.interfaces.ValidationRule;

public class App {

	public static void main(String[] args) {
		List<String> arguments = Arrays.asList(args);
		IValidationRules vr = new ValidationRules();

		try {
			vr.getArgumentValidator().validate(arguments);

			String fileName = args[0];

			IFileManager fileManager = new FileManager();

			List<String> fileLines = fileManager.getFileLines(fileName);
			vr.getFileValidator().validate(fileLines);

			for (ValidationRule rule : vr.getGameValidators()) {
				rule.validate(fileLines);
			}

			IBowlerManager bm = new BowlerManager();
			IScoreManager sm = new ScoreManager();
			IFormatManager fm = new FormatManager(sm);

			List<Bowler> bowlers = bm.getBowlers(fileLines);
			fm.printScoreboard(bowlers);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
