
package com.msbox.api.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Active implements Serializable
{

	private static final long serialVersionUID = 1L;

	private Integer id;// | int(11) unsigned | NO | PRI | NULL | auto_increment

	private Timestamp start_time;// datetime | YES | | NULL | |

	private Timestamp end_time;// | datetime | YES | | NULL | |

	private String title;// | varchar(255) | YES | | | |

	private String pic;// | varchar(255) | YES | | | |

	private Integer coupon_num;// | int(11) | YES | | NULL | |

	private Integer category;// | tinyint(11) | YES | | NULL | |

	private String content;// | varchar(255) | YES | | NULL | |

	private Integer status;//

	private Integer used_num;//

	private Integer merchant_id;//

	private Integer publish_price;//

	private Integer check_price;//

	private Integer publish_rule_id;//

	private Integer create_user_id;//

	private Timestamp create_time;//

	private Integer customer_type_id;//
	
	private Integer channel;//
	
	private String check_channel;

	public Integer getId()
	{

		return id;
	}

	public void setId(Integer id)
	{

		this.id = id;
	}

	public Timestamp getStart_time()
	{

		return start_time;
	}

	public void setStart_time(Timestamp start_time)
	{

		this.start_time = start_time;
	}

	public Timestamp getEnd_time()
	{

		return end_time;
	}

	public void setEnd_time(Timestamp end_time)
	{

		this.end_time = end_time;
	}

	public String getTitle()
	{

		return title;
	}

	public void setTitle(String title)
	{

		this.title = title;
	}

	public String getPic()
	{

		return pic;
	}

	public void setPic(String pic)
	{

		this.pic = pic;
	}

	public Integer getCoupon_num()
	{

		return coupon_num;
	}

	public void setCoupon_num(Integer coupon_num)
	{

		this.coupon_num = coupon_num;
	}

	public Integer getCategory()
	{

		return category;
	}

	public void setCategory(Integer category)
	{

		this.category = category;
	}

	public String getContent()
	{

		return content;
	}

	public void setContent(String content)
	{

		this.content = content;
	}

	public Integer getStatus()
	{

		return status;
	}

	public void setStatus(Integer status)
	{

		this.status = status;
	}

	public Integer getUsed_num()
	{

		return used_num;
	}

	public void setUsed_num(Integer used_num)
	{

		this.used_num = used_num;
	}

	public Integer getMerchant_id()
	{

		return merchant_id;
	}

	public void setMerchant_id(Integer merchant_id)
	{

		this.merchant_id = merchant_id;
	}

	public Integer getPublish_price()
	{

		return publish_price;
	}

	public void setPublish_price(Integer publish_price)
	{

		this.publish_price = publish_price;
	}

	public Integer getCheck_price()
	{

		return check_price;
	}

	public void setCheck_price(Integer check_price)
	{

		this.check_price = check_price;
	}

	public Integer getPublish_rule_id()
	{

		return publish_rule_id;
	}

	public void setPublish_rule_id(Integer publish_rule_id)
	{

		this.publish_rule_id = publish_rule_id;
	}

	public Integer getCreate_user_id()
	{

		return create_user_id;
	}

	public void setCreate_user_id(Integer create_user_id)
	{

		this.create_user_id = create_user_id;
	}

	public Timestamp getCreate_time()
	{

		return create_time;
	}

	public void setCreate_time(Timestamp create_time)
	{

		this.create_time = create_time;
	}

	public Integer getCustomer_type_id()
	{

		return customer_type_id;
	}

	public void setCustomer_type_id(Integer customer_type_id)
	{

		this.customer_type_id = customer_type_id;
	}

	
	public Integer getChannel()
	{
	
		return channel;
	}

	
	public void setChannel(Integer channel)
	{
	
		this.channel = channel;
	}

	public String getCheck_channel() {
		return check_channel;
	}

	public void setCheck_channel(String check_channel) {
		this.check_channel = check_channel;
	}

}
