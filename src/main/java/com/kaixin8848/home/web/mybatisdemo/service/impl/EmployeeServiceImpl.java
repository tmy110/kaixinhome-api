package com.kaixin8848.home.web.mybatisdemo.service.impl;

import com.kaixin8848.home.web.mybatisdemo.dao.EmployeeMapper;

import com.kaixin8848.home.web.mybatisdemo.model.Employee;
import com.kaixin8848.home.web.mybatisdemo.pojo.out.EmployeeInfo;
import com.kaixin8848.home.web.mybatisdemo.service.EmployeeService;
import com.kaixin8848.home.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by tmy on 2020/02/19.
 */
@Service
@Transactional
public class EmployeeServiceImpl extends AbstractService<Employee> implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * select_resultMap_关联查询_级联属性封装结果
     *
     * @param id
     * @return
     */
    @Override
    public List<EmployeeInfo> getEmpAndDept(Long id) {
        return employeeMapper.getEmpAndDept(id);
    }

    /**
     * select_resultMap_关联查询_association定义关联对象封装
     *
     * @param id
     * @return
     */
    @Override
    public List<EmployeeInfo> getEmpAndDept2(Long id) {
        return employeeMapper.getEmpAndDept2(id);
    }

    @Override
    public EmployeeInfo getEmpAndDept3(Long empId) {
        return employeeMapper.getEmpByIdStep(empId);
    }

    /**
     * select_resultMap_discriminator鉴别器
     * @param empId
     * @return
     */
    @Override
    public EmployeeInfo getEmpAndDept4(Long empId) {
        return employeeMapper.getEmpAndDept4(empId);
    }

    /**
     * 动态sql_trim_自定义字符串截取
     * @param employee
     * @return
     */
    @Override
    public List<Employee> getEmpsByConditionTrim(Employee employee) {
        return employeeMapper.getEmpsByConditionTrim(employee);
    }

    /**
     * 动态sql_choose_分支选择
     * @param employee
     * @return
     */
    @Override
    public List<Employee> getEmpsByConditionChoose(Employee employee) {
        return employeeMapper.getEmpsByConditionChoose(employee);
    }

    /**
     * 动态sql_set_与if结合的动态更新
     * @param employee
     */
    @Override
    public void updateEmpUseSet(Employee employee) {
        employeeMapper.updateEmpUseSet(employee);
    }

    /**
     * 动态sql_foreach_遍历集合
     * @param ids
     * @return
     */
    @Override
    public List<Employee> getEmpsByConditionForEach(List<String> ids) {
        return employeeMapper.getEmpsByConditionForEach(ids);
    }

    /**
     * 动态sql_foreach_mysql下foreach批量插入的两种方式
     * @param employees
     * @return
     */
    @Override
    public boolean addEmps(List<Employee> employees) {
        return employeeMapper.addEmps(employees);
    }

    /**
     * 动态sql_bind_绑定
     * @param employee
     * @return
     */
    @Override
    public List<Employee> getEmpsByConditionForBind(Employee employee) {
        return employeeMapper.getEmpsByConditionForBind(employee);
    }
}
