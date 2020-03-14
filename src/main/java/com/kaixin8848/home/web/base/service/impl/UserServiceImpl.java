package com.kaixin8848.home.web.base.service.impl;

import com.kaixin8848.home.web.base.dao.UserMapper;
import com.kaixin8848.home.web.base.model.User;
import com.kaixin8848.home.core.AbstractService;
import com.kaixin8848.home.web.base.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by tmy on 2019/12/08.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
