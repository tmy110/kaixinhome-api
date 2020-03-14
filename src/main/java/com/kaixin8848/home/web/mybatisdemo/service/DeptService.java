package com.kaixin8848.home.web.mybatisdemo.service;
import com.kaixin8848.home.web.mybatisdemo.model.Dept;
import com.kaixin8848.home.core.Service;
import com.kaixin8848.home.web.mybatisdemo.pojo.out.DeptInfo;


/**
 * Created by tmy on 2020/02/19.
 */
public interface DeptService extends Service<Dept> {
    /**
     * select_resultMap_关联查询_collection定义关联集合封装
     * @param deptId
     * @return
     */
    DeptInfo getDeptByIdPlus(Long deptId);

    /**
     * select_resultMap_关联查询_collection分步查询
     * @param deptId
     * @return
     */
    DeptInfo getDeptByIdStep(Long deptId);
}
