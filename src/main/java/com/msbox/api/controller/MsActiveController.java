package com.msbox.api.controller;

import com.msbox.api.common.constants.ActiveConstant;
import com.msbox.api.common.util.ConfigCoreManager;
import com.msbox.api.common.util.MD5Util;
import com.msbox.api.dao.active.ActiveMapper;
import com.msbox.api.dao.user.UserMapper;
import com.msbox.api.model.Active;
import com.msbox.api.task.ActiveMapUtil;
import com.sun.org.apache.xpath.internal.SourceTree;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Description: 活动控制器
 * Author: guost
 * Date: 2015-04-01 11:12
 */
@Controller
public class MsActiveController {
    private static Logger logger = Logger.getLogger(MsActiveController.class);
    @Autowired
    private ActiveMapper activeMapper;

    /**
     *  @param buId
     *  @param mCode
     *  测试数据{'bu_id':'9','bu_code':'m01','sign':'0b5433b6711bfa8a5fd8d045ec3b103b'}
     * @return
     */
    @RequestMapping(value = "getActiveIdList.do",method = RequestMethod.GET)
    @ResponseBody
    public String getActiveIdList(@Param(value="buId") String buId,@Param(value="mCode")String mCode,@Param(value="sign") String sign) {
        //http://localhost:8080/msbox_api/getActiveIdList.do?buId=9&mCode=m01&sign=ee42b2deaba0a417b2185765d24374df
        //json = "{'bu_id':'9','bu_code':'m01','sign':'0b5433b6711bfa8a5fd8d045ec3b103b'}";
        String json = "{\"buId\":\""+buId+"\"," + "\"mCode\":\"" +mCode + "\"}";
        String result = null;
        Map<String,String> map2 = null;

        if (StringUtils.isNotEmpty(buId) && StringUtils.isNotEmpty(mCode)) {
            result = "{\"code\":\"1\",\"message\":\"success\",\"result\":{";

            String bu_key = activeMapper.getSignByBuId(buId);
            String md5 = new MD5Util().getKeyedDigest(json, bu_key, "md5", "utf-8");
            System.out.println(bu_key + "========" + sign + "=========" + md5);
            if (sign.equals(md5)) {
                map2 = ActiveConstant.buIdMap.get(buId);
                //未加载文件前，这个map是空的
                if(map2 != null) {
                    result = result + "\"activeIds\":\""+ map2.get(mCode)+"\"";
                }else {
                    result = result + "\"activeIds\":\""+"\"";
                }
            }
            result = result + "}}";
            logger.info("Success getActiveIdList::::"+ result);
        }else{
            result = "{\"code\":\"0\",\"message\":,\"参数有空值！\",\"result\":{}}";
            logger.info("Error getActiveIdList::::"+ result);
        }

        return result;
    }
}
