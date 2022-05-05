package com.atguigu.eduservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.eduservice.entity.TaskInfo;
import com.atguigu.eduservice.entity.TaskTest;
import com.atguigu.eduservice.entity.TaskUserLink;
import com.atguigu.eduservice.entity.TitleInfo;
import com.atguigu.eduservice.entity.query.TaskQuery;
import com.atguigu.eduservice.entity.so.TaskCreateParam;
import com.atguigu.eduservice.entity.vo.TaskTestLevel;
import com.atguigu.eduservice.entity.vo.TaskTitleVO;
import com.atguigu.eduservice.entity.vo.TestLevelOne;
import com.atguigu.eduservice.entity.vo.UserVO;
import com.atguigu.eduservice.mapper.*;
import com.atguigu.eduservice.service.ExamService;
import com.atguigu.eduservice.util.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @date 2021-02-26 14:04
 */
@Service
@Transactional
public class ExamServiceImpl implements ExamService {

    @Autowired
    private TitleInfoMapper titleInfoMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Autowired
    private TaskUserLinkMapper userLinkMapper;

    @Autowired
    private TaskTestMapper taskTestMapper;


    @Override
    public TaskTestLevel queryTaskCompleted(Integer taskId, String userId) {
        TaskTestLevel testLevel = new TaskTestLevel();
        List<TestLevelOne> oneList2 = new ArrayList<>();
        List<TestLevelOne> oneList3 = new ArrayList<>();
        List<TaskTitleVO> taskTitleVOS = taskInfoMapper.queryTitleTask(taskId);
        List<TaskTest> zjTaskTests = taskTestMapper.queryTaskTestByUserIdAndTaskId(taskId, userId);
        HashMap<Integer,String> map = new HashMap<>();
        zjTaskTests.forEach(f->{
            map.put(f.getTitleId(),f.getAnswer());
        });

        //分析试卷
        List<TaskTitleVO> collect = taskTitleVOS.stream().filter(f -> f.getTitleStatus() == 0).collect(Collectors.toList());
        List<TaskTitleVO> collect1 = taskTitleVOS.stream().filter(f -> f.getTitleStatus() == 1).collect(Collectors.toList());
        List<TaskTitleVO> collect2 = taskTitleVOS.stream().filter(f -> f.getTitleStatus() == 2).collect(Collectors.toList());
        //单选题总分数
        if (!ObjectUtils.isEmpty(collect)) {
            List<TestLevelOne> oneList1 = new ArrayList<>();
            for (TaskTitleVO titleVO : collect) {
                TestLevelOne levelOne = new TestLevelOne();
                levelOne.setTitleName(titleVO.getTitleName());
                levelOne.setId(titleVO.getTitleId());
                levelOne.setTitleFraction(titleVO.getFraction());
                levelOne.setChoice1(titleVO.getChoice1());
                levelOne.setChoice2(titleVO.getChoice2());
                levelOne.setChoice3(titleVO.getChoice3());
                levelOne.setChoice4(titleVO.getChoice4());
                levelOne.setAnswer(titleVO.getTitleAnswer());
                map.forEach((k,v)->{
                    if (k.equals(titleVO.getTitleId())){
                        levelOne.setStudentAnswers(v);
                    }
                });
                oneList1.add(levelOne);
            }
            testLevel.setOneList1(oneList1);
        }
        //填空
        if (!ObjectUtils.isEmpty(collect1)) {
            for (TaskTitleVO titleVO : collect1) {
                TestLevelOne levelOne = new TestLevelOne();
                levelOne.setTitleName(titleVO.getTitleName());
                levelOne.setId(titleVO.getTitleId());
                levelOne.setTitleFraction(titleVO.getFraction());
                levelOne.setAnswer(titleVO.getTitleAnswer());
                map.forEach((k,v)->{
                    if (k.equals(titleVO.getTitleId())){
                        levelOne.setStudentAnswers(v);
                    }
                });
                oneList2.add(levelOne);
            }
            testLevel.setOneList2(oneList2);
        }
        //主观
        if (!ObjectUtils.isEmpty(collect2)) {
            for (TaskTitleVO titleVO : collect2) {
                TestLevelOne levelOne = new TestLevelOne();
                levelOne.setTitleName(titleVO.getTitleName());
                levelOne.setId(titleVO.getTitleId());
                levelOne.setTitleFraction(titleVO.getFraction());
                levelOne.setAnswer(titleVO.getTitleAnswer());
                map.forEach((k,v)->{
                    if (k.equals(titleVO.getTitleId())){
                        levelOne.setStudentAnswers(v);
                    }
                });
                oneList3.add(levelOne);
            }
            testLevel.setOneList3(oneList3);
        }
        return testLevel;
    }

    @Override
    public List<TaskInfo> queryTaskList(TaskQuery query) {
        return taskInfoMapper.queryTaskList(query);
    }


    @Override
    public void insertTitle(TitleInfo info, UserVO user) {
        String currentDateTime = DateUtil.getCurrentDateTime();
        info.setUserId(user.getUserId());
        info.setUserName(user.getUserName());
        info.setCreateTime(currentDateTime);
        info.setUpdateTime(currentDateTime);
        titleInfoMapper.insert(info);
    }

    @Override
    public void updateTitle(TitleInfo info) {
        String currentDateTime = DateUtil.getCurrentDateTime();
        info.setUpdateTime(currentDateTime);
        titleInfoMapper.updateById(info);
    }

