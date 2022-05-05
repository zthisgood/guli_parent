package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.TaskUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface TaskUserService extends IService<TaskUser> {

    List<Map<String, String>> getPaperInfoList();

    /**
     * 计算paper分数，修改作业状态
     * @return
     */
    Integer calculatePaperScore(String taskId, String userId);
}
