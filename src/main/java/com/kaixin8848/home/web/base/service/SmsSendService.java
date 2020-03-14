package com.kaixin8848.home.web.base.service;

import org.springframework.scheduling.annotation.Async;

public interface SmsSendService {
    /**
     * 异步发送验证码
     * @param mobile
     * @param verifyCode
     */
    @Async// 异步方法
    void sendCode(String mobile, String verifyCode);
}
