package com.kaixin8848.home.web.favorites.controller;

import com.kaixin8848.home.utility.IdWorker;
import com.kaixin8848.home.utility.Log4J2.LogHelper;
import com.kaixin8848.home.utility.PageUtil;
import com.kaixin8848.home.utility.excel.ExcelExportUtil;
import com.kaixin8848.home.utility.result.ParameterErrorResultUtil;
import com.kaixin8848.home.utility.result.Result;
import com.kaixin8848.home.utility.result.ResultGenerator;
import com.kaixin8848.home.web.favorites.model.Favorites;
import com.kaixin8848.home.web.favorites.pojo.in.FavoritesCondition;
import com.kaixin8848.home.web.favorites.pojo.in.SortFavoritesCondition;
import com.kaixin8848.home.web.favorites.service.FavoritesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by tmy on 2019/12/08.
 * edit by 田满意119 2.0
 */
@RestController
@RequestMapping("/favorites")
@Api(description = "收藏夹操作接口")
public class FavoritesController {

    @Resource
    private FavoritesService favoritesService;

    @Autowired
    private IdWorker idWorker;

    @ApiOperation(value = "新增收藏夹", notes = "新增收藏夹")
    @PostMapping
    public Result add(@RequestBody Favorites favorites) {
        if (!ParameterErrorResultUtil.forLong(favorites.getUserId()))
            return ResultGenerator.genParameterErrorResult("参数非法：用户编码(user_id)");
        if (!ParameterErrorResultUtil.forString(favorites.getFavoritesName()))
            return ResultGenerator.genParameterErrorResult("参数非法：收藏夹名称(favorites_name)");
        if (!ParameterErrorResultUtil.forString(favorites.getUrl()))
            return ResultGenerator.genParameterErrorResult("参数非法：链接(url)");
        if (!ParameterErrorResultUtil.forString(favorites.getPicture()))
            return ResultGenerator.genParameterErrorResult("参数非法：图片(picture)");
        favorites.setId(idWorker.nextId());
        favorites.setSort(0);//排序
        favorites.setCreateTime(new Date());//创建时间
        favoritesService.save(favorites);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "删除收藏夹", notes = "删除收藏夹")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        favoritesService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改收藏夹", notes = "修改收藏夹")
    @PutMapping
    public Result update(@RequestBody Favorites favorites) {
        if (!ParameterErrorResultUtil.forLong(favorites.getId()))
            return ResultGenerator.genParameterErrorResult("参数非法：编码(id)");
        if (!ParameterErrorResultUtil.forLong(favorites.getUserId()))
            return ResultGenerator.genParameterErrorResult("参数非法：用户编码(user_id)");
        if (!ParameterErrorResultUtil.forString(favorites.getFavoritesName()))
            return ResultGenerator.genParameterErrorResult("参数非法：收藏夹名称(favorites_name)");
        if (!ParameterErrorResultUtil.forString(favorites.getUrl()))
            return ResultGenerator.genParameterErrorResult("参数非法：链接(url)");
        if (!ParameterErrorResultUtil.forString(favorites.getPicture()))
            return ResultGenerator.genParameterErrorResult("参数非法：图片(picture)");
        favoritesService.update(favorites);
        return ResultGenerator.genSuccessResult();
    }

//    @ApiOperation(value = "根据ID查询收藏夹", notes = "根据ID查询收藏夹")
//    @GetMapping("/{id}")
//    public Result detail(@PathVariable Long id) {
//        Favorites favorites = favoritesService.findById(id);
//        return ResultGenerator.genSuccessResult(favorites);
//    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "size", value = "每页显示数量", required = false, dataType = "Integer"),
    })
    @GetMapping
    public Result list(FavoritesCondition favoritesCondition, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        if (!ParameterErrorResultUtil.forLong(favoritesCondition.getUserId()))
            return ResultGenerator.genParameterErrorResult("参数非法：用户编码(user_id)");
        PageHelper.startPage(page, size);
        List<Favorites> list = favoritesService.findFavorites(favoritesCondition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(PageUtil.pageInfo(pageInfo));
    }

    @ApiOperation(value = "导出搜藏夹列表", notes = "导出搜藏夹列表")
    @PostMapping("/export-favorites")
    public void exportFavorites(HttpServletResponse response) {
        List<Favorites> list = favoritesService.findAll();
        if (list.size() > 0) {
            String[] headers = {"编码", "用户编码", "收藏夹名称", "链接", "排序", "图片"};
            List<List<String>> dataList = new ArrayList<>();
            for (Favorites favorites : list) {
                dataList.add(Arrays.asList(favorites.getId() + "",
                        favorites.getUserId() + "",
                        favorites.getFavoritesName(),
                        favorites.getUrl(),
                        favorites.getSort() + "",
                        favorites.getPicture()));
            }
            ExcelExportUtil.excelExport(response, headers, dataList, "搜藏夹列表");
        }
    }

    @ApiOperation(value = "排序收藏夹", notes = "排序收藏夹")
    @PostMapping("sort-favorites")
    public Result sortFavorites(@RequestBody List<SortFavoritesCondition> sortFavoritesConditions) {
        try {
            if (sortFavoritesConditions == null)
                return ResultGenerator.genParameterErrorResult("参数非法：集合为null");
            for (SortFavoritesCondition sortFavoritesCondition : sortFavoritesConditions) {
                if (!ParameterErrorResultUtil.forLong(sortFavoritesCondition.getId()))
                    return ResultGenerator.genParameterErrorResult("参数非法：编码(id)");
            }
            return favoritesService.sortFavorites(sortFavoritesConditions);
        } catch (Exception e) {
            LogHelper.error("排序收藏夹", e, "FavoritesController", "sortFavorites");
            return ResultGenerator.getErrorResult();
        }

    }

}
