package com.kaixin8848.home.web.favorites.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "Favorites", description = "收藏夹实体")
@Data
public class Favorites {
    /**
     * 编码
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "编号", hidden = true)
    private Long id;

    /**
     * 用户编码
     */
    @Column(name = "user_id")
    @ApiModelProperty(value = "用户编码")
    private Long userId;

    /**
     * 收藏夹名称
     */
    @Column(name = "favorites_name")
    @ApiModelProperty(value = "收藏夹名称")
    private String favoritesName;

    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    private String url;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;


}