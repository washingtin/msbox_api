package com.msbox.api.controller;

import com.msbox.api.common.util.ConfigCoreManager;
import com.msbox.api.common.util.HttpClienCommon;
import com.msbox.api.common.util.MD5Util;
import com.msbox.api.dao.active.ActiveMapper;
import com.msbox.api.dao.coupon.CouponMapper;
import com.msbox.api.enitiy.ResultBean;
import com.msbox.api.model.Coupon;
import com.msbox.api.model.CouponDeliver;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Description: 优惠券控制器
 * Author: guost
 * Date: 2015-04-01 11:12
 */
@Controller
public class MsCouponController {
    private static Logger logger = Logger.getLogger(MsCouponController.class);
    @Autowired
    private ActiveMapper activeMapper;
    @Autowired
    private CouponMapper couponMapper;

    @RequestMapping(value="sendCoupon.do",method = RequestMethod.POST)
    @ResponseBody
    public String sendCoupon(@Param(value="buId") String buId,@Param(value="mCode")String mCode,@Param(value="activeId") String activeId,@Param(value="userId") String userId,@Param(value="sign") String sign) {
        String json = "{\"buId\":\""+buId + "\"," + "\"mCode\":\"" +mCode + "\"," + "\"activeId\":\"" +activeId + "\"," + "\"userId\":\"" +userId +"\"" +"}";
        JSONObject jso = JSONObject.fromObject(json);

        String mobile = "";
        String c_buid = null;
        Integer numbers = 0;
        String coupon_id = null;

        if (StringUtils.isNotEmpty(buId) && StringUtils.isNotEmpty(mCode)) {
            //query coupon_id
            Map map = new HashMap<String,String>();
            map.put("bu_id",buId);
            map.put("active_id",activeId);
            coupon_id = couponMapper.getCouponIdByBuIdAndActiveId(map);
            // current time
            Timestamp now = new Timestamp(System.currentTimeMillis());
            System.out.println("timestamp======" + now.getTime()/1000);
            //获得用户手机
            if (StringUtils.isNotEmpty(userId)) {
                mobile = activeMapper.getMobileByUid(Integer.parseInt(userId));
            }
            //get cbuid
            c_buid = activeMapper.getCbuidByBuId(buId);
            //获得bu_key 用来加密验证
            String bu_key = activeMapper.getSignByBuId(buId);

            //MD5 generate
            MD5Util md5 = new MD5Util();
            String md5key = md5.getKeyedDigest(jso.toString(), bu_key, "md5", "utf-8");
            logger.info("==md5key==" + md5key);

            String newJson = "{\"cbuid\":\""+c_buid+"\",\"mcode\":\""+mCode+"\",\"activeid\":\""+activeId+"\",\"num\":\"1\",\"cardnos\":\"[]\",\"sign\":\""+md5key+"\"}";
            JSONObject newJO = JSONObject.fromObject(newJson);
            //String url = "http://114.112.94.49:8000/v1/";
            String url = ConfigCoreManager.getInstance("conf/Config.properties").getString("coupon_api_url");
            HttpClienCommon httpclient = new HttpClienCommon();
            String result = httpclient.doPostStream(newJO, HttpClienCommon.headMapUtf8, url + "getCouponNoList.do", 50000, 50000, "UTF-8");

            logger.info("返回结果=====" + result);
            JSONObject hpResult = JSONObject.fromObject(result);
            if (!hpResult.get("result").equals("")) {
                JSONObject jsonResult = hpResult.optJSONObject("result");
                numbers = jsonResult.optInt("coupon_no");
            }
            System.out.println(sign + "==!==" + md5key);
            if (sign.equals(md5key)) {
                CouponDeliver couponDeliver = new CouponDeliver();
                couponDeliver.setBu_id(Integer.parseInt(buId));
                couponDeliver.setCreation_time(now.getTime() / 1000);
                couponDeliver.setMobile(mobile);
                couponDeliver.setEmployee_id(0);
                couponDeliver.setUid(Integer.parseInt(userId));
                couponDeliver.setBalance(0.0);
                if (StringUtils.isNotEmpty(coupon_id)) {
                    couponDeliver.setCoupon_id(Integer.parseInt(coupon_id));
                }else {
                    couponDeliver.setCoupon_id(0);
                }
                couponDeliver.setNumber(numbers);
                if (numbers == 0) {
                    return "{\"code\":\"0\",\"message\":\"返回结果里的coupon_no为空！\",\"result\":{}}";
                }
                try {
                    couponMapper.saveCouponDeliver(couponDeliver);
                    couponMapper.updateBuCoupon(map);//
                }catch(Exception e) {
                    return "{\"code\":\"0\",\"message\":\"保存数据失败，此券号已被用！\",\"result\":{}}";
                }
                return "{\"code\":\"1\",\"message\":\"success\",\"result\":{}}";
            }else {
                return "{\"code\":\"0\",\"message\":\"验证失败！\",\"result\":{}}";
            }
        }else {
            return "{\"code\":\"0\",\"message\":\"参数有空值！\",\"result\":{}}";
        }
    }
}
