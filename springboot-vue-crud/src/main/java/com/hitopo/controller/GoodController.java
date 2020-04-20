package com.hitopo.controller;


import com.hitopo.common.R;
import com.hitopo.common.ResultEnum;
import com.hitopo.entity.Good;
import com.hitopo.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Autowired
    private GoodService goodService;

    @GetMapping("/goods")
    public R goods() {
        List<Good> goodList = goodService.list(null);
        return R.success(ResultEnum.SUCCESS, goodList);
    }
}

