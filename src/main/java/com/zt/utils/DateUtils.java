package com.zt.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * <p>Title: DateUtils</p>
 * <p>Description: 日期工具类</p>
 * @author wjc
 * @date 2017年4月6日
 */
public class DateUtils {

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_SECOND_FORMAT_2 = "yyyyMMddHHmmss";
	public static final String DATE_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String CN_DATE_FORMAT = "yyyy年MM月dd日";

	private DateUtils() {
	}
	
	/**
	 * 
	 * <p>Description: 获取指定日期的前一天的日期</p>
	 * @param date
	 * @return
	 * @author wjc
	 * @date 2017年4月6日
	 */
	public static Date getYesterdayDate(Date date){
		DateTime dateTime = new DateTime(date);
		return dateTime.minusDays(1).toDate();
	}
	
	/**
	 * 
	 * <p>Description: 获取指定日期的前6天的日期</p>
	 * @param date
	 * @return
	 * @author wjc
	 * @date 2017年4月6日
	 */
	public static Date getWeekAgo(Date date){
		DateTime dateTime = new DateTime(date);
		return dateTime.minusDays(6).toDate();
	}
	
	/**
	 * 
	 * <p>Description: 获取指定日期的前9天的日期</p>
	 * @param date
	 * @return
	 * @author wjc
	 * @date 2017年4月6日
	 */
	public static Date getTenDaysAgo(Date date){
		DateTime dateTime = new DateTime(date);
		return dateTime.minusDays(9).toDate();
	}
	
	public static List<String> getHourList(Date date){
		List<String> result = new ArrayList<String>();
		if(date == null){
			return result;
		}
		DateTime dateTime = new DateTime(date);
		int hour = dateTime.getHourOfDay()+1;
		for (int i = 1; i <= 24; i++) {
			int temp = hour + i;
			if (temp > 24) {
				temp = temp - 24;
			}
			result.add(temp + "H");
		}
		
		return result;
	}
	
	public static List<Integer> getDayList(Date date){
		List<Integer> result = new ArrayList<Integer>();
		if(date == null){
			return result;
		}
		DateTime dateTime = new DateTime(date);
		for (int i = 9; i >= 0; i--) {
			result.add(dateTime.minusDays(i).getDayOfMonth());
		}
		
		return result;
	}
	
	public static List<String> getLegendDayList2(Date date){
		List<String> result = new ArrayList<String>();
		if(date == null){
			return result;
		}
		DateTime dateTime = new DateTime(date);
		for (int i = 9; i >= 0; i--) {
			result.add(dateTime.minusDays(i).toString("M月d日"));
		}
		
		return result;
	}

	/**
	 * 
	 * <p>
	 * Description: 解析yyyy-MM-dd HH:mm:ss格式的日期字符串，返回日期对象
	 * </p>
	 * 
	 * @param date
	 * @return
	 * @author wjc
	 * @date 2016年12月30日
	 */
	public static Date parseDate(String date) {
		Date result = null;
		if (StringUtils.isNotEmpty(date)) {
			DateTimeFormatter format = DateTimeFormat.forPattern(DATE_SECOND_FORMAT);
			DateTime dateTime = DateTime.parse(date, format);
			result = dateTime.toDate();
		}

		return result;
	}

	/**
	 * 
	 * <p>
	 * Description: 使用指定的日期格式解析日期字符串，返回日期对象
	 * </p>
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 * @author wjc
	 * @date 2016年12月30日
	 */
	public static Date parseDate(String date, String dateFormat) {
		Date result = null;
		if (StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(dateFormat)) {
			DateTimeFormatter format = DateTimeFormat.forPattern(dateFormat);
			DateTime dateTime = DateTime.parse(date, format);
			result = dateTime.toDate();
		}

		return result;
	}

	/**
	 * 
	 * <p>
	 * Description: 使用指定的格式格式化日期对象，返回格式化后的日期字符串
	 * </p>
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 * @author wjc
	 * @date 2016年12月30日
	 */
	public static String formatDate(Date date, String dateFormat) {
		String result = null;
		if (date != null && StringUtils.isNotEmpty(dateFormat)) {
			DateTimeFormatter format = DateTimeFormat.forPattern(dateFormat);
			DateTime dateTime = new DateTime(date.getTime());
			result = dateTime.toString(format);
		}
		return result;
	}
	
	/**
	 * 
	 * <p>Description: 将日期对象格式化为yyyy-MM-dd格式的字符串</p>
	 * @param date
	 * @return
	 * @author wjc
	 * @date 2017年4月6日
	 */
	public static String formatDate(Date date){
		if(date == null){
			return null;
		}
		String result = null;
		DateTimeFormatter format = DateTimeFormat.forPattern(DATE_FORMAT);
		DateTime dateTime = new DateTime(date.getTime());
		result = dateTime.toString(format);
		
		return result;
	}
	
