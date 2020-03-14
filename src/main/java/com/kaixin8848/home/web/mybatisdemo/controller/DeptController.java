package com.kaixin8848.home.web.mybatisdemo.controller;

import com.kaixin8848.home.utility.result.Result;
import com.kaixin8848.home.utility.result.ResultGenerator;
import com.kaixin8848.home.web.mybatisdemo.model.Dept;
import com.kaixin8848.home.web.mybatisdemo.pojo.out.DeptInfo;
import com.kaixin8848.home.web.mybatisdemo.service.DeptService;
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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
* Created by tmy on 2020/02/19.
*/
@Api(description = "mybatisdemo-部门操作接口")
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @Autowired
    private IdWorker idWorker;

    @ApiOperation(value="新增", notes="新增")
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        dept.setId(idWorker.nextId());
        deptService.save(dept);
        return ResultGenerator.genSuccessResult();
    }

//    @ApiOperation(value="删除", notes="删除")
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id) {
//        deptService.deleteById(id);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @ApiOperation(value="修改", notes="修改")
//    @PutMapping
//    public Result update(@RequestBody Dept dept) {
//        deptService.update(dept);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @ApiOperation(value="根据ID查询", notes="根据ID查询")
//    @GetMapping("/{id}")
//    public Result detail(@PathVariable Long id) {
//        Dept dept = deptService.findById(id);
//        return ResultGenerator.genSuccessResult(dept);
//    }
//
//    @ApiOperation(value="分页查询", notes="分页查询")
//    @ApiImplicitParams({
//    @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", required = false, dataType = "Integer"),
//    @ApiImplicitParam(paramType = "query", name = "size", value = "每页显示数量", required = false, dataType = "Integer"),
//    })
//    @GetMapping
//    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<Dept> list = deptService.findAll();
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(PageUtil.pageInfo(pageInfo));
//    }
//

    @ApiOperation(value="select_resultMap_关联查询_collection定义关联集合封装", notes="select_resultMap_关联查询_collection定义关联集合封装")
    @GetMapping("/getDeptByIdPlus/{deptId}")
    public Result getDeptByIdPlus(@PathVariable Long deptId) {
        DeptInfo deptInfo=deptService.getDeptByIdPlus(deptId);
        return ResultGenerator.genSuccessResult(deptInfo);
    }

    @ApiOperation(value="select_resultMap_关联查询_collection分步查询", notes="select_resultMap_关联查询_collection分步查询")
    @GetMapping("/getDeptByIdStep/{deptId}")
    public Result getDeptByIdStep(@PathVariable Long deptId) {
        DeptInfo deptInfo=deptService.getDeptByIdStep(deptId);
        return ResultGenerator.genSuccessResult(deptInfo);
    }





}
