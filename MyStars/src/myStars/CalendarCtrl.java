package myStars;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CalendarCtrl {
	
	static Scanner sc = new Scanner(System.in);
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public static String calendarToString(Calendar cal) {
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		return String.format("%02d/%02d/%4d %02d:%02d", day, month + 1, year, hour, minute);
	}
	
	public static Calendar stringToCalendar(String s) throws ParseException {
		Date date = dateFormat.parse(s);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public static boolean CheckAccessTime(Calendar start,Calendar end) throws ParseException {
	      Calendar currentTime= CalendarCtrl.GetCurrentDatetime();
	      if(currentTime.compareTo(start) >= 0 && currentTime.compareTo(end)<=0) {
	         System.out.println("valid access time!");
	         return true;
	      } else {
	         System.out.println("Invalid access time!");
	         System.out.println("Your access period is from "+ CalendarCtrl.calendarToString(start)+" to "+ CalendarCtrl.calendarToString(end));
	         return false;
	      }
	   }
	public static Calendar GetCurrentDatetime() {
	       //getting current date time using calendar class 
	       Calendar calobj = Calendar.getInstance();
	       System.out.println("Current time is " + dateFormat.format(calobj.getTime()));
	       return calobj;
	}

}
