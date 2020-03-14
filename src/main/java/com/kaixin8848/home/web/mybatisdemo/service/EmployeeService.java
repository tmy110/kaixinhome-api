package com.kaixin8848.home.web.mybatisdemo.service;

import com.kaixin8848.home.core.Service;
import com.kaixin8848.home.web.mybatisdemo.model.Employee;
import com.kaixin8848.home.web.mybatisdemo.pojo.out.EmployeeInfo;

import java.util.List;


/**
 * Created by tmy on 2020/02/19.
 */
public interface EmployeeService extends Service<Employee> {
    /**
     * select_resultMap_关联查询_级联属性封装结果
     * @param id
     * @return
     */
    List<EmployeeInfo> getEmpAndDept(Long id);

    /**
     * select_resultMap_关联查询_association定义关联对象封装
     * @param id
     * @return
     */
    List<EmployeeInfo> getEmpAndDept2(Long id);

    /**
     * select_resultMap_关联查询_association分步查询
     * @param empId
     * @return
     */
    EmployeeInfo getEmpAndDept3(Long empId);

    /**
     * select_resultMap_discriminator鉴别器
     * @param empId
     * @return
     */
    EmployeeInfo getEmpAndDept4(Long empId);

    /**
     * 动态sql_trim_自定义字符串截取
     * @param employee
     * @return
     */
    List<Employee> getEmpsByConditionTrim(Employee employee);

    /**
     * 动态sql_choose_分支选择
     * @param employee
     * @return
     */
    List<Employee> getEmpsByConditionChoose(Employee employee);

    /**
     * 动态sql_set_与if结合的动态更新
     * @param employee
     */
    void updateEmpUseSet(Employee employee);

    /**
     * 动态sql_foreach_遍历集合
     * @param ids
     * @return
     */
    List<Employee> getEmpsByConditionForEach(List<String> ids);

    /**
     * 动态sql_foreach_mysql下foreach批量插入的两种方式
     * @param employees
     * @return
     */
    boolean addEmps(List<Employee> employees);

    /**
     * 动态sql_bind_绑定
     * @param employee
     * @return
     */
    List<Employee> getEmpsByConditionForBind(Employee employee);
}
