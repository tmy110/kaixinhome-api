package com.kaixin8848.home.web.mybatisdemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "Employee", description = "员工实体")
@Data
public class Employee {
    /**
     * 编码
     */
    @ApiModelProperty(value = "编号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Column(name = "last_name")
    private String lastName;

    /**
     * 性别 0:女生 1:男生
     */
    @ApiModelProperty(value = "性别 0:女生 1:男生")
    private String gender;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 部门编号
     */
    @ApiModelProperty(value = "部门编号")
    @Column(name = "d_id")
    private Long dId;
}