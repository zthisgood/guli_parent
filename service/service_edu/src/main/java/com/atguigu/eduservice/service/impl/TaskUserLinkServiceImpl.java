package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Student;
import com.atguigu.eduservice.entity.TaskUserLink;
import com.atguigu.eduservice.entity.vo.QuestionVO;
import com.atguigu.eduservice.mapper.StudentMapper;
import com.atguigu.eduservice.mapper.TaskUserLinkMapper;
import com.atguigu.eduservice.service.StudentService;
import com.atguigu.eduservice.service.TaskUserLinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskUserLinkServiceImpl  extends ServiceImpl<TaskUserLinkMapper, TaskUserLink> implements TaskUserLinkService {

    @Autowired
    private TaskUserLinkMapper taskUserLinkMapper;

    @Override
    public List<QuestionVO> getPaperQuestionList(Integer taskId) {
        return taskUserLinkMapper.queryPaperQuestionsByTeacher(taskId);
    }
}
