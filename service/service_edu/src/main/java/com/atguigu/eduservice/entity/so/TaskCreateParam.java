package com.atguigu.eduservice.entity.so;

import java.io.Serializable;

/**
 * @author ivern
 */
public class TaskCreateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String taskName;

    private String difficulty;

    private String taskDate;

    private String examTime;

    private String selectNum;

    private String selectScore;

    private String fillNum;

    private String fillScore;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(String selectNum) {
        this.selectNum = selectNum;
    }

    public String getSelectScore() {
        return selectScore;
    }

    public void setSelectScore(String selectScore) {
        this.selectScore = selectScore;
    }

    public String getFillNum() {
        return fillNum;
    }

    public void setFillNum(String fillNum) {
        this.fillNum = fillNum;
    }

    public String getFillScore() {
        return fillScore;
    }

    public void setFillScore(String fillScore) {
        this.fillScore = fillScore;
    }
}
