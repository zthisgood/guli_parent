package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author YS
 * @version 1.0
 * @date 2021-02-06 10:19
 */
@Data
public class UserVO {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 密码MD5
     */
    private String password;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 是否删除 0 否  1是
     */
    private Integer isDelete;
    /**
     * 开始时间
     */
    private String createTime;
    /**
     * 结束时间
     */
    private String updateTime;
    private Integer classId;

    private String token;
}
