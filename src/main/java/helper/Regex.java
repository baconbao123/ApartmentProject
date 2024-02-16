package helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	public static final String NUM = "[0-9]+";
	public static final String CHAR = "^[a-zA-Z\\s]+$";
	public static final String GENDER = "[0-1]";
	public final static String DATE = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
	public static final String FLOAT = "[0-9]+(\\.[0-9]+)?";
	public static final String STRING= "^[a-zA-Z0-9!@#$%^&*()_+{}\\[\\]:;<>,.?/~\\\\s-]*$\r\n";
	
	 // Kiểm tra định dạng email
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    // Kiểm tra chuỗi chỉ chứa số
    public static boolean isValidNumber(String phone) {
    	return Pattern.matches("\\d+", phone);
    }
 // Kiểm tra định dạng ngày
    public static boolean isValidDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        try {
            format.format(date); 
            return true; 
        } catch (Exception e) {
            return false; 
        }
    }
   

	
}
