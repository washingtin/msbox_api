package com.msbox.api.task;

import com.msbox.api.common.constants.ActiveConstant;
import com.msbox.api.dao.active.ActiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Description: 定时任务类，每5分钟执行一次
 * Author: guost
 * Date: 2015-04-01 11:12
 */
@Component("msTaskJob")
public class MsTaskJob {

    @Scheduled(cron = "0 */1 * * * ?")
    public void job1() {
        ActiveMapUtil amUtil = new ActiveMapUtil();
        amUtil.init();
        System.out.println("任务进行中。。。" + ActiveConstant.buIdMap.get("9"));
    }
}
