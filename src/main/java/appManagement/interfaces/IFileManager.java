package appManagement.interfaces;

import java.util.List;

public interface IFileManager {
	public List<String> getFileLines(String fileName) throws Exception;
}
