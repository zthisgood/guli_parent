package com.atguigu.eduservice.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @File: TaskInfo
 * @Author: ys
 * @Date: 2021-03-12 13:30:09
 * @Description:
 */
@Data
public class TaskInfo implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Integer taskId;
    /**
     *
     */
    private String taskName;
    /**
     * 难度
     */
    private Double difficulty;
    /**
     * 题型
     */
    private String subType;
    /**
     * 考试日期
     */
    private String taskDate;
    /**
     * 考试时间
     */
    private String examDate;
    /**
     * 考试题目
     */
    private Integer questionNum;
    /**
     *
     */
    private String teachName;
    /**
     *
     */
    private String teachId;
    /**
     *
     */
    private String createTime;
    /**
     *
     */
    private String updateTime;
    /**
     * 总分
     */
    private Integer taskScore;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    public String getTeachId() {
        return teachId;
    }

    public void setTeachId(String teachId) {
        this.teachId = teachId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTaskScore() {
        return taskScore;
    }

    public void setTaskScore(Integer taskScore) {
        this.taskScore = taskScore;
    }
}
