package com.hitopo.controller;


import com.hitopo.common.R;
import com.hitopo.common.ResultEnum;
import com.hitopo.entity.Good;
import com.hitopo.service.GoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    private final Logger log = LoggerFactory.getLogger(GoodController.class);

    @Autowired
    private GoodService goodService;

    /**
     * 分页查询商品数据
     */
    @GetMapping("/findAll")
    public R findAll(Good good,
                     @RequestParam("pageCode") Integer pageCode,
                     @RequestParam("pageSize") Integer pageSize) {
        log.info("查询参数信息={}", good);
        log.info("pageCode={}", pageCode);
        log.info("pageSize={}", pageSize);
        List<Good> goodList = goodService.list(null);
        return R.create(ResultEnum.SUCCESS, goodList);
    }
}

