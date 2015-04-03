
package com.msbox.api.common.util;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util
{

	private static MD5Util mD5Util;

	private static Logger log = Logger.getLogger(MD5Util.class);

	public static MD5Util getInstance()
	{

		if (mD5Util == null)
		{
			mD5Util = new MD5Util();
		}

		return mD5Util;
	}

	/**
	 * @Title:md5
	 * @Description:TODO
	 * @param str
	 * @param signType
	 * @param charset
	 * @return
	 * @return String
	 * @author kyj
	 * @date Sep 28, 2012
	 */
	public String md5(String str, String signType, String charset)
	{

		if (str == null)
		{
			return null;
		}

		MessageDigest messageDigest = null;
		try
		{
			messageDigest = MessageDigest.getInstance(signType);
			messageDigest.reset();
			messageDigest.update(str.getBytes(charset));
		}
		catch (NoSuchAlgorithmException e)
		{
			return str;
		}
		catch (UnsupportedEncodingException e)
		{
			return str;
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; ++i)
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));

		return md5StrBuff.toString();
	}

	/**
	 * @Title:md5
	 * @Description: 加密
	 * @param str
	 * @return String
	 * @author kyj
	 * @date Sep 28, 2012
	 */
	public String md5(String str)
	{

		String key = this.md5(str, "MD5", "UTF-8");

		return key;
	}

	/**
	 * @Title:getKeyedDigest
	 * @Description:TODO
	 * @param strSrc
	 * @param signType
	 * @param key
	 * @param charset
	 * @return
	 * @return String
	 * @author kyj
	 * @date Sep 28, 2012
	 */
	public String getKeyedDigest(String strSrc, String key, String signType,
			String charset)
	{

		try
		{
			MessageDigest md5 = MessageDigest.getInstance(signType);
			md5.update(strSrc.getBytes(charset));
			if (key == null)
				key = "";

			String result = "";

			byte[] temp = md5.digest(key.getBytes(charset));
			
			for (int i = 0; i < temp.length; ++i)
			{
				result = result
						+ Integer.toHexString(0xFF & temp[i] | 0xFFFFFF00)
								.substring(6);
			}

			log.info("串=" + strSrc + ",sign=" + result);
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.info("加密异常");

		}

		return null;
	}
}
