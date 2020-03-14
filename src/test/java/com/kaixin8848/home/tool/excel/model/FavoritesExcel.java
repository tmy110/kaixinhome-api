package com.kaixin8848.home.tool.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class FavoritesExcel extends BaseRowModel {

    @ExcelProperty(value = "用户编码", index = 0)
    private Long userId;

    @ExcelProperty(value = "收藏夹名称", index = 1)
    private String favoritesName;

    @ExcelProperty(value = "链接", index = 2)
    private String url;

    @ExcelProperty(value = "排序", index = 3)
    private Integer sort;


    @ExcelProperty(value = "图片", index = 4)
    private String picture;

}
