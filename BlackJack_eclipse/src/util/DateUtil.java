package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateFormat을 통일시키기 위하여 만든 클래스
 * @author LG
 *
 */
public class DateUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd(E) a hh:mm:ss"); 
	
	public static String getSysdateStr() {
		return sdf.format(new Date());
	}
	
	public static String toString(Date date) {
		return sdf.format(date);
	}
	
	public static Date toDate(String str) {
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("DateUtil parse error", e);
		}
	}
	
}
