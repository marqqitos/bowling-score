package appManagement.implementations;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import appManagement.interfaces.IFileManager;

/**
 * This class is in charge of managing the text file passed as an argument to the application
 * @author: Marcos Caccavaio
 * @version: 1.0
 */
public class FileManager implements IFileManager{
	public FileManager() {
		
	}

	/**
     * Reads the file passed as an argument
     * @param fileName the name of the .txt file
     * @throws Exception if it cannot open the file
     */
	@Override
	public List<String> getFileLines(String fileName) throws Exception {
		//Try to read the file
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
			//Get all the lines of the file
			return br.lines().collect(Collectors.toList());
		}
		catch(Exception e) {
			throw new Exception("Could not open file");
		}
		
	}
}
