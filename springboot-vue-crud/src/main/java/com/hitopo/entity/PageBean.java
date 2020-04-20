package com.hitopo.entity;

import java.util.List;

/**
 * @author hitopo
 * @version v1.0
 * @classname PageBean
 * @time 2020/4/20 12:07
 * @description 封装分页查询的参数
 */
public class PageBean<T> {
    /**
     * 总记录数
     */
    private Long total;

    /**
     * 数据列表
     */
    private List<T> dataList;

    public PageBean() {
    }

    public PageBean(Long total, List<T> dataList) {
        this.total = total;
        this.dataList = dataList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "total=" + total +
                ", dataList=" + dataList +
                '}';
    }
}
