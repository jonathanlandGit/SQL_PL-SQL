package ss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	public static Date parseDate(String dateStr) {
		try {
			Date date = sdf.parse(dateStr);
			return date;
		} catch (ParseException e) {
			throw new RuntimeException("Invalid date " + dateStr + ". It must be in MM/dd/yyyy format.", e);
		}
	}
	
	public static java.sql.Date toSqlDate(Date date) {
		if(date==null)
			return null;
		
		return new java.sql.Date(date.getTime());
	}
}
