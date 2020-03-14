package com.kaixin8848.home.web.favorites.pojo.in;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



/**
 * @Description TODO
 * @Author tmy
 * @Date 2019/8/27 9:33
 **/
@ApiModel(value = "FavoritesCondition",description = "查询收藏夹条件实体")
@Data
public class FavoritesCondition {
    /**
     * 用户编码
     */
    @ApiModelProperty(value = "用户编码")
    private Long userId;

}
