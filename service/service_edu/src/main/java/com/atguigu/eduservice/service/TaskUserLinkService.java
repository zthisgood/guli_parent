package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.TaskUserLink;
import com.atguigu.eduservice.entity.vo.QuestionVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TaskUserLinkService extends IService<TaskUserLink> {

    List<QuestionVO> getPaperQuestionList(Integer taskId);

}
