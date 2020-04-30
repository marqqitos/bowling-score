package appManagement.implementations;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import appManagement.interfaces.IFileManager;

public class FileManager implements IFileManager{
	public FileManager() {
		
	}

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
