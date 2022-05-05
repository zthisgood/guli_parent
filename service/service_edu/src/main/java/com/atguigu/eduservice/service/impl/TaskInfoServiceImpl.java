package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.TaskInfo;
import com.atguigu.eduservice.mapper.TaskInfoMapper;
import com.atguigu.eduservice.service.TaskInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TaskInfoServiceImpl  extends ServiceImpl<TaskInfoMapper, TaskInfo> implements TaskInfoService {
}
