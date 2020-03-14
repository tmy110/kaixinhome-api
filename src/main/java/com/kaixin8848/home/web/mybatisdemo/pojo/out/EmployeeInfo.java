package com.kaixin8848.home.web.mybatisdemo.pojo.out;

import com.kaixin8848.home.web.mybatisdemo.model.Dept;
import com.kaixin8848.home.web.mybatisdemo.model.Employee;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "EmployeeInfo", description = "员工信息实体")
@Data
public class EmployeeInfo extends Employee {

    /**
     * 部门信息
     */
    @ApiModelProperty(value = "部门信息")
    private Dept dept;
}
