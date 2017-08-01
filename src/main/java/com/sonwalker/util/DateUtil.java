package com.sonwalker.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * @author wuwl@19pay.com.cn
 * @date 2017-7-28
 * @describe日期工具类
 */

public class DateUtil {
	
	/**
	 * 解析日期格式的时间为Date类型
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseStringToDate(String date)  {
		if (StringUtils.isEmpty(date)) {
			return null;
		} 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date realDate = null;
		try {
			realDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return realDate;
	}
	
	/**
	 * 解析时间类型到字符型
	 * @param date
	 * @return
	 */
	public static String parseDateToString(Timestamp date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatDate = sdf.format(date);
		return formatDate;
	}
	
	public static void main(String[] args) {
		String aString = DateUtil.parseDateToString(new Timestamp(System.currentTimeMillis()));
		System.out.println(aString);
	}

}
