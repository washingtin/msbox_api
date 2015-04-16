
package com.msbox.api.enitiy;

import com.msbox.api.common.constants.Constants;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ResultBean
{

	private Integer code;

	private String message;

	private Object result;
	
	
	public ResultBean()
	{
		this.code = Constants.RESULT_CODE_SUCCESS;
		this.message = "success";
		this.result = "";
	}

	public Integer getCode()
	{

		return code;
	}

	public void setCode(Integer code)
	{

		this.code = code;
	}

	public String getMessage()
	{

		return message;
	}

	public void setMessage(String message)
	{

		this.message = message;
	}

	public Object getResult()
	{

		return result;
	}

	public void setResult(Object result)
	{

		this.result = result;
	}
	
	public void setResult(String filedname,Object result)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put(filedname, result);
		
		this.result = map;
	}
	
	public void log()
	{
		Logger log = Logger.getLogger(ResultBean.class);
		
		log.info("code="+code+",message="+message+",result="+result);
	}
}