	/**
	 * 
	 * <p>Description: 将日期对象格式化为yyyy-MM-dd HH:mm:ss格式的字符串</p>
	 * @param date
	 * @return
	 * @author wjc
	 * @date 2017年4月6日
	 */
	public static String formatDateTime(Date date){
		if(date == null){
			return null;
		}
		String result = null;
		DateTimeFormatter format = DateTimeFormat.forPattern(DATE_SECOND_FORMAT);
		DateTime dateTime = new DateTime(date.getTime());
		result = dateTime.toString(format);
		
		return result;
	}

	/**
	 * 
	 * <p>
	 * Description: 将yyyy-MM-dd格式的日期字符串转换为这一天的开始时间的日期对象
	 * 例如：将2017-01-01的日期字符串转换为表示2017-01-01 00:00:00的日期对象
	 * </p>
	 * 
	 * @param date
	 *            yyyy-MM-dd的日期格式的字符串
	 * @return
	 * @author wjc
	 * @date 2017年1月5日
	 */
	public static Date getStartDateTimeOfDay(String date) {
		Date result = null;
		if (StringUtils.isNotEmpty(date)) {
			DateTimeFormatter format = DateTimeFormat.forPattern(DATE_FORMAT);
			DateTime dateTime = format.parseDateTime(date);
			result = dateTime.withTimeAtStartOfDay().toDate();
		}

		return result;
	}

	/**
	 * 
	 * <p>
	 * Description: 将yyyy-MM-dd格式的日期字符串转换为这一天的结束时间的日期对象
	 * 例如：将2017-01-01的日期字符串转换为表示2017-01-01 23:59:59的日期对象
	 * </p>
	 * 
	 * @param date
	 *            yyyy-MM-dd的日期格式的字符串
	 * @return
	 * @author wjc
	 * @date 2017年1月5日
	 */
	public static Date getEndDateTimeOfDay(String date) {
		Date result = null;
		if (StringUtils.isNotEmpty(date)) {
			DateTimeFormatter format = DateTimeFormat.forPattern(DATE_FORMAT);
			DateTime dateTime = format.parseDateTime(date);
			result = dateTime.millisOfDay().withMaximumValue().toDate();
		}

		return result;
	}

	/**
	 * 
	 * <p>
	 * Description: 将不包含时间信息的日期对象转换为这一天的开始时间的日期对象
	 * 例如：将2017-01-01的日期对象转换为表示2017-01-01 00:00:00的日期对象
	 * </p>
	 * 
	 * @param date
	 * @return
	 * @author wjc
	 * @date 2017年1月5日
	 */
	public static Date getStartDateTimeOfDay(Date date) {
		Date result = null;
		if (date != null) {
			DateTime dateTime = new DateTime(date);
			result = dateTime.withTimeAtStartOfDay().toDate();
		}

		return result;
	}

	/**
	 * 
	 * <p>
	 * Description: 将不包含时间信息的日期对象转换为这一天的结束时间的日期对象
	 * 例如：将2017-01-01的日期对象转换为表示2017-01-01 23:59:59的日期对象
	 * </p>
	 * 
	 * @param date
	 * @return
	 * @author wjc
	 * @date 2017年1月5日
	 */
	public static Date getEndDateTimeOfDay(Date date) {
		Date result = null;
		if (date != null) {
			DateTime dateTime = new DateTime(date);
			result = dateTime.millisOfDay().withMaximumValue().toDate();
		}

		return result;
	}

	/**
	 * 
	 * <p>
	 * Description: 获取当前时间的日期对象
	 * </p>
	 * 
	 * @return
	 * @author wjc
	 * @date 2017年1月10日
	 */
	public static Date currentDate() {
		DateTime now = new DateTime();
		return now.toDate();
	}
	
	public static int getYearOfDate(Date date){
		if(date == null){
			return -1;
		}
		return new DateTime(date).getYear();
	}
	
	public static List<Integer> getHourList(){
		List<Integer> result = new ArrayList<Integer>();
		for(int i=0; i<24; i++){
			result.add(i);
		}
		return result;
	}
	
	/**
	 * 
	 * <p>Description: 获取当前时期前后某个天的日期</p>
	 * @param day
	 * @param date
	 * @return
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	public static Date paramesDateAddDay(Integer day,String date){
		DateTime dateTime = DateTime.parse(date);
		return dateTime.plusDays(day).toDate();
	}

	/**
	 *
	 * <p>Description: 获取指定时期前后某个天的日期</p>
	 * @param month
	 * @param date
	 * @return
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	public static Date paramesDateAddMonth(Integer month,String date){
		DateTime dateTime = DateTime.parse(date);
		return dateTime.plusMonths(month).toDate();
	}

	/**
	 * 
	 * <p>Description: 获取当前时期前后某个月的日期</p>
	 * @param month
	 * @return
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	public static Date currentDateAddMonth(Integer month){
		DateTime dateTime = new DateTime();
		return dateTime.plusMonths(month).toDate();
	}
}
