package com.atguigu.eduservice.entity.query;

import lombok.Data;

@Data
public class TaskByUserIdQuery extends BaseQuery{
    private String userId;
}
