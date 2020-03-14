package com.kaixin8848.home.web.base.service.impl;

import com.kaixin8848.home.utility.CommonUtils;
import com.kaixin8848.home.utility.constant.Code;
import com.kaixin8848.home.utility.redis.RedisCode;
import com.kaixin8848.home.utility.result.Result;
import com.kaixin8848.home.utility.result.ResultCode;
import com.kaixin8848.home.utility.result.ResultGenerator;
import com.kaixin8848.home.utility.sms.ChuangLanSms;
import com.kaixin8848.home.web.base.dao.UserMapper;
import com.kaixin8848.home.web.base.model.User;
import com.kaixin8848.home.web.base.pojo.in.UpdatePasswordParam;
import com.kaixin8848.home.web.base.pojo.out.UserInfo;
import com.kaixin8848.home.web.base.service.LoginService;
import com.kaixin8848.home.web.base.service.SmsSendService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {


    @Autowired
    private RedisTemplate redisTemplate;
    //短信有效期（秒）
    private final static int SMS_TIME_OUT = 10 * 60;
    //发送短信间隔时间（秒）
    private final static int RETRANSMISSION_INTERVAL_TIME = 60;

    @Value("${spring.profiles.active}")
    private String active;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SmsSendService smsSendService;

    /**
     * 发送验证码
     *
     * @param mobile 手机号
     * @return
     */
    @Override
    public Result sendVerificationCode(String mobile) {

        if (redisTemplate.opsForValue().get(RedisCode.MOBILE_VERIFICATION_CODE.key(mobile)) != null) {
            //获取短信验证码剩余过期时间
            Long expireTime = redisTemplate.getExpire(RedisCode.MOBILE_VERIFICATION_CODE.key(mobile), TimeUnit.SECONDS);
            if (SMS_TIME_OUT - RETRANSMISSION_INTERVAL_TIME <= expireTime) {
                return ResultGenerator.genResult(ResultCode.SMS_REPEAT_SENDING.code(), ResultCode.SMS_REPEAT_SENDING.msg());
            }
        }
        String verifyCode = CommonUtils.randomNumberStr(6);//生成6位短信验证码
        if (!"dev".equals(active) && !"test".equals(active)) {
            smsSendService.sendCode(mobile, verifyCode);
        }
        redisTemplate.opsForValue().set(RedisCode.MOBILE_VERIFICATION_CODE.key(mobile), verifyCode, SMS_TIME_OUT, TimeUnit.SECONDS);
        if ("dev".equals(active) || "test".equals(active)) {
            return ResultGenerator.genSuccessResult(verifyCode);
        } else {
            return ResultGenerator.genSuccessResult();
        }
    }

    /**
     * 更新密码
     *
     * @param updatePasswordParam
     * @return
     */
    @Override
    public Result updatePassword(UpdatePasswordParam updatePasswordParam) {
        if (!updatePasswordParam.getVerificationCode().equals(redisTemplate.opsForValue().get(RedisCode.MOBILE_VERIFICATION_CODE.key(updatePasswordParam.getPhone())))) {//传递过来的验证码和缓存不符
            return ResultGenerator.genResult(ResultCode.SMS_VERIFICATION_CODE_ERROR.code(), ResultCode.SMS_VERIFICATION_CODE_ERROR.msg());
        }
        redisTemplate.delete(RedisCode.MOBILE_VERIFICATION_CODE.key(updatePasswordParam.getPhone()));//验证后清除缓存
        Condition c = new Condition(User.class);
        User user = new User();
        user.setPhone(updatePasswordParam.getPhone());
        c.and().andEqualTo(user);
        List<User> users = userMapper.selectByCondition(c);
        if (users != null) {
            User user1 = users.get(0);
            user1.setPassword(updatePasswordParam.getPassword());
            userMapper.updateByPrimaryKeySelective(user1);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 手机号和密码登录
     *
     * @param phone
     * @param password
     * @param request
     * @return
     */
    @Override
    public Result loginByPhoneAndPassword(String phone, String password, HttpServletRequest request) {
        //设置session过期时间
        HttpSession session = request.getSession();//建一个session
        session.setMaxInactiveInterval(Code.SESSION_TIMEOUT); //秒
        Condition condition = new Condition(User.class);
        User q = new User();
        q.setPhone(phone);
        q.setPassword(password);
        condition.and().andEqualTo(q);
        UserInfo userInfo = new UserInfo();
        List<User> users = userMapper.selectByCondition(condition);
        if (users != null && users.size() > 0) {
            userInfo.setToken(session.getId());//设置session
            userInfo.setUser(users.get(0));
            return ResultGenerator.genSuccessResult(userInfo);
        } else {
            return ResultGenerator.genFailResult("账号和密码有误");
        }


    }
}
