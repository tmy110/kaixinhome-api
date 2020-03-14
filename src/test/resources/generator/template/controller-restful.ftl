package ${basePackage}.web;

import ${basePackage}.utility.result.Result;
import ${basePackage}.utility.result.ResultGenerator;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${basePackage}.utility.IdWorker;
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
* Created by ${author} on ${date}.
*/
@Api(description = "${modelNameUpperCamel}操作接口")
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @Autowired
    private IdWorker idWorker;

    @ApiOperation(value="新增", notes="新增")
    @PostMapping
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}.setId(idWorker.nextId());
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除", notes="删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="修改", notes="修改")
    @PutMapping
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="根据ID查询", notes="根据ID查询")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }

    @ApiOperation(value="分页查询", notes="分页查询")
    @ApiImplicitParams({
    @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", required = false, dataType = "Integer"),
    @ApiImplicitParam(paramType = "query", name = "size", value = "每页显示数量", required = false, dataType = "Integer"),
    })
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(PageUtil.pageInfo(pageInfo));
    }
}
