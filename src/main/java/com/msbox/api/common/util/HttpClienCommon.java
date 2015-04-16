package com.msbox.api.common.util;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title http 请求底层类，包含post，gei方法
 * @author kuangyj
 * @date Dec 5, 2012
 */
public class HttpClienCommon
{
	public static Logger log = Logger.getLogger(HttpClienCommon.class);

	public static Map<String, String> headMapUtf8 = new HashMap<String, String>();
	public static Map<String, String> headMapGBK = new HashMap<String, String>();
	public static String UTF = "UTF-8";
	public static String GBK = "GBK";
	private static String APPLICATION_JSON = "application/json";
	
	static
	{
		headMapUtf8.put("Content", "text/html,charset=utf-8");
		headMapUtf8.put("user-agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)");
		headMapUtf8.put("Cache-Control", "no-cache");
		headMapUtf8.put("If-Modified-Since","Thu, 29 Jul 2004 02:24:49 GMT");
	}
	
	static
	{
		headMapGBK.put("Content", "text/html,charset=GBK");
		headMapGBK.put("user-agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)");
		headMapGBK.put("Cache-Control", "no-cache");
		headMapGBK.put("If-Modified-Since","Thu, 29 Jul 2004 02:24:49 GMT");
	}
	
	/**
	 * @Title:doPost
	 * @Description:TODO
	 * @param paramMap
	 * @param headMap
	 * @param url
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param charset
	 * @return
	 * @return String
	 * @author kyj
	 * @date Dec 5, 2012
	 */
	public String doPost(Map<String, Object> paramMap, Map<String, String> headMap, String url,
			int connTimeOut, int soTimeOut,String charset)
	{
		if (connTimeOut == 0)
		{
			connTimeOut = 10 * 3000;
		}

		if (soTimeOut == 0)
		{
			soTimeOut = 20 * 3000;
		}

		HttpClient httpClient = new HttpClient();
		PostMethod method = new PostMethod(url);
		HttpConnectionManager httpManager = new SimpleHttpConnectionManager();
		HttpConnectionManagerParams httpParam = new HttpConnectionManagerParams();

		//设置链接超时时间
		httpParam.setConnectionTimeout(connTimeOut);
		//设置访问超时时间
		httpParam.setSoTimeout(soTimeOut);
		httpManager.setParams(httpParam);
		//设置http访问管理机制
		httpClient.setHttpConnectionManager(httpManager);
		
		//设置post请求的请求头
		method = (PostMethod) this.setPostHead(method, headMap);
		//设置请求的参数
		method = (PostMethod) this.setQueryPair(method, paramMap,charset);
		//设置http的重复访问机制
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(0,false));

		String backString = "";

		try
		{
			int status = httpClient.executeMethod(method);

			if (status == 200)
			{
				log.info("[请求接口" + url + "返回状态]status=" + status
						+ "[status=200请求成功]");

				backString = method.getResponseBodyAsString();
			}
			
			log.info("backString="+backString);
			
		}
		catch (Exception e)
		{
			log.info("[请求接口" + url + "异常");
			e.printStackTrace();
		}
		finally
		{
			method.releaseConnection();
		}

