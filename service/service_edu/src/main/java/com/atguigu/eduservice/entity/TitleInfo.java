package com.atguigu.eduservice.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @File: TitleInfo
 * @Date: 2021-02-26 13:46:32
 * @Description:
 */
@Data
public class TitleInfo implements Serializable {
    /**
     * 题目id
     */
    private Integer titleId;
    /**
     * 题目
     */
    private String titleName;

    private Integer classId;
    /**
     * 难度系数
     */
    private Double titleType;
    /**
     * 分数
     */
    private Integer titleFraction;
    /**
     * 答案
     */
    private String titleAnswer;
    /**
     * 0单选题（a,b,c,d,e,f）  1 填空（0对 1错） 2主观
     */
    private Integer titleStatus;
    /**
     * 选项A
     */
    private String choice1;
    /**
     * 选项B
     */
    private String choice2;
    /**
     * 选项C
     */
    private String choice3;
    /**
     * 选项D
     */
    private String choice4;

    /**
     * 出题人id
     */
    private String userId;
    /**
     * 出题人姓名
     */
    private String userName;
    /**
     *
     */
    private String createTime;
    /**
     *
     */
    private String updateTime;

    private Integer subjectId;

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Double getTitleType() {
        return titleType;
    }

    public void setTitleType(Double titleType) {
        this.titleType = titleType;
    }

    public Integer getTitleFraction() {
        return titleFraction;
    }

    public void setTitleFraction(Integer titleFraction) {
        this.titleFraction = titleFraction;
    }

    public String getTitleAnswer() {
        return titleAnswer;
    }

    public void setTitleAnswer(String titleAnswer) {
        this.titleAnswer = titleAnswer;
    }

    public Integer getTitleStatus() {
        return titleStatus;
    }

    public void setTitleStatus(Integer titleStatus) {
        this.titleStatus = titleStatus;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
