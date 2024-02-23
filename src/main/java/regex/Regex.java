package regex;

public class Regex {
	public static final String CHAR = "[a-zA-Z\\s]+$";
	public static final String NUM = "[0-9]+";
	public static final String DATE_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
	public static final String FLOAT = "[0-9]+(\\.[0-9]+)?";
	public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	public static final String TYPE = "^(15\\*20|20\\*20) m2$";
}