		return backString;
	}

	/**
	 * @Title:doGet
	 * @Description:get请求
	 * @param paramMap
	 * @param headMap
	 * @param url
	 * @param connTimeOut
	 * @param soTimeOut
	 * @return String
	 * @author kyj
	 * @date Oct 16, 2012
	 */
	public String doGet(Map<String, Object> paramMap, Map<String, String> headMap, String url,
			int connTimeOut, int soTimeOut,String charset)
	{
		if (connTimeOut == 0)
		{
			connTimeOut = 10 * 3000;
		}

		if (soTimeOut == 0)
		{
			soTimeOut = 20 * 3000;
		}

		HttpClient httpClient = new HttpClient();
		GetMethod method = new GetMethod(url);
		HttpConnectionManager httpManager = new SimpleHttpConnectionManager();
		HttpConnectionManagerParams httpParam = new HttpConnectionManagerParams();

		//设置链接超时时间
		httpParam.setConnectionTimeout(connTimeOut);
		//设置访问超时时间
		httpParam.setSoTimeout(soTimeOut);		
		httpManager.setParams(httpParam);
		//设置http访问管理机制
		httpClient.setHttpConnectionManager(httpManager);
		
		//设置post请求的请求头
		method = (GetMethod) this.setPostHead(method, headMap);
		//设置请求的参数
		method = (GetMethod) this.setQueryPair(method, paramMap,charset);
		//设置http的重复访问机制
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(0,true));
		
		String backString = "";

		try
		{
			int status = httpClient.executeMethod(method);

			if (status == 200)
			{
				log.info("[请求接口" + url + "返回状态]status=" + status
						+ "[status=200请求成功]");

				backString = method.getResponseBodyAsString();
			}
			else
			{
				log.info("[请求接口" + url + "返回状态]status=" + status
						+ "[status=]"+status+"请求成功]");
			}
			
			log.info("backString="+backString);
			
		}
		catch (Exception e)
		{
			log.info("[请求接口" + url + "异常");
			e.printStackTrace();
		}
		finally
		{
			method.releaseConnection();
		}

		return backString;
	}
	
	/**
	 * @Title:getPostParam
	 * @Description:设置请求头
	 * @param paramMap
	 * @return HttpMethodParams
	 * @author kyj
	 * @date Aug 1, 2012
	 */
	private HttpMethodBase setPostHead(HttpMethodBase method,Map<String, String> paramMap)
	{
		if (paramMap != null && paramMap.size() > 0)
		{
			for (Map.Entry<String, String> entry : paramMap.entrySet())
			{
				method.setRequestHeader(entry.getKey(), entry.getValue());
			}
		}

		return method;
	}

	/**
	 * @Title:setQueryPair
	 * @Description:设置参数
	 * @param method
	 * @param paramMap
	 * @return EntityEnclosingMethod
	 * @author kyj
	 * @date Sep 28, 2012
	 */
	protected HttpMethodBase setQueryPair(HttpMethodBase method,
			Map<String, Object> paramMap,String charset)
	{
		if (paramMap != null && paramMap.size() > 0)
		{
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			
			for (Map.Entry<String, Object> entry : paramMap.entrySet())
			{
				if(entry.getKey() != null)
				{
					NameValuePair name = new NameValuePair();
					name.setName(entry.getKey());
					try
					{
						name.setValue(entry.getValue()!=null?entry.getValue().toString():"");
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					list.add(name);
				}
			}
			
			if(list != null && list.size() > 0)
			{
				NameValuePair[] pairArr = new NameValuePair[list.size()];
				
				pairArr = list.toArray(pairArr);
				method.setQueryString(EncodingUtil.formUrlEncode(pairArr,charset));
			}
			
		}

		return method;
	}
	
	/**
	 * @Title:doPost
	 * @Description:TODO
	 * @param headMap
	 * @param url
	 * @param connTimeOut
	 * @param soTimeOut
	 * @param charset
	 * @return
	 * @return String
	 * @author kyj
	 * @date Dec 5, 2012
	 */
	public String doPostStream(JSONObject json, Map<String, String> headMap, String url,
			int connTimeOut, int soTimeOut,String charset)
	{
		if (connTimeOut == 0)
		{
			connTimeOut = 10 * 3000;
		}

		if (soTimeOut == 0)
		{
			soTimeOut = 20 * 3000;
		}

		HttpClient httpClient = new HttpClient();
		PostMethod method = new PostMethod(url);
		HttpConnectionManager httpManager = new SimpleHttpConnectionManager();
		HttpConnectionManagerParams httpParam = new HttpConnectionManagerParams();

		//设置链接超时时间
		httpParam.setConnectionTimeout(connTimeOut);
		//设置访问超时时间
		httpParam.setSoTimeout(soTimeOut);
		httpManager.setParams(httpParam);
		//设置http访问管理机制
		httpClient.setHttpConnectionManager(httpManager);
		
		//设置post请求的请求头
		method = (PostMethod) this.setPostHead(method, headMap);
		//设置请求的参数
		try
		{
			RequestEntity requestEntity = new StringRequestEntity(json.toString(),APPLICATION_JSON,charset);
			method.setRequestEntity(requestEntity);
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}  
		
		//设置http的重复访问机制
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(0,false));

		String backString = "";

		try
		{
			int status = httpClient.executeMethod(method);

			if (status == 200)
			{
				log.info("[请求接口" + url + "返回状态]status=" + status
						+ "[status=200请求成功]");

				backString = method.getResponseBodyAsString();
			}
			
			log.info("status="+status+",backString="+backString);
			
		}
		catch (Exception e)
		{
			log.info("[请求接口" + url + "异常");
			e.printStackTrace();
		}
		finally
		{
			method.releaseConnection();
		}

		return backString;
	}

	
}
