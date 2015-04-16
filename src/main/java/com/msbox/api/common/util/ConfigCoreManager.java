package com.msbox.api.common.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @title 获取配置文件信息
 * @author kuangyj
 * @date Sep 18, 2012
 */
public class ConfigCoreManager
{

	private static Logger log = Logger.getLogger(ConfigCoreManager.class);

	private static Map<String, ConfigCoreManager> configMap = new ConcurrentHashMap<String, ConfigCoreManager>();
	/**
	 * 属性文件对象
	 */
	private Properties property;

	/**
	 * 文件的路径，保存时用来进行刷新用。
	 */
	private String configfile = "";

	/**
	 * 文件最后更新时间
	 */
	private long lastModifiedTime = 0;

	/**
	 * 属性文件
	 */
	private File file = null;

	private ConfigCoreManager()
	{

	}

	/**
	 * @Title:getInstance
	 * @Description:初始化一个configManager对象
	 * @return ConfigManager
	 * @author kyj
	 * @date Sep 13, 2012
	 */
	public static ConfigCoreManager getInstance(String proName)
	{
		String filePath = ConfigCoreManager.class.getResource("/")
		.getPath();
		ConfigCoreManager configManager = null;
		
		if (proName == null || proName.trim().equals(""))
		{
			configManager = new ConfigCoreManager();
			configManager.configfile = filePath+"Config.properties";
			
			configMap.put(filePath+"Config.properties", configManager);
		}
		else if(configMap.get(filePath + proName) == null)
		{
			configManager = new ConfigCoreManager();
			configManager.configfile = filePath + proName;
			configMap.put(filePath + proName, configManager);
		}
		else if(configMap.get(filePath + proName) != null)
		{
			configManager = configMap.get(filePath + proName);
		}
		else
		{
			configManager = configMap.get(filePath + "Config.properties");
		}

		return configManager;
	}

	/**
	 * @Title:getProperty
	 * @Description:加载文件
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

				//判断文件路径是否为空
				if (configfile == null || configfile.trim().equals(""))
				{
					//如果文件为null 测试寻找相对路径下的默认文件
					String filePath = this.getClass().getResource("/")
							.getPath()
							+ "Config.properties";
					configfile = filePath;
				}

				log.debug("Config.properties Path===" + configfile);
				ExecutorService exc = Executors.newFixedThreadPool(1);

				try
				{
					//加载文件
					property.load(new FileInputStream(configfile));

					//开启定时刷新文件的线程
					ResetFile reFile = new ResetFile();

					exc.submit(reFile);
				}
				catch (Exception e)
				{
					log.debug("没有读取到Config.properties 配置文件");
					e.printStackTrace();
					exc.shutdownNow();
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
	 * @return String
	 * @author kyj
	 * @date Sep 13, 2012
	 */
	public String getString(String key)
	{
		this.getProperty();
		String defaultValue = "";

		if (property != null)
		{
			defaultValue = property.getProperty(key, defaultValue);
		}

		log.info(key + "=" + defaultValue);

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

		if (property != null)
		{
			defaultValue = property.getProperty(key, defaultValue);
		}

		log.info(key + "=" + defaultValue);

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

		if (value != null && value.matches("^[-|0-9]+$"))
		{
			intVal = Integer.parseInt(value);
		}

		log.info(key + "=" + value);

		return intVal;
	}
	
	/**
	 * @Title: modifyProperties
	 * 
	 * @Description: 修改properties中的键值
	 * @param key 修改的keu
	 * @param value 修改的值
	 * @return void 返回类型
	 * @date 2011-11-23 下午01:03:55
	 * 
	 * @author kyj
	 */
	public void modifyProperties(String key, String value)
	{
		Properties prop = new Properties();

		try
		{
			File file = new File(configfile);

			InputStream fis = new FileInputStream(file);
			prop.load(fis);
			// 一定要在修改值之前关闭fis
			fis.close();
			OutputStream fos = new FileOutputStream(configfile);
			prop.setProperty(key, value);
			// 保存，并加入注释
			prop.store(fos, "Update '" + key + "' value");
			fos.close();
			property.load(new FileInputStream(configfile));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @Title:resetModifyFile
	 * @Description:修改文件重新加载
	 * @return void
	 * @author kyj
	 * @date Oct 26, 2012
	 */
	class ResetFile implements Runnable
	{

		public void run()
		{
			while (!Thread.interrupted())
			{
				file = new File(configfile);

				if (file != null)
				{
					long new_lasttime = file.lastModified();
					
					//判断文件是否更新
					if (new_lasttime > lastModifiedTime)
					{
						try
						{
							lastModifiedTime = new_lasttime;
							property.load(new FileInputStream(configfile));
						}
						catch (Exception e)
						{
							log.debug("没有读取到Config.properties 配置文件");
							e.printStackTrace();
						}
					}

					try
					{
						Thread.sleep(2 * 60 * 1000);// 睡眠2分钟
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}

	}
}
