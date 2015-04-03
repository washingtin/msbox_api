package com.msbox.api.common.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @title 获取配置文件信息
 * @author kuangyj
 * @date Sep 18, 2012
 */
public class ConfigManager
{
	private static Logger log = Logger.getLogger(ConfigManager.class);
	private static Properties property;
	private static ConfigManager configManager;
	
	private ConfigManager()
	{
		
	}
	
	/**
	 * @Title:getInstance
	 * @Description:初始化一个configManager对象
	 * @return ConfigManager
	 * @author kyj
	 * @date Sep 13, 2012
	 */
	public static ConfigManager getInstance()
	{
		if(configManager == null)
		{
			configManager = new ConfigManager();
		}
		
		return configManager;
	}
	
	/**
	 * @Title:getProperty
	 * @Description:读取
	 * @return Properties
	 * @author kyj
	 * @date Sep 13, 2012
	 */
	private Properties getProperty()
	{
		if (property == null)
		{
			if (property == null || property.isEmpty())
			{
				property = new Properties();

				String filePath = this.getClass().getResource("/").getPath()
						+ "config/Config.properties";

				log.debug("clear_web.properties Path===" + filePath);

				try
				{
					property.load(new FileInputStream(filePath));
				}
				catch (Exception e)
				{
					log.debug("没有读取到Config.properties 配置文件");
					e.printStackTrace();
				}
			}

		}

		return property;
	}
	
	/**
	 * @Title:refreshConfig
	 * @Description:刷新当前配置文件
	 * @return void
	 * @author kyj
	 * @date Sep 13, 2012
	 */
	public void refreshConfig()
	{
		property = null;
		this.getProperty();
	}
	
	/**
	 * @Title:getString
	 * @Description:获得string 值
	 * @param key
	 * @param defaultValue
	 * @return String
	 * @author kyj
	 * @date Sep 13, 2012
	 */
	public String getString(String key)
	{
		this.getProperty();
		String defaultValue = "";
		
		if(property != null)
		{
			defaultValue = property.getProperty(key, defaultValue);
		}
		
		log.info(key +"="+defaultValue);
		
		return defaultValue;
	}
	
	/**
	 * @Title:getString
	 * @Description:获得string 值
	 * @param key
	 * @param defaultValue
	 * @return String
	 * @author kyj
	 * @date Sep 13, 2012
	 */
	public String getString(String key, String defaultValue)
	{
		this.getProperty();
		
		if(property != null)
		{
			defaultValue = property.getProperty(key, defaultValue);
		}
		
		log.info(key +"="+defaultValue);
		
		return defaultValue;
	}
	
	/**
	 * @Title:getInteger
	 * @Description: 获得整数值
	 * @param key
	 * @param defaultValue
	 * @return Integer
	 * @author kyj
	 * @date Sep 13, 2012
	 */
	public Integer getInteger(String key, String defaultValue)
	{
		Integer intVal = null;
			
		String value = this.getString(key, defaultValue);
		
		if(value != null && value.matches("^[-|0-9]+$"))
		{
			intVal = Integer.parseInt(value);
		}
		
		log.info(key +"="+value);
		
		return intVal;
	}
	
	
}
