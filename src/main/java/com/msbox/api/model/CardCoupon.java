
package com.msbox.api.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CardCoupon implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer card_coupon_id;

	private String card_no;

	private Integer active_id;

	private Integer buid;

	private String coupon_id;

	private Timestamp create_time;
	
	
	public Timestamp getCreate_time()
	{
	
		return create_time;
	}
	
	public void setCreate_time(Timestamp create_time)
	{
	
		this.create_time = create_time;
	}


	public Integer getCard_coupon_id()
	{
	
		return card_coupon_id;
	}

	
	public void setCard_coupon_id(Integer card_coupon_id)
	{
	
		this.card_coupon_id = card_coupon_id;
	}

	
	public String getCard_no()
	{
	
		return card_no;
	}

	
	public void setCard_no(String card_no)
	{
	
		this.card_no = card_no;
	}

	
	public Integer getActive_id()
	{
	
		return active_id;
	}

	
	public void setActive_id(Integer active_id)
	{
	
		this.active_id = active_id;
	}

	
	public Integer getBuid()
	{
	
		return buid;
	}

	
	public void setBuid(Integer buid)
	{
	
		this.buid = buid;
	}

	
	public String getCoupon_id()
	{
	
		return coupon_id;
	}

	
	public void setCoupon_id(String coupon_id)
	{
	
		this.coupon_id = coupon_id;
	}
	
	
}
