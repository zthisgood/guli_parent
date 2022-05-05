package com.atgui.eduneo4j.controller;


import com.atgui.eduneo4j.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 321
 * @since 2022-02-18
 */
@Controller
@RequestMapping("/entity")
@CrossOrigin
public class EntityController {

    @Autowired
    private EntityService entityService;


}

