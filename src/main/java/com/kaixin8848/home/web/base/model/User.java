package com.kaixin8848.home.web.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@ApiModel(value = "User", description = "用户实体")
@Data
@Validated//JSR303数据校验
public class User {
    /**
     * 编码
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "编号")
    private Long id;

    /**
     * 手机号
     */

    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 性别  0:男 1:女
     */
    @ApiModelProperty(value = "性别 0:男 1:女")
    private Integer gender;
    /**
     * 邮箱
     */
    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;


}