package com.msbox.api.common.exception;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DaoException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(DaoException.class);
	
	public <T> DaoException(Exception e, Class<T> clazz)
	{
		super();
		log.info("数据库错误信息："+e.getMessage()+" 位置在："+clazz.getName());
		e.printStackTrace();
	}
	
	public <T> DaoException(String message, Exception e, Class<T> clazz)
	{
		super();
		log.info("数据库错误信息："+message+" 位置在："+clazz.getName());
		e.printStackTrace();
	}
	
	public <T> DaoException(Exception e, Class<T> clazz,ComboPooledDataSource pool)
	{
		super();
		log.info("数据库错误信息："+e.getMessage()+" 位置在："+clazz.getName());
		getStatusPool(pool);
		e.printStackTrace();
	}
	

	/**
	* @Title: getStatusPool
	* @Description: 打印连接池的情况
	* @param
	* @param pool
	* @throws
	* @author kyj
	*/
	public void getStatusPool(ComboPooledDataSource pool)
	{

		try
		{
			log.info("poolname:" + pool.getDataSourceName() + ",MaxPoolSize:"
					+ pool.getMaxPoolSize() + ",num_busy_connections:"
					+ pool.getNumBusyConnectionsDefaultUser()
					+ ",num_idle_connections:"
					+ pool.getNumIdleConnectionsDefaultUser()
					+ ",NumBusyConnectionsAllUsers:"
					+ pool.getNumBusyConnectionsAllUsers()
					+ ",NumBusyConnectionsAllUsers:"
					+ pool.getNumBusyConnectionsAllUsers());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			log.info(e);
		}

	}
	
}
