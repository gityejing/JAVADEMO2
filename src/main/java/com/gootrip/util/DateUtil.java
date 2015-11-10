package com.gootrip.util;

/**
 * <p>Title: 时锟斤拷锟斤拷锟斤拷诘墓锟斤拷锟斤拷锟�/p>
 * <p>Description: DateUtil锟斤拷锟斤拷吮锟阶硷拷锟绞憋拷锟斤拷锟斤拷锟节革拷式锟斤拷锟皆硷拷锟斤拷些锟斤拷式锟斤拷锟街凤拷锟斤拷锟斤拷之锟斤拷转锟斤拷锟侥凤拷锟斤拷</p>
 * <p>Copyright: Copyright (c) 2007 advance,Inc. All Rights Reserved</p>
 * <p>Company: advance,Inc.</p>
 * @author advance
 * @version 1.0
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	//~ Static fields/initializers =============================================

	private static String datePattern = "MM/dd/yyyy";

	private static String timePattern = datePattern + " HH:MM a";

	//~ Methods ================================================================

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		return datePattern;
	}

	/**
	 * This method attempts to convert an Oracle-formatted date
	 * in the form dd-MMM-yyyy to mm/dd/yyyy.
	 *
	 * @param aDate date from database as a string
	 * @return formatted string for the ui
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(datePattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static final String date2Str(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(datePattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static final String date2Str(String pattern, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(pattern);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param strDate a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws java.text.ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			return null;
		}

		return (date);
	}

	public static final Date str2Date(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			return null;
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format:
	 * MM/dd/yyyy HH:MM a
	 *
	 * @param theTime the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 *
	 * @return the current date
	 * @throws java.text.ParseException
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(datePattern);

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param aDate a date object
	 * @return a formatted string representation of the date
	 *
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			System.out.print("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based
	 * on the System Property 'dateFormat'
	 * in the format you specify on input
	 *
	 * @param aDate A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(datePattern, aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 *
	 * @param strDate the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 *
	 * @throws java.text.ParseException
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate = null;

		try {

			aDate = convertStringToDate(datePattern, strDate);
		} catch (ParseException pe) {
			//log.error("Could not convert '" + strDate
			//          + "' to a date, throwing exception");
			pe.printStackTrace();
			return null;

		}
		return aDate;
	}

	//锟斤拷锟节革拷式转锟斤拷锟斤拷时锟斤拷锟�
	public static long getTimeStamp(String pattern, String strDate) {
		long returnTimeStamp = 0;
		Date aDate = null;
		try {
			aDate = convertStringToDate(pattern, strDate);
		} catch (ParseException pe) {
			aDate = null;
		}
		if (aDate == null) {
			returnTimeStamp = 0;
		} else {
			returnTimeStamp = aDate.getTime();
		}
		return returnTimeStamp;
	}

	//锟斤拷取锟斤拷前锟斤拷锟节碉拷锟绞达拷
	public static long getNowTimeStamp() {
		long returnTimeStamp = 0;
		Date aDate = null;
		try {
			aDate = convertStringToDate("yyyy-MM-dd HH:mm:ss", getNowDateTime());
		} catch (ParseException pe) {
			aDate = null;
		}
		if (aDate == null) {
			returnTimeStamp = 0;
		} else {
			returnTimeStamp = aDate.getTime();
		}
		return returnTimeStamp;
	}

	/**
	 *锟矫碉拷锟斤拷式锟斤拷锟斤拷锟较低筹拷锟角帮拷锟斤拷锟�
	 *@param strScheme 锟斤拷式模式锟街凤拷
	 *@return 锟斤拷式锟斤拷锟斤拷锟较低筹拷锟角笆憋拷洌拷锟斤拷锟斤拷锟届常锟斤拷锟斤拷锟截空达拷""
	 *@see java.util.SimpleDateFormat
	 */
	public static final String getNowDateTime(String strScheme) {
		String strReturn = null;
		Date now = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strScheme);
			strReturn = sdf.format(now);
		} catch (Exception e) {
			strReturn = "";
		}
		return strReturn;
	}

	public static final String getNowDateTime() {
		String strReturn = null;
		Date now = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strReturn = sdf.format(now);
		} catch (Exception e) {
			strReturn = "";
		}
		return strReturn;
	}

	/**
	 *转锟斤拷锟斤拷锟节革拷式"MM/dd/YY锟斤拷MM.dd.YY锟斤拷MM-dd-YY锟斤拷MM/dd/YY"锟斤拷锟斤拷锟斤拷锟轿拷锟侥革拷式yyyy-MM-dd
	 *@param strDate 锟斤拷转锟斤拷锟斤拷锟斤拷锟节革拷式
	 *@return 锟斤拷式锟斤拷锟斤拷锟斤拷锟斤拷冢锟斤拷锟斤拷锟斤拷锟届常锟斤拷锟斤拷锟截空达拷""
	 *@see java.util.SimpleDateFormat
	 */
	public static final String convertNormalDate(String strDate) {
		String strReturn = null;
		//锟饺把达拷锟斤拷锟斤拷锟街革拷锟阶拷锟斤拷锟絡ava锟斤拷识锟侥分革拷锟�
		String[] date_arr = strDate.split("\\.|\\/|\\-");
		try {
			if (date_arr.length == 3) {
				if (date_arr[2].length() != 4) {
					String nowYear = getNowDateTime("yyyy");
					date_arr[2] = nowYear.substring(0, 2) + date_arr[2];
				}
				strReturn = DateUtil.getDateTime("yyyy-MM-dd",
						convertStringToDate(combineStringArray(date_arr, "/")));
			}

		} catch (Exception e) {
			return strReturn;
		}
		return strReturn;
	}

	/**
	 * 锟斤拷锟街凤拷锟斤拷锟斤拷使锟斤拷指锟斤拷锟侥分革拷锟斤拷喜锟斤拷锟揭伙拷锟斤拷址锟�
	 * @param array 锟街凤拷锟斤拷锟斤拷
	 * @param delim 锟街革拷锟斤拷为null锟斤拷时锟斤拷使锟斤拷""锟斤拷为锟街革拷锟斤拷没锟叫分革拷锟斤拷
	 * @return 锟较诧拷锟斤拷锟斤拷址锟�
	 * @since  0.4
	 */
	public static final String combineStringArray(String[] array, String delim) {
		int length = array.length - 1;
		if (delim == null) {
			delim = "";
		}
		StringBuffer result = new StringBuffer(length * 8);
		for (int i = 0; i < length; i++) {
			result.append(array[i]);
			result.append(delim);
		}
		result.append(array[length]);
		return result.toString();
	}

	public static final int getWeekNum(String strWeek) {
		int returnValue = 0;
		if (strWeek.equals("Mon")) {
			returnValue = 1;
		} else if (strWeek.equals("Tue")) {
			returnValue = 2;
		} else if (strWeek.equals("Wed")) {
			returnValue = 3;
		} else if (strWeek.equals("Thu")) {
			returnValue = 4;
		} else if (strWeek.equals("Fri")) {
			returnValue = 5;
		} else if (strWeek.equals("Sat")) {
			returnValue = 6;
		} else if (strWeek.equals("Sun")) {
			returnValue = 0;
		} else if (strWeek == null) {
			returnValue = 0;
		}

		return returnValue;
	}
	/**
	 * 锟斤拷取锟斤拷锟斤拷锟街凤拷锟叫碉拷锟斤拷锟斤拷时锟斤拷锟绞撅拷址锟�
	 * @param strDate
	 * @return
	 */
	public static final String getSabreTime(String strDate) {
		String strReturn = "";
		try {

			Date d = DateUtil.str2Date("yyyy-MM-dd HH:mm:ss", CTool.replace(
					strDate, "T", " "));
			strReturn = DateUtil.date2Str("hh:mm aaa", d);

		} catch (Exception e) {
			return strReturn;
		}
		return strReturn;
	}
	/**
	 * 锟斤拷取锟斤拷锟斤拷锟街凤拷锟叫碉拷锟斤拷锟斤拷锟斤拷锟节憋拷示锟街凤拷
	 * @param strDate
	 * @return
	 */
	public static final String getSabreDate(String strDate) {
		String strReturn = "";
		try {
			String p = null;
			if (strDate.length() > 10)
				p = "yyyy-MM-dd HH:mm:ss";
			else
				p = "yyyy-MM-dd";
			Date d = DateUtil.str2Date(p, CTool.replace(strDate, "T", " "));
			strReturn = DateUtil.date2Str("EEE d-MMM", d);

		} catch (Exception e) {
			return strReturn;
		}
		return strReturn;
	}
	/**
	 * 锟斤拷取锟斤拷锟斤拷锟街凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞憋拷锟斤拷示
	 * @param strDate
	 * @return
	 */
	public static final String getSabreDateTime(String strDate) {
		String strReturn = "";
		try {
			String p = null;
			if (strDate.length() > 10)
				p = "yyyy-MM-dd HH:mm:ss";
			else
				p = "yyyy-MM-dd";
			Date d = DateUtil.str2Date(p, CTool.replace(strDate, "T", " "));
			strReturn = DateUtil.date2Str("EEE d-MMM hh:mm aaa", d);

		} catch (Exception e) {
			return strReturn;
		}
		return strReturn;
	}

	/**
	 *锟矫碉拷锟斤拷式锟斤拷锟斤拷锟街革拷锟斤拷锟斤拷锟�
	 *@param strScheme 锟斤拷式模式锟街凤拷
	 *@param date 指锟斤拷锟斤拷锟斤拷锟斤拷值
	 *@return 锟斤拷式锟斤拷锟斤拷锟街革拷锟斤拷锟斤拷冢锟斤拷锟斤拷锟斤拷锟届常锟斤拷锟斤拷锟截空达拷""
	 *@see java.util.SimpleDateFormat
	 */
	public static final String getDateTime(Date date, String strScheme) {
		String strReturn = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strScheme);
			strReturn = sdf.format(date);
		} catch (Exception e) {
			strReturn = "";
		}

		return strReturn;
	}
	/**
	 * 锟斤拷取锟斤拷锟斤拷
	 * @param timeType 时锟斤拷锟斤拷锟酵ｏ拷譬锟界：Calendar.DAY_OF_YEAR
	 * @param timenum  时锟斤拷锟斤拷锟街ｏ拷譬锟界：-1 锟斤拷锟届，0 锟斤拷锟届，1 锟斤拷锟斤拷
	 * @return 锟斤拷锟斤拷
	 */
	public static final Date getDateFromNow(int timeType, int timenum){
		Calendar cld = Calendar.getInstance();
		cld.set(timeType, cld.get(timeType) + timenum);
		return cld.getTime();
	}
	/**
	 * 锟斤拷取锟斤拷锟斤拷
	 * @param timeType 时锟斤拷锟斤拷锟酵ｏ拷譬锟界：Calendar.DAY_OF_YEAR
	 * @param timenum  时锟斤拷锟斤拷锟街ｏ拷譬锟界：-1 锟斤拷锟届，0 锟斤拷锟届，1 锟斤拷锟斤拷
	 * @param format_string 时锟斤拷锟绞斤拷锟狡╋拷纾�yyyy-MM-dd HH:mm:ss"
	 * @return 锟街凤拷
	 */
	public static final String getDateFromNow(int timeType, int timenum, String format_string){
		if ((format_string == null)||(format_string.equals("")))
			format_string = "yyyy-MM-dd HH:mm:ss";
		Calendar cld = Calendar.getInstance();
		Date date = null;
	    DateFormat df = new SimpleDateFormat(format_string);
		cld.set(timeType, cld.get(timeType) + timenum);
	    date = cld.getTime();
	    return df.format(date);
	}
	/**
	 * 锟斤拷取锟斤拷前锟斤拷锟节碉拷锟街凤拷
	 * @param format_string 时锟斤拷锟绞斤拷锟狡╋拷纾�yyyy-MM-dd HH:mm:ss"
	 * @return 锟街凤拷
	 */
	public static final String getDateNow(String format_string){
		if ((format_string == null)||(format_string.equals("")))
			format_string = "yyyy-MM-dd HH:mm:ss";
		Calendar cld = Calendar.getInstance();
	    DateFormat df = new SimpleDateFormat(format_string);
	    return df.format(cld.getTime());
	}

    private DateUtil() {
    }

}
