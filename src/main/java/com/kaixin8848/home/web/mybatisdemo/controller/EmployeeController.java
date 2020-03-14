package com.kaixin8848.home.web.mybatisdemo.controller;

import com.kaixin8848.home.utility.result.Result;
import com.kaixin8848.home.utility.result.ResultGenerator;

import com.kaixin8848.home.web.mybatisdemo.model.Employee;
import com.kaixin8848.home.web.mybatisdemo.pojo.out.EmployeeInfo;
import com.kaixin8848.home.web.mybatisdemo.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaixin8848.home.utility.IdWorker;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import com.kaixin8848.home.utility.PageUtil;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tmy on 2020/02/19.
 */
@Api(description = "mybatisdemo-员工操作接口")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Autowired
    private IdWorker idWorker;

    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping
    public Result add(@RequestBody Employee employee) {
        employee.setId(idWorker.nextId());
        employeeService.save(employee);
        return ResultGenerator.genSuccessResult();
    }

//    @ApiOperation(value = "删除", notes = "删除")
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id) {
//        employeeService.deleteById(id);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @ApiOperation(value = "修改", notes = "修改")
//    @PutMapping
//    public Result update(@RequestBody Employee employee) {
//        employeeService.update(employee);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @ApiOperation(value = "根据ID查询", notes = "根据ID查询")
//    @GetMapping("/{id}")
//    public Result detail(@PathVariable Long id) {
//        Employee employee = employeeService.findById(id);
//        return ResultGenerator.genSuccessResult(employee);
//    }
//
//    @ApiOperation(value = "分页查询", notes = "分页查询")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", required = false, dataType = "Integer"),
//            @ApiImplicitParam(paramType = "query", name = "size", value = "每页显示数量", required = false, dataType = "Integer"),
//    })
//    @GetMapping
//    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<Employee> list = employeeService.findAll();
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(PageUtil.pageInfo(pageInfo));
//    }

    @ApiOperation(value = "select_resultMap_关联查询_级联属性封装结果", notes = "select_resultMap_关联查询_级联属性封装结果")
    @GetMapping("/getEmpAndDept/{empId}")
    public Result getEmpAndDept(@PathVariable Long empId) {
        List<EmployeeInfo> employeeInfos = employeeService.getEmpAndDept(empId);
        return ResultGenerator.genSuccessResult(employeeInfos);
    }

    @ApiOperation(value = "select_resultMap_关联查询_association定义关联对象封装", notes = "select_resultMap_关联查询_association定义关联对象封装")
    @GetMapping("/getEmpAndDept2/{empId}")
    public Result getEmpAndDept2(@PathVariable Long empId) {
        List<EmployeeInfo> employeeInfos = employeeService.getEmpAndDept2(empId);
        return ResultGenerator.genSuccessResult(employeeInfos);
    }

    @ApiOperation(value = "select_resultMap_关联查询_association分步查询", notes = "select_resultMap_关联查询_association分步查询")
    @GetMapping("/getEmpAndDept3/{empId}")
    public Result getEmpAndDept3(@PathVariable Long empId) {
        EmployeeInfo employeeInfo = employeeService.getEmpAndDept3(empId);
        return ResultGenerator.genSuccessResult(employeeInfo);
    }

    @ApiOperation(value = "select_resultMap_discriminator鉴别器", notes = "select_resultMap_discriminator鉴别器")
    @GetMapping("/getEmpAndDept4/{empId}")
    public Result getEmpAndDept4(@PathVariable Long empId) {
        EmployeeInfo employeeInfo = employeeService.getEmpAndDept4(empId);
        return ResultGenerator.genSuccessResult(employeeInfo);
    }

    @ApiOperation(value = "动态sql_trim_自定义字符串截取", notes = "动态sql_trim_自定义字符串截取")
    @GetMapping("/dynamicsql/getEmpsByConditionTrim")
    public Result getEmpsByConditionTrim(Employee employee) {
        List<Employee> employees = employeeService.getEmpsByConditionTrim(employee);
        return ResultGenerator.genSuccessResult(employees);
    }

    @ApiOperation(value = "动态sql_choose_分支选择", notes = "动态sql_choose_分支选择")
    @GetMapping("/dynamicsql/getEmpsByConditionChoose")
    public Result getEmpsByConditionChoose(Employee employee) {
        List<Employee> employees = employeeService.getEmpsByConditionChoose(employee);
        return ResultGenerator.genSuccessResult(employees);
    }

    @ApiOperation(value = "动态sql_set_与if结合的动态更新", notes = "动态sql_set_与if结合的动态更新")
    @GetMapping("/dynamicsql/updateEmpUseSet")
    public Result updateEmpUseSet(Employee employee) {
        employeeService.updateEmpUseSet(employee);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "动态sql_foreach_遍历集合", notes = "动态sql_foreach_遍历集合")
    @PostMapping("/dynamicsql/getEmpsByConditionForEach")
    public Result getEmpsByConditionForEach(@RequestBody List<String> ids) {
        List<Employee> employees = employeeService.getEmpsByConditionForEach(ids);
        return ResultGenerator.genSuccessResult(employees);
    }

    @ApiOperation(value = "动态sql_foreach_mysql下foreach批量插入的两种方式+动态sql_sql_抽取可重用的sql片段", notes = "动态sql_foreach_mysql下foreach批量插入的两种方式+动态sql_sql_抽取可重用的sql片段")
    @PostMapping("/dynamicsql/addEmps")
    public Result addEmps(@RequestBody List<Employee> employees) {
        for (Employee emp : employees) {
            emp.setId(idWorker.nextId());
        }
        boolean b = employeeService.addEmps(employees);
        System.out.println(b);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "动态sql_bind_绑定", notes = "动态sql_bind_绑定")
    @GetMapping("/dynamicsql/getEmpsByConditionForBind")
    public Result getEmpsByConditionForBind(Employee employee) {
        List<Employee> employees = employeeService.getEmpsByConditionForBind(employee);
        return ResultGenerator.genSuccessResult(employees);
    }


}
