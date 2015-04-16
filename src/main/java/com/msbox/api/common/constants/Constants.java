
package com.msbox.api.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kyj
 * @TODO
 * @2013-9-12 上午09:50:36
 * 
 */
public class Constants
{

	/**
	 * result error code
	 */
	public static int RESULT_CODE_ERROR = 0;

	/**
	 * 成功
	 */
	public static int RESULT_CODE_SUCCESS = 1;

	/**
	 * 部分成功
	 */
	public static int RESULT_CODE_SUCCESS_SOME = 2;

	/**
	 * 30001:参数不足
	 */
	public static int RESULT_CODE_ERROR_30001 = 30001;

	/**
	 * 10002:没有查询到数据
	 */
	public static int RESULT_CODE_ERROR_10002 = 10002;

	/**
	 * 10003:参数校验不同通过
	 */
	public static int RESULT_CODE_ERROR_10003 = 10003;

	/**
	 * 10004:活动等未启动
	 */
	public static int RESULT_CODE_ERROR_10004 = 10004;

	/**
	 * 10005:小票信息不符合条件
	 */
	public static int RESULT_CODE_ERROR_10005 = 10005;

	/**
	 * 10006:活动等已经结束
	 */
	public static int RESULT_CODE_ERROR_10006 = 10006;

	/**
	 * 10007:活动等时间不明确
	 */
	public static int RESULT_CODE_ERROR_10007 = 10007;

	/**
	 * 10008:券池等数量不足
	 */
	public static int RESULT_CODE_ERROR_10008 = 10008;

	/**
	 * 10009:数据不存在
	 */
	public static int RESULT_CODE_ERROR_10009 = 10009;

	/**
	 * 10010:无效状态
	 */
	public static int RESULT_CODE_ERROR_10010 = 10010;

	/**
	 * 10011:券验证失败
	 */
	public static int RESULT_CODE_ERROR_10011 = 10011;

	/**
	 * 10012:状态已使用
	 */
	public static int RESULT_CODE_ERROR_10012 = 10012;
	
	/**
	 * 10013:活动渠道不匹配
	 */
	public static int RESULT_CODE_ERROR_10013 = 10013;
	
	/**
	 * 10014:验券方式不允许
	 */
	public static int RESULT_CODE_ERROR_10014 = 10014;
	
	
	/**
	 * 30013:安全认证失败
	 */
	public static int RESULT_CODE_ERROR_30013 = 30013;

	/**
	 * 20001:系统异常
	 */
	public static int RESULT_CODE_ERROR_20001 = 20001;

	/**
	 * 4已发放
	 */
	public static int Active_status_4 = 4;

	/**
	 * 3正在发放
	 */
	public static int Active_status_3 = 3;

	/**
	 * 2已确认
	 */
	public static int Active_status_2 = 2;

	/**
	 * 1待确认
	 */
	public static int Active_status_1 = 1;

	/**
	 * -1已过期
	 */
	public static int Active_status_0 = 0;

	/**
	 * 0 已作废
	 */
	public static int Active_status_0_1 = -1;
	
	/**0:拒绝*/
	public static int publis_rule_me_0 = 0;
	
	 /**1：接受*/
	public static int publis_rule_me_1 = 1;

	public static Map<Object, String> ActiveStatusMap = new HashMap<Object, String>();

	static
	{
		ActiveStatusMap.put(Active_status_0, "作废");
		ActiveStatusMap.put(Active_status_1, "待确认");
		ActiveStatusMap.put(Active_status_2, "已确认");
		ActiveStatusMap.put(Active_status_3, "正在发放");
		ActiveStatusMap.put(Active_status_4, "已发放");
		ActiveStatusMap.put(Active_status_0_1, "已过期");
	}

	/**
	 * 0 作废
	 */
	public static int Coupon_status_0 = 0;

	/**
	 * 1 待发放
	 */
	public static int Coupon_status_1 = 1;

	/**
	 * 2已发放
	 */
	public static int Coupon_status_2 = 2;

	/**
	 * -1 已校验
	 */
	public static int Coupon_status_0_1 = -1;

	/**
	 * －2 已过期
	 */
	public static int Coupon_status_0_2 = -2;
	
	/**
	 * 资金优惠
	 */
	public static int active_type_1 = 1;

	/**
	 * 柜台优惠
	 */
	public static int active_type_2 = 2;
	/**
	 * 3 折扣
	 */
	public static int active_type_3 = 3;
	/**
	 * ,4 赠品,
	 */
	public static int active_type_4 = 4;

	
	/**
	 * 每次获取优惠券的最大数量
	 */
	public static int coupon_num_max = 1000;
	
