package com.atguigu.eduservice.entity.vo;

import lombok.Data;


/**
 * @author YS
 * @version 1.0
 * @date 2021-03-15 18:50
 */
@Data
public class TaskPageVO {
    private Integer taskId;
    /**
     *
     */
    private String taskName;
    /**
     *
     */
    private Integer difficulty;
    /**
     *
     */
    private String subType;
    /**
     *
     */
    private String taskDate;
    /**
     *
     */
    private String examDate;
    /**
     *
     */
    private Integer taskScore;
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

    private Integer status;

    private Integer fraction;
}
