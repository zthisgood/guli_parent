package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.config.UserUtil;
import com.atguigu.eduservice.entity.*;
import com.atguigu.eduservice.entity.query.TaskQuery;
import com.atguigu.eduservice.entity.so.TaskCreateParam;
import com.atguigu.eduservice.entity.vo.QuestionVO;
import com.atguigu.eduservice.entity.vo.TaskTestVO;
import com.atguigu.eduservice.entity.vo.UserVO;
import com.atguigu.eduservice.service.*;
import com.atguigu.eduservice.util.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 * @version 1.0
 * @date 2021-02-26 14:30
 */
@RestController
@RequestMapping("/eduservice/examination/")
public class ExamController{

    @Autowired
    private TaskInfoService taskInfoService;

    @Autowired
    private TaskUserLinkService taskUserLinkService;

    @Autowired
    private TaskTestService taskTestService;

    @Autowired
    private TaskUserService taskUserService;

    @Autowired
    private ExamService examService;

    @Autowired
    private UserUtil userUtil;

    /**
     * 获取作业相关的习题
     * @param taskId
     * @return
     */
    @PostMapping("paperTest")
    public R getPaperTest(String taskId){
        EduUser user = userUtil.getUser();
        List<QuestionVO> list = taskUserLinkService.getPaperQuestionList(Integer.parseInt(taskId));
        for (QuestionVO q:
             list) {
            q.getQuestion().splitChoices();
            q.getQuestion().reWriteAnswer();
        }
        return R.ok().data("data", list);
    }

    /**
     * 1.获取学生试卷
     * 返回 taskTestLevel
     * 2.如果没有获取到试卷
     * 那么根据作业本重建一个 taskTest
     * 并返回 taskTestLevel
     * @return R
     */
    @PostMapping("getTaskTitleById")
    public R addPaperByStudent(@RequestBody Map<String, String> map){
        String teacher = "teacher";
        String taskId = map.get("taskId");
        String userId = "";
        EduUser user = userUtil.getUser();
        if (teacher.equals(user.getIdentity())){
            userId = map.get("userId");
        }else {
            userId = user.getId().toString();
        }
        List<TaskTestVO> taskTestList = taskTestService.getPaperFromTaskBook(Integer.parseInt(taskId), userId);
        //没有查询到新建的paper_test
        if(taskTestList.size() == 0){
            TaskUser taskUser = new TaskUser();
            taskUser.setUserId(userId);
            taskUser.setTaskId(Integer.parseInt(taskId));
            taskUser.setFraction(0);
            taskUser.setStatus(0);
            taskUserService.save(taskUser);
            taskTestService.generateTaskTest(Integer.parseInt(taskId), user.getId());
            taskTestList = taskTestService.getPaperFromTaskBook(Integer.parseInt(taskId), user.getId().toString());
        }
        //分割答案
        for (TaskTestVO taskVo:taskTestList) {
            taskVo.splitChoices();
            taskVo.reWriteAnswer();
        }
        return R.ok().data("data", taskTestList);
    }

    /**
     * 查询作业info list
     * @param query
     * @return R
     */
    @RequestMapping("queryTaskPage")
    public R queryTitlePage(TaskQuery query) {
        List<TaskInfo> listPageResult = examService.queryTaskList(query);
        return R.ok().data("data", listPageResult);
    }


    @RequestMapping("insertByTitle")
    public R insertByTitle(TitleInfo info) {
        UserVO user = new UserVO();
        examService.insertTitle(info, user);
        return R.ok();
    }

    /**
     *
     * @param info
     * @return r
     */
    @RequestMapping("updateTitle")
    public R updateTitle(TitleInfo info){
        examService.updateTitle(info);
        return R.ok();
    }