	/**
	 * 每次增加优惠券的最大数量
	 */
	public static int coupon_num_max_modify = 500;

	
	public static Map<Object, String> CouponStatusMap = new HashMap<Object, String>();

	static
	{
		CouponStatusMap.put(Coupon_status_0, "作废");
		CouponStatusMap.put(Coupon_status_1, "待发放");
		CouponStatusMap.put(Coupon_status_2, "已发放");
		CouponStatusMap.put(Coupon_status_0_1, "已校验");
		CouponStatusMap.put(Coupon_status_0_2, "已过期");
	}
	
	public static Map<String,String> JsonMap;
	public static String ticketinfo = "{cardno:'erere',ticket_no:'1233',pos_no:'12321e',shop_id:'222'," +
	"consume_time:'2012-12-25 08:12:09',total:'23.00'," +
	"productlist:" +
	"[" +
	"{" +
	"consume_id:'1222'," +
	"barcode:'6434563345'," +
	"goods_code:'343'," +
	"goods_name:'水果'," +
	"number:'1'," +
	"price:'23.44'," +
	"total:'23.44'" +
	"}" +
	"]" +
	"}";
	
	public static String rule = "{condition:[{barcode:'111',number:1,totalprice:'100.19'}],coupon_back:'14.22',checktype:1 }";
	public static String jbuids = "[{jbuid:1,mcodes:['ST4923','ST4453']}]";
	
	
	static
	{
		JsonMap = new HashMap<String, String>();
		
		JsonMap.put("getActiveList", "{cbuid:1,mcode:'ST4453'}");
		JsonMap.put("getActiveDetail", "{cbuid:1,activeid:2,mcode:'ST4453'}");
		JsonMap.put("getOneCouponNo", "{cbuid:1,activeid:2,cardno:'111',mcode:'ST4453',channel:2}");
		JsonMap.put("getCouponNoList", "{cbuid:1,activeid:3,num:11,cardnos:['111','222','444'],mcode:'ST4453',channel:2}");
		JsonMap.put("findNoByMemberCard", "{cbuid:1,cardno:'111',mcode:'ST4453'}");
		JsonMap.put("getCouponNoListByTicketInfo", "{cbuid:1,cardno:'333',mcode:'ST4453',ticketInfo:"+ticketinfo+"}");
		JsonMap.put("checkNoByCoponNo", "{cbuid:1,coupon_no:'4787391330000',mcode:'ST4453',ticketInfo:"+ticketinfo+"}");
		JsonMap.put("checkNoByTicketInfo", "{cbuid:1,cardno:'111',mcode:'ST4453',ticketInfo:"+ticketinfo+"}");
		JsonMap.put("revocationNoByToken", "{tokenid:'1233_2_2_1000',coupon_no:'4787391330000'}");
		JsonMap.put("findConpouNoByTicketNo", "{ticket_no:'1233',coupon_no:'4787391330000',cbuid:1,mcode:'ST4453'}");
		JsonMap.put("createActive", "{cbuid:3,  starttime:'2014-01-04 12:09:08', endtime:'2014-05-04 12:09:08'," +
				"jbuids:"+jbuids+",num: 3, title:'一个标题', content: '活动说明',  rule:"+rule+",type:1,channel:2}");
		JsonMap.put("updatePublish", "{cbuid:3,jbuids:"+jbuids+",  activeid:2}");
		JsonMap.put("findRevocationNoByAid", "{cbuid:1,starttime:'2014-01-04 12:09:08', endtime:'2014-05-04 12:09:08',activeid:1}");
		JsonMap.put("confirmActive", "{cbuid:3,num:10,activeid:2}");
		JsonMap.put("modifyActiveNum", "{cbuid:1,num:10,activeid:2}");
	}
	
	/**
	 * 1 活动+发放规则+验证规则(relation):key:active_活动 id,value:活动数据
	 */
	public static String coupon_active_ = "coupon_active_";
	
	/**
	 * 2 优惠券:key:coupon_优惠券号,value:优惠券数据
	 */
	public static String coupon_coupon_ = "coupon_coupon_";
	
	/**
	 * 3 门店活动列表:key:activelist_门店 id,value:活动 id 列表(set)
	 */
	public static String coupon_activelist_ = "coupon_activelist_";
	
	/**
	 * 4 待发放优惠券列表:key:send_coupon_活动 id,value:优惠券号列表(list)
	 */
	public static String coupon_send_coupon_ = "coupon_send_coupon_";
	
	/**
	 * 5 待验证优惠券列表:key:check_coupon_门店 id,value:优惠券号列表(set)
	 */
	public static String coupon_check_coupon_ = "coupon_check_coupon_";
	
	/**
	 * 6 会员卡优惠券:key:member_coupon_门店 id,value:卡号->优惠券号(map)
	 */
	public static String coupon_member_coupon_ = "coupon_member_coupon_";
	
