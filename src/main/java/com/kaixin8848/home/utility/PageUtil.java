package com.kaixin8848.home.utility;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页
 */
public class PageUtil<T> {
    /**
     * 共几行
     */
    private int total;

    private List<T> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageUtil() {
    }

    public PageUtil(int total, List<T> list) {
        this.setTotal(total);
        this.setList(list);
    }

    public static PageUtil pageInfo(PageInfo pageInfo){
        PageUtil pageUtil =new PageUtil((int) pageInfo.getTotal(),pageInfo.getList());
        return pageUtil;
    }
}