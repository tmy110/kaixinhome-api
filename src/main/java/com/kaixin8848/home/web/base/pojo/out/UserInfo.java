package com.kaixin8848.home.web.base.pojo.out;

import com.kaixin8848.home.web.base.model.User;
import lombok.Data;

@Data
public class UserInfo {


    /**
     * 登录签名
     */
    private String token;
    /**
     * 用户信息
     */
    private User user;

}