	/**
	 * 7 待消优惠券:key:delete_coupon_生成的 token,value:门店 id_优惠券号
	 */
	public static String coupon_delete_coupon_ = "coupon_delete_coupon_";
	
	/**
	 * 8 验券基本规则:key:check_rule_base:验券规则引擎库(js)
	 */
	public static String coupon_check_rule_base_ = "coupon_check_rule_base_";
	
	/**
	 * 集团私钥:key:merchant_集团 id,value:private key

	 */
	public static String coupon_merchant_ = "coupon_merchant_";

	/**
	 * 10 集团下参加活动的门店:key:coupon_merchant_active_集团 id_活动 id,value:门店 code 列表(set)
	 */
	public static String coupon_merchant_active_ = "coupon_merchant_store_";
	
	/**
	 * 11 集团下参加活动的门店:key:coupon_merchant_store_集团 id_store_code,value:merchant_id(自曾id)
	 */
	public static String coupon_merchant_store_ = "coupon_merchant_store_";
	
	/**
	 * 门店的集团key数据
	 */
	public static Map<String,String> merchant_keyMap = new HashMap<String, String>();
	
	
	/**
	 * 返回的活动数据
	 */
	public static Map<String,String> active_list_map = new HashMap<String, String>();
	
	/**
	 * 返回的活动详情,需添加一些字段
	 */
	public static Map<String,String> active_detail_map = new HashMap<String, String>();
	
	/**
	 * 2：时间转换模板
	 */
	public static String date_change_str = "{type:2,format:'yyyy-MM-dd HH:mm:ss'}";

	/**
	 * 1：字段重命名模板
	 */
	public static String name_rename = "{type:1,rename:''}";
	
	/**
	 * 3：既要转换又要重命名
	 */
	public static String change_name_rename = "{type:3,rename:'',format:'yyyy-MM-dd HH:mm:ss'}";
	
	/**
	 * 4：嵌套取值 模板 多层嵌套使用 . 来分割  如 a.b.c，最终存的key是c
	 */
	public static String nested_get_value = "{type:4,key:''}";
	
	public static Map<String,String> query_coupon_byCardno = new HashMap<String, String>();
	
	public static Map<String,String> query_coupon_byCardno_coupon = new HashMap<String, String>();
	
	static 
	{
		active_list_map.put("start_time", "{type:3,rename:'starttime',format:'yyyy-MM-dd HH:mm:ss'}");
		active_list_map.put("end_time", "{type:3,rename:'endtime',format:'yyyy-MM-dd HH:mm:ss'}");
		active_list_map.put("title", null);
		active_list_map.put("pic", null);
		active_list_map.put("coupon_num", null);
		active_list_map.put("content", null);
		active_list_map.put("used_num", null);
		active_list_map.put("id", "{type:1,rename:'activeid'}");
		
		//活动详情
		active_detail_map.put("start_time", "{type:3,rename:'starttime',format:'yyyy-MM-dd HH:mm:ss'}");
		active_detail_map.put("end_time", "{type:3,rename:'end_time',format:'yyyy-MM-dd HH:mm:ss'}");
		active_detail_map.put("title", null);
		active_detail_map.put("pic", null);
		active_detail_map.put("coupon_num", null);
		active_detail_map.put("content", null);
		active_detail_map.put("used_num", null);
		active_detail_map.put("id", "{type:1,rename:'activeid'}");
		active_detail_map.put("check_rule_relation", "{type:4,keys:[{key:'check_rule_ralation.check_rule_id'}]}");
		
		 //卡号 优惠券 活动信息
		 query_coupon_byCardno.put("start_time", "{type:3,rename:'starttime',format:'yyyy-MM-dd HH:mm:ss'}");
		 query_coupon_byCardno.put("end_time", "{type:3,rename:'endtime',format:'yyyy-MM-dd HH:mm:ss'}");
		 query_coupon_byCardno.put("id", "{type:1,rename:'active_id'}");
		 query_coupon_byCardno.put("content", null);
		 query_coupon_byCardno.put("title", null);
		 query_coupon_byCardno.put("pic", null);
			
		 //卡号 优惠券信息
		 query_coupon_byCardno_coupon.put("coupon_id", "{type:1,rename:'coupon_no'}");
			
		 
	}
	  
	/**
	 * 非返回数据，只是中间处理的数据
	 */
	public static Map<String,String> query_Valid_active_by_buid = new HashMap<String, String>();
	public static Map<String,String> query_coupon_by_activeid = new HashMap<String, String>();
	public static Map<String,String> query_coupon_by_buid = new HashMap<String, String>();
	public static Map<String,String> query_coupon_check = new HashMap<String, String>();
	public static Map<String,String> query_coupon_by_buid_cardno = new HashMap<String, String>();
	public static Map<String,String> query_coupon = new HashMap<String, String>();
	
