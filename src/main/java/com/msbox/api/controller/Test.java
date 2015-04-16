package com.msbox.api.controller;

import com.msbox.api.common.util.HttpClienCommon;
import com.msbox.api.common.util.MD5Util;
import com.sun.org.apache.xpath.internal.SourceTree;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by IntelliJ IDEA.
 * Description: Test
 * Author: guost
 * Update: ${MyName}(2015-04-01 13:43)
 */
public class Test {
    public static void main(String[] args) {
//http://114.112.94.42:8088/msbox_api/sendCoupon.do?buId=9&mCode=KGC001&activeId=4664&userId=1&sign=053fc1ba7467ae836964457602b07889&channel=2
//http://114.112.94.42:8088/msbox_api/sendCoupon.do?buId=9&mCode=KGC002&activeId=4664&userId=1&sign=dbad93393ee670bbb9d4221a1a35378f&channel=2




//  cbuid=93&mcode=YS0001&activeid=137&num=1&cardnos=&channel=2&sign=kNH8NX4MtrNMX8PZ
        String req = "{\"cbuid\":\"73\",\"mcode\":\"KGC008\",\"activeid\":\"4984\",\"channel\":\"2\",\"num\":\"1\",\"cardnos\":\"[]\"}";
        String key = "CMQhahYdX8nKTnXF";
        String sign = MD5Util.getInstance().getKeyedDigest(req,key,"MD5","UTF-8");
        System.out.println("---" + sign);
        String res = "{\"cbuid\":\"73\",\"mcode\":\"KGC001\",\"activeid\":\"4984\",\"channel\":\"2\",\"num\":\"1\",\"cardnos\":\"[]\",\"sign\":\"" + sign + "\"}";

//        String url = "http://localhost:8080/v1/";
        String url = "http://114.112.94.49:8000/v1/";
        JSONObject reqJson = JSONObject.fromObject(res);

        HttpClienCommon httpclient = new HttpClienCommon();
        String result = httpclient.doPostStream(reqJson, HttpClienCommon.headMapUtf8, url+"getCouponNoList.do", 50000, 50000, "UTF-8");
        System.out.println("-----" + result);
        JSONObject hpResult = JSONObject.fromObject(result);
        if (!hpResult.get("result").equals("")) {
            System.out.println("optResult ===" + hpResult.optString("result"));
            JSONArray jsonResult = hpResult.optJSONArray("result");
            JSONObject aa = jsonResult.optJSONObject(0);
            int numbers = aa.optInt("coupon_no");
            String starttime = aa.optString("starttime");
            String endtime = aa.optString("endtime");
            System.out.println(numbers + "-==-" + starttime);
        }
//        String json = "{\"bu_id\":90,\"bu_code\":90,\"a_id\":\"\",\"u_id\":\"\",\"sign\":\"\"}";
//        JSONObject jso = JSONObject.fromObject(json);
//        jso.remove("sign");
//        jso.put("bu_id","100");
//        System.out.println(jso.toString() + "====" + jso.get("bu_code"));
//        MD5Util md5 = new MD5Util();
//        System.out.println("md5===" + md5.md5(jso.toString()));

//        String res = "{\"cbuid\":43,\"activeid\":4846,\"num\":200,\"sign\":\"56fa065d33b99f483a5365ce2f4120ef\"} ";

    }
}
