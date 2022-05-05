package com.atguigu.eduservice.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @File: ZjTaskUser
 * @Author: ys
 * @Date: 2021-03-30 09:58:01
 * @Description:
 */
@Data
public class TaskUser implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 试卷id
     */
    private Integer taskId;
    /**
     * 考试人
     */
    private String userId;
    /**
     * 0未考试 1考试完成
     */
    private Integer status;
    /**
     * 分数
     */
    private Integer fraction;

}
