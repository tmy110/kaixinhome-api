package com.kaixin8848.home.web.base.service;

import com.kaixin8848.home.utility.result.Result;
import com.kaixin8848.home.web.base.pojo.in.UpdatePasswordParam;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    /**
     * 发送验证码
     *
     * @param mobile 手机号
     * @return
     */
    Result sendVerificationCode(String mobile);

    /**
     * 更新密码
     *
     * @param updatePasswordParam
     * @return
     */
    Result updatePassword(UpdatePasswordParam updatePasswordParam);

    /**
     * 手机号和密码登录
     * @param phone
     * @param password
     * @param request
     * @return
     */
    Result loginByPhoneAndPassword(String phone, String password, HttpServletRequest request);
}
