package com.msbox.api.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * @title 时间处理工具类
 * @author kuangyj
 * @date Dec 5, 2012
 */
public class CommonTimeUtil
{

	public static long oneDayMiss = 24 * 60 * 60 * 1000;
	
	private static CommonTimeUtil commonTimeUtil;
	
	/**
	* @Title: getInstance
	*
	* @Description: 实例化一个CommonSort对象
	* @return CommonSort    返回类型
	* @date 2011-10-28 上午11:06:51
	*
	* @author kyj
	*/
	public static CommonTimeUtil getInstance()
	{
		if(commonTimeUtil == null)
		{
			commonTimeUtil = new CommonTimeUtil();
		}
		
		return commonTimeUtil;
	}
	
	/**
	* @Title: changeToTimestamp
	*
	* @Description: 转换时间成Timestamp
	* @param time 传入的时间
	* @return Timestamp    返回类型
	* @date 2011-10-30 下午09:30:41
	*
	* @author kyj
	*/
	public Timestamp changeToTimestamp(String time)
	{
		if(time == null || time.trim().equals(""))
		{
			return null;
		}
			
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:hh:ss");
		Date data = new Date();
		
		Timestamp timestamp = null;
		
		try
		{
			data = format.parse(time);
			timestamp = new Timestamp(data.getTime());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return timestamp;
	}
	
	/**
	 * @Title: changeToDate
	 *
	 * @Description: 转换时间成Date
	 * @param time
	 * @return Timestamp    返回类型
	 * @date 2011-10-30 下午09:30:41
	 *
	 * @author kyj
	 */
	public Date changeToDate(String time, String formatStr)
	{
		if(time == null || time.trim().equals(""))
		{
			return null;
		}
		
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date data = new Date();
		
		try
		{
			data = format.parse(time);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return data;
	}
	/**
	 * @Title: changeToDate
	 *
	 * @Description: 转换时间成Date
	 * @param time
	 * @return Timestamp    返回类型
	 * @date 2011-10-30 下午09:30:41
	 *
	 * @author kyj
	 */
	public Date changeToDate(String time, String formatStr,Locale locale)
	{
		if(time == null || time.trim().equals(""))
		{
			return null;
		}
		
		SimpleDateFormat format = new SimpleDateFormat(formatStr,locale);
		Date data = new Date();
		
		try
		{
			data = format.parse(time);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * @Title: changeToTimstamp
	 *
	 * @Description: 转换时间成Timestamp
	 * @param time
	 * @param formatStr 输入的格式
	 * @return Timestamp    返回类型
	 * @date 2011-10-30 下午09:30:41
	 *
	 * @author kyj
	 */
	public Timestamp changeToTimstamp(String time, String formatStr)
	{
		if(time == null || time.trim().equals(""))
		{
			return null;
		}
		
		Timestamp timestamp = null;
		
		try
		{
			Date date = this.changeToDate(time, formatStr);
			
			timestamp = new Timestamp(date.getTime());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return timestamp;
	}
	
	/**
	 * @Title: changeToTimstamp
	 *
	 * @Description: 转换时间成Timestamp
	 * @param time
	 * @param formatStr 输入的格式
	 * @return Timestamp    返回类型
	 * @date 2011-10-30 下午09:30:41
	 *
	 * @author kyj
	 */
	public Timestamp changeToTimstamp(String time, String formatStr,Locale locale)
	{
		if(time == null || time.trim().equals(""))
		{
			return null;
		}
		
		Timestamp timestamp = null;
		
		try
		{
			Date date = this.changeToDate(time, formatStr);
			
			timestamp = new Timestamp(date.getTime());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return timestamp;
	}
	
	/**
	 * @Title: changeDateToString
	 *
	 * @Description: Date转换成String
	 * @param time
	 * @return String    返回类型
	 * @date 2011-10-30 下午09:30:41
	 *
	 * @author kyj
	 */
	public String changeDateToString(Timestamp time, String formatStr)
	{
		if(time == null)
		{
			return null;
		}
		
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String date = null;
		try
		{
			date = format.format(time);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return date;
	}
	
	/**
	 * @Title: changeDateToString
	 *
	 * @Description: Date转换成String
	 * @param time
	 * @return String    返回类型
	 * @date 2011-10-30 下午09:30:41
	 *
	 * @author kyj
	 */
	public String changeDateToString(Timestamp time)
	{
		String formatStr ="yyyy-MM-dd HH:mm:ss";
		if(time == null)
		{
			return null;
		}
		
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String date = null;
		try
		{
			date = format.format(time);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return date;
	}
	
	/**
	 * @Title: changeDateToString
	 *
	 * @Description: Date转换成String
	 * @param time
	 * @return String    返回类型
	 * @date 2011-10-30 下午09:30:41
	 *
	 * @author kyj
	 */
	public String changeDateToString(Date time, String formatStr)
	{
		if(time == null)
		{
			return null;
		}
		
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String date = null;
		try
		{
			date = format.format(time);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return date;
	}
	
	/**
	* @Title: changeToTimestamp
	*
	* @Description: 转换时间成Timestamp
	* @param time
	* @return Timestamp    返回类型
	* @date 2011-10-30 下午09:30:41
	*
	* @author kyj
	*/
	public Timestamp changeDateToTimestamp(String time)
	{
		if(time == null || time.trim().equals(""))
		{
			return null;
		}
			
		Date data = new Date(Long.parseLong(time));
		
		Timestamp timestamp = null;
		
		try
		{
			timestamp = new Timestamp(data.getTime());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return timestamp;
	}
	
	/**
	 * @Title:compareTime
	 * @Description:比较相差的天数
	 * @param dest 原始的时间
	 * @param osi 未来的时间
	 * @return int
	 * @author kyj
	 * @date Sep 26, 2012
	 */
	public int compareTime(Timestamp dest, Timestamp osi)
	{
		int day = 0;
		
		if(dest != null && osi != null)
		{
			long de = dest.getTime();
			long os = osi.getTime();
			
			long cha = os - de;
			
			day = (int) (cha/oneDayMiss);
		}
		
		return day;
	}
}
