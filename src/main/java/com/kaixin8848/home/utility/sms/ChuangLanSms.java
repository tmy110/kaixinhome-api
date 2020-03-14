package com.kaixin8848.home.utility.sms;

import com.alibaba.fastjson.JSON;
import com.kaixin8848.home.utility.Log4J2.LogHelper;
import com.kaixin8848.home.utility.StringUtils;
import com.kaixin8848.home.utility.sms.request.SmsSendRequest;
import com.kaixin8848.home.utility.sms.response.SmsSendResponse;
import org.springframework.scheduling.annotation.Async;


import java.io.UnsupportedEncodingException;

/**
 * 创蓝短信发送
 */
public class ChuangLanSms {
    public static final String charset = "utf-8";
    // 请登录zz.253.com 获取创蓝API账号(非登录账号,示例:N1234567)
    public static String account = "";
//    public static String account = "N0470624";
    // 请登录zz.253.com 获取创蓝API密码(非登录密码)
//    public static String password = "A4SO9QPmEHae2b";
    public static String password = "";

    /**
     * 验证码发送
     *
     * @param mobile 手机号LogHelper
     * @param code   验证码
     * @return
     * @throws UnsupportedEncodingException
     */

    public static SmsSendResponse smsSendCode(String mobile, String code) {

        //短信发送的URL 请登录zz.253.com 获取完整的URL接口信息
        String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
        // 设置您要发送的内容：其中“【】”中括号为运营商签名符号，多签名内容前置添加提交
        String msg = String.format("【阳光食堂】您的验证确认码是：%s。请不要把验证确认码泄露给他人。", code);
        //状态报告
        String report = "true";
        try {
            SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, msg, mobile, report);
            String requestJson = JSON.toJSONString(smsSingleRequest);
//            System.out.println("before request string is: " + requestJson);
            String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
            if (StringUtils.isEmptyOrNull(response)) {
                LogHelper.info("创蓝短信发送失败","ChuangLanSms","SmsSend");
                return null;
            }
//            System.out.println("response after request result is :" + response);
            SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
//            System.out.println("response  toString is :" + smsSingleResponse);
            return smsSingleResponse;
        } catch (Exception ex) {
            LogHelper.error("创蓝短信发送失败",ex,"ChuangLanSms","SmsSend");
            return null;
        }
    }


    /**
     * 开通信息通知短信
     *
     * @param mobile  手机号
     * @param status  状态 成功 失败 >=2字
     * @param message 消息  进行{招标活动,下订单,机构管理等等等等}/重新提交材料/ >=10字
     * @return
     * @throws UnsupportedEncodingException
     */
    public static SmsSendResponse smsSendOpen(String mobile, String status, String message) {

        //短信发送的URL 请登录zz.253.com 获取完整的URL接口信息
        String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
        // 设置您要发送的内容：其中“【】”中括号为运营商签名符号，多签名内容前置添加提交
        String msg = String.format("【阳光食堂】开通%s,请登录阳光食堂采购平台%s。", status, message);
        //状态报告
        String report = "true";

        try {
            SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, msg, mobile, report);

            String requestJson = JSON.toJSONString(smsSingleRequest);

//            System.out.println("before request string is: " + requestJson);

            String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
            if (StringUtils.isEmptyOrNull(response)) {
//                LogHelper.info("创蓝短信发送失败","ChuangLanSms","SmsSend");
                System.out.println("创蓝短信发送失败");
                return null;
            }

//            System.out.println("response after request result is :" + response);

            SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

//            System.out.println("response  toString is :" + smsSingleResponse);

            return smsSingleResponse;
        } catch (Exception ex) {
            System.out.println("创蓝短信发送失败");
//            LogHelper.error("创蓝短信发送失败",ex,"ChuangLanSms","SmsSend");
            return null;
        }
    }
}
