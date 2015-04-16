package com.msbox.api.controller;

import com.msbox.api.common.constants.ActiveConstant;
import com.msbox.api.common.util.ConfigCoreManager;
import com.msbox.api.common.util.MD5Util;
import com.msbox.api.dao.active.ActiveMapper;
import com.msbox.api.dao.user.UserMapper;
import com.msbox.api.enitiy.ResultBean;
import com.msbox.api.model.Active;
import com.msbox.api.model.ActiveJson;
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

import java.util.ArrayList;
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
     *  @param sign
     * @return
     */
    @RequestMapping(value = "getActiveIdList.do",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean getActiveIdList(@Param(value="buId") String buId,@Param(value="mCode")String mCode,@Param(value="sign") String sign) {
        JSONObject json = new JSONObject();
        json.element("buId",buId);
        json.element("mCode",mCode);

        ResultBean success = new ResultBean();//返回结果 成功json
        success.setCode(1);
        success.setMessage("success");
        ResultBean failure = new ResultBean();//返回结果 失败json
        failure.setCode(0);
        failure.setResult("[]");
        Map<String,String> map2 = null;

        if (StringUtils.isNotEmpty(buId) && StringUtils.isNotEmpty(mCode)) {

            String bu_key = activeMapper.getSignByBuId(buId);
            String md5 = MD5Util.getInstance().getKeyedDigest(json.toString(), bu_key, "md5", "utf-8");
            logger.info(bu_key + "========" + sign + "=========" + md5);
            if (sign.equals(md5)) {
                map2 = ActiveConstant.buIdMap.get(buId);
                //未加载文件前，这个map是空的
                if(map2 != null) {
                    String str = map2.get(mCode);
                    logger.info("==active.properties===" + str);
                    List<ActiveJson> list = new ArrayList<ActiveJson>();
                    if (StringUtils.isNotEmpty(str)) {
                        if (str.contains("@")) {
                            String[] arrays = str.split("@");
                            for (int i=0; i<arrays.length; i++) {
                                logger.info("截取后字符串：" + arrays[i]);
                                JSONObject j1 = JSONObject.fromObject(arrays[i]);
                                ActiveJson active = new ActiveJson();
                                active.setActive(j1.optString("active"));
                                active.setTitle(j1.optString("title"));
                                active.setMoney(j1.optDouble("money"));
                                list.add(active);
                            }
                        }else {
                            JSONObject j1 = JSONObject.fromObject(str);
                            ActiveJson active = new ActiveJson();
                            active.setActive(j1.optString("active"));
                            active.setTitle(j1.optString("title"));
                            active.setMoney(j1.optDouble("money"));
                            list.add(active);
                        }
                    }


                    success.setResult(list);
                }else {
                    success.setResult("[]");
                }
                logger.info("Success getActiveIdList::::" + success.toString());
                return success;
            }else {
                failure.setResult("sign传值有误！");
                return failure;
            }

        }else{
            failure.setResult("参数有空值！");
            logger.info("Error getActiveIdList::::" + failure.toString());
            return failure;

        }
    }
}
