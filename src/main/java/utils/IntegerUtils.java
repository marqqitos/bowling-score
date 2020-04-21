package utils;

public final class IntegerUtils {
	
	public static Integer tryParseInt(String value) {  
	     try {    
	         return Integer.parseInt(value);  
	      } catch (NumberFormatException e) {  
	         return null;  
	      }  
	}

}
