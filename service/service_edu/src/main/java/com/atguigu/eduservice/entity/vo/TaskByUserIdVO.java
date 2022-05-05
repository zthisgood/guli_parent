package com.atguigu.eduservice.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskByUserIdVO {
    private Integer id;
    private String userName;
    private String paperName;
    private Integer fraction;
    private Integer paperScore;
    private Integer paperId;
}
