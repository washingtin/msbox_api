package com.msbox.api.controller;

import com.msbox.api.common.util.ConfigCoreManager;
import com.msbox.api.common.util.HttpClienCommon;
import com.msbox.api.common.util.MD5Util;
import com.msbox.api.dao.active.ActiveMapper;
import com.msbox.api.dao.coupon.CouponMapper;
import com.msbox.api.enitiy.ResultBean;
import com.msbox.api.model.Coupon;
import com.msbox.api.model.CouponDeliver;
import net.sf.json.JSONArray;
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
    private CouponMapper couponMapper;
    @Autowired
    private ActiveMapper activeMapper;

    @RequestMapping(value="sendCoupon.do",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean sendCoupon(@Param(value="buId") String buId,@Param(value="mCode")String mCode,@Param(value="activeId") String activeId,@Param(value="userId") String userId,@Param(value="sign") String sign) {
        ResultBean success = new ResultBean();//返回结果 成功json
        success.setCode(1);
        success.setMessage("success");
        ResultBean failure = new ResultBean();//返回结果 失败json
        failure.setCode(0);
        failure.setResult("[]");

        //请求参数转成jsonObject
        JSONObject jso1 = new JSONObject();
        jso1.element("buId", buId);
        jso1.element("mCode",mCode);
        jso1.element("activeId",activeId);
        jso1.element("userId",userId);

        String title = "";
        String mobile = "";  //手机号
        String c_buid = null; //
        Integer numbers = 0; //优惠券号
        String starttime = ""; //开始时间
        String endtime = ""; //结束时间

        if (StringUtils.isNotEmpty(buId) && StringUtils.isNotEmpty(mCode)) {
            // current time
            Timestamp now = new Timestamp(System.currentTimeMillis());
            logger.info("timestamp======" + now.getTime() / 1000);
            //获得bu_key 用来加密验证
            String bu_key = activeMapper.getSignByBuId(buId);
            //这个用来和请求者匹配 md5key1
            String md5key1 = MD5Util.getInstance().getKeyedDigest(jso1.toString(), bu_key, "MD5", "UTF-8");
            logger.info("==bu_key==" + bu_key);
            //获得用户手机
            if (StringUtils.isNotEmpty(userId)) {
                mobile = activeMapper.getMobileByUid(Integer.parseInt(userId));
            }
            //get cbuid
            c_buid = activeMapper.getCbuidByBuId(buId);

            Map actveMap = new HashMap<String,Object>();
            actveMap.put("buid",Integer.parseInt(buId));
            actveMap.put("activeid", Integer.parseInt(activeId));
            String otherActiveId = couponMapper.getActiveIdByBuid(actveMap);
            logger.info("query activeid== + " + otherActiveId);
            //MD5 generate
            JSONObject jso = new JSONObject();
            jso.element("cbuid",c_buid);
            jso.element("mcode",mCode);
            jso.element("activeid",otherActiveId);
            jso.element("num","1");
            jso.element("channel","2");
            jso.element("cardnos", "[]");

            String md5key = MD5Util.getInstance().getKeyedDigest(jso.toString(), bu_key, "MD5", "UTF-8");
            logger.info(c_buid + "==Http md5key==" + md5key);

            jso.element("sign",md5key);
            ConfigCoreManager ccm = ConfigCoreManager.getInstance("conf/Config.properties");
            String url = ccm.getString("coupon_api_url");
            HttpClienCommon httpclient = new HttpClienCommon();
            String result = httpclient.doPostStream(jso, HttpClienCommon.headMapUtf8, url + "getCouponNoList.do", 50000, 50000, "UTF-8");


            String mCbuid = ccm.getString("c_buid");
            String mBukey = ccm.getString("bu_key");
            JSONObject modifyNum = new JSONObject();
            modifyNum.element("cbuid",mCbuid);
            modifyNum.element("activeid",otherActiveId);
            modifyNum.element("num", "1");
            String modifyMD5 = MD5Util.getInstance().getKeyedDigest(modifyNum.toString(), mBukey, "MD5", "UTF-8");
            modifyNum.element("sign",modifyMD5);
            String activeBean = httpclient.doPostStream(modifyNum, HttpClienCommon.headMapUtf8, url + "modifyActiveNum.do", 50000, 50000, "UTF-8");

            JSONObject hpResult = JSONObject.fromObject(result);
            if (!hpResult.get("result").equals("")) {
                JSONArray jsonArray = hpResult.optJSONArray("result");
                JSONObject jsonResult = jsonArray.optJSONObject(0);
                title = jsonResult.optString("title");
                numbers = jsonResult.optInt("coupon_no");
                starttime = jsonResult.optString("starttime");
                endtime = jsonResult.optString("endtime");
            }else {
                if (numbers == 0) {
                    failure.setMessage(hpResult.optString("message"));
                    return failure;
                }
            }
            logger.info("请求sign=" + sign + "是否等于=" + md5key1);
            if (sign.equals(md5key1)) {
                CouponDeliver couponDeliver = new CouponDeliver();
                couponDeliver.setBu_id(Integer.parseInt(buId));
                couponDeliver.setCreation_time(now.getTime() / 1000);
                couponDeliver.setMobile(mobile);
                couponDeliver.setEmployee_id(0);
                couponDeliver.setUid(Integer.parseInt(userId));
                couponDeliver.setBalance(0.0);
                couponDeliver.setCoupon_id(Integer.parseInt(activeId));
                couponDeliver.setNumber(numbers);
                try {
                    Map map = new HashMap<String,Integer>();
                    map.put("bu_id", Integer.parseInt(buId));
                    map.put("active_id", Integer.parseInt(activeId));
                    couponMapper.saveCouponDeliver(couponDeliver);
                    couponMapper.updateBuCoupon(map);//
                    couponMapper.updateBuCouponTotal(map);
                    /**
                     * 测试是否更新里面的数量+1
                     */
//                    Map mapTest = new HashMap<String,Integer>();
//                    mapTest.put("bu_id", Integer.parseInt(buId));
//                    mapTest.put("active_id", Integer.parseInt(activeId));
//                    System.out.println("前：" + couponMapper.getTotalByBuIdAndActiveId(mapTest));
                }catch(Exception e) {
                    e.printStackTrace();
                    failure.setMessage(hpResult.optString(e.getMessage()));
                    return failure;
                }
                logger.info("http请求返回结果=====" + result);
                logger.info("二次调modifyActiveNum:==" + activeBean);
                JSONObject jsonSub = new JSONObject();
                jsonSub.element("coupon_no",numbers);
                jsonSub.element("starttime",starttime);
                jsonSub.element("endtime", endtime);
                jsonSub.element("title", title);

                success.setResult(jsonSub);
                return success;
            }else {
                failure.setMessage("验证失败！");
                return failure;
            }
        }else {
            failure.setMessage("参数有空值！");
            return failure;
        }
    }
}
