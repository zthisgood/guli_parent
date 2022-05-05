package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.config.UserUtil;
import com.atguigu.eduservice.entity.EduUser;
import com.atguigu.eduservice.entity.Question;
import com.atguigu.eduservice.entity.TaskTest;
import com.atguigu.eduservice.entity.TaskUser;
import com.atguigu.eduservice.service.QuestionService;
import com.atguigu.eduservice.service.TaskTestService;
import com.atguigu.eduservice.service.TaskUserService;
import com.atguigu.eduservice.util.DateUtil;

import com.atguigu.eduservice.util.PearsonCorrelationScore;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eduservice/paper/")
public class PaperController {

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private TaskUserService taskUserService;

    @Autowired
    private TaskTestService taskTestService;

    @Autowired
    private QuestionService questionService;

    /**
     * 查询学生们的作业完成情况
     * @return r
     */
    @PostMapping("testList")
    public R getPaperTaskList() {

        List<Map<String, String>> list = taskUserService.getPaperInfoList();
        return R.ok().data("data", list);
    }

    /**
     * 根据知识点获取相应的题目
     * @return 1
     */
    @PostMapping("queryKnowledge")
    public R getTitleListByKnowledge(String knowledge){
        if (knowledge==null || "".equals(knowledge)){
            QueryWrapper<Question> wrapper = new QueryWrapper<>();
            wrapper.eq("id", 1);
            Question q = questionService.getOne(wrapper);
            knowledge = q.getKnowl();
        }
        List<Question> questionList = taskTestService.getSomeTitleByKnowledge(knowledge);
        questionList.forEach(Question::splitChoices);
        return R.ok().data("data",questionList);
    }

    /**
     * 教师批改学生作业paper
     * 1.提交正确的题目编号
     * 2.后台更新学生作业分数
     * 3.生成错题记录
     * @return r
     */
    @PostMapping("markPaper")
    public R markPaperResult(@RequestBody Map<String, Object> objectMap) {
        List<Integer> ids = (List<Integer>) objectMap.get("ids");
        List<TaskTest> list = taskTestService.getTitleFraction(ids);
        //更新答题成绩
        if (taskTestService.updateBatchById(list)) {
            //计算分数，更新作业状态
            taskUserService.calculatePaperScore(objectMap.get("taskId").toString(), objectMap.get("stuId").toString());
            return R.ok();
        }
        return R.error();
    }

    /**
     * 获取所有的错题
     * 获取用户所有的错题
     * @return r
     */
    @GetMapping("wrongTitle")
    public R getWrongTitle() {
        EduUser user = userUtil.getUser();
        List<Map<String, String>> list = taskTestService.getWrongTaskTestTitle(user.getId().toString());
        return R.ok().data("data", list);
    }

    /**
     * 错题模块
     * 根据titleId获取
     * 题库题目
     */
    @GetMapping("getTitleById")
    public R getTitleByTitleId(String titleId){
        if (titleId!=null){
            Question question = taskTestService.getQuestionById(Integer.parseInt(titleId));
            question.splitChoices();
            return R.ok().data("data",question);
        }
        return R.error().message("数据参数报错");
    }

    /**
     * 重新更新学生作业的
     * 错题记录
     * @return 1
     */
    @PostMapping("submitTheTitle")
    public R updateTaskTest(@RequestBody TaskTest taskTest){
        taskTest.filterCorrectAnswer();
        if (taskTestService.updateById(taskTest)){
            return R.ok();
        }
        return R.error().message("更新失败");
    }

    /**
     * 协同过滤算法
     * fraction-based
     * 1.相同level的分数
     * 2.皮尔逊相关系数计算作业分数矩阵
     * 计算出与本用户分数相似的用户
     * 然后从其他用户题目的知识点里
     * knowledge-based
     * 1.推荐本用户没有做过的知识点题目
     * 以加强本用户知识点掌握熟练程度
     * @return r
     */
    @GetMapping("collaborate")
    public R filterCollaborativeTitle(){
        EduUser user = userUtil.getUser();
        PearsonCorrelationScore pearsonCorrelation = new PearsonCorrelationScore();
        QueryWrapper<TaskUser> wrapper = new QueryWrapper<>();
        List<TaskUser> taskUserList = taskUserService.list(wrapper);
        List<String> userIdList = taskUserList.stream().map(TaskUser::getUserId).collect(Collectors.toList());
        List<String> userIds = userIdList.stream().distinct().collect(Collectors.toList());
        for (String userId: userIds){
            Map<Integer, Integer> map1 = taskUserList.stream().
                    filter(p -> p.getUserId().equals(userId)).
                    collect(Collectors.toMap(TaskUser::getTaskId,TaskUser::getFraction));
            pearsonCorrelation.addDataMap(userId, map1);
        }
        String similarPerson = pearsonCorrelation.getBestMatchUser(user.getId().toString());
        // query匹配的用户做过的knowledge
        if (!"".equals(similarPerson)){
            List<String> knowList = taskTestService.getMatchedKnowledgeByOtherPeople(user.getId().toString(),similarPerson);
            if (knowList.size()==0){
                return R.error().message("用户信息不足");
            }
            List<Question> questions = new ArrayList<>();
            int titleSize = 20;
            // 但是该用户所没有的knowledge
            Random random = new Random();
            while (questions.size()< titleSize){
                String knowledge = knowList.get(random.nextInt(knowList.size()));
                questions.addAll(taskTestService.getSomeTitleByKnowledge(knowledge));
            }
            return R.ok().data("data",questions);
        }
        return R.error().message("用户信息不足");
    }

    /**
     * 根据错题的知识点
     * 推荐一定数量的题目
     * 根据知识点的词频进行题目的筛选
     * 根据知识点随机抽取
     * @return 1
     */
    @GetMapping("recommendTitle")
    public R getRecommendTitle() {
        EduUser user = userUtil.getUser();
        List<Map<String, String>> list = taskTestService.getWrongTaskTestTitle(user.getId().toString());
        String s = list.stream().map(p->p.get("knowl")).collect(Collectors.joining(","));
        String[] arr1 = s.split(",");
        Random random = new Random();
        List<Question> list1 = new ArrayList<>();
        int titleSize = 20;
        while (list1.size()< titleSize){
            String knowledge = arr1[random.nextInt(arr1.length)];
            list1.addAll(taskTestService.getSomeTitleByKnowledge(knowledge));
        }
        list1.forEach(Question::splitChoices);
        return R.ok().data("data", list1);
    }

    /**
     * 提交推荐的题目所做的答案
     * 只提交已经完成的部分
     * @return 1
     */
    @PostMapping("submitRecommendTitle")
    public R submitRecommendTitle(@RequestBody List<Question> list){
        EduUser user = userUtil.getUser();
        List<Question> list2 = list.stream().filter(a -> a.getStuAnswer() != null).collect(Collectors.toList());
        List<TaskTest> taskTestList = new ArrayList<>();
        for (Question q:
             list2) {
            q.filterCorrectAnswer();
            TaskTest taskTest = new TaskTest();
            taskTest.setTitleId(q.getId());
            taskTest.setQuestionAnswer(q.getAnswer());
            taskTest.setAnswer(q.getStuAnswer());
            taskTest.setUserId(user.getId().toString());
            taskTest.setUserName(user.getUsername());
            String currentDateTime = DateUtil.getCurrentDateTime();
            taskTest.setCreateTime(currentDateTime);
            taskTestList.add(taskTest);
        }
        taskTestService.saveBatch(taskTestList);
        return R.ok();
    }
}
