package com.atguigu.eduservice.entity.query;

import lombok.Data;

/**
 * @author YS
 * @version 1.0
 * @date 2021-03-15 18:50
 */
@Data
public class TaskQuery extends BaseQuery {

    private Integer classId;
    private String userId;
}
