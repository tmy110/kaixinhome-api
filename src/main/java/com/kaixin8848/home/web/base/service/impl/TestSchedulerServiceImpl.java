package com.kaixin8848.home.web.base.service.impl;


import com.google.gson.Gson;
import com.kaixin8848.home.scheduler.TestScheduler;
import com.kaixin8848.home.utility.HttpClientUtils;
import com.kaixin8848.home.web.base.service.TestSchedulerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class TestSchedulerServiceImpl implements TestSchedulerService {

    @Override
    public void testScheduler() {
//        String path = TestScheduler.TEST_GET_URL;
//        HttpClientUtils httpClientUtils = HttpClientUtils.getInstance();
//        String result = httpClientUtils.sendHttpGet(path);
//        System.out.println("测试GET定时任务:" + result);

//        Map<String, String> maps =new HashMap<>();
//
//        String path = TestScheduler.TEST_POST_URL;
//        HttpClientUtils httpClientUtils = HttpClientUtils.getInstance();
//        String result = httpClientUtils.sendHttpPost(path,maps);
//        System.out.println("测试POST定时任务:" + result);

        String jsonStr="{\n" +
                "\"name\":\"小黑\",\n" +
                "\"grade\":\"天蝎座\",\n" +
                "\"phone\":18361036456,\n" +
                "\"sex\":\"男\",\n" +
                "\"age\":28,\n" +
                "\"addr\":\"河南省济源市北海大道32号\"\n" +
                "}";

        String path = TestScheduler.TEST_POST_URL;

        HttpClientUtils httpClientUtils = HttpClientUtils.getInstance();
        String result = httpClientUtils.sendHttpPostWithJson(path,jsonStr);
        System.out.println("测试POST定时任务:" + result);



    }
}
