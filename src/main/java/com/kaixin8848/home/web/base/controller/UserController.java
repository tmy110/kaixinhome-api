package com.kaixin8848.home.web.base.controller;

import com.kaixin8848.home.utility.IdWorker;
import com.kaixin8848.home.utility.PageUtil;
import com.kaixin8848.home.utility.PropertiesUtil;
import com.kaixin8848.home.utility.result.ParameterErrorResultUtil;
import com.kaixin8848.home.utility.result.Result;
import com.kaixin8848.home.utility.result.ResultGenerator;
import com.kaixin8848.home.web.base.model.User;
import com.kaixin8848.home.web.base.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by tmy on 2019/12/08.
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户操作接口")
public class UserController {

    @Autowired
    IdWorker idWorker;

    @Resource
    private UserService userService;

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping
    public Result add(@RequestBody User user) {
        if (user == null)
            return ResultGenerator.genParameterErrorResult("参数非法：数据为空");
        if (!ParameterErrorResultUtil.forMobile(user.getPhone()))
            return ResultGenerator.genParameterErrorResult("参数非法：手机号(phone)");
        if (!ParameterErrorResultUtil.forString(user.getPassword()))
            return ResultGenerator.genParameterErrorResult("参数非法：密码(password)");
        if (!ParameterErrorResultUtil.forString(user.getPassword()))
            return ResultGenerator.genParameterErrorResult("参数非法：姓名(name)");
        if (user.getGender() == null || (user.getGender() != 0 && user.getGender() != 1))
            return ResultGenerator.genParameterErrorResult("参数非法：性别 0:男 1:女(gender)");
        if (!ParameterErrorResultUtil.forString(user.getEmail()))
            return ResultGenerator.genParameterErrorResult("参数非法：邮箱(email)");
        if (!ParameterErrorResultUtil.forString(user.getAddress()))
            return ResultGenerator.genParameterErrorResult("参数非法：地址(address)");
        //判断是否已经有这个手机号了 如果有就不能进行注册

        Condition c = new Condition(User.class);
        User q = new User();
        q.setPhone(user.getPhone());
        c.and().andEqualTo(q);
        List<User> users = userService.findByCondition(c);
        if (users != null && users.size() > 0) {
            return ResultGenerator.genParameterErrorResult("该手机号已存在");
        }
        user.setId(idWorker.nextId());
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "更新用户", notes = "更新用户")
    @PutMapping
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @ApiOperation(value = "分页查询用户", notes = "分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页显示数量", required = false, dataType = "Integer"),
    })
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
//        LogHelper.error("testerror", "testerrorPoBidSegmentController", "testerrorupdatePoBidSegment");
//        LogHelper.info("testinfo", "testinfo库存接口", "testinfo出库或入库");
        return ResultGenerator.genSuccessResult(PageUtil.pageInfo(pageInfo));
    }
}
