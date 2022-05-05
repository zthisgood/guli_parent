package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Question;
import com.atguigu.eduservice.entity.TaskTest;
import com.atguigu.eduservice.entity.vo.TaskTestVO;
import com.atguigu.eduservice.mapper.QuestionMapper;
import com.atguigu.eduservice.mapper.TaskTestMapper;
import com.atguigu.eduservice.service.TaskTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskTestServiceImpl extends ServiceImpl<TaskTestMapper,TaskTest> implements TaskTestService {


    @Autowired
    private TaskTestMapper taskTestMapper;

    @Autowired
    private QuestionMapper questionMapper;


    /**
     * 1.获取题目id query
     * 2.生成题目paper_test add
     * 3.query查询paper_test
     * @param
     * @param userId
     * @return
     */
    @Override
    public List<TaskTestVO> getPaperFromTaskBook(Integer taskId, String userId) {
        return taskTestMapper.queryStuTaskTest(userId, taskId);
    }

    /**
     * 新建task作业
     * @param taskId
     * @param userId
     * @return
     */
    @Override
    public Boolean addTaskTest(Integer taskId, String userId) {

        return null;
    }

    /**
     * 生成指定学生的作业题目test_paper
     * @param taskId
     * @param userId
     * @return
     */
    @Override
    public Integer generateTaskTest(Integer taskId, Integer userId) {
        return taskTestMapper.insertTaskTest(taskId, userId);
    }

    /**
     * 更新题目分数
     * @return
     */
    @Override
    public List<TaskTest> getTitleFraction(List<Integer> ids) {
        List<TaskTest> list = taskTestMapper.getTitleFraction(ids);
        return list;
    }

    @Override
    public List<Map<String, String>> getWrongTaskTestTitle(String userId) {
        String score = "0";
        return taskTestMapper.getWrongTaskTestTitle(userId);
    }

    @Override
    public Question getQuestionById(Integer titleId) {
        return taskTestMapper.getQuestionById(titleId);
    }

    @Override
    public List<Question> getSomeTitleByKnowledge(String knowledge) {
        return questionMapper.querySomeTitleByKnowledge(knowledge);
    }

    @Override
    public List<String> getMatchedKnowledgeByOtherPeople(String userId, String other){
        List<String> list1 = questionMapper.queryUserKnowledge(Integer.parseInt(userId));
        List<String> list2 = questionMapper.queryUserKnowledge(Integer.parseInt(other));
        if (list1.size()!=0){
            try {
                list2.removeAll(list1);
            }catch (Exception e){
                e.fillInStackTrace();
            }
        }
        return list2;
    }
}
