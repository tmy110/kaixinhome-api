package com.kaixin8848.home.web.mybatisdemo.dao;

import com.kaixin8848.home.core.Mapper;
import com.kaixin8848.home.web.mybatisdemo.model.Dept;
import com.kaixin8848.home.web.mybatisdemo.model.Employee;
import com.kaixin8848.home.web.mybatisdemo.pojo.out.DeptInfo;

public interface DeptMapper extends Mapper<Dept> {

    public Dept getDeptById(Long id);

    public DeptInfo getDeptByIdPlus(Long id);

    /**
     * select_resultMap_关联查询_collection分步查询
     * @param id
     * @return
     */
    public DeptInfo getDeptByIdStep(Long id);
}