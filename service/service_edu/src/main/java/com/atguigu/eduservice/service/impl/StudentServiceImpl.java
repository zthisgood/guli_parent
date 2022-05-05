package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Student;
import com.atguigu.eduservice.mapper.StudentMapper;
import com.atguigu.eduservice.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author:ivern
 * @since 2022-02-25
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
