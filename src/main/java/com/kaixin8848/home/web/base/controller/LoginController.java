package com.kaixin8848.home.web.base.controller;


import com.kaixin8848.home.utility.Log4J2.LogHelper;
import com.kaixin8848.home.utility.result.ParameterErrorResultUtil;
import com.kaixin8848.home.utility.result.Result;
import com.kaixin8848.home.utility.result.ResultGenerator;
import com.kaixin8848.home.web.base.model.User;
import com.kaixin8848.home.web.base.pojo.in.UpdatePasswordParam;
import com.kaixin8848.home.web.base.service.LoginService;
import com.kaixin8848.home.web.base.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/login")
@Api(description = "登录操作接口")
public class LoginController {
    @Resource
    private UserService userService;
    @Autowired
    private LoginService loginService;

    @GetMapping("/login-by-phone-and-password")
    @ApiOperation(value = "手机号和密码登录", notes = "手机号和密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "操作失败！！！"),
            @ApiResponse(code = 205, message = "参数非法！！！"),
    })
    public Result loginByMobile(@RequestParam String phone,
                                @RequestParam String password,
                                @ApiIgnore HttpServletRequest request) {
        try {
            return loginService.loginByPhoneAndPassword(phone, password, request);
        } catch (Exception e) {
            LogHelper.error("系统异常-账号登录", e, "LoginController", "loginByMobile");
        }
        return ResultGenerator.getErrorResult();
    }

    @PostMapping("/send-verification-code")
    @ApiOperation(value = "发送手机号验证码", notes = "发送手机号验证码统一接口，验证码有效期10分钟，发送一分钟后可重发")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "mobile", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "1注册 2找回密码", required = false, dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "操作失败！！！"),
            @ApiResponse(code = 20004, message = "重复发送失败，请在发送60秒后重试！！！"),
    })
    public Result sendVerificationCode(@RequestParam(required = true) String mobile, @RequestParam(required = false, defaultValue = "0") Integer type) {
        try {
            if (!ParameterErrorResultUtil.forMobile(mobile))
                return ResultGenerator.genParameterErrorResult("参数非法：手机号(mobile)");
            if (type != 1 && type != 2)
                return ResultGenerator.genParameterErrorResult("参数非法：1注册 2找回密码(type)");
            if (type == 2) {
                Condition c = new Condition(User.class);
                User user = new User();
                user.setPhone(mobile);
                c.and().andEqualTo(user);
                List<User> users = userService.findByCondition(c);
                if (users == null || users.size() == 0) {
                    return ResultGenerator.genParameterErrorResult("该手机号不存在");
                }
            }
            return loginService.sendVerificationCode(mobile);
        } catch (Exception e) {
            LogHelper.error("系统异常-发送手机号验证码", e, "LoginController", "sendVerificationCode");
        }
        return ResultGenerator.getErrorResult();
    }

    @ApiOperation(value = "更新密码", notes = "更新密码")
    @PutMapping("/update-password")
    public Result updatePassword(@RequestBody UpdatePasswordParam updatePasswordParam) {
        try {
            if (!ParameterErrorResultUtil.forMobile(updatePasswordParam.getPhone()))
                return ResultGenerator.genParameterErrorResult("参数非法：手机号(mobile)");
            if (!ParameterErrorResultUtil.forString(updatePasswordParam.getVerificationCode()))
                return ResultGenerator.genParameterErrorResult("参数非法：验证码(verificationCode)");
            if (!ParameterErrorResultUtil.forString(updatePasswordParam.getPassword()))
                return ResultGenerator.genParameterErrorResult("参数非法：新密码(password)");
            Condition c = new Condition(User.class);
            User user = new User();
            user.setPhone(updatePasswordParam.getPhone());
            c.and().andEqualTo(user);
            List<User> users = userService.findByCondition(c);
            if (users == null || users.size() == 0) {
                return ResultGenerator.genParameterErrorResult("该手机号不存在");
            }
            return loginService.updatePassword(updatePasswordParam);
        } catch (Exception e) {
            System.out.println("系统异常");
            LogHelper.error("系统异常", e, "LoginController", "sendVerificationCode");
            return ResultGenerator.getErrorResult();
        }

    }


}