    /**
     * 1.保存学生答案
     * 2.保存生成task_user成绩单
     * @return r
     */
    @PostMapping("savePaper")
    public R saveTaskPaper(@RequestBody List<TaskTest> list){
        list.forEach(TaskTest::filterCorrectAnswer);
        taskTestService.updateBatchById(list);
        TaskUser taskUser = new TaskUser();
        EduUser user = userUtil.getUser();
        taskUser.setUserId(user.getId().toString());
        taskUser.setTaskId(list.get(0).getTaskId());
        taskUser.setStatus(1);
        QueryWrapper<TaskUser> wrapper = new QueryWrapper<>();
        wrapper.eq("task_id", list.get(0).getTaskId());
        wrapper.eq("user_id", user.getId().toString());
        // 更新失败
        if(!taskUserService.update(taskUser,wrapper)){
            // 保存
            taskUserService.save(taskUser);
        }
        return R.ok();
    }


    /**
     * 添加习题到作业里
     * @return 1
     */
    @PostMapping("addTitleForTask")
    public R addTitleToTask(@RequestBody TaskUserLink taskUserLink){
        taskUserLink.generateUpdateTime();
        taskUserLink.setCreateTime(taskUserLink.getUpdateTime());
        QueryWrapper<TaskUserLink> wrapper = new QueryWrapper<>();
        wrapper.eq("task_id", taskUserLink.getTaskId());
        wrapper.eq("question_id", taskUserLink.getQuestionId());
        if(taskUserLinkService.getObj(wrapper)==null){
            taskUserLinkService.save(taskUserLink);
            return R.ok().message("添加成功");
        }else {
            return R.ok().message("该题目已经存在了");
        }
    }

    /**
     * 自动创建试卷
     * 并分发到各个用户
     * @param taskParam
     * @return R
     */
    @PostMapping("createATask")
    public R createTaskAutomatically(@RequestBody TaskCreateParam taskParam){
        EduUser user = userUtil.getUser();
        if(user == null){
            return R.error();
        }
        String currentDateTime = DateUtil.getCurrentDateTime();
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setCreateTime(currentDateTime);
        taskInfo.setUpdateTime(currentDateTime);
        taskInfo.setTaskName(taskParam.getTaskName());
        taskInfo.setTaskDate(taskParam.getTaskDate());
        taskInfo.setExamDate(taskParam.getExamTime());
        Integer quesNum = Integer.parseInt(taskParam.getFillNum())+Integer.parseInt(taskParam.getSelectNum());
        taskInfo.setQuestionNum(quesNum);
        Integer score = Integer.parseInt(taskParam.getFillScore())*Integer.parseInt(taskParam.getFillNum()) +
                Integer.parseInt(taskParam.getSelectNum())*Integer.parseInt(taskParam.getSelectScore());
        taskInfo.setTaskScore(score);
        taskInfo.setTeachId(user.getId().toString());
        taskInfo.setTeachName(user.getUsername());
        examService.createATask(taskInfo, taskParam);
        return R.ok();
    }

    @RequestMapping("updateTitleByList")
    public R updateTitleByList(String titleString){
        examService.updateTitle(titleString);
        return R.ok();
    }

    /**
     * 删除试卷
     * @param taskId
     * @return
     */
    @GetMapping("deleteTaskById")
    public R deleteTaskById(String taskId){
        QueryWrapper<TaskInfo> wrapper1 = new QueryWrapper<>();
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskId(Integer.parseInt(taskId));
        wrapper1.setEntity(taskInfo);
        if(taskInfoService.remove(wrapper1)){
            QueryWrapper<TaskUserLink> wrapper = new QueryWrapper<>();
            TaskUserLink taskUserLink = new TaskUserLink();
            taskUserLink.setTaskId(Integer.parseInt(taskId));
            taskUserLinkService.remove(wrapper.setEntity(taskUserLink));
            return R.ok();
        }
        return R.error().data("msg","parameter error");
    }

    /**
     * 更新task试卷信息
     * @param
     * @return
     */
    @PostMapping("updateTaskInfoById")
    public R updateTaskInfo(@RequestBody TaskInfo taskInfo){
        if(taskInfoService.updateById(taskInfo)){
            return R.ok();
        }
        return R.error();
    }
}
