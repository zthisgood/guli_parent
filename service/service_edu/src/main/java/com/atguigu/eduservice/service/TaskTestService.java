package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Question;
import com.atguigu.eduservice.entity.TaskTest;
import com.atguigu.eduservice.entity.TaskUserLink;
import com.atguigu.eduservice.entity.vo.TaskTestLevel;
import com.atguigu.eduservice.entity.vo.TaskTestVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface TaskTestService extends IService<TaskTest> {

    List<TaskTestVO> getPaperFromTaskBook(Integer taskId, String userId);

    Boolean addTaskTest(Integer taskId, String userId);

    Integer generateTaskTest(Integer taskId, Integer userId);

    List<TaskTest> getTitleFraction(List<Integer> ids);

    List<Map<String, String>> getWrongTaskTestTitle(String userId);

    Question getQuestionById(Integer titleId);

    /**
     * 根据知识点查询题目
     * @param knowledge
     * @return 1
     */
    List<Question> getSomeTitleByKnowledge(String knowledge);

    /**
     * 获取匹配的用户的对称差
     * 知识点
     * @param userId
     * @return
     */
    List<String> getMatchedKnowledgeByOtherPeople(String userId, String others);
}
