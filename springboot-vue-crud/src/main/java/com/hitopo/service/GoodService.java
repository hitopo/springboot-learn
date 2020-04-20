package com.hitopo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hitopo.entity.Good;
import com.hitopo.entity.PageBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hitopo
 * @since 2020-04-19
 */
public interface GoodService extends IService<Good> {

    /**
     * 分页查询
     * @param good 分页查询参数
     * @param pageCode 页码
     * @param pageSize 每一页大小
     */
    PageBean<Good> findAllByPage(Good good, Integer pageCode, Integer pageSize);
}
