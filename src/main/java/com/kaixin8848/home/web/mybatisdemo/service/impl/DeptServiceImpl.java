package com.kaixin8848.home.web.mybatisdemo.service.impl;

import com.kaixin8848.home.web.mybatisdemo.dao.DeptMapper;
import com.kaixin8848.home.web.mybatisdemo.model.Dept;
import com.kaixin8848.home.web.mybatisdemo.pojo.out.DeptInfo;
import com.kaixin8848.home.web.mybatisdemo.service.DeptService;
import com.kaixin8848.home.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by tmy on 2020/02/19.
 */
@Service
@Transactional
public class DeptServiceImpl extends AbstractService<Dept> implements DeptService {
    @Resource
    private DeptMapper deptMapper;

    /**
     * select_resultMap_关联查询_collection定义关联集合封装
     * @param deptId
     * @return
     */
    @Override
    public DeptInfo getDeptByIdPlus(Long deptId) {

        return deptMapper.getDeptByIdPlus(deptId);
    }

    /**
     * select_resultMap_关联查询_collection分步查询
     * @param deptId
     * @return
     */
    @Override
    public DeptInfo getDeptByIdStep(Long deptId) {
        return  deptMapper. getDeptByIdStep(deptId);
    }
}
