package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.TaskTest;
import com.atguigu.eduservice.entity.TaskUser;
import com.atguigu.eduservice.mapper.TaskTestMapper;
import com.atguigu.eduservice.mapper.TaskUserMapper;
import com.atguigu.eduservice.service.TaskUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author ivern
 */
@Transactional
@Service
public class TaskUserServiceImpl extends ServiceImpl<TaskUserMapper, TaskUser> implements TaskUserService {

    @Autowired
    private TaskUserMapper taskUserMapper;

    @Autowired
    private TaskTestMapper taskTestMapper;

    @Override
    public List<Map<String, String>> getPaperInfoList() {
        return taskUserMapper.queryPaperList();
    }

    @Override
    public Integer calculatePaperScore(String taskId, String userId) {
        QueryWrapper<TaskTest> wrapper = new QueryWrapper<>();
        TaskTest taskTest = new TaskTest();
        taskTest.setTaskId(Integer.parseInt(taskId));
        taskTest.setUserId(userId);
        wrapper.setEntity(taskTest);
        List<TaskTest> list = taskTestMapper.selectList(wrapper);
        Integer f = list.stream().filter(p-> p.getAnswer().equals(p.getQuestionAnswer()))
                .mapToInt(TaskTest::getQuestionFraction).sum();
        TaskUser taskUser = new TaskUser();
        taskUser.setFraction(f);
        taskUser.setStatus(1);
        QueryWrapper<TaskUser> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("task_id", taskId).eq("user_id", userId);
        return taskUserMapper.update(taskUser,wrapper1);
    }
}
