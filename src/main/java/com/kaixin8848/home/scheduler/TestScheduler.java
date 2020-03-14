package com.kaixin8848.home.scheduler;

import com.kaixin8848.home.utility.CommonUtils;
import com.kaixin8848.home.utility.constant.Code;
import com.kaixin8848.home.web.base.service.TestSchedulerService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestScheduler {

    @Autowired
    TestSchedulerService testSchedulerService;

    //测试GET地址
    public final static String TEST_GET_URL = "http://api.nnzhp.cn/api/user/stu_info?stu_name=%E5%B0%8F%E9%BB%91";

    //测试POST地址
    public final static String TEST_POST_URL = "http://api.nnzhp.cn/api/user/add_stu";




    /**
     * second(秒), minute（分）, hour（时）, day of month（日）, month（月）, day of week（周几）.
     * 0 * * * * MON-FRI
     * 【0 0/5 14,18 * * ?】 每天14点整，和18点整，每隔5分钟执行一次
     * 【0 15 10 ? * 1-6】 每个月的周一至周六10:15分执行一次
     * 【0 0 2 ? * 6L】每个月的最后一个周六凌晨2点执行一次
     * 【0 0 2 LW * ?】每个月的最后一个工作日凌晨2点执行一次
     * 【0 0 2-4 ? * 1#1】每个月的第一个周一凌晨2点到4点期间，每个整点都执行一次；
     */
    // @Scheduled(cron = "0 05 03 ? * *")每天3：05执行
    // @Scheduled(cron = "0 * * * * MON-SAT")
    // @Scheduled(cron = "0,1,2,3,4 * * * * MON-SAT")
    // @Scheduled(cron = "0-4 * * * * MON-SAT")
    // @Scheduled(cron = "0/4 * * * * MON-SAT")  //每4秒执行一次
    //@Scheduled(cron = "0 05 03 ? * *")//每天3：05执行
    @Scheduled(cron = "0 50 17 ? * *")
    public void testTasks() {
        System.out.println("定时任务执行开始：" + CommonUtils.dateCastToString(new Date(), Code.YYYY_MM_DD_HH_MM_SS));
        testSchedulerService.testScheduler();
        System.out.println("定时任务执行结束：" + CommonUtils.dateCastToString(new Date(), Code.YYYY_MM_DD_HH_MM_SS));
    }
}
