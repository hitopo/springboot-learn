package com.hitopo.controller;


import com.hitopo.common.R;
import com.hitopo.common.ResultEnum;
import com.hitopo.entity.Good;
import com.hitopo.entity.PageBean;
import com.hitopo.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 * @author hitopo
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private GoodService goodService;

    /**
     * 分页查询商品数据
     */
    @GetMapping("/goods")
    public R findAllByPage(Good good,
                           @RequestParam("pageCode") Integer pageCode,
                           @RequestParam("pageSize") Integer pageSize) {
        PageBean<Good> pageInfo = goodService.findAllByPage(good, pageCode, pageSize);
        return R.create(ResultEnum.SUCCESS, pageInfo);
    }

    /**
     * 批量删除商品数据
     */
    @PostMapping("/goods/delete")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        boolean result = goodService.removeByIds(ids);
        if (result) {
            return R.create(ResultEnum.DELETED);
        } else {
            return R.create(ResultEnum.INNER_ERROR);
        }
    }

    /**
     * 新添加商品
     */
    @PostMapping("/goods")
    public R save(@RequestBody Good good) {
        boolean result = goodService.save(good);
        if (result) {
            return R.create(ResultEnum.CREATED);
        } else {
            return R.create(ResultEnum.INNER_ERROR);
        }
    }

    /**
     * 查询单个商品信息
     */
    @GetMapping("/goods/{id}")
    public R findById(@PathVariable("id") Long id) {
        Good good = goodService.getById(id);
        return R.create(ResultEnum.SUCCESS, good);
    }


    /**
     * 修改商品信息
     */
    @PutMapping("/goods/{id}")
    public R update(@PathVariable("id") Long id, @RequestBody Good good) {
        boolean result = goodService.updateById(good);
        if (result) {
            return R.create(ResultEnum.SUCCESS);
        } else {
            return R.create(ResultEnum.INNER_ERROR);
        }
    }

}

