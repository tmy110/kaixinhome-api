package com.kaixin8848.home.tool.excel;

import com.alibaba.excel.EasyExcelFactory;

import com.kaixin8848.home.tool.excel.model.FavoritesExcel;
import com.kaixin8848.home.utility.IdWorker;
import com.kaixin8848.home.web.favorites.model.Favorites;
import com.kaixin8848.home.web.favorites.service.FavoritesService;
import io.swagger.annotations.ApiModelProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportFavoritesByExcel {

    @Autowired
    private IdWorker idWorker;

    @Resource
    private FavoritesService favoritesService;

    @Test
    public void importExecel() throws FileNotFoundException {
        File file = new File("D:\\work\\ytWork\\kaixinhome-api\\src\\test\\java\\com\\kaixin8848\\home\\tool\\excel\\data\\收藏夹列表.xlsx");
        InputStream io = new FileInputStream(file);
        //第二种使用InstallerExcelBean有表头获取方式，无表头为Sheet(0,1)
        com.alibaba.excel.metadata.Sheet sheet = new com.alibaba.excel.metadata.Sheet(1, 1, FavoritesExcel.class);
        List<Object> data = EasyExcelFactory.read(io, sheet);
        for (Object o : data) {
            if (o instanceof FavoritesExcel) {
                FavoritesExcel importEntity = (FavoritesExcel) o;

                Long id = idWorker.nextId();
                Favorites favorites = new Favorites();
                //编码
                favorites.setId(id);
                //用户编码
                favorites.setUserId(importEntity.getUserId());
                //收藏夹名称
                favorites.setFavoritesName(importEntity.getFavoritesName());
                //链接
                favorites.setUrl(importEntity.getUrl());
                //排序
                favorites.setSort(importEntity.getSort());
                //图片
                favorites.setPicture(importEntity.getPicture());

                favoritesService.save(favorites);
            }
        }
        System.out.println("导入成功");

    }
}