	static
	{
		query_Valid_active_by_buid.put("start_time", "{type:1,rename:'starttime'}");
		query_Valid_active_by_buid.put("end_time", "{type:1,rename:'endtime'}");
		query_Valid_active_by_buid.put("coupon_num", null);
		query_Valid_active_by_buid.put("status", null);
		query_Valid_active_by_buid.put("used_num", null);
		query_Valid_active_by_buid.put("id", "{type:1,rename:'active_id'}");
		query_Valid_active_by_buid.put("publish_rule", "{type:4,keys:[{key:'publish_rule.barcode'}]}");
		query_Valid_active_by_buid.put("channel", null);

		//获取活动的优惠券号信息
		
		query_coupon_by_activeid.put("start_time", "{type:3,rename:'starttime',format:'yyyy-MM-dd HH:mm:ss'}");
		query_coupon_by_activeid.put("end_time", "{type:3,rename:'endtime',format:'yyyy-MM-dd HH:mm:ss'}");
		query_coupon_by_activeid.put("id", "{type:1,rename:'activeid'}");
		query_coupon_by_activeid.put("title", null);
		query_coupon_by_activeid.put("content", null);
	
		//获取券的信息
		query_coupon_by_buid.put("coupon_id", null);
		query_coupon_by_buid.put("active_id", null);
		query_coupon_by_buid.put("status", "{type:1,rename:'coustatus'}");
		
		  
		 //获取验券的规则数据
		 query_coupon_check.put("check_rule_relation", "{type:4,keys:[{key:'check_rule_ralation.check_rule_id'},{key:'check_rule_ralation.rule'}]}");
		 query_coupon_check.put("title", null);
		 query_coupon_check.put("content", null);
		 query_coupon_check.put("category", null);
		 
		 //crr.rule,crb.rule_text,crb.id as ruleid,crb.type
		 //取得活动的一些字段
		 query_coupon_by_buid_cardno.put("start_time",  null);
		 query_coupon_by_buid_cardno.put("end_time",  null);	
		 query_coupon_by_buid_cardno.put("status", "{type:1,rename:'actstatus'}");	
		 
		 //销券coupon的数据
		 query_coupon.put("coupon_id", null);
		 query_coupon.put("active_id", null);
		 query_coupon.put("status", null);
		
	}

	public static Map<Integer,String> coupon_status_map = new HashMap<Integer, String>();
	
	static
	{
		coupon_status_map.put(Coupon_status_0, "券号已作废");
		coupon_status_map.put(Coupon_status_1, "券号待发放");
		coupon_status_map.put(Coupon_status_2, "券号已发放");
		coupon_status_map.put(Coupon_status_0_1, "券号已使用");
		coupon_status_map.put(Coupon_status_0_2, "券号已过期");
	}
	
	public static Map<Integer,String> coupon_status_back_map = new HashMap<Integer, String>();
	
	/**
	 * 0:成功,
	 */
	public static String coupon_back_status_0 = "0";
	/**
	 * 10:券作废
	 */
	public static String coupon_back_status_10 = "10";
	/**
	 * 11:券未发放
	 */
	public static String coupon_back_status_11 = "11";
	/**
	 * 12 :券已使用
	 */
	public static String coupon_back_status_12 = "12";
	/**
	 * 13:券已过期
	 */
	public static String coupon_back_status_13 = "13";
	/**
	 * 20:活动为开始
	 */
	public static String coupon_back_status_20 = "20";
	/**
	 * 21:活动已经结束
	 */
	public static String coupon_back_status_21 = "21";
	/**
	 * 30:小票信息不匹配
	 */
	public static String coupon_back_status_30 = "30";
	/**
	 * 40:门店未参加活动
	 */
	public static String coupon_back_status_40 = "40";
	

	static
	{
		coupon_status_back_map.put(Coupon_status_0, coupon_back_status_10);
		coupon_status_back_map.put(Coupon_status_1, coupon_back_status_11);
		coupon_status_back_map.put(Coupon_status_0_1, coupon_back_status_12);
		coupon_status_back_map.put(Coupon_status_0_2, coupon_back_status_13);
	}
	
	/**
	 * 活动渠道1
	 */
	public static Integer active_channel_1 = 1;
	/**
	 * 活动渠道2
	 */
	public static Integer active_channel_2 = 2;
	
	/**
	 * 发券放入redis的队列
	 */
	public static String send_coupon_list_key = "send_coupon_list_key";

	/**
	 * 发券放入redis的队列执行固定次数错误 放入
	 */
	public static String send_coupon_list_fail_key = "send_coupon_list_fail_key";
}
