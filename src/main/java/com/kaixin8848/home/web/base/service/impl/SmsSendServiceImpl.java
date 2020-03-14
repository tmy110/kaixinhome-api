package com.kaixin8848.home.web.base.service.impl;


import com.kaixin8848.home.utility.sms.ChuangLanSms;
import com.kaixin8848.home.web.base.service.SmsSendService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SmsSendServiceImpl implements SmsSendService {
    /**
     * 异步发送验证码
     *
     * @param mobile
     * @param verifyCode
     */
    @Override
    public void sendCode(String mobile, String verifyCode) {
        //        ChuangLanSms.smsSendCode(mobile, verifyCode);
        System.out.println("发送验证码中");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发送验证码成功");
    }
}
