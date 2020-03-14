package com.kaixin8848.home.web.mybatisdemo.dao;


import com.kaixin8848.home.core.Mapper;
import com.kaixin8848.home.web.mybatisdemo.model.Employee;
import com.kaixin8848.home.web.mybatisdemo.pojo.out.EmployeeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper extends Mapper<Employee> {
    /**
     * select_resultMap_关联查询_级联属性封装结果
     *
     * @param id
     * @return
     */
    public List<EmployeeInfo> getEmpAndDept(Long id);

    /**
     * select_resultMap_关联查询_association定义关联对象封装
     *
     * @param id
     * @return
     */
    List<EmployeeInfo> getEmpAndDept2(Long id);


    public EmployeeInfo getEmpByIdStep(Long id);

    public List<Employee> getEmpsByDeptId(Long deptId);

    /**
     * select_resultMap_discriminator鉴别器
     *
     * @param empId
     * @return
     */
    EmployeeInfo getEmpAndDept4(Long empId);

    public List<Employee> getEmpsByConditionTrim(Employee employee);

    /**
     * 动态sql_choose_分支选择
     *
     * @param employee
     * @return
     */
    List<Employee> getEmpsByConditionChoose(Employee employee);

    /**
     * 动态sql_set_与if结合的动态更新
     *
     * @param employee
     */
    void updateEmpUseSet(Employee employee);

    /**
     * 动态sql_foreach_遍历集合
     *
     * @param ids
     * @return
     */
    List<Employee> getEmpsByConditionForEach(@Param("ids") List<String> ids);

    public boolean addEmps(@Param("emps") List<Employee> emps);

    /**
     * 动态sql_bind_绑定
     * @param employee
     * @return
     */
    List<Employee> getEmpsByConditionForBind(Employee employee);
}