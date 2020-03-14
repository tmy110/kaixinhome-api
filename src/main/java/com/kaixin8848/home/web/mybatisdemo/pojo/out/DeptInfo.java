package com.kaixin8848.home.web.mybatisdemo.pojo.out;

import com.kaixin8848.home.web.mybatisdemo.model.Dept;
import com.kaixin8848.home.web.mybatisdemo.model.Employee;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "DeptInfo", description = "部门信息实体")
@Data
public class DeptInfo extends Dept {

    /**
     * 员工信息集合
     */
    @ApiModelProperty(value = "员工信息集合")
    List<Employee> employees;
}
