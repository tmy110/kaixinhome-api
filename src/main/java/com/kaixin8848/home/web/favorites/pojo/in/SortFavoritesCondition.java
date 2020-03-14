package com.kaixin8848.home.web.favorites.pojo.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Description TODO
 * @Author tmy
 * @Date 2019/8/27 9:33
 **/
@ApiModel(value = "SortFavoritesCondition", description = "排序收藏夹条件实体")
@Data
public class SortFavoritesCondition {
    /**
     * 编码
     */
    private Long id;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
