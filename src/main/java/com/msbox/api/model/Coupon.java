
package com.msbox.api.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Coupon implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String coupon_id;// | varchar(20) | NO | PRI | | |

	private Integer active_id;// | int(11) | YES | | NULL | |

	private Integer merchant_id;// | int(11) | YES | | NULL | |

	private Integer status;// | tinyint(1) | YES | | NULL | |

	private Timestamp created_time;// | datetime | YES | | NULL | |

	private Timestamp publish_at;// | datetime | YES | | NULL |

	
	public String getCoupon_id()
	{
	
		return coupon_id;
	}

	
	public void setCoupon_id(String coupon_id)
	{
	
		this.coupon_id = coupon_id;
	}

	
	public Integer getActive_id()
	{
	
		return active_id;
	}

	
	public void setActive_id(Integer active_id)
	{
	
		this.active_id = active_id;
	}
	
	public Integer getMerchant_id()
	{
	
		return merchant_id;
	}
	
	public void setMerchant_id(Integer merchant_id)
	{
	
		this.merchant_id = merchant_id;
	}


	public Integer getStatus()
	{
	
		return status;
	}

	
	public void setStatus(Integer status)
	{
	
		this.status = status;
	}

	
	public Timestamp getCreated_time()
	{
	
		return created_time;
	}

	
	public void setCreated_time(Timestamp created_time)
	{
	
		this.created_time = created_time;
	}

	
	public Timestamp getPublish_at()
	{
	
		return publish_at;
	}

	
	public void setPublish_at(Timestamp publish_at)
	{
	
		this.publish_at = publish_at;
	}

	
}
