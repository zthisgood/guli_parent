package com.atgui.eduneo4j.controller;


import com.atgui.eduneo4j.entity.Relation;
import com.atgui.eduneo4j.service.RelationService;
import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 321
 * @since 2022-02-18
 */
@RequestMapping("/relation")
@RestController
public class RelationController {

    @Autowired
    private RelationService relationService;

    @GetMapping("/params")
    public R getRelationParams(){
        QueryWrapper<Relation> wrapper = new QueryWrapper<>();
        List<Relation> list = relationService.list(wrapper);
        return R.ok().data("list", list);
    }

}

