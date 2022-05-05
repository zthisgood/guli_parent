package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.TaskInfo;
import com.atguigu.eduservice.entity.TitleInfo;
import com.atguigu.eduservice.entity.query.TaskQuery;
import com.atguigu.eduservice.entity.so.TaskCreateParam;
import com.atguigu.eduservice.entity.vo.TaskTestLevel;
import com.atguigu.eduservice.entity.vo.UserVO;

import java.util.List;


public interface ExamService{


    //试卷管理分页
    List<TaskInfo> queryTaskList (TaskQuery query);

    void insertTitle(TitleInfo info, UserVO user);

    void updateTitle(TitleInfo info);

    //试卷页面
    TaskTestLevel queryTask(Integer taskId);

    //查看已考试卷
    TaskTestLevel queryTaskCompleted(Integer taskId,String userId);
    //自动组卷
    void createATask(TaskInfo taskInfo, TaskCreateParam taskCreateParam);

    //修改试题
    void updateTitle(String titleString);

}
