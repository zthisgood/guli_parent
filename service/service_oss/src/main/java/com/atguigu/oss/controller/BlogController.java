package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.entity.BlogModel;
import com.atguigu.oss.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @PostMapping("/add")
    public R add(@RequestBody BlogModel blogModel) {
        blogRepository.save(blogModel);
        return R.ok();
    }
}
