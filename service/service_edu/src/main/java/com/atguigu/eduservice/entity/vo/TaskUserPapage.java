package com.atguigu.eduservice.entity.vo;

import lombok.Data;

/**
 * @author YS
 * @version 1.0
 * @date 2021-03-30 13:17
 */
@Data
public class TaskUserPapage {
    private Integer paperId;
    private String paperName;
    private Integer fraction;
    private Integer difficulty;
    private String userName;
    private String className;
    private Integer paperScore;
    private Integer classId;
}
