package com.hitopo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitopo.entity.Good;
import com.hitopo.entity.PageBean;
import com.hitopo.mapper.GoodMapper;
import com.hitopo.service.GoodService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hitopo
 * @since 2020-04-19
 */
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {

    @Override
    public PageBean<Good> findAllByPage(Good good, Integer pageCode, Integer pageSize) {
        String brand = good.getBrand();
        String title = good.getTitle();
        QueryWrapper<Good> queryWrapper = new QueryWrapper<>();
        if (brand != null) {
            queryWrapper.like("brand", brand);
        }
        if (title != null) {
            queryWrapper.like("title", title);
        }
        Page<Good> goodPage = baseMapper.selectPage(new Page<>(pageCode, pageSize), queryWrapper);

        return new PageBean<>(goodPage.getTotal(), goodPage.getRecords());
    }
}
