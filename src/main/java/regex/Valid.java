package regex;

public class Valid {
	public static boolean matchesPattern(String str, String regex) {
		return str.matches(regex);
	}
	
	public static String validateInputWithEmpty(String input, String regex, String fielName, String mess) {
		if(input.isEmpty() || matchesPattern(input, regex)) {
			return null;
		}
		return fielName + mess;
	}
}
