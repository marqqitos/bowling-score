package validators.interfaces;

import java.util.List;

public interface ValidationRule {

	void validate(List<String> fileLines) throws Exception;	
}
