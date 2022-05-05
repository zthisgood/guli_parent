package com.atguigu.eduservice.entity.query;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YS
 * @version 1.0
 * @date 2021-03-30 13:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskUserQuery extends BaseQuery {
    private String paperName;
    private String userName;
    private String className;
    private Integer paperId;
    private Integer classId;

}