    @Override
    public TaskTestLevel queryTask(Integer taskId) {
        TaskTestLevel testLevel = new TaskTestLevel();
        List<TestLevelOne> oneList2 = new ArrayList<>();
        List<TestLevelOne> oneList3 = new ArrayList<>();
        List<TaskTitleVO> taskTitleVOS = taskInfoMapper.queryTitleTask(taskId);
        //分析试卷
        List<TaskTitleVO> collect = taskTitleVOS.stream().filter(f -> f.getTitleStatus() == 0).collect(Collectors.toList());
        List<TaskTitleVO> collect1 = taskTitleVOS.stream().filter(f -> f.getTitleStatus() == 1).collect(Collectors.toList());
        List<TaskTitleVO> collect2 = taskTitleVOS.stream().filter(f -> f.getTitleStatus() == 2).collect(Collectors.toList());
        //单选题总分数
        if (!ObjectUtils.isEmpty(collect)) {
            List<TestLevelOne> oneList1 = new ArrayList<>();
            for (TaskTitleVO titleVO : collect) {
                TestLevelOne levelOne = new TestLevelOne();
                levelOne.setTitleName(titleVO.getTitleName());
                levelOne.setId(titleVO.getTitleId());
                levelOne.setTitleFraction(titleVO.getFraction());
                levelOne.setChoice1(titleVO.getChoice1());
                levelOne.setChoice2(titleVO.getChoice2());
                levelOne.setChoice3(titleVO.getChoice3());
                levelOne.setChoice4(titleVO.getChoice4());
                levelOne.setAnswer(titleVO.getTitleAnswer());
                oneList1.add(levelOne);
            }
            testLevel.setOneList1(oneList1);
        }
        //填空
        if (!ObjectUtils.isEmpty(collect1)) {
            for (TaskTitleVO titleVO : collect1) {
                TestLevelOne levelOne = new TestLevelOne();
                levelOne.setTitleName(titleVO.getTitleName());
                levelOne.setId(titleVO.getTitleId());
                levelOne.setTitleFraction(titleVO.getFraction());
                levelOne.setAnswer(titleVO.getTitleAnswer());
                oneList2.add(levelOne);
            }
            testLevel.setOneList2(oneList2);
        }
        //主观
        if (!ObjectUtils.isEmpty(collect2)) {

            for (TaskTitleVO titleVO : collect2) {
                TestLevelOne levelOne = new TestLevelOne();
                levelOne.setTitleName(titleVO.getTitleName());
                levelOne.setId(titleVO.getTitleId());
                levelOne.setTitleFraction(titleVO.getFraction());
                levelOne.setAnswer(titleVO.getTitleAnswer());
                oneList3.add(levelOne);
            }
            testLevel.setOneList3(oneList3);
        }
        return testLevel;
    }

    /**
     * 1.创建试卷 taskinfo
     * 2.选择题目
     * 从题库中选择指定数量的题目
     * 将题目的id号记录到对应的作业题目表
     * @param taskInfo
     */
    @Override
    public void createATask(TaskInfo taskInfo, TaskCreateParam taskCreateParam) {
        //1.创建试卷
        String currentDateTime = DateUtil.getCurrentDateTime();
        if(taskInfoMapper.insert(taskInfo)!=0){
            QueryWrapper<TaskInfo> taskInfoQueryWrapper = new QueryWrapper<>();
            taskInfoQueryWrapper.setEntity(taskInfo);
            TaskInfo taskInfo1 = taskInfoMapper.selectOne(taskInfoQueryWrapper);
            //2.选择题目并将记录到task_user_link作业题目表
//        userLinkMapper taskTestMapper
            List<Integer> list1 = questionMapper.getFillQuestionList();
            List<Integer> list2 = questionMapper.getMultiQuestionList();
            Random random= new Random();
            List<TaskUserLink> taskQuestions = new ArrayList<>();
            //用户作业本，获取填空题
            for(int i=0;i<Integer.parseInt(taskCreateParam.getFillNum());i++){
                TaskUserLink taskUserLink = new TaskUserLink();
                taskUserLink.setTaskId(taskInfo1.getTaskId());
                taskUserLink.setCreateTime(currentDateTime);
                taskUserLink.setUpdateTime(currentDateTime);
                taskUserLink.setQuestionId(list1.get(random.nextInt(list1.size())));
                taskUserLink.setQuestionFraction(Integer.parseInt(taskCreateParam.getFillScore()));
                taskQuestions.add(taskUserLink);
            }
            //用户作业本，获取选择题
            for(int i=0;i<Integer.parseInt(taskCreateParam.getSelectNum());i++){
                TaskUserLink taskUserLink = new TaskUserLink();
                taskUserLink.setTaskId(taskInfo1.getTaskId());
                taskUserLink.setCreateTime(currentDateTime);
                taskUserLink.setUpdateTime(currentDateTime);
                taskUserLink.setQuestionId(list2.get(random.nextInt(list2.size())));
                taskUserLink.setQuestionFraction(Integer.parseInt(taskCreateParam.getSelectScore()));
                taskQuestions.add(taskUserLink);
            }
            //生成教师作业本
            userLinkMapper.saveTaskQuestionBookBatch(taskQuestions);
        }
    }

    @Override
    public void updateTitle(String titleString) {
        String str = "[" + titleString + "]";
        JSONArray objects = JSON.parseArray(str);
        List<Integer> ids = new ArrayList<>();
        for (Object obj : objects) {
            TitleInfo taskTest = new TitleInfo();
            JSONObject object = JSON.parseObject(obj.toString());
            Object id = object.get("id");
            Object answer = object.get("answer");
            if (!ObjectUtils.isEmpty(id)) {
                taskTest.setTitleId(Integer.valueOf(id.toString()));
                ids.add(Integer.valueOf(id.toString()));
            }
            if (!ObjectUtils.isEmpty(answer)) {
                taskTest.setTitleAnswer(answer.toString());
                titleInfoMapper.updateById(taskTest);
            }
        }
    }
}